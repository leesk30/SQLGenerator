package org.lee.statement.clause.modify;

import org.lee.statement.SQLStatement;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.node.NodeTag;
import org.lee.statement.clause.Clause;

import java.util.Iterator;

public abstract class ModifyTableClause extends Clause<RangeTableEntry> {
    public ModifyTableClause(SQLStatement statement) {
        super(statement);
    }

    protected ModifyTableClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.modifyTableClause;
    }
}
