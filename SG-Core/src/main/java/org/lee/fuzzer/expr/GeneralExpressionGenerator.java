package org.lee.fuzzer.expr;

import org.lee.common.DevTempConf;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.symbol.Aggregation;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.ListUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneralExpressionGenerator extends UnrelatedGenerator<Expression> implements ExpressionGenerator{

    public enum SymbolType{
        aggregate,
        function,
        operator,
    }

    public static final List<Scalar> unmodifiableEmptyList = Collections.unmodifiableList(new Vector<>(0));
    public static final GeneralExpressionGenerator EmptyCandidateExpressionGenerator = new GeneralExpressionGenerator(unmodifiableEmptyList);

    public GeneralExpressionGenerator(Scalar ... scalars){
        super(scalars);
    }

    public GeneralExpressionGenerator(List<? extends Scalar> scalars){
        super(scalars);
    }

    public List<Signature> getSignatures(TypeTag root){
        List<Signature> copiedSignature = ListUtil.copyList(finder.getFunctionByReturn(root));
        List<Signature> operators = finder.getOperatorByReturn(root);
        if(operators != null){
            copiedSignature.addAll(operators);
        }
        return copiedSignature;
    }

    public Expression getCompleteExpressionTree(TypeTag root, int recursionDepth, boolean enableAggregate){
        final List<Signature> copiedSignature = getSignatures(root);
        if(enableAggregate && FuzzUtil.probability(DevTempConf.EXPRESSION_APPEND_AGGREGATION_PROB)){
            List<Signature> aggregators = finder.getAggregateByReturn(root);
            if(aggregators != null){
                copiedSignature.addAll(aggregators);
            }
        }
        Collections.shuffle(copiedSignature);
        while (!copiedSignature.isEmpty()){
            final Signature signature = FuzzUtil.pop(copiedSignature);
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
            if(suitable(statistic)){
                return stopGrowth(signature, statistic);
            }else {
                if(preferRecursion(statistic, recursionDepth)){
                    return growth(signature, statistic, recursionDepth+1);
                }
                // else backtrace
            }
        }
        return fallback(root);
    }

    private boolean suitable(Statistic statistic){
//        System.out.println(statistic.suitableFactorProb());
        return FuzzUtil.probability(statistic.suitableFactorProb());
    }

    private boolean needBacktrace(Statistic statistic){
        // more suitable there is no more necessary to fallback
        return !(FuzzUtil.probability(statistic.suitableFactorProb()) || FuzzUtil.probability(50));
    }

    private boolean preferRecursion(Statistic statistic, int currentDepth){
        // more suitable there is no more necessary to fallback
        if(currentDepth > DevTempConf.MAX_EXPRESSION_RECURSION_DEPTH){
            return false;
        }
        // not suit Or not in depth fix
        return !FuzzUtil.probability(statistic.suitableFactorProb()) && FuzzUtil.probability(DevTempConf.EXPRESSION_RECURSION_PROBABILITY / (currentDepth == 0? 1: currentDepth));
    }

    private Expression stopGrowth(Signature signature, Statistic statistic){
        final Expression expression = new Expression(signature);
        final Scalar[] scalars = statistic.findForAll();
        final List<Expression> children = new Vector<>();
        IntStream.range(0, signature.argsNum()).parallel().forEachOrdered(
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


    private Expression growth(Signature signature, Statistic statistic, int incrementalDepth){
        final Expression expression = new Expression(signature);
        final Scalar[] scalars = statistic.findForAll();
        final List<Expression> children = new Vector<>();
        final boolean childrenEnableAggregation = !(signature instanceof Aggregation);
//        final int[] nullOfIndex = IntStream.range(0, scalars.length).parallel().filter(Objects::isNull).toArray();
        // The arguments must be ordered.
        IntStream.range(0, signature.argsNum()).parallel().forEachOrdered(
                i -> {
                    final TypeTag targetType = signature.getArgumentsTypes().get(i);
                    if(scalars[i] == null || preferRecursion(statistic, incrementalDepth)){
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
        return generate(FuzzUtil.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE), 0);
    }

    @Override
    public Expression generate(TypeTag required) {
        return generate(required, 0, true);
    }

    @Override
    public Expression fallback(TypeTag required) {
        if(replicated.isEmpty()){
            return getLiteral(required).toExpression();
        }
        // On concurrency without ListUtil.copyList(replicated) may cause ConcurrencyModificationException
        List<Expression> matchedList = ListUtil.copyList(replicated).stream().parallel().filter(expression -> expression.getType() == required).collect(Collectors.toList());
        if(matchedList.isEmpty()){
            return getLiteral(required).toExpression();
        }
        return fallback(matchedList);
    }

    public Expression generate(TypeTag required, int recursionDepth) {
        return generate(required, recursionDepth, true);
    }

    public Expression generate(TypeTag required, int recursionDepth, boolean enableAggregate) {
        Expression expression = getCompleteExpressionTree(required, recursionDepth, enableAggregate);
        if(expression.isComplete() && expression.getType() == required){
            return expression;
        }else {
            System.out.println("The Expression is not Required or Incomplete");
            System.out.println("IsComplete: " + expression.isComplete());
            System.out.println("IsRequired: " + (expression.getType() == required));
        }
        return fallback(required);
    }
}