package org.lee.statement.syntax;

import org.lee.common.Parameter;
import org.lee.fuzzer.FuzzGenerator;
import org.lee.statement.SQLType;
import org.lee.statement.SQLStatement;
import org.lee.util.FuzzUtil;

public class SelectSyntax extends SQLSyntax implements FuzzGenerator {
    private boolean useAgg;
    private boolean useSetOp;
    public SelectSyntax(SQLType sqlType){
        super(sqlType);
    }

    @Override
    public void generate(SQLStatement currentStatement) {
        Parameter parameter = currentStatement.getContext().getParameter();

        if (sqlType != SQLType.setop && FuzzUtil.probability(parameter.getProbCTE())){
            enableCTE = true;
        }
    }

    @Override
    public void fuzzy(SQLStatement statement) {

    }

    @Override
    public void prepare(SQLStatement statement) {

    }

    @Override
    public void finish(SQLStatement statement) {

    }


}
