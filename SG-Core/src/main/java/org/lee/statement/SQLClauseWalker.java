package org.lee.statement;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.common.exception.NotImplementedException;
import org.lee.statement.clause.Clause;

import java.util.Iterator;
import java.util.Map;

// package protected
public class SQLClauseWalker implements Iterator<Clause<? extends Node>> {

    private final Map<NodeTag, Clause<? extends Node>> childrenMap;
    private final NodeTag[] router;
    private final boolean isAssemble;

    private int index;

    private static final NodeTag[] selectAssembler = {
            NodeTag.withClause,
            NodeTag.selectClause,
            NodeTag.fromClause,
            NodeTag.startWithClause,
            NodeTag.connectByClause,
            NodeTag.whereClause,
            NodeTag.groupByClause,
            NodeTag.havingClause,
            NodeTag.sortByClause,
            NodeTag.limitOffset,
    };

    private static final NodeTag[] valuesAssembler = {
            NodeTag.valuesClause,
            NodeTag.sortByClause,
            NodeTag.limitOffset,
    };

    private static final NodeTag[] insertAssembler = {
            NodeTag.modifyTableClause,
            NodeTag.valuesClause,
    };

    // in same order
    private static final NodeTag[] valuesGenerator = valuesAssembler;
    private static final NodeTag[] insertGenerator = insertAssembler;

    private static final NodeTag[] selectGenerator = {
            NodeTag.withClause,
            NodeTag.fromClause,
            NodeTag.selectClause,
    };

    public SQLClauseWalker(SQLType sqlType, Map<NodeTag, Clause<? extends Node>> childrenMap){
        this(sqlType, true, childrenMap);
    }
    public SQLClauseWalker(SQLType sqlType, boolean isAssemble, Map<NodeTag, Clause<? extends Node>> childrenMap){
        this.childrenMap = childrenMap;
        this.index = 0;
        this.isAssemble = isAssemble;
        switch (sqlType){
            case select:
                this.router = isAssemble ? selectAssembler: selectGenerator;
                break;
            case values:
                this.router = isAssemble ? valuesAssembler: valuesGenerator;
                break;
            case insert:
                this.router = isAssemble ? insertAssembler: insertGenerator;
                break;
            default:
                throw new NotImplementedException("NotImplemented for " + sqlType);
        }
    }

    @Override
    public boolean hasNext() {
        while (index < router.length){
            final Clause<? extends Node> clause = childrenMap.get(router[index]);
            if (clause != null && !clause.isEmpty())
                return true;
            index++;
        }
        return false;
    }

    @Override
    public Clause<? extends Node> next() {
        Clause<? extends Node> clause = childrenMap.get(router[index]);
        index++;
        return clause;
    }

    public NodeTag[] getRouter() {
        return router;
    }

    public boolean isAssemble() {
        return isAssemble;
    }
}
