package org.lee.entry.scalar;

import org.lee.base.NodeTag;
import org.lee.common.Assertion;
import org.lee.entry.NormalizedEntryWrapper;
import org.lee.statement.support.Projectable;
import org.lee.type.TypeTag;

public class ScalarSubquery implements NormalizedEntryWrapper<Projectable>, Scalar {
    private final Projectable scalarStatement;

    public ScalarSubquery(Projectable scalarStatement){
        this.scalarStatement = scalarStatement;
    }

    @Override
    public Projectable getWrapped() {
        return scalarStatement;
    }

    @Override
    public TypeTag getType() {
        Assertion.requiredEquals(scalarStatement.project().size(), 1);
        return scalarStatement.project().get(0).getType();
    }

    @Override
    public String getString() {
        return LP + scalarStatement.getString() + RP;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.scalar;
    }
}
