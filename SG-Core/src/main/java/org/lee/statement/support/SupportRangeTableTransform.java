package org.lee.statement.support;

import org.lee.common.config.Conf;
import org.lee.entry.relation.Partition;
import org.lee.entry.relation.Pivoted;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.relation.Relation;
import org.lee.base.NodeTag;
import org.lee.common.util.FuzzUtil;

import java.util.List;

public interface SupportRangeTableTransform extends SupportRuntimeConfiguration {

    default RangeTableEntry randomlyConvertToPartition(RangeTableEntry entry){
        if(!(entry instanceof Relation)){
            return entry;
        }
        final Relation relation = (Relation) entry;
        final List<Partition> partitions = relation.getPartitions();
        if (relation.getPartitions().isEmpty() ||
                !probability(Conf.CONVERT_TO_PARTITION_PROB)){
            return relation;
        }else {
            return FuzzUtil.randomlyChooseFrom(partitions);
        }
    }

    default RangeTableEntry randomlyConvertToPivoted(RangeTableEntry entry){
        // todo implements
        if(entry.getNodeTag() != NodeTag.pivoted && probability(Conf.CONVERT_TO_PIVOTED_PROB)){
            Pivoted pivoted = new Pivoted(entry);
            pivoted.fuzz();
            return pivoted;
        }
        return entry;
    }
}
