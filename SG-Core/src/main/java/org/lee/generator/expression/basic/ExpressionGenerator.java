package org.lee.generator.expression.basic;

import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.global.SymbolTable;
import org.lee.generator.expression.statistic.UnrelatedStatistic;
import org.lee.sql.SQLGeneratorContext;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.symbol.Function;
import org.lee.sql.symbol.Operator;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.type.TypeCategory;
import org.lee.sql.type.TypeTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface ExpressionGenerator extends IExpressionGenerator<Expression> {

    Expression generate(TypeTag required);
    Expression fallback(TypeTag required);
    Expression fallback();

    default Expression fallbackFor(List<Scalar> anyInputs){
        // todo remove theses all
        UnrelatedStatistic statistic = new UnrelatedStatistic(anyInputs);
        Scalar anyScalar = statistic.findAny();
        if(anyScalar != null){
            return anyScalar.toCompleteExpression();
        }
        return getLiteral().toCompleteExpression();
    }

    default Expression generateOperatorExpression(Scalar left, Scalar right){
        final SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        List<TypeTag> arg = new ArrayList<>(2);
        arg.add(left.getType());
        arg.add(right.getType());
        Operator op = (Operator) Utility.randomlyChooseFrom(symbolTable.getOperator(arg));
        if(op != null){
            return new Expression(op).newChild(left).newChild(right);
        }
        return null;
    }

    default Expression functionUnit(Scalar ... scalars){
        return functionUnit(Arrays.asList(scalars));
    }

    default Expression functionUnit(List<Scalar> scalars){
        final SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        final List<Symbol> candidate = symbolTable.getFunction(scalars.stream().map(Scalar::getType).collect(Collectors.toList()));
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
        SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        List<Symbol> input = symbolTable.getAggregateByReturn(targetType);
        if(input != null){

        }
        List<Symbol> suitable = input.stream()
                .filter(signature -> signature.getArgumentsTypes().size() == 1)
                .filter(signature -> signature.getArgumentsTypes().get(0) == expression.getType())
                .collect(Collectors.toList());
        if(suitable.isEmpty()){
            // todo
        }
        return null;
    }

    default List<Expression> tryMergeToExpression(List<Scalar> scalars){
        final List<Scalar> template = Utility.copyListShuffle(scalars);
        if(template.isEmpty()){
            return Collections.singletonList(new Expression(getLiteral()));
        }
        final SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        final int maxSize = Math.min(symbolTable.maxFunctionArgWidth(), scalars.size());
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
        return template.stream().map(scalar -> scalar instanceof Expression ? (Expression) scalar: new Expression(scalar)).collect(Collectors.toList());
    }
}
