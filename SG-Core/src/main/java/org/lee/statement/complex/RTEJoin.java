package org.lee.statement.complex;

import org.lee.statement.SQLStatement;
import org.lee.statement.entry.RangeTableReference;
import org.lee.statement.entry.scalar.Field;
import org.lee.statement.node.NodeTag;
import org.lee.statement.entry.relation.RangeTableEntry;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.statement.clause.JoinClause;

import java.util.Iterator;
import java.util.List;

public class RTEJoin extends JoinClause implements RangeTableEntry {
    private RangeTableReference left;
    private RangeTableReference right;
    private final Filter condition = new Filter();
    private Pattern pattern;

    public RTEJoin(SQLStatement statement, RangeTableReference left, RangeTableReference right){
        super(statement, 2);
        this.left = left;
        this.right = right;
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
    public List<RangeTableReference> getChildNodes() {
        return null;
    }

    @Override
    public Iterator<RangeTableReference> walk() {
        return null;
    }

    @Override
    public List<Field> getFields() {
        return null;
    }

    @Override
    public String getName() {
        return null;
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
    public void fuzz() {

    }
}
