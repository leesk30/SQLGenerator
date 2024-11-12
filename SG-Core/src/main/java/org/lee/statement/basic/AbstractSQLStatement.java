package org.lee.statement.basic;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.common.SQLFormatter;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.entry.relation.CTE;
import org.lee.portal.SQLGeneratorContext;
import org.lee.statement.SQLClauseWalker;
import org.lee.statement.clause.Clause;
import org.lee.statement.support.SQLStatement;
import org.lee.statement.support.SupportCommonTableExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class AbstractSQLStatement implements SQLStatement {
    protected final SQLType sqlType;
    protected final SQLStatement parent;
    protected final RuntimeConfiguration config;
    protected final Map<NodeTag, Clause<? extends Node>> childrenMap = new EnumMap<>(NodeTag.class);
    protected final UUID uuid;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected AbstractSQLStatement(SQLType sqlType){
        this(sqlType, null);
    }

    protected AbstractSQLStatement(SQLType sqlType, SQLStatement parentStatement){
        if(parentStatement == null){
            this.uuid = UUID.randomUUID();
            this.sqlType = sqlType;
            this.parent = null;
            this.config = SQLGeneratorContext.getCurrentConfigProvider().newRuntimeConfiguration();
            MDC.put("stmtId", uuid.toString().replaceAll("-", ""));
            logger.info(String.format("Start to build statement for type: %s.", sqlType));
        }else {
            this.uuid = parentStatement.getUUID();
            this.sqlType = sqlType;
            this.parent = parentStatement;
            this.config = this.parent.getConfig().newChildRuntimeConfiguration();
            logger.info(String.format("Start to build subquery for type: %s, parent type: %s.", sqlType, parentStatement.getSQLType()));
        }
    }

    @Override
    public SQLStatement getParent() {
        return parent;
    }

    @Override
    public SQLType getSQLType() {
        return sqlType;
    }

    @Override
    public RuntimeConfiguration getConfig() {
        return config;
    }

    protected void addClause(Clause<? extends Node> child){
        NodeTag key = child.getNodeTag();
        if(childrenMap.containsKey(child.getNodeTag())){
            throw new RuntimeException("Cannot add duplicate clause on same statement.");
        }
        childrenMap.put(key, child);
    }

    @Override
    public boolean containsClause(NodeTag key){
        return childrenMap.containsKey(key);
    }

    @Override
    public Clause<? extends Node> getClause(NodeTag key){
        return childrenMap.get(key);
    }

    @Override
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

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String toString(){
        // for debug
        return new SQLFormatter().format(getString());
    }
}
