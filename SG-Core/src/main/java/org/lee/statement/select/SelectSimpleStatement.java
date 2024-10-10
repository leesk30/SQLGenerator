package org.lee.statement.select;


import org.lee.statement.support.SQLStatement;

public final class SelectSimpleStatement extends AbstractSimpleSelectStatement {
    public SelectSimpleStatement() {
        super(SelectType.simple);
    }

    public SelectSimpleStatement(SQLStatement parent) {
        super(SelectType.simple, parent);
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
        if(targetList.size() != 1){
            return false;
        }
        //  Eg. `Avg(a)` `Avg(a + b)` is scalar,
        //      but the  Avg(a) + b is not a scalar because `b` is not including in aggregator,
        //      there is more than for distinct b value
        return targetList.getChildNodes().get(0).toExpression().isCurrentAggregation();
    }
}
