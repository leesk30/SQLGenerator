package org.lee.generator.expression;

import org.lee.common.exception.InternalError;
import org.lee.generator.WeightedGenerator;
import org.lee.generator.expression.basic.AbstractExpressionGenerator;
import org.lee.generator.expression.basic.QualificationGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.generator.expression.statistic.GeneratorStatistic;
import org.lee.sql.entry.RangeTableReference;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;

import java.lang.reflect.Method;

public class JoinQualificationGenerator
        extends AbstractExpressionGenerator<Qualification>
        implements QualificationGenerator {

    private final WeightedGenerator<Method, Qualification> invoker = WeightedGenerator.getInvoker(this);
    private final GeneratorStatistic statistic;

    public JoinQualificationGenerator(SQLStatement statement, RangeTableReference left, RangeTableReference right){
        super(statement);
        statistic = GeneratorStatistic.create(left.getFieldReferences(), right.getFieldReferences());
        init();
    }

    private void init(){
        Class<? extends JoinQualificationGenerator> cls = this.getClass();
        try {
            invoker.add(cls.getMethod("predicateScalarAndScalar"), 500);
            invoker.add(cls.getMethod("predicateFieldAndLiteral"), 4);
            invoker.add(cls.getMethod("predicateBetweenAnd"), 4);
            invoker.add(cls.getMethod("predicateIsNull"), 4);
            if(statement.enableSubquery() && probability(50)){
                invoker.add(cls.getMethod("predicateSubqueryExists"), 1);
                invoker.add(cls.getMethod("predicateInSubquery"), 1);
            }
        } catch (NoSuchMethodException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public Qualification generate() {
        Qualification qualification = invoker.generate();
        if(qualification == null){
            return fallback();
        }
        return qualification;
    }

    @Override
    public Qualification fallback() {
        return predicateFieldAndLiteral();
    }

    @Override
    public GeneratorStatistic getStatistic() {
        return statistic;
    }

    @Override
    public ExpressionLocation getExpressionLocation() {
        return ExpressionLocation.join;
    }
}
