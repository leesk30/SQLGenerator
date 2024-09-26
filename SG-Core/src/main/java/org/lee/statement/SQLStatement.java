package org.lee.statement;

import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.config.RuntimeConfigurationProvider;
import org.lee.entry.relation.CTE;
import org.lee.fuzzer.Fuzzer;
import org.lee.node.NodeTag;
import org.lee.common.config.RuleName;
import org.lee.statement.clause.Clause;
import org.lee.node.Node;
import org.lee.node.TreeNode;
import org.lee.statement.support.SupportCommonTableExpression;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class SQLStatement implements TreeNode<Clause<? extends Node>>, Fuzzer {
    protected final SQLType sqlType;
    protected final SQLStatement parent;
    protected final RuntimeConfiguration configuration;
    protected final Map<NodeTag, Clause<? extends Node>> childrenMap = new ConcurrentHashMap<>();

    protected SQLStatement(SQLType sqlType){
        this(sqlType, null);
    }

    protected SQLStatement(SQLType sqlType, SQLStatement parentStatement){
        if(parentStatement == null){
            this.sqlType = sqlType;
            this.parent = null;
            this.configuration = RuntimeConfigurationProvider.getDefaultProvider().newRuntimeConfiguration();
        }else {
            this.sqlType = sqlType;
            this.parent = parentStatement;
            this.configuration = this.parent.configuration.newChildRuntimeConfiguration();
        }
//        this.sqlSyntax = SQLSyntax.newSyntax(this);
    }

    public boolean isFinished(){
        return this.parent == null;
    }

    public SQLStatement getParent() {
        return parent;
    }

    public SQLType getSqlType() {
        return sqlType;
    }

    public RuntimeConfiguration getConfig() {
        return configuration;
    }

    public boolean confirm(RuleName ruleName){
        return configuration.getRule(ruleName);
    }

    protected void addClause(Clause<? extends Node> child){
        NodeTag key = child.getNodeTag();
        if(childrenMap.containsKey(child.getNodeTag())){
            throw new RuntimeException("Cannot add duplicate clause on same statement.");
        }
        childrenMap.put(key, child);
    }

    public boolean containsClause(NodeTag key){
        return childrenMap.containsKey(key);
    }

    public Clause<? extends Node> getClause(NodeTag key){
        return childrenMap.get(key);
    }

    public List<CTE> recursiveGetCTEs(){
        List<CTE> parentCTEList = parent!=null ? parent.recursiveGetCTEs() : Collections.emptyList();
        if(this instanceof SupportCommonTableExpression){
            List<CTE> cteList = new Vector<>(((SupportCommonTableExpression) this).getCTEs());
            if(parentCTEList != null){
                cteList.addAll(parentCTEList);
            }
            return cteList;
        }else {
            return parentCTEList;
        }
    }

    @Override
    public Stream<Clause<? extends Node>> walk() {
        return StreamSupport.stream(
                Spliterators.spliterator(new SQLClauseWalker(this.sqlType, this.childrenMap), childrenMap.size(), 0),
                false
        );
    }

}
