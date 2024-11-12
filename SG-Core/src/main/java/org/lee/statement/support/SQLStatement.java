package org.lee.statement.support;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.entry.relation.CTE;
import org.lee.statement.basic.SQLType;
import org.lee.statement.clause.Clause;

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
        return true;
    }

    default boolean enableSetop(){
        return true;
    }

}
