package org.lee.sql.statement;

import org.lee.base.Node;
import org.lee.base.Statement;
import org.lee.common.SQLFormatter;
import org.lee.common.enumeration.NodeTag;
import org.lee.context.SQLGeneratorContext;
import org.lee.sql.clause.Clause;
import org.lee.sql.entry.relation.CTE;
import org.lee.sql.statement.common.SQLType;

import java.util.List;

public interface SQLStatement extends Statement<Clause<? extends Node>> {

    SQLType getSQLType();

    SQLGeneratorContext retrieveContext();

    List<CTE> recursiveGetCTEs();

    boolean containsClause(NodeTag clauseTag);

    Clause<? extends Node> getClause(NodeTag clauseTag);


    default boolean isFinished(){
        return this.getParent() == null;
    }

    default boolean enableSubquery(){
        return true;
    }

    default boolean enableSetop(){
        return true;
    }

    default String getFormattedString(){
        return SQLFormatter.formatStatement(this.getString());
    }

}
