package org.lee.entry.relation;

import org.lee.statement.ValuesStatement;


public final class ValuesRelation extends SubqueryRelation{
    private final ValuesStatement valuesProjectableStatement;
    public ValuesRelation(ValuesStatement projectable) {
        super(projectable);
        this.valuesProjectableStatement = projectable;
    }

    @Override
    public ValuesStatement getWrapped() {
        return valuesProjectableStatement;
    }
}
