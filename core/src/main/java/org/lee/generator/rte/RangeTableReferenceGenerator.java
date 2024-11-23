package org.lee.generator.rte;

import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.enumeration.Conf;
import org.lee.common.enumeration.NodeTag;
import org.lee.common.enumeration.Rule;
import org.lee.common.generator.Generator;
import org.lee.common.utils.RandomUtils;
import org.lee.context.SQLGeneratorContext;
import org.lee.resource.MetaEntry;
import org.lee.sql.entry.RangeTableReference;
import org.lee.sql.entry.relation.Partition;
import org.lee.sql.entry.relation.Pivoted;
import org.lee.sql.entry.relation.RangeTableEntry;
import org.lee.sql.entry.relation.Relation;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.statement.WithCommonTableExpression;
import org.lee.sql.support.SupportRuntimeConfiguration;

import java.util.ArrayList;
import java.util.List;

public class RangeTableReferenceGenerator implements Generator<RangeTableReference>, SupportRuntimeConfiguration {

    private final SQLStatement statement;
    private final SQLGeneratorContext context;
    private final RuntimeConfiguration config;

    protected final List<RangeTableEntry> rawEntryList;
    protected final List<RangeTableEntry> currentCTECandidates = new ArrayList<>();
    protected List<RangeTableEntry> allOfCandidates = null;

    public RangeTableReferenceGenerator(SQLStatement statement, List<RangeTableEntry> rawEntryList){
        this.statement = statement;
        this.context = statement.retrieveContext();

        // this.config = context.currentFrame().current().getConfig();
        this.config = statement.getConfig();
        this.rawEntryList = rawEntryList;
    }

    @Override
    public RangeTableReference generate() {
        RangeTableEntry entry;

        if(this.statement.enableSubquery() && probability(Conf.USING_SUBQUERY_IN_FROM_PROBABILITY)){
            entry = context.recursive().generateProjectable().toRelation();
        }else {
            entry = randomlyGetRangeTable();
            entry = randomlyConvertToPartition(entry);
            rawEntryList.add(entry);
        }
        entry = randomlyTransformToPivoted(entry);
        RangeTableReference reference = new RangeTableReference(entry);
        reference.setAlias();
        return reference;
    }

    private RangeTableEntry randomlyConvertToPartition(RangeTableEntry entry){
        if(!(entry instanceof Relation)){
            // not a relation
            return entry;
        }
        final Relation relation = (Relation) entry;
        final List<Partition> partitions = relation.getPartitions();
        if(relation.getPartitions().isEmpty()){
            // no partition found
            return relation;
        }
        if (!probability(Conf.CONVERT_TO_PARTITION_PROB)){
            // cannot pass to probability
            return relation;
        }
        return RandomUtils.randomlyChooseFrom(partitions);
    }

    private RangeTableEntry randomlyTransformToPivoted(RangeTableEntry entry){
        if(entry.getFields().size() < 2){
            // cannot convert
            return entry;
        }

        NodeTag tag = entry.getNodeTag();
        if(tag == NodeTag.pivoted){
            // cannot nested pivot & unpivot
            return entry;
        }

        if(tag == NodeTag.rteJoin){
            // cannot transform range table join
            return entry;
        }

        if(!probability(Conf.CONVERT_TO_PIVOTED_PROB)){
            // cannot pass to probability
            return entry;
        }

        RangeTableEntry transformedEntry = Pivoted.fuzzy(context, entry);

        if(this.config.confirm(Rule.ENABLE_SINGLE_RELATION_TRANSFORM_PIVOT)){
            return transformedEntry;
        }

        return transformedEntry.toShelledSubqueryEntry(context);
    }


    protected RangeTableEntry randomlyGetRangeTable(){
        if(allOfCandidates == null){
            initializeCandidate();
        }

        if(!currentCTECandidates.isEmpty() && probability(90)){
            if(probability(99)){
                return currentCTECandidates.remove(0);
            }else {
                return RandomUtils.randomlyChooseFrom(currentCTECandidates);
            }
        }
        return RandomUtils.randomlyChooseFrom(allOfCandidates);
    }

    protected void initializeCandidate(){
        MetaEntry entry = context.getMetaEntry();
        allOfCandidates = new ArrayList<>(entry.getRelationMap().values());
        if(statement instanceof WithCommonTableExpression){
            currentCTECandidates.addAll(((WithCommonTableExpression) this.statement).getCTEs());
        }
        // related cte is prob
        if(RandomUtils.probability(50)){
            allOfCandidates.addAll(statement.recursiveGetCTEs());
        }
    }

    @Override
    public RuntimeConfiguration getConfig() {
        return config;
    }
}
