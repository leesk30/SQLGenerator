package org.lee.generator.expression.statistic;

import org.lee.common.NamedLoggers;
import org.lee.common.structure.Pair;
import org.lee.common.utils.RandomUtils;
import org.lee.context.SQLGeneratorContext;
import org.lee.resource.SymbolTable;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.type.TypeCategory;
import org.lee.sql.type.TypeTag;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface GeneratorStatistic {
    Logger LOGGER = NamedLoggers.getCoreLogger(GeneratorStatistic.class);

    List<Scalar> getAllCandidates();

    SQLGeneratorContext retrieveContext();

    GeneratorStatistic toRelated();

    boolean contains(TypeTag typeTag);

    List<Scalar> findMatch(TypeTag typeTag);

    List<Scalar> findMatch(TypeCategory typeTag);

    Scalar findAny();

    Pair<Scalar, Scalar> findSimilarPair();

    Pair<Scalar, Scalar> tryFindSimilarPair();

    Pair<Scalar, Scalar> findAnyPair();

    Pair<Scalar, Scalar> findAnyPair(TypeTag target);

    Pair<Scalar, Scalar> tryFindAnyPair(TypeTag target);

    Scalar[] findMatchedForSignature(Symbol symbol);

    int suitableFactorProb(Symbol symbol);

    default Scalar findAny(TypeTag typeTag){
        return RandomUtils.randomlyChooseFrom(findMatch(typeTag));
    }

    default Optional<Expression> nullableCast(Expression expression, TypeTag targetType){
        final SymbolTable symbolTable = retrieveContext().getSymbolTable();
        final List<Symbol> candidateSymbols = symbolTable.getCaster(expression.getType(), targetType);
        Symbol symbol = RandomUtils.randomlyChooseFrom(candidateSymbols);
        if(symbol == null){
            return Optional.empty();
        }
        return Optional.of(new Expression(symbol, Collections.singletonList(expression)));
    }

    default Scalar tryFastConvert(Scalar candidate, TypeTag target){
        if(candidate.getType() != target){
            Optional<Expression> result = nullableCast(candidate.toCompleteExpression(), target);
            if(result.isPresent()){
                LOGGER.debug(String.format("Fast convert type %s -> %s", candidate.getType(), target));
                candidate = result.get();
            }
        }
        return candidate;
    }

    static GeneratorStatistic create(SQLGeneratorContext context, List<? extends Scalar> left, List<? extends Scalar> right){
        return new RelatedStatistic(context, left, right);
    }

    static GeneratorStatistic create(SQLGeneratorContext context, List<? extends Scalar> candidates){
        return new UnrelatedStatistic(context, candidates);
    }

    static GeneratorStatistic create(SQLGeneratorContext context){
        return UnrelatedStatistic.empty(context);
    }
}
