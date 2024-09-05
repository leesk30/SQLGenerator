package org.lee.statement.clause;

import org.lee.statement.entry.scalar.TargetEntry;
import org.lee.statement.node.Node;
import org.lee.statement.node.NodeTag;
import org.lee.statement.expression.Expression;
import org.lee.statement.select.SelectStatement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectClause extends Clause<TargetEntry> {
    private final List<Expression> orderByTargetList = new ArrayList<>();
    private final List<Expression> outputTargetList = new ArrayList<>();

    public SelectClause(SelectStatement statement) {
        super(statement);
    }

    @Override
    public String getString() {
        return this.nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public Iterator<TargetEntry> walk() {
        return children.iterator();
    }

    @Override
    public void fuzz() {

    }
}
