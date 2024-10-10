package org.lee.statement.insert;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.statement.SQLType;
import org.lee.statement.basic.AbstractSQLStatement;
import org.lee.statement.clause.modify.ModifyTableClause;
import org.lee.statement.support.SupportModifyTable;

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
    public List<? extends Node> getChildNodes() {
        return new ArrayList<>(childrenMap.values());
    }

    @Override
    public ModifyTableClause getModifyTableClause() {
        return (ModifyTableClause) childrenMap.get(NodeTag.modifyTableClause);
    }
}
