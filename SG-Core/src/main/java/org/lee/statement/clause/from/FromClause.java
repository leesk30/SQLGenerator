package org.lee.statement.clause.from;

import org.lee.common.DevTempConf;
import org.lee.common.MetaEntry;
import org.lee.entry.relation.*;
import org.lee.statement.SQLStatement;
import org.lee.statement.ValuesStatement;
import org.lee.statement.clause.Clause;
import org.lee.statement.support.Projectable;
import org.lee.entry.complex.RTEJoin;
import org.lee.entry.RangeTableReference;
import org.lee.node.NodeTag;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.support.SupportCommonTableExpression;
import org.lee.util.FuzzUtil;

import java.util.*;

public abstract class FromClause extends Clause<RangeTableReference> {
    protected final List<RangeTableEntry> rawEntryList = new Vector<>();
    protected final List<RangeTableEntry> allOfCandidates = new Vector<>();
//    protected List<List<RangeTableReference>> candidatesList;
    protected FromClause(SQLStatement statement) {
        super(statement);
    }

    protected FromClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    public List<RangeTableEntry> getRawEntryList() {
        return rawEntryList;
    }

    @Override
    public String getString() {
        return "FROM " + nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.fromClause;
    }

    protected void merge(RangeTableReference[][] candidatesList){
        for(RangeTableReference[] joinCandidate: candidatesList){
            final List<RangeTableReference> template = Arrays.asList(joinCandidate);
            final Optional<RangeTableReference> optionalResult =
                    template.stream().reduce((left, right) -> {
                        RTEJoin join = new RTEJoin(statement, left, right);
                        join.fuzz();
                        Collections.shuffle(template);
                        return new RangeTableReference(join);
            });
            children.add(optionalResult.orElseThrow(() -> new RuntimeException("NPE happen after reduce")));
        }
    }

    protected RangeTableReference randomlyGetRangeReference(){
        SelectStatement currentStatement = (SelectStatement) this.statement;
        RangeTableEntry entry;

        if(currentStatement.subqueryDepth < DevTempConf.MAX_SUBQUERY_RECURSION_DEPTH
                && FuzzUtil.probability(DevTempConf.USING_SUBQUERY_IN_FROM_PROBABILITY)){
            Projectable projectableStatement;
            if(FuzzUtil.probability(DevTempConf.USING_VALUES_IN_FROM_PROBABILITY)){
                ValuesStatement valuesStatement = ValuesStatement.newStatement();
                valuesStatement.fuzz();
                projectableStatement = valuesStatement;
            }else {
                SelectStatement selectStatement = SelectStatement.randomlyGetStatement(this.statement);
                selectStatement.fuzz();
                projectableStatement = selectStatement;
                rawEntryList.addAll(selectStatement.getRawRTEList());
            }
            entry = projectableStatement.toRelation();
        }else {
            entry = randomlyGetRangeTable();
            entry = randomlyConvertToPartition(entry);
            rawEntryList.add(entry);
        }
//        return randomlyConvertToPivoted(entry);
        return new RangeTableReference(entry);
    }

    protected RangeTableEntry randomlyGetRangeTable(){
        cacheCandidateAll();
        return FuzzUtil.randomlyChooseFrom(allOfCandidates);
    }

    protected void cacheCandidateAll(){
        if(!allOfCandidates.isEmpty()){
            return;
        }
        // related with parent cte
        if(FuzzUtil.probability(50)){
            allOfCandidates.addAll(statement.recursiveGetCTEs());
        }else {
            // repeat self cte
            if(statement instanceof SupportCommonTableExpression){
                allOfCandidates.addAll(((SupportCommonTableExpression) this.statement).getCTEs());
            }
        }
        allOfCandidates.addAll(MetaEntry.relationMap.values());
    }

    protected RangeTableEntry randomlyConvertToPartition(RangeTableEntry entry){
        if(!(entry instanceof Relation)){
            return entry;
        }
        final Relation relation = (Relation) entry;
        final List<Partition> partitions = relation.getPartitions();
        if (relation.getPartitions().isEmpty() ||
                !FuzzUtil.probability(DevTempConf.CONVERT_TO_PARTITION_PROB)){
            return relation;
        }else {
            return FuzzUtil.randomlyChooseFrom(partitions);
        }

    }

    protected RangeTableEntry randomlyConvertToPivoted(RangeTableEntry entry){
        if(entry.getNodeTag() != NodeTag.pivoted && FuzzUtil.probability(DevTempConf.CONVERT_TO_PIVOTED_PROB)){
            Pivoted pivoted = new Pivoted(entry);
            pivoted.fuzz();
            return pivoted;
        }
        return entry;
    }
}
