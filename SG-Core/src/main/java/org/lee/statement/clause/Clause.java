package org.lee.statement.clause;

import org.lee.fuzzer.Fuzzer;
import org.lee.statement.SQLStatement;
import org.lee.node.Node;
import org.lee.node.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class Clause<T extends Node> implements TreeNode<T>, Fuzzer {
    protected final SQLStatement statement;
    protected final List<T> children;
    protected static int defaultChildrenInitialCapacity = 8;
    protected Clause(SQLStatement statement){
        this.statement = statement;
        this.children = new Vector<>(defaultChildrenInitialCapacity);
    }

    protected Clause(SQLStatement statement, int childrenInitialCapacity){
        this.statement = statement;
        this.children = new Vector<>(childrenInitialCapacity);
    }

    public SQLStatement statement() {
        return statement;
    }

    public boolean isEmpty(){
        return children.isEmpty();
    }

    public int size(){
        return children.size();
    }

    @Override
    public List<T> getChildNodes() {
        return children;
    }
}
