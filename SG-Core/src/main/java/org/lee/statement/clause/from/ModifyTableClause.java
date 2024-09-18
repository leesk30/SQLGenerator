package org.lee.statement.clause.from;

import org.lee.statement.SQLStatement;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.node.NodeTag;
import org.lee.statement.clause.Clause;

import java.util.Iterator;

public class ModifyTableClause extends Clause<RangeTableEntry> {
    protected ModifyTableClause(SQLStatement statement) {
        super(statement);
    }

    protected ModifyTableClause(SQLStatement statement, int initialCapacity) {
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
    public void fuzz() {

    }
}
