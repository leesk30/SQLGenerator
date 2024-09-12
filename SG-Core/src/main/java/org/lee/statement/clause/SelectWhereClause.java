package org.lee.statement.clause;

import org.lee.fuzzer.Generator;
import org.lee.node.NodeTag;
import org.lee.statement.SQLStatement;
import org.lee.statement.expression.Qualification;

public final class SelectWhereClause extends WhereClause{
    public SelectWhereClause(SQLStatement statement) {
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
