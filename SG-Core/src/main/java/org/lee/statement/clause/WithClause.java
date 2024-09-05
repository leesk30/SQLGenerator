package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.statement.entry.relation.CTE;
import org.lee.statement.node.NodeTag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WithClause extends Clause<CTE> {

    private final List<CTE> cteList = new ArrayList<>();
    private boolean materialized = false;

    public WithClause(SQLStatement statement) {
        super(statement);
    }

    public WithClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    @Override
    public String getString() {
        return (materialized ? "WITH MATERIALIZED " : "WITH ") + nodeArrayToString(",\n", cteList);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.clause;
    }

    @Override
    public Iterator<CTE> walk() {
        return cteList.iterator();
    }

    @Override
    public void fuzz() {

    }
}
