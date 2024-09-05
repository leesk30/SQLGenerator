package org.lee.statement.entry.relation;

import org.lee.statement.common.Projectable;
import org.lee.statement.entry.NormalizedEntryNode;
import org.lee.statement.entry.scalar.Field;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.statement.node.NodeTag;

import java.util.List;

public class SubqueryRelation implements RangeTableEntry, NormalizedEntryNode<Projectable> {
    private final Projectable projectable;
    public SubqueryRelation(Projectable projectable){
        this.projectable = projectable;
    }
    @Override
    public List<Field> getFields() {
        return projectable.project();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Projectable getRawNode() {
        return null;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }
}
