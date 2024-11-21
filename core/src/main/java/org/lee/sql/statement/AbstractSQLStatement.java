package org.lee.sql.statement;

import com.sun.istack.internal.NotNull;
import org.lee.base.Node;
import org.lee.common.NamedLoggers;
import org.lee.common.SQLFormatter;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.enumeration.NodeTag;
import org.lee.context.SQLGeneratorContext;
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
    protected final Logger logger = NamedLoggers.getCoreLogger(this.getClass());
    protected final SQLGeneratorContext context;


    protected AbstractSQLStatement(@NotNull SQLType sqlType, @NotNull SQLGeneratorContext context){
        this.context = context;
        this.sqlType = sqlType;

        this.parent = context.currentFrame().previousStatement();

        if(parent == null){
            this.config = context.getConfigProvider().newRuntimeConfiguration();
        }else {
            this.config = this.parent.getConfig().newChildRuntimeConfiguration();
            logger.info(String.format("Start to build subquery for type: %s, parent type: %s.", sqlType, parent.getSQLType()));
        }
    }

    protected AbstractSQLStatement(SQLType sqlType){
        this.context = null;
        this.sqlType = sqlType;
        this.parent = null;
        this.config = null;
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
        if(this instanceof WithCommonTableExpression){
            List<CTE> cteList = new ArrayList<>(((WithCommonTableExpression) this).getCTEs());
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
    public SQLGeneratorContext retrieveContext() {
        return context;
    }

    @Override
    public String toString(){
        // for debug
        String res = getString();
        if(res == null){
            return "<unknown>";
        }
        return SQLFormatter.formatStatement(getString());
    }
}
