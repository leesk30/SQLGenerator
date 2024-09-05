package org.lee.statement.clause;

import org.lee.common.DevTempConf;
import org.lee.statement.SQLStatement;
import org.lee.statement.ValuesStatement;
import org.lee.statement.common.Projectable;
import org.lee.statement.complex.RTEJoin;
import org.lee.statement.entry.relation.Partition;
import org.lee.statement.entry.relation.Pivoted;
import org.lee.statement.entry.relation.RangeTableEntry;
import org.lee.statement.entry.RangeTableReference;
import org.lee.statement.entry.relation.Relation;
import org.lee.statement.node.NodeTag;
import org.lee.statement.select.SelectStatement;
import org.lee.util.FuzzUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class FromClause extends Clause<RangeTableReference>{
    protected final List<RangeTableEntry> rawEntryList = new ArrayList<>();
    protected List<List<RangeTableReference>> candidatesList;
    protected FromClause(SQLStatement statement) {
        super(statement);
    }

    protected FromClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    public List<RangeTableEntry> getRawEntryList() {
        return rawEntryList;
    }

    protected void merge(){
        for (List<RangeTableReference> joinCandidate: candidatesList){
            List<RangeTableReference> template = new ArrayList<>(joinCandidate.size());
            Collections.copy(template, joinCandidate);
            while (template.size() > 1){
                Collections.shuffle(template);
                RangeTableReference left = template.remove(0);
                RangeTableReference right = template.remove(0);
                RTEJoin join = new RTEJoin(statement, left, right);
                RangeTableReference refJoin = new RangeTableReference(join);
                template.add(refJoin);
            }
            children.add(template.get(0));
        }
    }

    protected RangeTableEntry randomlyGetRangeTable(){
        SelectStatement currentStatement = (SelectStatement) this.statement;
        RangeTableEntry entry;

        if(currentStatement.subqueryDepth < DevTempConf.MAX_SUBQUERY_RECURSION_DEPTH){
            Projectable projectableStatement;
            if(FuzzUtil.probability(DevTempConf.USING_VALUES_IN_FROM_PROBABILITY)){
                ValuesStatement valuesStatement = ValuesStatement.newStatement();
                valuesStatement.fuzz();
                projectableStatement = valuesStatement;
            }else {
                SelectStatement selectStatement = SelectStatement.randomlyGetStatement();
                selectStatement.fuzz();
                projectableStatement = selectStatement;
                rawEntryList.addAll(selectStatement.getRawRTEList());
            }
            entry = projectableStatement.toRelation();
        }else {
            entry = randomlyConvertToPartition(FuzzUtil.getRandomRelationFromMetaEntry());
            rawEntryList.add(entry);
        }
//        return randomlyConvertToPivoted(entry);
        return entry;
    }

    protected RangeTableEntry randomlyConvertToPartition(Relation relation){
        List<Partition> partitions = relation.getPartitions();
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
