package org.lee.sql.statement;

import org.lee.base.Node;
import org.lee.common.NamedLoggers;
import org.lee.common.SQLFormatter;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.enumeration.NodeTag;
import org.lee.sql.SQLGeneratorContext;
import org.lee.sql.clause.Clause;
import org.lee.sql.entry.relation.CTE;
import org.lee.sql.statement.common.SQLClauseWalker;
import org.lee.sql.statement.common.SQLType;
import org.slf4j.Logger;
import org.slf4j.MDC;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class AbstractSQLStatement implements SQLStatement {
    protected final SQLType sqlType;
    protected final SQLStatement parent;
    protected final RuntimeConfiguration config;
    protected final Map<NodeTag, Clause<? extends Node>> childrenMap = new EnumMap<>(NodeTag.class);
    protected final UUID uniqueTraceID;
    protected final UUID uniqueStatementID;
    protected final Logger logger = NamedLoggers.getCoreLogger(this.getClass());

    protected AbstractSQLStatement(SQLType sqlType){
        this(sqlType, null);
    }

    protected AbstractSQLStatement(SQLType sqlType, SQLStatement parentStatement){
        this.uniqueStatementID = UUID.randomUUID();
        if(parentStatement == null){
            this.uniqueTraceID = this.uniqueStatementID;
            this.sqlType = sqlType;
            this.parent = null;
            this.config = SQLGeneratorContext.getCurrentConfigProvider().newRuntimeConfiguration();
        }else {
            this.uniqueTraceID = parentStatement.getUniqueTraceID();
            this.sqlType = sqlType;
            this.parent = parentStatement;
            this.config = this.parent.getConfig().newChildRuntimeConfiguration();
            logger.info(String.format("Start to build subquery for type: %s, parent type: %s.", sqlType, parentStatement.getSQLType()));
        }
        initMDCKey();
    }

    protected final void initMDCKey(){
        String mdcTraceID = truncate(uniqueTraceID, 8);
        MDC.put("traceID", mdcTraceID);
        logger.info(String.format("Start to build statement<%s> tracer<%s> for type: %s.", uniqueStatementID, uniqueTraceID, sqlType));
    }

    private static String truncate(UUID u, int n){
        String s = u.toString().replaceAll("-", "");
        if(s.length() > n){
            return s.substring(0, n);
        }else {
            return s;
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
    public UUID getUniqueTraceID() {
        return uniqueTraceID;
    }

    @Override
    public UUID getUniqueStatementID() {
        return uniqueStatementID;
    }

    @Override
    public String toString(){
        // for debug
        return SQLFormatter.formatStatement(getString());
    }
}
