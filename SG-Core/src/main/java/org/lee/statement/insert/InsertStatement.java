package org.lee.statement.insert;

import org.lee.node.Node;
import org.lee.node.NodeTag;
import org.lee.statement.SQLStatement;
import org.lee.statement.SQLType;
import org.lee.statement.clause.modify.ModifyTableClause;
import org.lee.statement.support.SupportModifyTable;

import java.util.List;
import java.util.Vector;

public abstract class InsertStatement extends SQLStatement implements SupportModifyTable {

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
        return new Vector<>(childrenMap.values());
    }

    @Override
    public ModifyTableClause getModifyTableClause() {
        return (ModifyTableClause) childrenMap.get(NodeTag.modifyTableClause);
    }
}
