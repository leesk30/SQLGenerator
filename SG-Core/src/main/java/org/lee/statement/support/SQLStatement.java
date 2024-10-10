package org.lee.statement.support;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.common.config.Conf;
import org.lee.entry.relation.CTE;
import org.lee.statement.SQLType;
import org.lee.statement.clause.Clause;
import org.lee.statement.select.SelectStatement;

import java.util.List;

public interface SQLStatement extends Statement<Clause<? extends Node>>{

    SQLType getSQLType();
    List<CTE> recursiveGetCTEs();
    boolean containsClause(NodeTag clauseTag);

    Clause<? extends Node> getClause(NodeTag clauseTag);

    default boolean isFinished(){
        return this.getParent() == null;
    }

    default boolean enableSubquery(){
        if(this instanceof SelectStatement){
            SelectStatement selectStatement = (SelectStatement) this;
            return selectStatement.subqueryDepth < getConfig().getShort(Conf.MAX_SUBQUERY_RECURSION_DEPTH);
        }
        return true;
    }

    default boolean enableSetop(){
        if(this instanceof SelectStatement){
            SelectStatement selectStatement = (SelectStatement) this;
            return selectStatement.setopDepth < getConfig().getShort(Conf.MAX_SETOP_RECURSION_DEPTH);
        }
        return true;
    }
}
