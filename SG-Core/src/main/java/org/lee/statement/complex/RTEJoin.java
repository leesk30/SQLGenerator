package org.lee.statement.complex;

import org.lee.statement.entry.relation.RangeTableReference;
import org.lee.statement.node.NodeTag;
import org.lee.statement.node.Node;
import org.lee.statement.entry.relation.RangeTableEntry;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.statement.clause.JoinClause;

import java.util.List;

public class RTEJoin implements RangeTableEntry, JoinClause {
    private RangeTableReference left;
    private RangeTableReference right;
    private final Filter condition = new Filter();
    private JoinPattern pattern;

    public RTEJoin(RangeTableReference left, RangeTableReference right){
        this.left = left;
        this.right = right;
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
    public List<? extends Node> getChildNodes() {
        return null;
    }

    @Override
    public List<? extends Scalar> getFields() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
