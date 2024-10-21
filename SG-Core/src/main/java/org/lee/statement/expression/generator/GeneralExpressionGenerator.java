package org.lee.statement.expression.generator;

import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.support.SQLStatement;
import org.lee.symbol.Aggregation;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneralExpressionGenerator
        extends UnrelatedGenerator<Expression>
        implements ExpressionGenerator {

    public enum SymbolType{
        aggregate,
        function,
        operator,
    }

    public static final List<Scalar> unmodifiableEmptyList = Collections.emptyList();
    private final boolean parentFlagEnableAggregate;

    public static GeneralExpressionGenerator emptyCandidateExpressionGenerator(SQLStatement statement){
        return new GeneralExpressionGenerator(false, statement, unmodifiableEmptyList);
    }

    public static GeneralExpressionGenerator emptyCandidateExpressionGenerator(boolean enableAggregation, SQLStatement statement){
        return new GeneralExpressionGenerator(enableAggregation, statement, unmodifiableEmptyList);
    }

    public GeneralExpressionGenerator(boolean enableAggregation, SQLStatement statement, Scalar ... scalars){
        super(statement, scalars);
        parentFlagEnableAggregate = enableAggregation;
    }

    public GeneralExpressionGenerator(boolean enableAggregation, SQLStatement statement, List<? extends Scalar> scalars){
        super(statement, scalars);
        parentFlagEnableAggregate = enableAggregation;
    }

    public List<Signature> getCandidateSignatures(TypeTag root, boolean childrenFlagEnableAggregate){
        final List<Signature> copiedSignature = Utility.copyList(finder.getFunctionByReturn(root));
        final List<Signature> operators = finder.getOperatorByReturn(root);

        if(operators != null){
            copiedSignature.addAll(operators);
        }
        // The parentFlag means whether the expression generator can enable aggregation.
        // The childrenFlag is an internal control flag which is used to avoid the nested aggregation.
        //  After a one aggregator generated, the children of this aggregated expression can not enable generate aggregator.

        // For example, when the parent flag is true, you probably generate an expression like `max(a)`,
        //  and for the sub-expression, we use the childrenFlag to disable aggregation for nested another aggregator.
        //  Otherwise, we may generate an `max(max(a))`. This is not allowed in mostly database engines.
        if(parentFlagEnableAggregate && childrenFlagEnableAggregate && probability(Conf.EXPRESSION_APPEND_AGGREGATION_PROB)){
            final List<Signature> aggregators = finder.getAggregateByReturn(root);
            if(aggregators != null){
                copiedSignature.addAll(aggregators);
            }
        }
        return copiedSignature;
    }

    public Expression getCompleteExpressionTree(TypeTag root, int recursionDepth, boolean childrenFlagEnableAggregate){
        final List<Signature> copiedSignature = getCandidateSignatures(root, childrenFlagEnableAggregate);
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
                // else backtrace to another symbol
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
        for (int i=0; i < signature.argsNum(); i++){
            final TypeTag targetType = signature.getArgumentsTypes().get(i);
            if(scalars[i] != null){
                children.add(scalars[i].toExpression());
            }else {
                children.add(getLiteral(targetType).toExpression());
            }
        }
        children.forEach(expression::newChild);
        return expression;
    }


    private Expression growth(Signature signature, int incrementalDepth){
        final Expression expression = new Expression(signature);
        final Scalar[] scalars = statistic.findMatchedForSignature(signature);
        final boolean childrenEnableAggregation = !(signature instanceof Aggregation);
        // The arguments must be ordered.
        for (int i=0; i < signature.argsNum(); i++){
            final TypeTag targetType = signature.getArgumentsTypes().get(i);
            if(scalars[i] == null || preferRecursion(signature, incrementalDepth)){
                expression.newChild(generate(targetType, incrementalDepth, childrenEnableAggregation));
            }else {
                expression.newChild(scalars[i].toExpression());
            }
        }
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

    // The childrenFlag guides the expression generator to avoid nested aggregation.
    // There is no necessary to public this method to outside.
    private Expression generate(TypeTag required, int recursionDepth, boolean childrenFlagEnableAggregate) {
        Expression expression = getCompleteExpressionTree(required, recursionDepth, childrenFlagEnableAggregate);
        if(!expression.isComplete() || expression.getType() != required){
            logger.error("The Expression is not Required or Incomplete");
            logger.debug("IsComplete: " + expression.isComplete());
            logger.debug("IsRequired: " + (expression.getType() == required));
            return fallback(required);
        }
        return expression;
    }

}
