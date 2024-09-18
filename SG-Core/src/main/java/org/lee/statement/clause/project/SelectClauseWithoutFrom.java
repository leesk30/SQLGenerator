package org.lee.statement.clause.project;

import org.lee.entry.complex.TargetEntry;
import org.lee.fuzzer.expr.EmptyCandidateExpressionGenerator;
import org.lee.fuzzer.expr.GeneralExpressionGenerator;
import org.lee.statement.select.SelectClauseStatement;
import org.lee.statement.select.SelectStatement;
import org.lee.util.FuzzUtil;

import java.util.ConcurrentModificationException;
import java.util.stream.IntStream;

public class SelectClauseWithoutFrom extends SelectClause{
    public SelectClauseWithoutFrom(SelectClauseStatement statement) {
        super(statement);
    }

    @Override
    public final void fuzz(){
        SelectClauseStatement statement = (SelectClauseStatement) this.statement;
        final GeneralExpressionGenerator generator = GeneralExpressionGenerator.EmptyCandidateExpressionGenerator;
        if(statement.getProjectTypeLimitation().isEmpty()){
            IntStream.range(0, FuzzUtil.randomIntFromRange(1, 7)).parallel().forEach(
                    i -> {
                        TargetEntry entry = new TargetEntry(generator.generate());
                        entry.setAlias();
                        children.add(entry);
                    }
            );
        }else {
            statement.getProjectTypeLimitation().stream().parallel().forEachOrdered(
                    requiredType -> {
                        TargetEntry entry = new TargetEntry(generator.generate(requiredType));
                        entry.setAlias();
                        children.add(entry);
                    }
            );
        }
    }
}
