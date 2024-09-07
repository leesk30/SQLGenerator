package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.node.NodeTag;

import java.util.Iterator;

public class ModifyTableClause extends Clause<RangeTableEntry>{
    protected ModifyTableClause(SQLStatement statement) {
        super(statement);
    }

    protected ModifyTableClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
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
    public Iterator<RangeTableEntry> walk() {
        return null;
    }

    @Override
    public void fuzz() {

    }
}
