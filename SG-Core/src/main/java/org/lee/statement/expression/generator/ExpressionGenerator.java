package org.lee.statement.expression.generator;

import org.lee.common.Utility;
import org.lee.common.Assertion;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.statistic.UnrelatedStatistic;
import org.lee.statement.expression.Expression;
import org.lee.symbol.*;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface ExpressionGenerator extends IExpressionGenerator<Expression> {

    Expression generate(TypeTag required);
    Expression fallback(TypeTag required);
    Expression fallback();

    default Expression fallbackFor(List<? extends Scalar> anyInputs){
        UnrelatedStatistic statistic = new UnrelatedStatistic(anyInputs);
        Scalar anyScalar = statistic.findAny();
        if(anyScalar != null){
            return anyScalar.toExpression();
        }
        return getLiteral().toExpression();
    }

    default Expression generateOperatorExpression(Scalar left, Scalar right){
        final Finder finder = Finder.getFinder();
        List<TypeTag> arg = new ArrayList<>(2);
        arg.add(left.getType());
        arg.add(right.getType());
        Operator op = (Operator) Utility.randomlyChooseFrom(finder.getOperator(arg));
        if(op != null){
            return new Expression(op).newChild(left).newChild(right);
        }
        return null;
    }

    default Expression functionUnit(Scalar ... scalars){
        return functionUnit(Arrays.asList(scalars));
    }

    default Expression functionUnit(List<Scalar> scalars){
        final Finder finder = Finder.getFinder();
        final List<Signature> candidate = finder.getFunction(scalars.stream().map(Scalar::getType).collect(Collectors.toList()));
        Function function = (Function) Utility.randomlyChooseFrom(candidate);
        if(function == null){
            return fallbackFor(scalars);
        }
        final Expression result = new Expression(function);
        for (Scalar scalar : scalars) {
            result.newChild(scalar);
        }
        assert result.isComplete();
        return result;
    }

    default Expression withAggregator(TypeTag targetType, Expression expression){
        if(expression.isIncludingAggregation()){
            // like max(a + b). return itself.
            return expression;
        }else {
            // We cannot cast if an expression includes aggregation but not in outermost layer.
            // like max(a) + b
            Assertion.requiredFalse(expression.isIncludingAggregation());
        }
        Finder finder = Finder.getFinder();
        List<Signature> input = finder.getAggregateByReturn(targetType);
        if(input != null){

        }
        List<Signature> suitable = input.stream()
                .filter(signature -> signature.getArgumentsTypes().size() == 1)
                .filter(signature -> signature.getArgumentsTypes().get(0) == expression.getType())
                .collect(Collectors.toList());
        if(suitable.isEmpty()){
            // todo
        }
        return null;
    }

    default Expression cast(Expression expression, TypeTag targetType){
        return castRecursionUnit(expression, targetType);
    }

    default Expression nullableCast(Expression expression, TypeTag targetType){
        final Finder finder = Finder.getFinder();
        List<Signature> result = finder.getFunction(expression.getType())
                .stream()
                .filter(
                        signature -> signature.getArgumentsTypes().get(0) == targetType
                )
                .collect(Collectors.toList());
        if(!result.isEmpty() && probability(99)){
            return new Expression(Utility.randomlyChooseFrom(result)).newChild(expression);
        }
        return null;
    }

    default Expression castRecursionUnit(Expression expression, TypeTag targetType){
        final boolean skipForceConvert = (probability(99) || probability(99));
        if(expression.getType() == targetType && skipForceConvert){
            return expression;
        }
        Expression castedExpression = nullableCast(expression, targetType);
        if(castedExpression != null){
            return castedExpression;
        }
        // todo
        return null;

    }

    default List<Expression> tryMergeToExpression(List<Scalar> scalars){
        final List<Scalar> template = Utility.copyListShuffle(scalars);
        if(template.isEmpty()){
            return Collections.singletonList(new Expression(getLiteral()));
        }
        final int maxSize = Math.min(Finder.getFinder().maxFunctionArgWidth(), scalars.size());
        int epoch = 0;
        do {
            int windowSize = Math.min(template.size(), Utility.randomIntFromRange(1, maxSize));
            epoch++;
            Collections.shuffle(template);
            if(windowSize == 1){
                template.add(functionUnit(template.remove(0)));
            }else if(windowSize == 2){
                Scalar left = template.remove(0);
                Scalar right = template.remove(1);
                int prob = 50;
                if(left.getType().getCategory() == TypeCategory.NUMBER && right.getType().getCategory() == TypeCategory.NUMBER){
                    prob = 75;
                }
                if(Utility.probability(prob)){
                    template.add(generateOperatorExpression(left, right));
                }else {
                    template.add(functionUnit(left, right));
                }
            }else {
                template.add(
                        functionUnit(
                                IntStream.range(0, windowSize)
                                        .sequential()
                                        .mapToObj(i -> template.remove(0))
                                        .collect(Collectors.toList())
                        )
                );
            }
        }while (Utility.probability(50/epoch));
        return template.stream().map(scalar -> scalar instanceof Expression? (Expression) scalar: new Expression(scalar)).collect(Collectors.toList());
    }
}
