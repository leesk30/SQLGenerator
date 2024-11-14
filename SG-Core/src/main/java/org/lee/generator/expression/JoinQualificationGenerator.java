package org.lee.generator.expression;

import org.lee.common.exception.InternalError;
import org.lee.generator.WeightedGenerator;
import org.lee.generator.expression.basic.QualificationGenerator;
import org.lee.generator.expression.basic.RelatedGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.entry.RangeTableReference;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;

import java.lang.reflect.Method;

public class JoinQualificationGenerator
        extends RelatedGenerator<Qualification>
        implements QualificationGenerator {

    private final WeightedGenerator<Method, Qualification> invoker = WeightedGenerator.getInvoker(this);

    public JoinQualificationGenerator(SQLStatement statement, RangeTableReference left, RangeTableReference right){
        super(statement, left.getFieldReferences(), right.getFieldReferences());
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
    public ExpressionLocation getExpressionLocation() {
        return ExpressionLocation.join;
    }
}
