package org.lee.generator.expression;

import org.lee.common.exception.InternalError;
import org.lee.generator.WeightedGenerator;
import org.lee.generator.expression.basic.QualificationGenerator;
import org.lee.generator.expression.basic.UnrelatedGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.type.TypeTag;

import java.lang.reflect.Method;
import java.util.List;

public class WhereQualificationGenerator
        extends UnrelatedGenerator<Qualification>
        implements QualificationGenerator {

    private final WeightedGenerator<Method, Qualification> invoker = WeightedGenerator.getInvoker(this);
    public WhereQualificationGenerator(SQLStatement statement, List<? extends Scalar> expresssionList) {
        super(statement, expresssionList);
        init();
    }

    private void init(){
        Class<? extends WhereQualificationGenerator> cls = this.getClass();
        try {
            invoker.add(cls.getMethod("predicateScalarAndScalar"), 80);
            invoker.add(cls.getMethod("predicateFieldAndLiteral"), 120);
            invoker.add(cls.getMethod("predicateBetweenAnd"), 10);
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
    final public Qualification generate(TypeTag require) {
        return generate();
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
    public ExpressionLocation getExpressionLocation() {
        return ExpressionLocation.where;
    }
}
