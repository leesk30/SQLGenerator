package org.lee.statement.clause.from;

import org.lee.SQLGeneratorContext;
import org.lee.base.NodeTag;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.global.MetaEntry;
import org.lee.entry.RangeTableReference;
import org.lee.entry.complex.RTEJoin;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.statement.clause.Clause;
import org.lee.statement.generator.ProjectableGenerator;
import org.lee.statement.support.SQLStatement;
import org.lee.statement.support.SupportCommonTableExpression;
import org.lee.statement.support.SupportRangeTableTransform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FromClause extends Clause<RangeTableReference>
        implements SupportRangeTableTransform {
    protected final List<RangeTableEntry> rawEntryList = new ArrayList<>();
    protected final List<RangeTableEntry> currentCTECandidates = new ArrayList<>();
    protected final List<RangeTableEntry> allOfCandidates = new ArrayList<>();
    protected final boolean enableSubquery;
    protected final ProjectableGenerator generator;
//    protected List<List<RangeTableReference>> candidatesList;
    protected FromClause(SQLStatement statement) {
        super(statement);
        enableSubquery = statement.enableSubquery();
        generator = enableSubquery ? new ProjectableGenerator(statement) : null;
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
        RangeTableEntry entry;

        if(enableSubquery && probability(Conf.USING_SUBQUERY_IN_FROM_PROBABILITY)){
            entry = generator.generate().toRelation();
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
                    return Utility.randomlyChooseFrom(currentCTECandidates);
                }
            }
        }
        return Utility.randomlyChooseFrom(allOfCandidates);
    }

    protected void initializeCandidate(){
        MetaEntry entry = SQLGeneratorContext.getCurrentMetaEntry();
        allOfCandidates.addAll(entry.getRelationMap().values());
        if(statement instanceof SupportCommonTableExpression){
            currentCTECandidates.addAll(((SupportCommonTableExpression) this.statement).getCTEs());
        }
        // related cte is prob
        if(Utility.probability(50)){
            allOfCandidates.addAll(statement.recursiveGetCTEs());
        }
    }
}
