package org.lee.context.factory;

import org.lee.common.SGException.NotImplementedException;
import org.lee.context.decription.SQLContext;
import org.lee.context.decription.SelectContext;
import org.lee.context.decription.SelectScalarContext;
import org.lee.context.decription.SubqueryContext;
import org.lee.node.tree.SQLType;

public class ContextFactory {

    public static SQLContext createSQLContext(SQLType sqlType){
        switch (sqlType){
            case scalar:
                return new SelectScalarContext();
            case select:
                return new SelectContext();
            case subquery:
                return new SubqueryContext();
            default:
                throw new NotImplementedException(String.format("The context of the sql type<%s> doesn't implement.", sqlType));
        }
    }
}
