package org.lee.fuzzer.expr;

import org.lee.common.DevTempConf;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.Qualification;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.ListUtil;
import org.lee.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneralExpressionGenerator extends UnrelatedGenerator<Expression> implements ExpressionGenerator{

    public GeneralExpressionGenerator(Scalar ... scalars){
        super(scalars);
    }

    public GeneralExpressionGenerator(List<? extends Scalar> scalars){
        super(scalars);
    }

    public Expression getCompleteExpressionTree(TypeTag root, int recursionDepth){
        final List<Signature> copiedSignature = ListUtil.copyList(finder.getFunctionByReturn(root));
        while (!copiedSignature.isEmpty()){
            final Signature signature = FuzzUtil.randomlyPop(copiedSignature);
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

            final Statistic statistic = new Statistic(signature.getArgumentsTypes(), candidateList);
            if(needBacktrace(statistic)){
                continue;
            }
            return growth(signature, statistic, recursionDepth+1);
        }
        return fallback(root);
    }

    private boolean needBacktrace(Statistic statistic){
        // more suitable there is no more necessary to fallback
        return !FuzzUtil.probability(statistic.suitableFactorProb());
    }

    private boolean needRecursion(Statistic statistic, int currentDepth){
        // more suitable there is no more necessary to fallback
        int depthFactor = currentDepth == 0 ? 1 : currentDepth;
        if(depthFactor > DevTempConf.MAX_EXPRESSION_RECURSION_DEPTH){
            return false;
        }
        return FuzzUtil.probability(statistic.suitableFactorProb() / depthFactor);
    }

    private Expression stopGrowth(Signature signature, Statistic statistic){
        Expression expression = new Expression(signature);
        Scalar[] scalars = statistic.findForAll();
        IntStream.range(0, signature.argsNum()).sequential().forEach(
                i -> {
                    if(scalars[i] != null){
                        expression.newChild(scalars[i]);
                    }else {
                        TypeTag targetType = signature.getArgumentsTypes().get(i);
                        expression.newChild(fallback(targetType));
                    }
                }
        );
        return expression;
    }


    private Expression growth(Signature signature, Statistic statistic, int incrementalDepth){
        final Expression expression = new Expression(signature);
        final Scalar[] scalars = statistic.findForAll();
        final List<Expression> children = new Vector<>();
        IntStream.range(0, signature.argsNum()).parallel().forEach(
                i -> {
                    final TypeTag targetType = signature.getArgumentsTypes().get(i);
                    final Expression sub;
                    if(scalars[i] == null){
                        if(needRecursion(statistic, incrementalDepth)){
                            sub = generate(targetType, incrementalDepth);
                        }else {
                            sub = fallback(targetType);
                        }
                    }else {
                        if(needRecursion(statistic, incrementalDepth) && FuzzUtil.probability(25)){
                            sub = generate(targetType, incrementalDepth);
                        }else {
                            sub = scalars[i].toExpression();
                        }
                    }
                    children.add(sub);
                }
        );
        children.forEach(expression::newChild);
        return expression;
    }

    public Expression getUncompletedExpressionTree(List<Scalar> scalars){
        return null;
    }


    @Override
    public Expression generate() {
        return generate(FuzzUtil.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE), 0);
    }

    @Override
    public Expression generate(TypeTag required) {
        return generate(required, 0);
    }

    @Override
    public Expression fallback(TypeTag required) {
        List<Expression> matchedList = replicated.stream().parallel().filter(
                expression -> expression.getType() == required).collect(Collectors.toList());
        return fallback(matchedList);
    }

    public Expression generate(TypeTag required, int recursionDepth) {
        Expression expression = getCompleteExpressionTree(required, recursionDepth);
        if(expression.isComplete() && expression.getType() == required){
            return expression;
        }
        return fallback(required);
    }
}
