package org.lee.statement.node;

import org.lee.statement.clause.Clause;

import java.util.Iterator;
import java.util.List;

public interface TreeNode<T extends Node> extends Node, Iterable<T> {
    List<? extends Node> getChildNodes();

    Iterator<T> walk();

    @Override
    default Iterator<T> iterator(){
        assert this.walk() != null;
        return this.walk();
    }
}
