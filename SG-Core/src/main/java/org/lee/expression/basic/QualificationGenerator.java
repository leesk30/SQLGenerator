package org.lee.expression.basic;

import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.common.structure.Pair;
import org.lee.entry.complex.AdaptiveRecordScalar;
import org.lee.entry.scalar.Scalar;
import org.lee.expression.Expression;
import org.lee.expression.Qualification;
import org.lee.expression.common.ExprGeneratorUtils;
import org.lee.expression.statistic.GeneratorStatistic;
import org.lee.statement.support.Projectable;
import org.lee.statement.support.ProjectableGenerator;
import org.lee.symbol.Comparator;
import org.lee.symbol.Symbol;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public interface QualificationGenerator extends IExpressionGenerator<Qualification> {
    Qualification generate();
    Qualification fallback();

    default Comparator getCompareOperator(TypeTag lhs, TypeTag rhs){
        if(rhs == lhs || rhs.getCategory() == lhs.getCategory()){
            return Comparator.fastGetComparatorByCategory(lhs.getCategory());
        }
        // todo
        return Comparator.fastGetComparatorByCategory(lhs.getCategory());
    }

    default Qualification predicateFieldAndLiteral(){
        Scalar left = Utility.randomlyChooseFrom(getStatistic().getWholeScopeCandidates());
        Scalar right = getContextFreeScalar(left.getType());
        Symbol symbol = getCompareOperator(left.getType(), right.getType());
        return new Qualification(symbol).newChild(left).newChild(right);
    }

    default Qualification predicateScalarAndScalar(){
        final GeneratorStatistic generic = getStatistic();
        final Pair<Scalar, Scalar> pair = generic.tryFindSimilarPair();
        Scalar left = pair.getFirst();
        Scalar right = pair.getSecond();
        Symbol symbol = getCompareOperator(left.getType(), right.getType());
        if(symbol == null){
            final TypeTag lt = left.getType();
            final TypeTag rt = right.getType();
            final Logger logger = getLogger();
            if(lt == rt || lt.getCategory() == rt.getCategory()){
                logger.error(String.format("Cannot find compare operator for type: %s and %s", lt, rt));
                return fallback();
            }
            Expression converted = cast(right.toExpression(), lt);
            Symbol newSymbol = getCompareOperator(lt, lt);
            if(newSymbol == null){
                logger.error(String.format("Cannot find compare operator for type: %s and %s", lt, rt));
                return fallback();
            }
            logger.debug(String.format("Convert %s -> %s for predicate scalar to scalar", rt, lt));
            return new Qualification(newSymbol).newChild(left).newChild(converted);
        }
        return new Qualification(symbol).newChild(left).newChild(right);
    }

    default Qualification predicateSimilarScalarCompare(){

        // todo
        return null;
    }

    default Qualification predicateSubqueryExists(){
        // An exists statement shouldn't have the raw values
        Projectable statement = ProjectableGenerator.newExistsPredicateProjectable(this.retrieveParent());
        statement.setConfig(Rule.PREFER_RELATED, true);
        statement.fuzz();
        AdaptiveRecordScalar adaptiveRecordScalar = statement.toAdaptiveRecordScalar();
        Symbol symbol = probability(50) ? Comparator.EXISTS : Comparator.NOT_EXISTS;
        return new Qualification(symbol).newChild(adaptiveRecordScalar);
    }

    default Qualification predicateInSubquery(){
        List<Scalar> scalars = new ArrayList<>();
        Logger logger = getLogger();
        int maxCandidates = getStatistic().getWholeScopeCandidates().size();
        if(maxCandidates == 0){
            logger.error("Cannot find any candidates whiling generate in-predicate");
            return fallback();
        }
        do{
            scalars.add(getStatistic().findAny());
        }while (scalars.size() < maxCandidates && probability(Conf.MULTI_FIELD_IN_PREDICATE_SUBQUERY_PROBABILITY));
        AdaptiveRecordScalar left = AdaptiveRecordScalar.adaptScalarList(scalars);
        Projectable statement = ProjectableGenerator.newExistsPredicateProjectable(this.retrieveParent());
        // nonLimitationStatement.setConfig(Rule.PREFER_RELATED, true);
        statement.withProjectTypeLimitation(left.getTypeList());
        statement.fuzz();
        AdaptiveRecordScalar right = statement.toAdaptiveRecordScalar();
        Symbol symbol = probability(50) ? Comparator.NOT_IN : Comparator.IN;
        return new Qualification(symbol).newChild(left).newChild(right);
    }

    default Qualification predicateBetweenAnd(){
        // todo: optimize code
        final GeneratorStatistic statistic = getStatistic();
        final Logger logger = getLogger();
        final List<Scalar> dateCandidates = getStatistic().findMatch(TypeCategory.DATE);
        final List<Scalar> numberCandidates = getStatistic().findMatch(TypeCategory.NUMBER);
        final List<Scalar> targetCandidates;
        if(dateCandidates.isEmpty() && numberCandidates.isEmpty()){
            return predicateScalarAndScalar();
        } else if (dateCandidates.isEmpty()) {
            targetCandidates = numberCandidates;
        } else if (numberCandidates.isEmpty()) {
            targetCandidates = numberCandidates;
        }else {
            targetCandidates = probability(50)? dateCandidates : numberCandidates;
        }
        Scalar targetField = Utility.randomlyChooseFrom(targetCandidates);

        if(targetCandidates.size() <= 1 || probability(50)){
            return ExprGeneratorUtils.compareToRangeLiteral(targetField);
        }
        return ExprGeneratorUtils.compareToRangeLiteral(targetField, Utility.randomlyChooseFrom(targetCandidates));
    }

}