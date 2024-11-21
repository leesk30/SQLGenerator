package org.lee.context;

import org.lee.common.Assertion;
import org.lee.common.NamedLoggers;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.enumeration.Conf;
import org.lee.common.enumeration.Rule;
import org.lee.common.generator.Generator;
import org.lee.common.utils.DebugUtils;
import org.lee.common.utils.RandomUtils;
import org.lee.sql.statement.Projectable;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.statement.select.*;
import org.lee.sql.statement.values.ValuesStatement;
import org.lee.sql.type.TypeTag;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class RecursiveProjectableGenerator implements Generator<Projectable> {
    private final static Logger LOGGER = NamedLoggers.getCoreLogger(RecursiveProjectableGenerator.class);

    private final SQLGeneratorContext context;
    protected RecursiveProjectableGenerator(SQLGeneratorContext context){
        this.context = context;
    }

    protected SQLGeneratorFrame createFrame(Supplier<SQLStatement> statement){
        SQLGeneratorFrame frame = new SQLGeneratorFrame(context, statement);
        frame.fuzz();
        return frame;
    }

    public SelectStatement generateSelect(){
        Supplier<SQLStatement> supplier = () -> getStatementBySelectType(null);
        SQLGeneratorFrame frame = createFrame(supplier);
        return (SelectStatement) frame.statement();
    }

    public Projectable generateProjectable(){
        Supplier<SQLStatement> supplier = this::newRandomlyProjectable;
        SQLGeneratorFrame frame = createFrame(supplier);
        return (Projectable) frame.statement();
    }

    public Projectable generateProjectable(List<TypeTag> limitations){
        Supplier<SQLStatement> supplier = () -> {
            Projectable statement = newRandomlyProjectable();
            if(!limitations.isEmpty()){
                statement.withProjectTypeLimitation(limitations);
            }
            return statement;
        };
        SQLGeneratorFrame frame = createFrame(supplier);
        return (Projectable) frame.statement();
    }

    public SelectStatement generateSelect(SelectType selectType){
        Supplier<SQLStatement> supplier = () -> getStatementBySelectType(selectType);
        SQLGeneratorFrame frame = createFrame(supplier);
        return (SelectStatement) frame.statement();
    }

    protected SelectStatement getStatementBySelectType(SelectType selectType){
        SQLStatement parent = context.currentFrame().previousStatement();

        if(selectType == null){
            if(parent != null){
                if(parent.enableSetop() && parent.probability(Conf.SETOP_STATEMENT_AS_SUBQUERY_PROBABILITY)){
                    selectType = SelectType.setop;
                }else {
                    selectType = RandomUtils.randomlyChooseFrom(SelectType.ALL);
                }
            } else {
                RuntimeConfiguration configuration = context.getConfigProvider().newRuntimeConfiguration();
                if(configuration.confirm(Rule.REWRITER_REORDER)){
                    selectType = RandomUtils.probability(5) ? SelectType.setop : SelectType.normal;
                }else {
                    selectType = RandomUtils.randomlyChooseFrom(SelectType.ALL);
                }
            }
        }

        switch (selectType){
            case normal:
                return new SelectNormalStatement(context);
            case setop:
                return new SelectSetopStatement(context);
            case clause:
                return new SelectClauseStatement(context);
            case simple:
                return new SelectSimpleStatement(context);
            default:
                throw new RuntimeException("Unexpected token");
        }
    }

    public Projectable generateScalarSubquery(TypeTag typeTag){
        final ProjectableState state = new ProjectableState(false, true, false, false);
        Supplier<SQLStatement> supplier = () -> {
            Projectable projectable = newRandomlyProjectable(state);
            Assertion.requiredFalse(projectable instanceof SelectSimpleStatement);
            projectable.withProjectTypeLimitation(Collections.singletonList(typeTag));
            projectable.setConfig(Rule.REQUIRE_SCALA,true);
            return projectable;
        };
        SQLGeneratorFrame frame = createFrame(supplier);
        return (Projectable) frame.statement();
    }

    public Projectable generateRelatedScalarSubquery(TypeTag typeTag){
        final ProjectableState state = new ProjectableState(false, true, false, false);
        Supplier<SQLStatement> supplier = () -> {
            Projectable projectable = newRandomlyProjectable(state);
            Assertion.requiredFalse(projectable instanceof SelectSimpleStatement);
            projectable.withProjectTypeLimitation(Collections.singletonList(typeTag));
            projectable.setConfig(Rule.REQUIRE_SCALA,true);
            projectable.setConfig(Rule.PREFER_RELATED,true);
            return projectable;
        };
        SQLGeneratorFrame frame = createFrame(supplier);
        return (Projectable) frame.statement();
    }

    public Projectable generatePredicateExistsSubquery(){
        final ProjectableState state = new ProjectableState(true, false, false, true);
        Supplier<SQLStatement> supplier = () -> newRandomlyProjectable(state);
        SQLGeneratorFrame frame = createFrame(supplier);
        return (Projectable) frame.statement();
    }

    public Projectable generatePredicateExistsRelatedSubquery(){
        final ProjectableState state = new ProjectableState(true, false, false, true);
        Supplier<SQLStatement> supplier = () -> {
            Projectable projectable = newRandomlyProjectable(state);
            projectable.setConfig(Rule.PREFER_RELATED,true);
            return projectable;
        };
        SQLGeneratorFrame frame = createFrame(supplier);
        return (Projectable) frame.statement();
    }

    public Projectable generatePredicateInSubquery(){
        return generatePredicateInSubquery(Collections.emptyList());
    }

    public Projectable generatePredicateInRelatedSubquery(){
        return generatePredicateInRelatedSubquery(Collections.emptyList());
    }

    public Projectable generatePredicateInSubquery(List<TypeTag> limitations){
        final ProjectableState state = new ProjectableState(false, false, false, true);
        Supplier<SQLStatement> supplier = () -> {
            Projectable projectable = newRandomlyProjectable(state);
            projectable.withProjectTypeLimitation(limitations);
            return projectable;
        };
        SQLGeneratorFrame frame = createFrame(supplier);
        return (Projectable) frame.statement();
    }

    public Projectable generatePredicateInRelatedSubquery(List<TypeTag> limitations){
        final ProjectableState state = new ProjectableState(false, false, false, true);
        Supplier<SQLStatement> supplier = () -> {
            Projectable projectable = newRandomlyProjectable(state);
            projectable.setConfig(Rule.PREFER_RELATED,true);
            projectable.withProjectTypeLimitation(limitations);
            return projectable;
        };
        SQLGeneratorFrame frame = createFrame(supplier);
        return (Projectable) frame.statement();
    }

    private Projectable newRandomlyProjectable(){
        return newRandomlyProjectable(new ProjectableState(false, false, false, false));
    }

    private Projectable newRandomlyProjectable(ProjectableState state){
        final int PValues = state.getValuesProb();
        final int PSetop = state.getSetopProb();
        final int PClause = state.getClauseProb();
        final int total = PValues + PSetop + PClause;
        final int probEdge = total > 100 ? 2 * total : 100;

        if(total >= 100){
            LOGGER.error("Accept an invalid probability distribution(total >= 100)." +
                    String.format("PValues: %d, PSetop: %d, PClause: %d", PValues, PSetop, PClause));
        }

        final int randomValue = RandomUtils.randomIntFromRange(0, probEdge);
        if(PValues > randomValue){
            return new ValuesStatement(this.context);
        } else if (PSetop > randomValue - PValues) {
            return new SelectSetopStatement(this.context);
        } else if (PClause > randomValue - PValues - PSetop) {
            return new SelectClauseStatement(this.context);
        }else {
            if(!state.rejectedSimple && RandomUtils.probability(10)){
                return new SelectSimpleStatement(this.context);
            }
            return new SelectNormalStatement(this.context);
        }
    }

    @Override
    public Projectable generate() {
        return generateSelect();
    }

    public class ProjectableState{
        private final RuntimeConfiguration config;
        private final boolean rejectedValues;
        private final boolean rejectedSimple;
        private final boolean rejectedSetop;
        private final boolean rejectedClause;

        public ProjectableState(
                boolean rejectedValues,
                boolean rejectedSimple,
                boolean rejectedSetop,
                boolean rejectedClause
        ){
            SQLStatement parent = context.currentFrame().previousStatement();
            this.rejectedValues = rejectedValues;
            this.rejectedSimple = rejectedSimple;
            this.rejectedClause = rejectedClause;
            if(parent == null){
                this.config = context.getConfigProvider().newRuntimeConfiguration();
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
