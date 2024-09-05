package org.lee.statement;

import org.lee.statement.clause.Clause;
import org.lee.statement.common.Projectable;
import org.lee.statement.entry.relation.RangeTableEntry;
import org.lee.statement.entry.scalar.Field;
import org.lee.statement.node.Node;
import org.lee.statement.node.NodeTag;

import java.util.Iterator;
import java.util.List;

public class ValuesStatement extends SQLStatement implements Projectable {
    protected ValuesStatement() {
        super(SQLType.values);
    }

    protected ValuesStatement(SQLType sqlType, SQLStatement parentStatement) {
        super(sqlType, parentStatement);
    }

    public static ValuesStatement newStatement(){
        return new ValuesStatement();
    }

    @Override
    public void fuzz() {

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
    public List<? extends Node> getChildNodes() {
        return null;
    }

    @Override
    public Iterator<Clause<? extends Node>> walk() {
        return null;
    }

    @Override
    public List<Field> project() {
        return null;
    }

    @Override
    public RangeTableEntry toRelation() {
        return null;
    }
}
