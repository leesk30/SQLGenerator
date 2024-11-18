package org.lee.sql.clause;

import org.lee.base.Fuzzer;
import org.lee.base.Node;
import org.lee.base.TreeNode;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.support.SQLStatementChildren;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class Clause<T extends Node> implements TreeNode<T>, Fuzzer, SQLStatementChildren {
    protected static int DEFAULT_CHILDREN_CAPACITY = 8;

    protected final SQLStatement statement;
    protected final List<T> children;
    protected final RuntimeConfiguration config;
    protected final Logger logger;

    protected Clause(SQLStatement statement){
        this.statement = statement;
        this.children = new ArrayList<>(DEFAULT_CHILDREN_CAPACITY);
        this.config = statement.getConfig();
        this.logger = statement.getLogger();
    }

    protected Clause(SQLStatement statement, int childrenInitialCapacity){
        this.statement = statement;
        this.children = new ArrayList<>(childrenInitialCapacity);
        this.config = statement.getConfig();
        this.logger = statement.getLogger();
    }

    @Override
    public SQLStatement retrieveParent() {
        return statement;
    }

    @Override
    public Logger getLogger(){
        return logger;
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

    @Override
    public String toString(){
        // for debug
        return getString();
    }
}
