package org.lee.statement.clause.modify;

import org.lee.base.NodeTag;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.statement.clause.Clause;
import org.lee.statement.support.SQLStatement;

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
