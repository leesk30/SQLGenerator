package org.lee.statement.clause;

import org.lee.statement.node.NodeTag;
import org.lee.statement.node.Node;
import org.lee.statement.expression.Expression;
import org.lee.statement.SQLStatement;

import java.util.ArrayList;
import java.util.List;

public class SelectClause implements Clause{
    private final List<Expression> projection = new ArrayList<>();
    private final List<Expression> orderByTargetList = new ArrayList<>();
    private final List<Expression> outputTargetList = new ArrayList<>();


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
    public boolean isEmptyClause() {
        return projection.isEmpty();
    }

    @Override
    public void generate(SQLStatement scopeContext) {

    }
}
