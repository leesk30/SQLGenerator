package org.lee.statement.clause.condition;

import org.lee.base.Generator;
import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.statement.clause.from.FromClause;
import org.lee.statement.expression.Qualification;
import org.lee.statement.expression.generator.ExprGenerators;
import org.lee.statement.expression.generator.WhereQualificationGenerator;
import org.lee.statement.select.SelectStatement;

import java.util.ArrayList;
import java.util.List;

public final class SelectWhereClause extends WhereClause {
    public SelectWhereClause(SelectStatement statement) {
        super(statement);
    }

    private void selectWhereFuzz(){
        Generator<Qualification> generator = ExprGenerators.qualificationFactory(this);
        int num = Utility.randomIntFromRange(0, config.getInt(Conf.MAX_SELECT_WHERE_FILTER_NUM));
        for(int i=0; i< num; i++){
            Qualification qualification = generator.generate();
            if(qualification == null){
                // todo not null
                continue;
            }
            filter.put(qualification);
        }
    }

    @Override
    public void fuzz() {
        if(statement.containsClause(NodeTag.fromClause)){
            processImplicitJoin();
        }
        selectWhereFuzz();
        if(!filter.isEmpty()){
            filter.fuzz();
            children.add(filter);
        }
    }
}
