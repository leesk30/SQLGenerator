package org.lee.statement.generator;

import org.lee.SQLGeneratorContext;
import org.lee.base.Generator;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.statement.ValuesStatement;
import org.lee.statement.select.SelectClauseStatement;
import org.lee.statement.select.SelectNormalStatement;
import org.lee.statement.select.SelectSetopStatement;
import org.lee.statement.select.SelectSimpleStatement;
import org.lee.statement.support.Projectable;
import org.lee.statement.support.SQLStatement;
import org.lee.type.TypeTag;
import org.slf4j.Logger;

import java.util.List;

public final class ProjectableGenerator implements Generator<Projectable> {
    private final SQLStatement parent;
    public static final ProjectableGenerator emptyParentGenerator = new ProjectableGenerator();

    public ProjectableGenerator(){
        this(null);
    }

    public ProjectableGenerator(SQLStatement parent){
        this.parent = parent;
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

    private Projectable newRandomlyProjectable(SQLStatement parent){
        final RuntimeConfiguration config;
        final boolean enableSetop;
        final Logger logger;
        if(parent == null){
            config = SQLGeneratorContext.getCurrentConfigProvider().newRuntimeConfiguration();
            enableSetop = true;
            logger = SQLGeneratorContext.getCurrentLogger();
        }else {
            config = parent.getConfig();
            enableSetop = parent.enableSetop();
            logger = parent.getLogger();
        }
        final int PValues = config.getInt(Conf.VALUES_STATEMENT_AS_SUBQUERY_PROBABILITY);
        final int PSetop = (enableSetop ? config.getInt(Conf.SETOP_STATEMENT_AS_SUBQUERY_PROBABILITY) : 0);
        final int PClause = config.getInt(Conf.PURE_SELECT_CLAUSE_AS_SUBQUERY_PROBABILITY);
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
            if(Utility.probability(10)){
                return new SelectSimpleStatement(parent);
            }
            return new SelectNormalStatement(parent);
        }
    }

}
