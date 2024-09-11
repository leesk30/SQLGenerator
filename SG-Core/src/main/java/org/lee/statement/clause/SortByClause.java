package org.lee.statement.clause;

import org.lee.entry.complex.SortEntry;
import org.lee.statement.SQLStatement;
import org.lee.entry.scalar.Scalar;
import org.lee.node.NodeTag;

import java.util.Iterator;

public abstract class SortByClause extends Clause<SortEntry>{

    public SortByClause(SQLStatement statement) {
        super(statement);
    }

    @Override
    public String getString() {
        return "ORDER BY " + nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.sortByClause;
    }

}
