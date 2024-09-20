package org.lee.fuzzer.expr;

import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.symbol.*;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.ListUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface ExpressionGenerator extends IExpressionGenerator<Expression> {

    Expression generate(TypeTag required);
    Expression fallback(TypeTag required);
    Expression fallback();

    default Expression fallbackFor(List<? extends Scalar> anyInputs){
        UnrelatedGenerator.Statistic statistic = new UnrelatedGenerator.Statistic(anyInputs);
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
        Operator op = (Operator) FuzzUtil.randomlyChooseFrom(finder.getOperator(arg));
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
        Function function = (Function) FuzzUtil.randomlyChooseFrom(candidate);
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


    default List<Expression> tryMergeToExpression(List<Scalar> scalars){
        final List<Scalar> template = ListUtil.copyListShuffle(scalars);
        if(template.isEmpty()){
            return Collections.singletonList(new Expression(getLiteral()));
        }
        final int maxSize = Math.min(Finder.getFinder().maxFunctionArgWidth(), scalars.size());
        int epoch = 0;
        do {
            int windowSize = Math.min(template.size(), FuzzUtil.randomIntFromRange(1, maxSize));
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
                if(FuzzUtil.probability(prob)){
                    template.add(generateOperatorExpression(left, right));
                }else {
                    template.add(functionUnit(left, right));
                }
            }else {
                template.add(
                        functionUnit(
                                IntStream.range(0, windowSize)
                                        .mapToObj(i -> template.remove(0))
                                        .collect(Collectors.toList())
                        )
                );
            }
        }while (FuzzUtil.probability(50/epoch));
        return template.parallelStream().map(scalar -> scalar instanceof Expression? (Expression) scalar: new Expression(scalar)).collect(Collectors.toList());
    }
}
