package org.lee.expression.basic;

import org.lee.base.Generator;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.common.global.SymbolTable;
import org.lee.entry.scalar.Scalar;
import org.lee.portal.SQLGeneratorContext;
import org.lee.expression.Expression;
import org.lee.expression.common.Location;
import org.lee.expression.statistic.GeneratorStatistic;
import org.lee.statement.select.SelectSimpleStatement;
import org.lee.statement.support.Projectable;
import org.lee.statement.support.ProjectableGenerator;
import org.lee.statement.support.SQLStatement;
import org.lee.statement.support.SQLStatementChildren;
import org.lee.symbol.Symbol;
import org.lee.type.TypeTag;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;

public interface IExpressionGenerator<T extends Expression>
        extends
        Generator<T>,
        SQLStatementChildren {

    interface ExpressionGeneratorChildren {
        IExpressionGenerator<?> getParent();
    }

    GeneratorStatistic getStatistic();
    Location getExpressionLocation();

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
        final int prob = Utility.randomIntFromRange(0, 100);
        if(enableScalarSubquery && prob < 1){
            return getScalarSubquery(typeTag);
        }
        return getLiteral(typeTag);
    }

    default  Scalar getContextSensitiveScalar(TypeTag typeTag){
        final SQLStatement statement = retrieveParent();
        final boolean enableScalarSubquery = statement == null || statement.enableSubquery();
        final int prob = Utility.randomIntFromRange(0, 100);
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
        Projectable projectable = ProjectableGenerator.newPreparedScalarProjectable(retrieveParent());
        Assertion.requiredFalse(projectable instanceof SelectSimpleStatement);
        projectable.withProjectTypeLimitation(Collections.singletonList(typeTag));
        projectable.setConfig(Rule.REQUIRE_SCALA,true);
        projectable.fuzz();
        return projectable.toScalar();
    }

    default Scalar getScalarSubquery(){
        return getScalarSubquery(TypeTag.randomGenerateTarget());
    }

    default Scalar getRelatedScalarSubquery(TypeTag typeTag){
        Projectable projectable = ProjectableGenerator.newPreparedScalarProjectable(retrieveParent());
        Assertion.requiredFalse(projectable instanceof SelectSimpleStatement);
        projectable.withProjectTypeLimitation(Collections.singletonList(typeTag));
        projectable.setConfig(Rule.REQUIRE_SCALA,true);
        projectable.setConfig(Rule.PREFER_RELATED,true);
        projectable.fuzz();
        return projectable.toScalar();
    }

    default Scalar getRelatedScalarSubquery(){
        return getRelatedScalarSubquery(TypeTag.randomGenerateScalarTarget());
    }

    default Scalar getPseudo(){
        // todo
        return null;
    }

    default Expression cast(final Expression expression, final TypeTag target){
        final SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        final int maxCastingDepth = getConfig().getInt(Conf.MAX_CASTING_RECURSION_DEPTH);
        final List<TypeTag> paths = symbolTable.findCasterPath(expression.getType(), target, maxCastingDepth);
        final Logger logger = getLogger();
        if(paths.isEmpty()){
            logger.error(String.format("Cannot find any caster for '%s' to '%s'. Fallback to generate an context free scalar", expression.getType(), target));
            return getContextFreeScalar(target).toExpression();
        }
        Expression currentExpression = expression;
        for(TypeTag next: paths){
            Symbol symbol = Utility.randomlyChooseFrom(symbolTable.getCaster(currentExpression.getType(), next));
            Assertion.requiredNonNull(symbol); // impossible
            currentExpression = new Expression(symbol).newChild(currentExpression);
        }
        Assertion.requiredTrue(currentExpression.getType() == target);
        return currentExpression;
    }

    default Scalar getLiteral(TypeTag typeTag){
        final SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();

        return typeTag.asMapped().generate();
    }

    default Scalar getLiteral(){
        return getLiteral(Utility.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
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