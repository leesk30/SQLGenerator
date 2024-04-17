package org.lee.node.entry.normalized;

import org.lee.node.NodeTag;
import org.lee.node.base.Node;
import org.lee.node.entry.relation.RangeTableEntry;
import org.lee.node.entry.scalar.Scalar;

import java.util.List;

public class NormalizedRelation<R extends Node> implements NormalizedEntryNode<R>, RangeTableEntry {

    private final R original;

    public NormalizedRelation(R node){
        // todo: R can be join_clause/subquery statement
        this.original = node;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public List<Scalar> getFields() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public R getOriginalNode() {
        return original;
    }
}
