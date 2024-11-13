package org.lee.expression.generator;

import org.lee.base.Generator;
import org.lee.common.Utility;
import org.lee.common.exception.InternalError;
import org.lee.entry.scalar.Scalar;
import org.lee.expression.Qualification;
import org.lee.expression.basic.QualificationGenerator;
import org.lee.expression.basic.UnrelatedGenerator;
import org.lee.expression.common.Location;
import org.lee.statement.support.SQLStatement;
import org.lee.type.TypeTag;

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
    public Location getExpressionLocation() {
        return Location.where;
    }
}
