package org.lee.statement.clause.from;

import org.lee.common.MetaEntry;
import org.lee.common.config.Conf;
import org.lee.entry.relation.*;
import org.lee.common.exception.Assertion;
import org.lee.statement.SQLStatement;
import org.lee.statement.clause.Clause;
import org.lee.statement.support.Projectable;
import org.lee.entry.complex.RTEJoin;
import org.lee.entry.RangeTableReference;
import org.lee.node.NodeTag;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.support.SupportCommonTableExpression;
import org.lee.statement.support.SupportGenerateProjectable;
import org.lee.statement.support.SupportRangeTableTransform;
import org.lee.common.util.FuzzUtil;

import java.util.*;

public abstract class FromClause extends Clause<RangeTableReference>
        implements SupportGenerateProjectable, SupportRangeTableTransform {
    protected final List<RangeTableEntry> rawEntryList = new Vector<>();
    protected final List<RangeTableEntry> currentCTECandidates = new Vector<>();
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
        return FROM + SPACE + nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.fromClause;
    }

    @Override
    public Projectable generate(SQLStatement parent){
        Projectable projectableStatement = SupportGenerateProjectable.super.generate(this.statement);
        if(projectableStatement instanceof SelectStatement){
            rawEntryList.addAll(((SelectStatement) projectableStatement).getRawRTEList());
        }
        return projectableStatement;
    }

    protected void merge(RangeTableReference[][] candidatesList){
        for(RangeTableReference[] joinCandidate: candidatesList){
            final List<RangeTableReference> template = Arrays.asList(joinCandidate);
            final RangeTableReference reference = template.stream().reduce(
                    (left, right) -> {
                        RTEJoin join = new RTEJoin(this.statement, left, right);
                        join.fuzz();
                        return new RangeTableReference(join);
                    })
                    .orElseThrow(Assertion.IMPOSSIBLE);
            children.add(reference);
        }
    }

    protected RangeTableReference randomlyGetRangeReference(){
        SelectStatement currentStatement = (SelectStatement) this.statement;
        RangeTableEntry entry;

        if(currentStatement.subqueryDepth < config.getInt(Conf.MAX_SUBQUERY_RECURSION_DEPTH )
                && probability(Conf.USING_SUBQUERY_IN_FROM_PROBABILITY)){
            entry = this.generate(this.statement).toRelation();
        }else {
            entry = randomlyGetRangeTable();
            entry = randomlyConvertToPartition(entry);
            rawEntryList.add(entry);
        }
//        return randomlyConvertToPivoted(entry);
        return new RangeTableReference(entry);
    }

    protected RangeTableEntry randomlyGetRangeTable(){
        if(allOfCandidates.isEmpty()){
            initializeCandidate();
        }
        // atomic operation
        synchronized (currentCTECandidates){
            if(!currentCTECandidates.isEmpty() && probability(90)){
                if(probability(99)){
                    return currentCTECandidates.remove(0);
                }else {
                    return FuzzUtil.randomlyChooseFrom(currentCTECandidates);
                }
            }
        }
        return FuzzUtil.randomlyChooseFrom(allOfCandidates);
    }

    protected void initializeCandidate(){
        allOfCandidates.addAll(MetaEntry.relationMap.values());
        if(statement instanceof SupportCommonTableExpression){
            currentCTECandidates.addAll(((SupportCommonTableExpression) this.statement).getCTEs());
        }
        // related cte is prob
        if(FuzzUtil.probability(50)){
            allOfCandidates.addAll(statement.recursiveGetCTEs());
        }
    }
}
