package org.lee.context;

import org.lee.common.NamedLoggers;
import org.lee.common.config.InternalConfig;
import org.lee.common.config.InternalConfigs;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.config.RuntimeConfigurationProvider;
import org.lee.common.utils.DebugUtils;
import org.lee.common.utils.RandomUtils;
import org.lee.generator.sql.SQLGenerator;
import org.lee.resource.MetaEntry;
import org.lee.resource.SymbolTable;
import org.lee.sql.entry.relation.Relation;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.statement.insert.InsertStatement;
import org.lee.sql.statement.select.SelectStatement;
import org.slf4j.Logger;
import org.slf4j.MDC;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;


public final class SQLGeneratorContext implements SQLGenerator {
    public static final SQLGeneratorContext EMPTY = new SQLGeneratorContext(InternalConfigs.EMPTY);

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

    public @Nonnull SQLGeneratorFrame currentFrame(){
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

    public static SQLGeneratorContext create(final InternalConfig config){
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


    public SQLGeneratorContext copy(){
        return new SQLGeneratorContext(this.config);
    }

    public List<InsertStatement> generateEntriesData(){
        List<InsertStatement> statements = new ArrayList<>();
        for(Relation relation: this.entries.getRelationMap().values()){
            final int numOfInsertStatement = RandomUtils.randomIntFromRange(10, 20);
            for(int i=0; i < numOfInsertStatement; i++){
                statements.add(relation.getInitializedInsert(this, 3));
            }
        }
        return statements;
    }

}
