package org.lee.statement.support;

import org.lee.base.Generator;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.portal.SQLGeneratorContext;
import org.lee.statement.ValuesStatement;
import org.lee.statement.select.SelectClauseStatement;
import org.lee.statement.select.SelectNormalStatement;
import org.lee.statement.select.SelectSetopStatement;
import org.lee.statement.select.SelectSimpleStatement;
import org.lee.type.TypeTag;
import org.slf4j.Logger;

import java.util.List;

public final class ProjectableGenerator implements Generator<Projectable>, SQLStatementChildren {
    private final SQLStatement parent;
    private final RuntimeConfiguration config;
    private final Logger logger;
    private final boolean rejectedValues;
    private final boolean rejectedSimple;
    private final boolean rejectedSetop;
    private final boolean rejectedClause;
    public static final ProjectableGenerator emptyParentGenerator = new ProjectableGenerator();

    public ProjectableGenerator(){
        this(null);
    }

    public ProjectableGenerator(SQLStatement parent){
        this(parent, false, false, false, false);
    }

    public ProjectableGenerator(
            SQLStatement parent,
            boolean rejectedValues,
            boolean rejectedSimple,
            boolean rejectedSetop,
            boolean rejectedClause
    ){
        this.parent = parent;
        this.rejectedValues = rejectedValues;
        this.rejectedSimple = rejectedSimple;
        this.rejectedClause = rejectedClause;
        if(parent == null){
            this.config = SQLGeneratorContext.getCurrentConfigProvider().newRuntimeConfiguration();
            this.rejectedSetop = rejectedSetop;
            this.logger = SQLGeneratorContext.getCurrentLogger();
        }else {
            this.config = parent.getConfig();
            this.rejectedSetop = rejectedSetop || !parent.enableSetop();
            this.logger = parent.getLogger();
        }
    }

    @Override
    public Projectable generate(){
        Projectable statement = newRandomlyProjectable(parent);
        statement.fuzz();
        return statement;
    }

    public Projectable generate(List<TypeTag> limitations){
        Projectable statement = newRandomlyProjectable(parent);
        if(!limitations.isEmpty()){
            statement.withProjectTypeLimitation(limitations);
        }
        statement.fuzz();
        return statement;
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

    private Projectable newRandomlyProjectable(SQLStatement parent){
        final int PValues = getValuesProb();
        final int PSetop = getSetopProb();
        final int PClause = getClauseProb();
        final int total = PValues + PSetop + PClause;
        final int probEdge = total > 100 ? 2 * total : 100;

        if(total >= 100){
            logger.error("Accept an invalid probability distribution(total >= 100)." +
                    String.format("PValues: %d, PSetop: %d, PClause: %d", PValues, PSetop, PClause));
            Utility.recordLocalFrameInfo4DebugInLog(logger);
        }

        final int randomValue = Utility.randomIntFromRange(0, probEdge);
        if(PValues > randomValue){
            return new ValuesStatement(parent);
        } else if (PSetop > randomValue - PValues) {
            return new SelectSetopStatement(parent);
        } else if (PClause > randomValue - PValues - PSetop) {
            return new SelectClauseStatement(parent);
        }else {
            if(!rejectedSimple && Utility.probability(10)){
                return new SelectSimpleStatement(parent);
            }
            return new SelectNormalStatement(parent);
        }
    }

    public static Projectable newPreparedProjectable(SQLStatement parent){
        return new ProjectableGenerator(parent).newRandomlyProjectable(parent);
    }

    public static Projectable newPreparedScalarProjectable(SQLStatement parent){
        ProjectableGenerator generator = new ProjectableGenerator(parent, false, true, false, false);
        return generator.newRandomlyProjectable(parent);
    }

    public static Projectable newExistsPredicateProjectable(SQLStatement parent){
        // An exists statement shouldn't have the raw values
        ProjectableGenerator generator = new ProjectableGenerator(parent, true, false, false, true);
        return generator.newRandomlyProjectable(parent);
    }

    @Override
    public SQLStatement retrieveParent() {
        return parent;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public RuntimeConfiguration getConfig() {
        return config;
    }
}