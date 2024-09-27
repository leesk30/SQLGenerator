package org.lee.entry.scalar;

import org.lee.entry.NormalizedEntryWrapper;
import org.lee.statement.SQLStatement;
import org.lee.base.NodeTag;
import org.lee.type.TypeTag;

public class ScalarSubquery implements NormalizedEntryWrapper<SQLStatement>, Scalar {
    private final SQLStatement scalarStatement;

    public ScalarSubquery(SQLStatement scalarStatement){
        this.scalarStatement = scalarStatement;
    }

    @Override
    public SQLStatement getWrapped() {
        return scalarStatement;
    }

    @Override
    public TypeTag getType() {
        return null;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }
}
