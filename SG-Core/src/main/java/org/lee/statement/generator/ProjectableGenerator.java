package org.lee.statement.generator;

import org.lee.base.Generator;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.statement.ValuesStatement;
import org.lee.statement.select.SelectClauseStatement;
import org.lee.statement.select.SelectNormalStatement;
import org.lee.statement.select.SelectSetopStatement;
import org.lee.statement.select.SelectSimpleStatement;
import org.lee.statement.support.Projectable;
import org.lee.statement.support.SQLStatement;
import org.lee.type.TypeTag;

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
        final int PValues = parent.getConfig().getInt(Conf.VALUES_STATEMENT_AS_SUBQUERY_PROBABILITY);
        final int PSetop = (parent.enableSetop() ? parent.getConfig().getInt(Conf.SETOP_STATEMENT_AS_SUBQUERY_PROBABILITY) : 0);
        final int PClause = parent.getConfig().getInt(Conf.PURE_SELECT_CLAUSE_AS_SUBQUERY_PROBABILITY);
        final int total = PValues + PSetop + PClause;
        final int probEdge = total > 100 ? 2 * total : 100;
        if(total >= 100){
            parent.getLogger().error("Accept an invalid probability distribution(total >= 100)." +
                    String.format("PValues: %d, PSetop: %d, PClause: %d", PValues, PSetop, PClause));
            Utility.recordLocalFrameInfo4DebugInLog(parent.getLogger());
        }

        final int randomValue = Utility.randomIntFromRange(0, probEdge);
        if(PValues > randomValue){
            return new ValuesStatement(parent);
        } else if (PSetop > randomValue - PValues) {
            return new SelectSetopStatement(parent);
        } else if (PClause > randomValue - PValues - PSetop) {
            return new SelectClauseStatement(parent);
        }else {
            if(parent.probability(10)){
                return new SelectSimpleStatement(parent);
            }
            return new SelectNormalStatement(parent);
        }
    }

}
