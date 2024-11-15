package org.lee.generator.expression;

import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.global.SymbolTable;
import org.lee.generator.expression.basic.AbstractExpressionGenerator;
import org.lee.generator.expression.basic.ExpressionGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.generator.expression.statistic.GeneratorStatistic;
import org.lee.sql.SQLGeneratorContext;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.symbol.Aggregation;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.type.TypeTag;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommonExpressionGenerator
        extends AbstractExpressionGenerator<Expression>
        implements ExpressionGenerator {

    private final boolean parentFlagEnableAggregate;
    private final boolean parentFlagEnableWindow;
    protected final SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();

    public CommonExpressionGenerator(ExpressionLocation location, SQLStatement statement, Scalar ... scalars){
        super(location, statement, Arrays.asList(scalars));
        parentFlagEnableAggregate = location == ExpressionLocation.project;
        parentFlagEnableWindow = location == ExpressionLocation.project;
    }

    public CommonExpressionGenerator(ExpressionLocation location, boolean enableAggregation, boolean enableWindow, SQLStatement statement, List<? extends Scalar> scalars){
        super(location, statement, scalars);
        parentFlagEnableAggregate = enableAggregation && location == ExpressionLocation.project;
        parentFlagEnableWindow = enableWindow && location == ExpressionLocation.project;
    }

    public CommonExpressionGenerator(ExpressionLocation location, SQLStatement statement, GeneratorStatistic unrelatedStatistic){
        super(location, statement, unrelatedStatistic);
        parentFlagEnableAggregate = location == ExpressionLocation.project;
        parentFlagEnableWindow = location == ExpressionLocation.project;
    }

    public List<Symbol> getCandidateSignatures(TypeTag root, boolean childrenFlagEnableAggregate){
        final List<Symbol> copiedSymbol = Utility.copyList(symbolTable.getFunctionByReturn(root));
        final List<Symbol> operators = symbolTable.getOperatorByReturn(root);

        if(operators != null){
            copiedSymbol.addAll(operators);
        }
        // The parentFlag means whether the expression generator can enable aggregation.
        // The childrenFlag is an internal control flag which is used to avoid the nested aggregation.
        //  After a one aggregator generated, the children of this aggregated expression can not enable generate aggregator.

        // For example, when the parent flag is true, you probably generate an expression like `max(a)`,
        //  and for the sub-expression, we use the childrenFlag to disable aggregation for nested another aggregator.
        //  Otherwise, we may generate an `max(max(a))`. This is not allowed in mostly database engines.
        if(parentFlagEnableAggregate && childrenFlagEnableAggregate && probability(Conf.EXPRESSION_APPEND_AGGREGATION_PROB)){
            final List<Symbol> aggregators = symbolTable.getAggregateByReturn(root);
            if(aggregators != null){
                copiedSymbol.addAll(aggregators);
            }
        }
        return copiedSymbol;
    }

    public Expression getCompleteExpressionTree(TypeTag root, int recursionDepth, boolean childrenFlagEnableAggregate){
        final List<Symbol> copiedSymbol = getCandidateSignatures(root, childrenFlagEnableAggregate);
        Collections.shuffle(copiedSymbol);
        while (!copiedSymbol.isEmpty()){
            final Symbol symbol = Utility.pop(copiedSymbol);
            if(symbol == null){
                return fallback(root);
            }

            // empty arguments function is a terminate node.
            if(symbol.getArgumentsTypes().isEmpty()){
                return new Expression(symbol);
            }

            if(suitable(symbol)){
                return stopGrowth(symbol);
            }else {
                if(preferRecursion(symbol, recursionDepth)){
                    return growth(symbol, recursionDepth+1, childrenFlagEnableAggregate);
                }
                // else backtrace to another symbol
            }
        }
        return fallback(root);
    }

    private boolean suitable(Symbol symbol){
//        System.out.println(statistic.suitableFactorProb());
        return Utility.probability(statistic.suitableFactorProb(symbol));
    }

    private boolean preferRecursion(Symbol symbol, int currentDepth){
        // more suitable there is no more necessary to fallback
        RuntimeConfiguration config = getConfig();
        if(currentDepth > config.getInt(Conf.MAX_EXPRESSION_RECURSION_DEPTH)){
            return false;
        }
        // if suitable enough, don't recursion
        if(probability(statistic.suitableFactorProb(symbol))){
            return false;
        }
        final int adaptiveExpressionProbability = config.getInt(Conf.EXPRESSION_RECURSION_PROBABILITY) / (currentDepth == 0? 1: currentDepth);
        return probability(adaptiveExpressionProbability);
    }

    private Expression stopGrowth(Symbol symbol){
        final Expression expression = new Expression(symbol);
        final Scalar[] scalars = statistic.findMatchedForSignature(symbol);
        for (int i = 0; i < symbol.argsNum(); i++){
            final TypeTag targetType = symbol.getArgumentsTypes().get(i);
            Expression child;
            if(scalars[i] != null){
                child = scalars[i].toCompleteExpression();
            }else {
                child = getContextFreeScalar(targetType).toCompleteExpression();
            }
            expression.newChild(child);
        }
        return expression;
    }

    private Expression growth(Symbol symbol, int incrementalDepth, boolean hintChildrenFlagEnableAggregate){
        final Expression expression = new Expression(symbol);
        final Scalar[] scalars = statistic.findMatchedForSignature(symbol);
        final boolean childrenEnableAggregation = hintChildrenFlagEnableAggregate || !(symbol instanceof Aggregation);
        // The arguments must be ordered.
        for (int i = 0; i < symbol.argsNum(); i++){
            final TypeTag targetType = symbol.getArgumentsTypes().get(i);
            if(scalars[i] == null || preferRecursion(symbol, incrementalDepth)){
                expression.newChild(generate(targetType, incrementalDepth, childrenEnableAggregation));
            }else {
                expression.newChild(scalars[i].toCompleteExpression());
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
        Scalar requiredScalar = statistic.findAny(required);
        if(requiredScalar != null){
            return requiredScalar.toCompleteExpression();
        }
        return getLiteral(required).toCompleteExpression();
    }

    @Override
    public Expression fallback(){
        Scalar anyScalar = statistic.findAny();
        if(anyScalar != null){
            return anyScalar.toCompleteExpression();
        }
        return getLiteral().toCompleteExpression();
    }

    public Expression generate(TypeTag required, int recursionDepth) {
        return generate(required, recursionDepth, true);
    }

    // The childrenFlag guides the expression generator to avoid nested aggregation.
    // There is no necessary to public this method to outside.
    private Expression generate(TypeTag required, int recursionDepth, boolean childrenFlagEnableAggregate) {
        Expression expression = getCompleteExpressionTree(required, recursionDepth, childrenFlagEnableAggregate);
        if(!expression.isComplete() || expression.getType() != required){
            LOGGER.error("The Expression is not Required or Incomplete");
            LOGGER.debug("IsComplete: " + expression.isComplete());
            LOGGER.debug("IsRequired: " + (expression.getType() == required));
            return fallback(required);
        }
        return expression;
    }
}
