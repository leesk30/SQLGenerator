package org.lee.node.entry.relation;

import org.lee.node.NodeTag;
import org.lee.node.base.Node;
import org.lee.node.entry.scalar.Scalar;

import java.util.List;

public class RelationalAdaptor<R extends Node> implements RangeTableEntry{

    public RelationalAdaptor(R node){
        // todo: S can be join_clause/subquery statement
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
}
