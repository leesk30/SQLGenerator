package org.lee.generator.expression.statistic;

import org.lee.common.Utility;
import org.lee.common.structure.Pair;
import org.lee.generator.expression.basic.IExpressionGenerator;
import org.lee.generator.expression.common.ExprGeneratorUtils;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.type.TypeCategory;
import org.lee.sql.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public interface GeneratorStatistic extends IExpressionGenerator.ExpressionGeneratorChildren {
    Logger LOGGER = LoggerFactory.getLogger("GeneratorStatistic");
    List<Scalar> getWholeScopeCandidates();
    boolean contains(TypeTag typeTag);
    List<Scalar> findMatch(TypeTag typeTag);
    List<Scalar> findMatch(TypeCategory typeTag);
    Scalar findAny();

    Pair<Scalar, Scalar> findSimilarPair();
    Pair<Scalar, Scalar> tryFindSimilarPair();
    Pair<Scalar, Scalar> findAnyPair();
    Pair<Scalar, Scalar> findAnyPair(TypeTag target);
    Pair<Scalar, Scalar> tryFindAnyPair(TypeTag target);

    default Scalar findAny(TypeTag typeTag){
        return Utility.randomlyChooseFrom(findMatch(typeTag));
    }

    default Scalar tryFastConvert(Scalar candidate, TypeTag target){
        if(candidate.getType() != target){
            Optional<Expression> result = ExprGeneratorUtils.nullableCast(candidate.toCompleteExpression(), target);
            if(result.isPresent()){
                LOGGER.debug(String.format("Fast convert type %s -> %s", candidate.getType(), target));
                candidate = result.get();
            }
        }
        return candidate;
    }
}
