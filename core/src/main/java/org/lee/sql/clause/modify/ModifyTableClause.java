package org.lee.sql.clause.modify;

import org.lee.common.enumeration.NodeTag;
import org.lee.sql.clause.Clause;
import org.lee.sql.entry.relation.RangeTableEntry;
import org.lee.sql.statement.SQLStatement;

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
