package org.lee.statement.support;

import org.lee.entry.complex.TargetEntry;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.scalar.Field;
import org.lee.node.Node;
import org.lee.type.TypeTag;

import java.util.List;

public interface Projectable extends Node {
    List<TargetEntry> project();
    RangeTableEntry toRelation();

    boolean isScalar();

    default int width(){
        return project().size();
    }

    void withProjectTypeLimitation(List<TypeTag> limitation);
    List<TypeTag> getProjectTypeLimitation();
}
