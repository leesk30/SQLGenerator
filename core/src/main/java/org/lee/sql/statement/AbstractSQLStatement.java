package org.lee.sql.statement;

import org.lee.base.Node;
import org.lee.common.NamedLoggers;
import org.lee.common.SQLFormatter;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.enumeration.NodeTag;
import org.lee.common.utils.DebugUtils;
import org.lee.context.SQLGeneratorContext;
import org.lee.sql.clause.Clause;
import org.lee.sql.entry.relation.CTE;
import org.lee.sql.statement.common.SQLClauseWalker;
import org.lee.sql.statement.common.SQLType;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
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


    protected AbstractSQLStatement(@Nonnull SQLType sqlType, @Nonnull SQLGeneratorContext context){
        this.context = context;
        this.sqlType = sqlType;

        this.parent = context.currentParentStatement();

        if(parent == null){
            this.config = context.getConfigProvider().newRuntimeConfiguration();
        }else {
            this.config = this.parent.getConfig().newChildRuntimeConfiguration();
            if(DebugUtils.ENABLE_DEV_DEBUG_LOGGER){
                logger.info(String.format("Start to build subquery for type: %s, parent type: %s.", sqlType, parent.getSQLType()));
            }
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
        return res == null ? "<unknown>": SQLFormatter.formatStatement(res);
    }
}
