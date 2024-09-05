package org.lee.statement.complex;

import org.lee.statement.clause.Clause;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.statement.node.NodeTag;
import org.lee.statement.node.TreeNode;
import org.lee.type.TypeTag;

import java.util.Iterator;
import java.util.List;

public class Filter implements Scalar, TreeNode<Scalar> {
    @Override
    public TypeTag getType() {
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

    @Override
    public List<Scalar> getChildNodes() {
        return null;
    }

    @Override
    public Iterator<Scalar> walk() {
        return null;
    }
}
