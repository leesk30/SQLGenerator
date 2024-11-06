package org.lee.portal;

import org.json.JSONObject;
import org.lee.base.Generator;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.config.InternalConfig;
import org.lee.common.config.InternalConfigs;
import org.lee.common.config.RuntimeConfigurationProvider;
import org.lee.common.global.MetaEntry;
import org.lee.common.global.SymbolTable;
import org.lee.statement.SQLType;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.select.SelectType;
import org.lee.statement.support.SQLStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.UUID;


public final class SQLGeneratorContext  {
    private static final ThreadLocal<SQLGeneratorContext> contextThreadLocal = new ThreadLocal<>();
    private static SQLGeneratorContext sharedContext = null;
    private final InternalConfig config;
    private final RuntimeConfigurationProvider provider;
    private final Logger logger = LoggerFactory.getLogger("SQLGeneratorContext");
    private final SQLGenerator generator = new SQLGeneratorImplement();;
    private final MetaEntry entries;
    private final SymbolTable symbolTable;
    private final UUID uuid = UUID.randomUUID();

    private SQLGeneratorContext(InternalConfig config){
        this.config = config;
        this.provider = config.newProvider();
        this.entries = new MetaEntry();
        this.symbolTable = new SymbolTable();
        load();
    }

    private synchronized void load(){
        logger.info("Starting to loading meta entries and signatures");
        this.entries.init(config.getMetaEntry());
        this.symbolTable.init(config.getSymbolTable());
    }

    public static RuntimeConfigurationProvider getCurrentConfigProvider(){
        return getCurrentContext().provider;
    }

    public static Logger getCurrentLogger(){
        return getCurrentContext().logger;
    }

    public static MetaEntry getCurrentMetaEntry(){
        return getCurrentContext().entries;
    }

    public static SymbolTable getCurrentSymbolTable(){
        return getCurrentContext().symbolTable;
    }

    public RuntimeConfigurationProvider getConfigProvider(){
        return this.provider;
    }

    public Logger getLogger(){
        return this.logger;
    }

    public MetaEntry getMetaEntry(){
        return this.entries;
    }

    public SymbolTable getSymbolTable(){
        return this.symbolTable;
    }

    public void unset(){
        if(contextThreadLocal.get() == this){
            contextThreadLocal.remove();
        }
    }

    public SQLGeneratorContext copy(){
        return new SQLGeneratorContext(this.config.shallowCopy());
    }

    public void setCurrentToLocal(){
        if(contextThreadLocal.get() == null){
            contextThreadLocal.set(this);
            logger.info(String.format("SQLGeneratorContext[%s] assign to thread: %s", this.uuid, Thread.currentThread().getName()));
        } else if (contextThreadLocal.get() == this) {
            logger.debug("The current thread local variable is already bind by self.");
        }else {
            logger.warn(String.format("The current thread local variable will replace by self. Original: %s. This: %s", contextThreadLocal.get().uuid, this.uuid));
            unset();
            contextThreadLocal.set(this);
            logger.info(String.format("SQLGeneratorContext[%s] assign to thread: %s", this.uuid, Thread.currentThread().getName()));
        }
    }

    public static SQLGeneratorContext getSharedContext(){
        return sharedContext;
    }

    private static synchronized void setShared(SQLGeneratorContext context){
        if(sharedContext == null){
            sharedContext = context.copy();
        }
    }

    private static SQLGeneratorContext getCurrentContext(){
        SQLGeneratorContext context = contextThreadLocal.get();
        if(context == null){
            throw new RuntimeException("The context doesn't not initialized.");
        }
        return context;
    }

    public static SQLGeneratorContext getOrUseShared(){
        if(contextThreadLocal.get() == null){
            return sharedContext;
        }
        return contextThreadLocal.get();
    }

    public static SQLGeneratorContext getOrCreate(final InternalConfig config){
        if(contextThreadLocal.get() == null){
            SQLGeneratorContext context = new SQLGeneratorContext(config);
            contextThreadLocal.set(context);
            setShared(context);
        }
        return contextThreadLocal.get();
    }

    public static SQLGeneratorContext getOrCreate(final String inputMetaEntries){
        if(contextThreadLocal.get() == null){
            InternalConfig config = InternalConfigs.create(inputMetaEntries);
            SQLGeneratorContext context = new SQLGeneratorContext(config);
            contextThreadLocal.set(context);
            setShared(context);
        }
        return contextThreadLocal.get();
    }

    public SQLGenerator getGenerator() {
        return generator;
    }

    public static final class SQLGeneratorImplement implements SQLGenerator {
        private SQLGeneratorImplement(){}

        private SQLStatement getStatement(SQLType sqlType){
            switch (sqlType){
                case select:
                    return SelectStatement.getStatementBySelectType(null);
                case values:
                case update:
                case insert:
                case delete:
                case merge:
                default:
                    return null;
            }
        }

        private SQLStatement adaptiveGenerate(){
            SQLType sqlType = Utility.randomlyChooseFrom(SQLType.values());
            SQLStatement statement = getStatement(sqlType);
            Assertion.requiredNonNull(statement);
            assert statement != null;
            statement.fuzz();
            return statement;
        }

        @Override
        public SQLStatement generate() {
            // todo:
            return generateSelect();
//            return adaptiveGenerate();
        }

        public SelectStatement generateSelect(){
            SelectStatement selectStatement = SelectStatement.getStatementBySelectType(null);
            selectStatement.fuzz();
            return selectStatement;
        }

        public SelectStatement generateSelect(SelectType selectType){
            SelectStatement selectStatement = SelectStatement.getStatementBySelectType(selectType);
            selectStatement.fuzz();
            return selectStatement;
        }

    }



}
