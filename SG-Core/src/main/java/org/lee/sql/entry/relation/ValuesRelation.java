package org.lee.sql.entry.relation;

import org.lee.sql.statement.ValuesStatement;


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
