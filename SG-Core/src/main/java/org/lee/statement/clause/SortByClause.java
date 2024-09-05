package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.statement.entry.relation.RangeTableEntry;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.statement.node.NodeTag;

import java.util.Iterator;
import java.util.List;

public class SortByClause extends Clause<Scalar>{

    public SortByClause(SQLStatement statement) {
        super(statement);
    }

    public SortByClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
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
    public Iterator<Scalar> walk() {
        return null;
    }

    @Override
    public void fuzz() {

    }
}
