package org.lee.sql.statement.select;


import org.lee.context.SQLGeneratorContext;
import org.lee.sql.entry.complex.TargetEntry;

public final class SelectSimpleStatement extends AbstractSimpleSelectStatement {

    public SelectSimpleStatement(SQLGeneratorContext context) {
        super(SelectType.simple, context);
    }

    @Override
    public void fuzz() {
        fromClause.fuzz();
        whereClause.fuzz();
        targetList.fuzz();
        groupByClause.fuzz();
        havingClause.fuzz();
    }

    @Override
    public boolean isScalar() {
        if(width()!=1){
            return false;
        }
        //  Eg. `Avg(a)` `Avg(a + b)` is scalar,
        //      but the  Avg(a) + b is not a scalar because `b` is not including in aggregator,
        //      there is more than for distinct b value
        TargetEntry firstEntry = targetList.getChildNodes().get(0);
        return firstEntry.isScalarStyle();
    }
}
