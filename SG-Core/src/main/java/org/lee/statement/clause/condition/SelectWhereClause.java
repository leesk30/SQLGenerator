package org.lee.statement.clause.condition;

import org.lee.base.Generator;
import org.lee.base.NodeTag;
import org.lee.statement.expression.Qualification;
import org.lee.statement.select.SelectStatement;

public final class SelectWhereClause extends WhereClause {
    public SelectWhereClause(SelectStatement statement) {
        super(statement);
    }

    private void selectWhereFuzz(){
        Generator<Qualification> generator = null;
    }

    @Override
    public void fuzz() {
        if(statement.containsClause(NodeTag.fromClause)){
            joinCondInWhere();
        }
        selectWhereFuzz();
        if(!filter.isEmpty()){
            filter.fuzz();
            children.add(filter);
        }
    }
}
