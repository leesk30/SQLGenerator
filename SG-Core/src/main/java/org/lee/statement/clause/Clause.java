package org.lee.statement.clause;

import org.lee.common.config.RuntimeConfiguration;
import org.lee.base.Fuzzer;
import org.lee.statement.SQLStatement;
import org.lee.base.Node;
import org.lee.base.TreeNode;
import org.lee.statement.support.SupportRuntimeConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

public abstract class Clause<T extends Node> implements TreeNode<T>, Fuzzer, SupportRuntimeConfiguration {
    protected final SQLStatement statement;
    protected final List<T> children;
    protected static int defaultChildrenInitialCapacity = 8;
    protected final RuntimeConfiguration config;
    protected Clause(SQLStatement statement){
        this.statement = statement;
        this.config = statement.getConfig();
        this.children = new ArrayList<>(defaultChildrenInitialCapacity);
    }

    protected Clause(SQLStatement statement, int childrenInitialCapacity){
        this.statement = statement;
        this.config = statement.getConfig();
        this.children = new ArrayList<>(childrenInitialCapacity);
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

    @Override
    public Stream<T> walk() {
        return children.stream();
    }

    @Override
    public RuntimeConfiguration getConfig() {
        return config;
    }
}
