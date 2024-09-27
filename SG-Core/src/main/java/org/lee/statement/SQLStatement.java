package org.lee.statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.config.RuntimeConfigurationProvider;
import org.lee.entry.relation.CTE;
import org.lee.base.NodeTag;
import org.lee.common.config.Rule;
import org.lee.statement.clause.Clause;
import org.lee.base.Node;
import org.lee.statement.support.Statement;
import org.lee.statement.support.SupportCommonTableExpression;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class SQLStatement implements Statement<Clause<? extends Node>> {
    protected final SQLType sqlType;
    protected final SQLStatement parent;
    protected final RuntimeConfiguration config;
    protected final Map<NodeTag, Clause<? extends Node>> childrenMap = new ConcurrentHashMap<>();
    protected final UUID uuid;
    protected final Log LOGGER = LogFactory.getLog(this.getClass());

    protected SQLStatement(SQLType sqlType){
        this(sqlType, null);
    }

    protected SQLStatement(SQLType sqlType, SQLStatement parentStatement){
        if(parentStatement == null){
            this.uuid = UUID.randomUUID();
            this.sqlType = sqlType;
            this.parent = null;
            this.config = RuntimeConfigurationProvider.getDefaultProvider().newRuntimeConfiguration();
        }else {
            this.uuid = parentStatement.uuid;
            this.sqlType = sqlType;
            this.parent = parentStatement;
            this.config = this.parent.getConfig().newChildRuntimeConfiguration();
        }
        LOGGER.info("Hello world! - " + uuid);
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
        return config;
    }

    public boolean confirm(Rule ruleName){
        return config.getRule(ruleName);
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
            List<CTE> cteList = new ArrayList<>(((SupportCommonTableExpression) this).getCTEs());
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
