package org.lee.sql.statement.insert;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.sql.clause.Clause;
import org.lee.sql.clause.modify.ModifyTableClause;
import org.lee.sql.statement.AbstractSQLStatement;
import org.lee.sql.statement.common.SQLType;
import org.lee.sql.support.SupportModifyTable;

import java.util.ArrayList;
import java.util.List;

public abstract class InsertStatement extends AbstractSQLStatement implements SupportModifyTable {

    protected InsertStatement() {
        super(SQLType.insert, null);
    }

    @Override
    public String getString() {
        return nodeArrayToString(SPACE, this.walk()) + ENDING;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.statement;
    }

    @Override
    public List<Clause<? extends Node>> getChildNodes() {
        return new ArrayList<>(childrenMap.values());
    }

    @Override
    public ModifyTableClause getModifyTableClause() {
        return (ModifyTableClause) childrenMap.get(NodeTag.modifyTableClause);
    }
}
