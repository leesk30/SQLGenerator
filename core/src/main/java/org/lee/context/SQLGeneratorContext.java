package org.lee.context;

import com.sun.istack.internal.NotNull;
import org.lee.common.NamedLoggers;
import org.lee.common.config.InternalConfig;
import org.lee.common.config.InternalConfigs;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.config.RuntimeConfigurationProvider;
import org.lee.common.utils.DebugUtils;
import org.lee.generator.sql.SQLGenerator;
import org.lee.resource.MetaEntry;
import org.lee.resource.SymbolTable;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.statement.select.SelectStatement;
import org.slf4j.Logger;
import org.slf4j.MDC;

import java.util.Stack;
import java.util.UUID;


public final class SQLGeneratorContext implements SQLGenerator {
    private final InternalConfig config;
    private final RuntimeConfigurationProvider provider;
    private final Logger logger = NamedLoggers.getCoreLogger(SQLGeneratorContext.class);

    private final MetaEntry entries;
    private final SymbolTable symbolTable;
    private final UUID uuid = UUID.randomUUID();

    private final Stack<SQLGeneratorFrame> stack = new Stack<>();

    private final RecursiveProjectableGenerator recursiveGenerator = new RecursiveProjectableGenerator(this);

    public SQLGeneratorContext(InternalConfig config){
        this.config = config;
        this.provider = RuntimeConfigurationProvider.getProvider(config);
        this.entries = new MetaEntry();
        this.symbolTable = new SymbolTable(config.getGeneratePolicy());
        this.entries.init(config.getMetaEntry());
        this.symbolTable.init(config.getSymbolTable());
        MDC.put("traceID", DebugUtils.truncate(uuid));
        MDC.put("frameID", DebugUtils.truncate(uuid));
    }

    public Stack<SQLGeneratorFrame> stack(){
        return stack;
    }

    public @NotNull SQLGeneratorFrame currentFrame(){
        return stack.peek();
    }

    public UUID uuid() {
        return uuid;
    }

    public RuntimeConfiguration newRuntimeConfiguration(){
        return provider.newRuntimeConfiguration();
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

    public static SQLGeneratorContext create(final String inputMetaEntries){
        InternalConfig config = InternalConfigs.create(inputMetaEntries);
        return new SQLGeneratorContext(config);
    }

    @Override
    public SQLStatement generate() {
        // todo:
        return generateSelect();
//            return adaptiveGenerate();
    }

    public RecursiveProjectableGenerator recursive() {
        return recursiveGenerator;
    }

    public SelectStatement generateSelect(){
        return recursiveGenerator.generateSelect();
    }




}
