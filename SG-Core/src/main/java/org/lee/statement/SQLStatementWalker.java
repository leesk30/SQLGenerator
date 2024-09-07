package org.lee.statement;

import org.lee.statement.clause.Clause;
import org.lee.node.Node;
import org.lee.node.NodeTag;

import java.util.Iterator;
import java.util.Map;

// package protected
public class SQLStatementWalker implements Iterator<Clause<? extends Node>> {

    private final Map<NodeTag, Clause<? extends Node>> childrenMap;
    private int index;

    private static final NodeTag[] router = {
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

    private static final NodeTag[] generatorRouter = {
            NodeTag.withClause,
            NodeTag.fromClause,
            NodeTag.selectClause,

    };

    public SQLStatementWalker(Map<NodeTag, Clause<? extends Node>> childrenMap){
        this.childrenMap = childrenMap;
        this.index = 0;
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

}
