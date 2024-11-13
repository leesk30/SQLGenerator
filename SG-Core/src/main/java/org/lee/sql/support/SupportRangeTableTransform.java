package org.lee.sql.support;

import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.entry.relation.Partition;
import org.lee.entry.relation.Pivoted;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.relation.Relation;

import java.util.List;

public interface SupportRangeTableTransform extends SupportRuntimeConfiguration {

    default RangeTableEntry randomlyConvertToPartition(RangeTableEntry entry){
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
        return Utility.randomlyChooseFrom(partitions);
    }

    default RangeTableEntry randomlyTransformToPivoted(RangeTableEntry entry){
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

        RangeTableEntry transformedEntry = Pivoted.fuzzy(entry);

        if(this.getConfig().confirm(Rule.ENABLE_SINGLE_RELATION_TRANSFORM_PIVOT)){
            return transformedEntry;
        }

        return transformedEntry.toShelledSubqueryEntry();
    }
}
