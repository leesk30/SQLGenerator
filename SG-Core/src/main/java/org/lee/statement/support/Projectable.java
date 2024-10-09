package org.lee.statement.support;

import org.lee.base.Node;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.scalar.Scalar;
import org.lee.entry.scalar.ScalarSubquery;
import org.lee.statement.SQLStatement;
import org.lee.statement.ValuesStatement;
import org.lee.statement.clause.Clause;
import org.lee.statement.select.SelectClauseStatement;
import org.lee.statement.select.SelectNormalStatement;
import org.lee.statement.select.SelectSetopStatement;
import org.lee.statement.select.SelectSimpleStatement;
import org.lee.type.TypeTag;

import java.util.List;

public interface Projectable extends Statement<Clause<? extends Node>> {
    List<TargetEntry> project();
    RangeTableEntry toRelation();

    default Scalar toScalar(){
        return new ScalarSubquery(this);
    }

    boolean isWithLogicalParentheses();

    @Override
    default String getString(){
        if(this.isWithLogicalParentheses()){
            return LP + body() + RP;
        }
        if(((SQLStatement)this).isFinished()){
            return body() + ENDING;
        }
        return body();
    }

    boolean isScalar();

    default int width(){
        return project().size();
    }

    default String body(){
        return nodeArrayToString(SPACE, this.walk());
    }

    void withProjectTypeLimitation(List<TypeTag> limitation);
    List<TypeTag> getProjectTypeLimitation();

    default SQLStatement asStatement(){
        return (SQLStatement)this;
    }

    static Projectable newRandomlyProjectable(SQLStatement parent){
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
