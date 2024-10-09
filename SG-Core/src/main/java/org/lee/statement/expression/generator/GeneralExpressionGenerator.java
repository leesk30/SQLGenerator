package org.lee.statement.expression.generator;

import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.SQLStatement;
import org.lee.statement.expression.Expression;
import org.lee.symbol.Aggregation;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class GeneralExpressionGenerator
        extends UnrelatedGenerator<Expression>
        implements ExpressionGenerator {


    public enum SymbolType{
        aggregate,
        function,
        operator,
    }

    public static final List<Scalar> unmodifiableEmptyList = Collections.emptyList();

    public static GeneralExpressionGenerator emptyCandidateExpressionGenerator(SQLStatement statement){
        return new GeneralExpressionGenerator(statement, unmodifiableEmptyList);
    }

    public GeneralExpressionGenerator(SQLStatement statement, Scalar ... scalars){
        super(statement, scalars);
    }

    public GeneralExpressionGenerator(SQLStatement statement, List<? extends Scalar> scalars){
        super(statement, scalars);
    }

    public List<Signature> getCandidateSignatures(TypeTag root){
        List<Signature> copiedSignature = Utility.copyList(finder.getFunctionByReturn(root));
        List<Signature> operators = finder.getOperatorByReturn(root);
        if(operators != null){
            copiedSignature.addAll(operators);
        }
        return copiedSignature;
    }

    public Expression getCompleteExpressionTree(TypeTag root, int recursionDepth, boolean enableAggregate){
        final List<Signature> copiedSignature = getCandidateSignatures(root);
        if(enableAggregate && probability(Conf.EXPRESSION_APPEND_AGGREGATION_PROB)){
            List<Signature> aggregators = finder.getAggregateByReturn(root);
            final boolean inScalarRequiredAggregation = confirm(Rule.REQUIRE_SCALA) && confirm(Rule.SCALAR_FORCE_USING_AGGREGATION);
            if(inScalarRequiredAggregation){
                // do nothing
                logger.debug("The statement requires a scalar, and the scalar should be wrapped with an aggregate. " +
                        "To stop retrieving aggregation, we stop the aggregator search here.");
            }else if(aggregators != null){
                copiedSignature.addAll(aggregators);
            }
        }
        Collections.shuffle(copiedSignature);
        while (!copiedSignature.isEmpty()){
            final Signature signature = Utility.pop(copiedSignature);
            if(signature == null){
                return fallback(root);
            }

            // empty arguments function is a terminate node.
            if(signature.getArgumentsTypes().isEmpty()){
                return new Expression(signature);
            }

            // if all candidate are consumed. Fallback directly
            if(candidateList.isEmpty()){
                candidateList.addAll(replicated);
            }

            if(suitable(signature)){
                return stopGrowth(signature);
            }else {
                if(preferRecursion(signature, recursionDepth)){
                    return growth(signature, recursionDepth+1);
                }
                // else backtrace
            }
        }
        return fallback(root);
    }

    private boolean suitable(Signature signature){
//        System.out.println(statistic.suitableFactorProb());
        return Utility.probability(statistic.suitableFactorProb(signature));
    }

    private boolean preferRecursion(Signature signature, int currentDepth){
        // more suitable there is no more necessary to fallback
        if(currentDepth > config.getInt(Conf.MAX_EXPRESSION_RECURSION_DEPTH)){
            return false;
        }
        // if suitable enough, don't recursion
        if(probability(statistic.suitableFactorProb(signature))){
            return false;
        }
        final int adaptiveExpressionProbability = config.getInt(Conf.EXPRESSION_RECURSION_PROBABILITY) / (currentDepth == 0? 1: currentDepth);
        return probability(adaptiveExpressionProbability);
    }

    private Expression stopGrowth(Signature signature){
        final Expression expression = new Expression(signature);
        final Scalar[] scalars = statistic.findMatchedForSignature(signature);
        final List<Expression> children = new ArrayList<>();
        IntStream.range(0, signature.argsNum()).sequential().forEachOrdered(
                i -> {
                    final TypeTag targetType = signature.getArgumentsTypes().get(i);
                    if(scalars[i] != null){
                        children.add(scalars[i].toExpression());
                    }else {
                        children.add(getLiteral(targetType).toExpression());
                    }
                }
        );
        children.forEach(expression::newChild);
        return expression;
    }


    private Expression growth(Signature signature, int incrementalDepth){
        final Expression expression = new Expression(signature);
        final Scalar[] scalars = statistic.findMatchedForSignature(signature);
        final List<Expression> children = new ArrayList<>();
        final boolean childrenEnableAggregation = !(signature instanceof Aggregation);
//        final int[] nullOfIndex = IntStream.range(0, scalars.length).parallel().filter(Objects::isNull).toArray();
        // The arguments must be ordered.
        IntStream.range(0, signature.argsNum()).sequential().forEachOrdered(
                i -> {
                    final TypeTag targetType = signature.getArgumentsTypes().get(i);
                    if(scalars[i] == null || preferRecursion(signature, incrementalDepth)){
                        children.add(generate(targetType, incrementalDepth, childrenEnableAggregation));
                    }else {
                        children.add(scalars[i].toExpression());
                    }
//                    children.add(sub);
                }
        );
        children.forEach(expression::newChild);
        return expression;
    }


    @Override
    public Expression generate() {
        return generate(Utility.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE), 0);
    }

    @Override
    public Expression generate(TypeTag required) {
        return generate(required, 0, true);
    }

    @Override
    public Expression fallback(TypeTag required) {
        Scalar requiredScalar = statistic.findMatchedForTargetType(required);
        if(requiredScalar != null){
            return requiredScalar.toExpression();
        }

        return getLiteral(required).toExpression();
    }

    @Override
    public Expression fallback(){
        Scalar anyScalar = statistic.findAny();
        if(anyScalar != null){
            return anyScalar.toExpression();
        }
        return getLiteral().toExpression();
    }


    public Expression generate(TypeTag required, int recursionDepth) {
        return generate(required, recursionDepth, true);
    }

    public Expression generate(TypeTag required, int recursionDepth, boolean enableAggregate) {
        Expression expression = getCompleteExpressionTree(required, recursionDepth, enableAggregate);
        if(expression.isComplete() && expression.getType() == required){
            return expression;
        }else {
            logger.error("The Expression is not Required or Incomplete");
            logger.debug("IsComplete: " + expression.isComplete());
            logger.debug("IsRequired: " + (expression.getType() == required));
        }
        return fallback(required);
    }

}
