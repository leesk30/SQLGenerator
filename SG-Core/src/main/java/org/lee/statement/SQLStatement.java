package org.lee.statement;

import org.lee.entry.relation.CTE;
import org.lee.fuzzer.Fuzzer;
import org.lee.node.NodeTag;
import org.lee.rules.RuleName;
import org.lee.rules.RuleSet;
import org.lee.rules.SparkRuleSet;
import org.lee.statement.clause.Clause;
import org.lee.node.Node;
import org.lee.node.TreeNode;
import org.lee.statement.support.SupportCommonTableExpression;
import org.lee.statement.syntax.SQLSyntax;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class SQLStatement implements TreeNode<Clause<? extends Node>>, Fuzzer {
    protected final SQLGenerateContext context;
    protected final SQLType sqlType;
    protected final SQLStatement parent;
    protected final RuleSet ruleSet;
//    protected final SQLSyntax sqlSyntax;
    protected final Map<NodeTag, Clause<? extends Node>> childrenMap = new ConcurrentHashMap<>();

    protected SQLStatement(SQLType sqlType){
        this(sqlType, null);
    }

    protected SQLStatement(SQLType sqlType, SQLStatement parentStatement){
        if(parentStatement == null){
            this.context = new SQLGenerateContext(sqlType);
            this.sqlType = sqlType;
            this.parent = null;
        }else {
            this.context = new SQLGenerateContext(parentStatement.getContext(), sqlType);
            this.sqlType = sqlType;
            this.parent = parentStatement;
        }
        this.ruleSet = new SparkRuleSet();
//        this.sqlSyntax = SQLSyntax.newSyntax(this);
    }

    protected boolean isFinished(){
        return this.parent == null;
    }

    public SQLGenerateContext getContext(){
        return context;
    }

    public SQLStatement getParent() {
        return parent;
    }

    public SQLType getSqlType() {
        return sqlType;
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public boolean confirmByRuleName(RuleName ruleName){
        return ruleSet.confirm(ruleName);
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
