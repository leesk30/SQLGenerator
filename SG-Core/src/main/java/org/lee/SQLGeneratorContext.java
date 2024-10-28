package org.lee;

import org.json.JSONObject;
import org.lee.base.Generator;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.config.RuntimeConfigurationProvider;
import org.lee.common.global.SymbolTable;
import org.lee.common.global.MetaEntry;
import org.lee.statement.SQLType;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.select.SelectType;
import org.lee.statement.support.SQLStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;


public final class SQLGeneratorContext {
    private static final ThreadLocal<SQLGeneratorContext> contextThreadLocal = new ThreadLocal<>();
    private static SQLGeneratorContext sharedContext = null;

    private final RuntimeConfigurationProvider configurationProvider;
    private final Logger logger = LoggerFactory.getLogger("SQLGeneratorContext");
    private final Generator<SQLStatement> generator;
    private final MetaEntry entries;
    private final SymbolTable symbolTable;
    private final UUID uuid = UUID.randomUUID();

    private SQLGeneratorContext(RuntimeConfigurationProvider provider){
        this.configurationProvider = provider;
        this.generator = new SQLGenerator();
        // todo:
        this.entries = new MetaEntry();
        this.symbolTable = new SymbolTable();
        logger.info("Initialize sql generator context with id: " + uuid);
        loadingOther();
    }

    private synchronized void loadingOther(){
        logger.info("Starting to loading meta entries and signatures");
        InputStream inputStream = SQLGeneratorContext.class.getClassLoader().getResourceAsStream("tpcds.json");
        String jsonString = Utility.inputStreamToString(inputStream);
        JSONObject jsonObject = new JSONObject(jsonString);
        entries.load(jsonObject);
        symbolTable.load();
    }

    public static RuntimeConfigurationProvider getCurrentConfigProvider(){
        return getCurrentContext().configurationProvider;
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
        return this.configurationProvider;
    }

    public Logger getLogger(){
        return this.logger;
    }

    public MetaEntry getMetaEntry(){
        return this.entries;
    }

    public SymbolTable getFinder(){
        return this.symbolTable;
    }

    public void unset(){
        if(contextThreadLocal.get() == this){
            contextThreadLocal.remove();
        }
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

    public static void createDefaultSharedContext(){
        if(sharedContext == null){
            sharedContext = new SQLGeneratorContext(RuntimeConfigurationProvider.getDefaultProvider());
        }else {
            sharedContext.logger.error("The shared context has already been created.");
            throw new RuntimeException("Duplicate initialization.");
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
            if (sharedContext == null){
                sharedContext = getSharedContext();
            }
            sharedContext.setCurrentToLocal();
            return sharedContext;
        }
        return contextThreadLocal.get();
    }

    public static SQLGeneratorContext getOrCreate(final String providerSource){
        if(contextThreadLocal.get() == null){
            RuntimeConfigurationProvider provider = RuntimeConfigurationProvider.getProvider(providerSource);
            contextThreadLocal.set(new SQLGeneratorContext(provider));
            contextThreadLocal.get().setCurrentToLocal();
        }
        return contextThreadLocal.get();
    }

    public static SQLGeneratorContext getOrCreate(final File sourceFile){
        if(contextThreadLocal.get() == null){
            RuntimeConfigurationProvider provider = RuntimeConfigurationProvider.getProvider(sourceFile);
            contextThreadLocal.set(new SQLGeneratorContext(provider));
            contextThreadLocal.get().setCurrentToLocal();
        }
        return contextThreadLocal.get();
    }

    public Generator<SQLStatement> getGenerator() {
        return generator;
    }

    public static final class SQLGenerator implements Generator<SQLStatement> {
        private SQLGenerator(){}

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
