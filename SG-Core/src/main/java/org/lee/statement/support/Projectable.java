package org.lee.statement.support;

import org.lee.common.config.Conf;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.node.Node;
import org.lee.statement.SQLStatement;
import org.lee.statement.ValuesStatement;
import org.lee.statement.clause.Clause;
import org.lee.statement.select.SelectClauseStatement;
import org.lee.statement.select.SelectNormalStatement;
import org.lee.statement.select.SelectSetopStatement;
import org.lee.statement.select.SelectSimpleStatement;
import org.lee.type.TypeTag;
import org.lee.common.util.FuzzUtil;

import java.util.List;

public interface Projectable extends Statement<Clause<? extends Node>> {
    List<TargetEntry> project();
    RangeTableEntry toRelation();

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

    default Statement<Clause<? extends Node>> asStatement(){
        return this;
    }

    static Projectable newRandomlyProjectable(SQLStatement parent){
        if(parent.getConfig().probability(Conf.VALUES_STATEMENT_AS_SUBQUERY_PROBABILITY)){
            return new ValuesStatement(parent);
        }
        if(parent.getConfig().probability(Conf.SETOP_STATEMENT_AS_SUBQUERY_PROBABILITY)){
            return new SelectSetopStatement(parent);
        }
        if(parent.getConfig().probability(Conf.PURE_SELECT_AS_SUBQUERY_PROBABILITY)){
            return new SelectClauseStatement(parent);
        }
        if(FuzzUtil.probability(10)){
            return new SelectSimpleStatement(parent);
        }
        return new SelectNormalStatement(parent);
    }
}
