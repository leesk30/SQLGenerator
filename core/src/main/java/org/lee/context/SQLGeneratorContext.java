package org.lee.context;

import org.lee.common.Assertion;
import org.lee.common.NamedLoggers;
import org.lee.common.config.InternalConfig;
import org.lee.common.config.InternalConfigs;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.config.RuntimeConfigurationProvider;
import org.lee.common.enumeration.Conf;
import org.lee.common.enumeration.Rule;
import org.lee.common.utils.DebugUtils;
import org.lee.common.utils.RandomUtils;
import org.lee.generator.sql.SQLGenerator;
import org.lee.resource.MetaEntry;
import org.lee.resource.SymbolTable;
import org.lee.sql.statement.Projectable;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.statement.common.SQLType;
import org.lee.sql.statement.select.*;
import org.lee.sql.statement.values.ValuesStatement;
import org.lee.sql.type.TypeTag;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


public final class SQLGeneratorContext implements SQLGenerator {

    private static final ThreadLocal<SQLGeneratorContext> contextThreadLocal = new ThreadLocal<>();

    private final InternalConfig config;
    private final RuntimeConfigurationProvider provider;
    private final Logger logger = NamedLoggers.getCoreLogger(SQLGeneratorContext.class);

    private final MetaEntry entries;
    private final SymbolTable symbolTable;
    private final UUID uuid = UUID.randomUUID();

    private final SQLGeneratorStack stack = new SQLGeneratorStack();

    public SQLGeneratorContext(InternalConfig config){
        this.config = config;
        this.provider = RuntimeConfigurationProvider.getProvider(config);
        this.entries = new MetaEntry();
        this.symbolTable = new SymbolTable(config.getGeneratePolicy());
        load();
    }

    public SQLGeneratorStack stack(){
        return stack;
    }

    public SQLGeneratorFrame currentFrame(){
        return stack.peek();
    }

    public RuntimeConfiguration newRuntimeConfiguration(){
        return provider.newRuntimeConfiguration();
    }

