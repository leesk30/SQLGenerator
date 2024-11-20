package org.lee.context;

import org.lee.sql.entry.relation.CTE;
import org.lee.sql.statement.SQLStatement;

import java.util.List;

public interface SQLGeneratorFrame {
    SQLGeneratorContext retrieveContext();
    List<CTE> recursiveGetCTEs();
    SQLStatement current();

    default SQLStatement previous(){
        SQLGeneratorContext context = current().retrieveContext();
        if(context == null){
            return null;
        }
        return context.stack().peek();
    }
}
