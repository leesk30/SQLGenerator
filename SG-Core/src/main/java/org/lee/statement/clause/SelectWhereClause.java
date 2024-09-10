package org.lee.statement.clause;

import org.lee.fuzzer.Generator;
import org.lee.fuzzer.qualification.RelationalQualificationGenerator;
import org.lee.node.NodeTag;
import org.lee.statement.SQLStatement;
import org.lee.statement.expression.Qualification;

public final class SelectWhereClause extends WhereClause{
    public SelectWhereClause(SQLStatement statement) {
        super(statement);
    }

    private void selectWhereFuzz(){
        Generator<Qualification> generator = new RelationalQualificationGenerator() {
            @Override
            public Qualification generate() {
                return null;
            }
        };
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