    private synchronized void load(){
        logger.info(String.format("Starting to loading meta entries and signatures. ID: %s", uuid));
        this.entries.init(config.getMetaEntry());
        this.symbolTable.init(config.getSymbolTable());
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

    public static SQLGeneratorContext create(final String inputMetaEntries){
        InternalConfig config = InternalConfigs.create(inputMetaEntries);
        return new SQLGeneratorContext(config);
    }


    private SQLStatement getStatement(SQLType sqlType){
        switch (sqlType){
            case select:
                return SelectStatement.getStatementBySelectType(null, this);
            case values:
            case update:
            case insert:
            case delete:
            case merge:
            default:
                return null;
        }
    }

    @Override
    public SQLStatement generate() {
        // todo:
        return generateSelect();
//            return adaptiveGenerate();
    }

    public SelectStatement generateSelect(){
        SelectStatement selectStatement = SelectStatement.getStatementBySelectType(null, this);
        return (SelectStatement) stack.fuzzy(selectStatement);
    }

    public Projectable generateProjectable(){
        Projectable statement = newRandomlyProjectable();
        return (Projectable) stack.fuzzy(statement);
    }

    public Projectable generateProjectable(List<TypeTag> limitations){
        Projectable statement = newRandomlyProjectable();
        if(!limitations.isEmpty()){
            statement.withProjectTypeLimitation(limitations);
        }
        return (Projectable) stack.fuzzy(statement);
    }

    public SelectStatement generateSelect(SelectType selectType){
        SelectStatement selectStatement = SelectStatement.getStatementBySelectType(selectType, this);
        return (SelectStatement) stack.fuzzy(selectStatement);
    }

    public Projectable generateScalarSubquery(TypeTag typeTag){
        ProjectableState state = new ProjectableState(this, false, true, false, false);
        Projectable projectable = newRandomlyProjectable(state);
        Assertion.requiredFalse(projectable instanceof SelectSimpleStatement);
        projectable.withProjectTypeLimitation(Collections.singletonList(typeTag));
        projectable.setConfig(Rule.REQUIRE_SCALA,true);
        return (Projectable) stack.fuzzy(projectable);
    }

    public Projectable generateRelatedScalarSubquery(TypeTag typeTag){
        ProjectableState state = new ProjectableState(this, false, true, false, false);
        Projectable projectable = newRandomlyProjectable(state);
        Assertion.requiredFalse(projectable instanceof SelectSimpleStatement);
        projectable.withProjectTypeLimitation(Collections.singletonList(typeTag));
        projectable.setConfig(Rule.REQUIRE_SCALA,true);
        projectable.setConfig(Rule.PREFER_RELATED,true);
        return (Projectable) stack.fuzzy(projectable);
    }

    public Projectable generatePredicateExistsSubquery(){
        ProjectableState state = new ProjectableState(this, true, false, false, true);
        Projectable projectable = newRandomlyProjectable(state);
        return (Projectable) stack.fuzzy(projectable);
    }

    public Projectable generatePredicateExistsRelatedSubquery(){
        ProjectableState state = new ProjectableState(this, true, false, false, true);
        Projectable projectable = newRandomlyProjectable(state);
        projectable.setConfig(Rule.PREFER_RELATED,true);
        return (Projectable) stack.fuzzy(projectable);
    }

    public Projectable generatePredicateInSubquery(){
        return generatePredicateInSubquery(Collections.emptyList());
    }

    public Projectable generatePredicateInRelatedSubquery(){
        return generatePredicateInRelatedSubquery(Collections.emptyList());
    }

    public Projectable generatePredicateInSubquery(List<TypeTag> limitations){
        ProjectableState state = new ProjectableState(this, false, false, false, true);
        Projectable projectable = newRandomlyProjectable(state);
        projectable.withProjectTypeLimitation(limitations);
        return (Projectable) stack.fuzzy(projectable);
    }

    public Projectable generatePredicateInRelatedSubquery(List<TypeTag> limitations){
        ProjectableState state = new ProjectableState(this, false, false, false, true);
        Projectable projectable = newRandomlyProjectable(state);
        projectable.setConfig(Rule.PREFER_RELATED,true);
        projectable.withProjectTypeLimitation(limitations);
        return (Projectable) stack.fuzzy(projectable);
    }

    private Projectable newRandomlyProjectable(){
        return newRandomlyProjectable(new ProjectableState(this, false, false, false, false));
    }

    private Projectable newRandomlyProjectable(ProjectableState state){
        final int PValues = state.getValuesProb();
        final int PSetop = state.getSetopProb();
        final int PClause = state.getClauseProb();
        final int total = PValues + PSetop + PClause;
        final int probEdge = total > 100 ? 2 * total : 100;

        if(total >= 100){
            logger.error("Accept an invalid probability distribution(total >= 100)." +
                    String.format("PValues: %d, PSetop: %d, PClause: %d", PValues, PSetop, PClause));
            DebugUtils.recordLocalFrameInfo4DebugInLog(logger);
        }

        final int randomValue = RandomUtils.randomIntFromRange(0, probEdge);
        if(PValues > randomValue){
            return new ValuesStatement(this);
        } else if (PSetop > randomValue - PValues) {
            return new SelectSetopStatement(this);
        } else if (PClause > randomValue - PValues - PSetop) {
            return new SelectClauseStatement(this);
        }else {
            if(!state.rejectedSimple && RandomUtils.probability(10)){
                return new SelectSimpleStatement(this);
            }
            return new SelectNormalStatement(this);
        }
    }

    public static class ProjectableState{
        private final RuntimeConfiguration config;
        private final boolean rejectedValues;
        private final boolean rejectedSimple;
        private final boolean rejectedSetop;
        private final boolean rejectedClause;

        public ProjectableState(
                SQLGeneratorContext context,
                boolean rejectedValues,
                boolean rejectedSimple,
                boolean rejectedSetop,
                boolean rejectedClause
        ){
            SQLStatement parent = context.stack().peek();
            this.rejectedValues = rejectedValues;
            this.rejectedSimple = rejectedSimple;
            this.rejectedClause = rejectedClause;
            if(parent == null){
                this.config = context.provider.newRuntimeConfiguration();
                this.rejectedSetop = rejectedSetop;
            }else {
                this.config = parent.getConfig();
                this.rejectedSetop = rejectedSetop || !parent.enableSetop();
            }
        }

        private int getValuesProb(){
            return rejectedValues ? config.getInt(Conf.VALUES_STATEMENT_AS_SUBQUERY_PROBABILITY) : 0;
        }

        private int getSetopProb(){
            return rejectedSetop ? config.getInt(Conf.SETOP_STATEMENT_AS_SUBQUERY_PROBABILITY) : 0;
        }

        private int getClauseProb(){
            return rejectedClause ? config.getInt(Conf.PURE_SELECT_CLAUSE_AS_SUBQUERY_PROBABILITY):0;
        }
    }



}
