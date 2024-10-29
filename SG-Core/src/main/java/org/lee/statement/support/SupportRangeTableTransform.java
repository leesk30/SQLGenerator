package org.lee.statement.support;

import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.entry.relation.*;

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

    default RangeTableEntry randomlyConvertToPivoted(RangeTableEntry entry){
        if(entry.getFields().size() < 2){
            // cannot convert
            return entry;
        }
        if(entry.getNodeTag() == NodeTag.pivoted){
            // cannot nested pivot & unpivot
            return entry;
        }

        if(!probability(Conf.CONVERT_TO_PIVOTED_PROB)){
            // cannot pass to probability
            return entry;
        }

        return Pivoted.fuzzy(entry);
    }
}
