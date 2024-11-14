package org.lee.generator.expression;

import org.lee.common.exception.InternalError;
import org.lee.generator.WeightedGenerator;
import org.lee.generator.expression.basic.AbstractExpressionGenerator;
import org.lee.generator.expression.basic.QualificationGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.generator.expression.statistic.GeneratorStatistic;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;

import java.lang.reflect.Method;
import java.util.List;

public class WhereQualificationGenerator
        extends AbstractExpressionGenerator<Qualification>
        implements QualificationGenerator {

    private final WeightedGenerator<Method, Qualification> invoker = WeightedGenerator.getInvoker(this);
    private GeneratorStatistic statistic;
    public WhereQualificationGenerator(SQLStatement statement, List<? extends Scalar> expresssionList) {
        super(statement);
        statistic = GeneratorStatistic.create(expresssionList);
        init();
    }

    private void init(){
        Class<? extends WhereQualificationGenerator> cls = this.getClass();
        try {
            invoker.add(cls.getMethod("predicateScalarAndScalar"), 80);
            invoker.add(cls.getMethod("predicateFieldAndLiteral"), 120);
            invoker.add(cls.getMethod("predicateBetweenAnd"), 10);
            invoker.add(cls.getMethod("predicateIsNull"), 2);
            if(statement.enableSubquery()){
                invoker.add(cls.getMethod("predicateSubqueryExists"), 2);
                invoker.add(cls.getMethod("predicateInSubquery"), 3);
            }
        } catch (NoSuchMethodException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public Qualification fallback() {
        return predicateFieldAndLiteral();
    }

    @Override
    public Qualification generate(){
        Qualification qualification = invoker.generate();
        if(qualification == null){
            return fallback();
        }
        return qualification;
    }

    @Override
    public GeneratorStatistic getStatistic() {
        return statistic;
    }

    @Override
    public ExpressionLocation getExpressionLocation() {
        return ExpressionLocation.where;
    }
}
