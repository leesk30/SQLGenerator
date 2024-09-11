package org.lee.statement;

import org.lee.entry.complex.TargetEntry;
import org.lee.statement.clause.Clause;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.scalar.Field;
import org.lee.node.Node;
import org.lee.node.NodeTag;
import org.lee.statement.support.Projectable;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

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
    public Stream<Clause<? extends Node>> walk() {
        return null;
    }

    @Override
    public List<TargetEntry> project() {
        return null;
    }

    @Override
    public RangeTableEntry toRelation() {
        return null;
    }

    @Override
    public boolean isScalar() {
        return false;
    }
}
