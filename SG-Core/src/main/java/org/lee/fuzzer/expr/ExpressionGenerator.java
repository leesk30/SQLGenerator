package org.lee.fuzzer.expr;

import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Generator;
import org.lee.statement.expression.Expression;
import org.lee.symbol.Finder;
import org.lee.symbol.Function;
import org.lee.symbol.Operator;
import org.lee.symbol.Signature;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.ListUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface ExpressionGenerator extends Generator<Expression> {

    default Scalar scalarSubqueryGenerate(){
        return null;
    }

    default Scalar getLiteral(TypeTag typeTag){
        return typeTag.asMapped().generate();
    }

    default Scalar getLiteral(){
        return getLiteral(FuzzUtil.randomlyChooseFrom(TypeTag.ALL));
    }

    default Scalar getLiteral(int partial){
        return getLiteral(FuzzUtil.randomlyChooseFrom(TypeTag.ALL), partial);
    }

    default Scalar getLiteral(TypeTag typeTag, int partial){
        return typeTag.asMapped().generate(partial);
    }

    default List<Expression> tryMergeToExpression(Scalar ... scalars){
        return tryMergeToExpression(Arrays.asList(scalars));
    }

    default Expression fallbackUnit(Scalar ... anyInputs){
        return fallbackUnit(Arrays.asList(anyInputs));
    }

    default Expression fallbackUnit(List<Scalar> anyInputs){
        if(anyInputs != null && !anyInputs.isEmpty() && FuzzUtil.probability(50)){
            Scalar choose = FuzzUtil.randomlyChooseFrom(anyInputs);
            if(choose instanceof Expression){
                return (Expression) choose;
            }
            return new Expression(choose);
        }
        return new Expression(getLiteral());
    }

    default Expression operateUnit(Scalar left, Scalar right){
        final Finder finder = Finder.getFinder();
        Operator op = (Operator) FuzzUtil.randomlyChooseFrom(finder.getOperator(left.getType(), right.getType()));
        if(op != null){
            return new Expression(op).newChild(left).newChild(right);
        }else {
            return fallbackUnit(left, right);
        }
    }

    default Expression functionUnit(Scalar ... scalars){
        return functionUnit(Arrays.asList(scalars));
    }

    default Expression functionUnit(List<Scalar> scalars){
        final Finder finder = Finder.getFinder();
        final List<Signature> candidate = finder.getFunction(scalars.stream().map(Scalar::getType).collect(Collectors.toList()));
        Function function = (Function) FuzzUtil.randomlyChooseFrom(candidate);
        if(function == null){
            return fallbackUnit(scalars);
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
                    template.add(operateUnit(left, right));
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
