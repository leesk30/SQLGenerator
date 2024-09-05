package org.lee.statement.entry.scalar;

import org.lee.statement.SQLStatement;
import org.lee.statement.entry.NormalizedEntryNode;
import org.lee.statement.node.NodeTag;
import org.lee.type.TypeTag;

public class ScalarSubquery implements NormalizedEntryNode<SQLStatement>, Scalar {
    private final SQLStatement scalarStatement;

    public ScalarSubquery(SQLStatement scalarStatement){
        this.scalarStatement = scalarStatement;
    }

    @Override
    public SQLStatement getRawNode() {
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
