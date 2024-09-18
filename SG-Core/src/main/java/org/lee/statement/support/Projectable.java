package org.lee.statement.support;

import org.lee.common.DevTempConf;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.fuzzer.Fuzzer;
import org.lee.node.Node;
import org.lee.statement.SQLStatement;
import org.lee.statement.ValuesStatement;
import org.lee.statement.select.SelectClauseStatement;
import org.lee.statement.select.SelectNormalStatement;
import org.lee.statement.select.SelectSetopStatement;
import org.lee.statement.select.SelectSimpleStatement;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

import java.util.List;

public interface Projectable extends Node, Fuzzer {
    List<TargetEntry> project();
    RangeTableEntry toRelation();

    boolean isScalar();

    default int width(){
        return project().size();
    }

    void withProjectTypeLimitation(List<TypeTag> limitation);
    List<TypeTag> getProjectTypeLimitation();

    default SQLStatement asStatement(){
        return (SQLStatement) this;
    }

    static Projectable newRandomlyProjectable(SQLStatement parent){
        if(FuzzUtil.probability(DevTempConf.VALUES_STATEMENT_AS_SUBQUERY_PROBABILITY)){
            return new ValuesStatement(parent);
        }
        if(FuzzUtil.probability(DevTempConf.SETOP_STATEMENT_AS_SUBQUERY_PROBABILITY)){
            return new SelectSetopStatement(parent);
        }
        if(FuzzUtil.probability(DevTempConf.PURE_SELECT_AS_SUBQUERY_PROBABILITY)){
            return new SelectClauseStatement(parent);
        }
        if(FuzzUtil.probability(10)){
            return new SelectSimpleStatement(parent);
        }
        return new SelectNormalStatement(parent);
    }
}
