package org.lee.generator.expression.basic;

import org.lee.common.Assertion;
import org.lee.common.NamedLoggers;
import org.lee.common.enumeration.Conf;
import org.lee.common.generator.Generator;
import org.lee.common.utils.RandomUtils;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.generator.expression.statistic.GeneratorStatistic;
import org.lee.resource.SymbolTable;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.expression.IExpression;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.support.SQLStatementChildren;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.type.TypeTag;
import org.slf4j.Logger;

import java.util.List;

public interface IExpressionGenerator<T extends IExpression<Expression>>
        extends
        Generator<T>,
        SQLStatementChildren {

    Logger LOGGER = NamedLoggers.getCoreLogger(IExpressionGenerator.class);

    T fallback();

    GeneratorStatistic getStatistic();
    ExpressionLocation getExpressionLocation();

    default SymbolTable getSymbolTable(){
        return retrieveContext().getSymbolTable();
    }

    default Scalar getScalar(TypeTag typeTag){
        if(probability(50)){
            return getContextFreeScalar(typeTag);
        }
        return getContextSensitiveScalar(typeTag);
    }

    default Scalar getScalar(){
        return getScalar(TypeTag.randomGenerateTarget());
    }

    default Scalar getContextFreeScalar(TypeTag typeTag){
        final SQLStatement statement = retrieveParent();
        final boolean enableScalarSubquery = statement == null || statement.enableSubquery();
        final int prob = RandomUtils.randomIntFromRange(0, 100);
        if(enableScalarSubquery && prob < 1){
            return getScalarSubquery(typeTag);
        }
        return getLiteral(typeTag);
    }

    default  Scalar getContextSensitiveScalar(TypeTag typeTag){
        final SQLStatement statement = retrieveParent();
        final boolean enableScalarSubquery = statement == null || statement.enableSubquery();
        final int prob = RandomUtils.randomIntFromRange(0, 100);
        if(enableScalarSubquery && prob < 1){
            // context-sensitive generate a related scalar subquery
            return getRelatedScalarSubquery(typeTag);
        }
        Scalar scalar = getStatistic().findAny(typeTag);
        if(scalar != null){
            return scalar;
        }
        return getLiteral(typeTag);
    }

    default Scalar getScalarSubquery(TypeTag typeTag){
        return retrieveContext().generateScalarSubquery(typeTag).toScalar();
    }

    default Scalar getScalarSubquery(){
        // todo:
        return getScalarSubquery(TypeTag.randomGenerateTarget());
    }

    default Scalar getRelatedScalarSubquery(TypeTag typeTag){
        return retrieveContext().generateRelatedScalarSubquery(typeTag).toScalar();
    }

    default Scalar getRelatedScalarSubquery(){
        TypeTag target = RandomUtils.randomlyChooseFrom(getSymbolTable().getAllAggregateReturnType());
        return getRelatedScalarSubquery(target);
    }

    default Scalar getPseudo(){
        // todo
        return null;
    }

    default Expression cast(final Expression expression, final TypeTag target){
        final SymbolTable symbolTable = getSymbolTable();
        final int maxCastingDepth = getConfig().getInt(Conf.MAX_CASTING_RECURSION_DEPTH);
        final List<TypeTag> paths = symbolTable.findCasterPath(expression.getType(), target, maxCastingDepth);
        if(paths.isEmpty()){
            LOGGER.error(String.format("Cannot find any caster for '%s' to '%s'. Fallback to generate an context free scalar", expression.getType(), target));
            return getContextFreeScalar(target).toCompleteExpression();
        }
        Expression currentExpression = expression;
        for(TypeTag next: paths){
            Symbol symbol = RandomUtils.randomlyChooseFrom(symbolTable.getCaster(currentExpression.getType(), next));
            Assertion.requiredNonNull(symbol); // impossible
            currentExpression = new Expression(symbol).newChild(currentExpression);
        }
        Assertion.requiredTrue(currentExpression.getType() == target);
        return currentExpression;
    }

    default Scalar getLiteral(TypeTag typeTag){
        return typeTag.asMapped().generate();
    }

    default Scalar getLiteral(){
        return getLiteral(RandomUtils.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
    }

    default Scalar getLiteral(int partial){
        return getLiteral(TypeTag.randomGenerateTarget(), partial);
    }

    default Scalar getLiteral(TypeTag typeTag, int partial){
        return typeTag.asMapped().generate(partial);
    }

    default boolean isContainsType(List<? extends Scalar> scalarList, TypeTag check){
        return scalarList.stream()
                .map(Scalar::getType)
                .anyMatch(exprType -> exprType == check);
    }

    default int containsHowMany(List<? extends Scalar> scalarList, TypeTag check){
        if(scalarList.isEmpty()){
            return 0;
        }
        return (int)scalarList.stream()
                .map(Scalar::getType)
                .filter(type -> type == check)
                .count();
    }
}
