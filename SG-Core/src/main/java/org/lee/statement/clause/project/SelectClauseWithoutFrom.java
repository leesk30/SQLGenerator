package org.lee.statement.clause.project;

import org.lee.statement.generator.GeneralExpressionGenerator;
import org.lee.statement.select.SelectClauseStatement;
import org.lee.common.util.FuzzUtil;

import java.util.stream.IntStream;

public class SelectClauseWithoutFrom extends SelectClause{
    public SelectClauseWithoutFrom(SelectClauseStatement statement) {
        super(statement);
    }

    @Override
    public final void fuzz(){
        SelectClauseStatement statement = (SelectClauseStatement) this.statement;
        final GeneralExpressionGenerator generator = GeneralExpressionGenerator.emptyCandidateExpressionGenerator(config);
        if(statement.getProjectTypeLimitation().isEmpty()){
            IntStream.range(0, FuzzUtil.randomIntFromRange(1, 7)).parallel().forEach(
                    i -> {
                        if(FuzzUtil.probability(20)){
                            processEntry(generator.getLiteral());
                        }else {
                            processEntry(generator.generate());
                        }
                    }
            );
        }else {
            statement.getProjectTypeLimitation().stream().parallel().forEachOrdered(
                    requiredType -> {
                        if(FuzzUtil.probability(20)){
                            processEntry(generator.generate(requiredType));
                        }else {
                            processEntry(generator.generate(requiredType));
                        }
                    }
            );
        }
    }
}
