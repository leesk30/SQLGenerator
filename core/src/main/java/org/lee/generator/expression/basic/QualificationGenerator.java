package org.lee.generator.expression.basic;

import org.lee.common.Assertion;
import org.lee.common.NamedLoggers;
import org.lee.common.enumeration.Conf;
import org.lee.common.structure.Pair;
import org.lee.common.utils.RandomUtils;
import org.lee.generator.expression.statistic.GeneratorStatistic;
import org.lee.resource.SymbolTable;
import org.lee.sql.entry.FieldReference;
import org.lee.sql.entry.complex.AdaptiveRecordScalar;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.expression.Qualification;
import org.lee.sql.literal.Literal;
import org.lee.sql.statement.Projectable;
import org.lee.sql.symbol.Comparator;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.type.TypeCategory;
import org.lee.sql.type.TypeTag;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface QualificationGenerator extends IExpressionGenerator<Qualification> {
    Logger LOGGER = NamedLoggers.getCoreLogger(QualificationGenerator.class);
    Qualification generate();
    Qualification fallback();

    default Comparator getCompareOperator(TypeTag lhs, TypeTag rhs){
        if(rhs == lhs || rhs.getCategory() == lhs.getCategory()){
            return Comparator.fastGetComparatorByCategory(lhs.getCategory());
        }
        // todo
        return Comparator.fastGetComparatorByCategory(lhs.getCategory());
    }

    public static <T> Qualification compareToLiteral(Scalar fieldReference){
        TypeTag typeTag = fieldReference.getType();
        Literal<T> literal = Literal.fromType(typeTag);
        // check the value is same category
        Assertion.requiredTrue(typeTag.getCategory() == literal.getType().getCategory());
        Comparator comparator = Comparator.fastGetComparatorByCategory(typeTag.getCategory());
        return new Qualification(comparator)
                .newChild(fieldReference)
                .newChild(literal);
    }

    public static <T> Qualification compareToRangeLiteral(Scalar fieldReference){
        if(!(fieldReference instanceof FieldReference)){
            return compareToLiteral(fieldReference);
        }
        final TypeTag typeTag = fieldReference.getType();
        final Literal<T> b1 = Literal.fromType(typeTag);
        final Literal<T> b2 = Literal.fromType(typeTag);
        Pair<Literal<T>, Literal<T>> ordered = Literal.orderedPair(b1, b2);
        if(ordered == null){
            return compareToLiteral(fieldReference);
        }
        return new Qualification(Comparator.BETWEEN_AND)
                .newChild(fieldReference)
                .newChild(ordered.getFirst())
                .newChild(ordered.getFirst());
    }

    default  <T> Qualification compareToRangeLiteral(Scalar source, Scalar compared){
        Assertion.requiredTrue(source.getType().getCategory() == compared.getType().getCategory());
        if(!(source instanceof FieldReference)){
            return compareToLiteral(source);
        }
        final TypeTag typeTag = source.getType();
        final Literal<T> start = Literal.fromType(typeTag);
        final Qualification qualification = new Qualification(Comparator.BETWEEN_AND).newChild(source);

        if(RandomUtils.probability(50)){
            if(RandomUtils.probability(50)){
                return qualification.newChild(compared).newChild(start);
            }
            return qualification.newChild(start).newChild(compared);
        }

        // complex
        final SymbolTable symbolTable = getSymbolTable();
        final List<Symbol> operators = symbolTable.getOperator(typeTag, typeTag)
                .stream()
                .filter(s -> s.argsNum() == 2).collect(Collectors.toList());

        if(operators.isEmpty()){
            if(RandomUtils.probability(50)){
                return qualification.newChild(compared).newChild(start);
            }
            return qualification.newChild(start).newChild(compared);
        }
        Literal<T> partial = Literal.fromType(typeTag);
        Expression expression = new Expression(RandomUtils.randomlyChooseFrom(operators));
        expression.newChild(compared).newChild(partial);
        if(RandomUtils.probability(50)){
            return qualification.newChild(expression).newChild(start);
        }
        return qualification.newChild(start).newChild(expression);
    }

    default Qualification tryWithPredicateAddition(final Qualification qualification){
        if(!RandomUtils.probability(5)){
            return qualification;
        }
        final List<FieldReference> referenceList = qualification.extractField();
        final FieldReference addPredicateLhs = RandomUtils.randomlyChooseFrom(referenceList);

        if(addPredicateLhs == null)
            return qualification;

        final Qualification rhs = RandomUtils.probability(50) ?
                compareToRangeLiteral(addPredicateLhs): compareToLiteral(addPredicateLhs);

        if(RandomUtils.probability(80)){
            return qualification.and(rhs);
        }
        return qualification.or(rhs);
    }

    default Qualification predicateFieldAndLiteral(){
        Scalar left = RandomUtils.randomlyChooseFrom(getStatistic().getAllCandidates());
        Scalar right = getContextFreeScalar(left.getType());
        Symbol symbol = getCompareOperator(left.getType(), right.getType());
        return new Qualification(symbol).newChild(left).newChild(right);
    }

    default Qualification predicateScalarAndScalar(){
        final GeneratorStatistic generic = getStatistic();
        final Pair<Scalar, Scalar> pair = generic.tryFindSimilarPair();
        Scalar left = pair.getFirst();
        Scalar right = pair.getSecond();
        // TODO: quick fix for wrong type
        if(left.getType().getCategory() != right.getType().getCategory()){
            TypeTag target = TypeTag.minimal(left.getType(), right.getType());
            left = cast(left.toCompleteExpression(), target);
            right = cast(right.toCompleteExpression(), target);
        }
        // TODO: fix end

        Symbol symbol = getCompareOperator(left.getType(), right.getType());
        if(symbol == null){
            final TypeTag lt = left.getType();
            final TypeTag rt = right.getType();
            if(lt == rt || lt.getCategory() == rt.getCategory()){
                LOGGER.error(String.format("Cannot find compare operator for type: %s and %s", lt, rt));
                return fallback();
            }
            Expression converted = cast(right.toCompleteExpression(), lt);
            Symbol newSymbol = getCompareOperator(lt, lt);
            if(newSymbol == null){
                LOGGER.error(String.format("Cannot find compare operator for type: %s and %s", lt, rt));
                return fallback();
            }
            LOGGER.debug(String.format("Convert %s -> %s for predicate scalar to scalar", rt, lt));
            return new Qualification(newSymbol).newChild(left).newChild(converted);
        }
        return new Qualification(symbol).newChild(left).newChild(right);
    }

    default Qualification predicateComplexScalarCompare(){
        return null;
    }

    default Qualification predicateIsNull(){
        final GeneratorStatistic statistic = getStatistic();
        final Scalar scalar = statistic.findAny();
        Assertion.requiredNonNull(scalar);
        final Comparator comparator = RandomUtils.probability(50) ? Comparator.IS_NULL : Comparator.IS_NOT_NULL;
        return new Qualification(comparator).newChild(scalar);
    }

    default Qualification predicateSubqueryExists(){
        // An exists statement shouldn't have the raw values
        Projectable statement = retrieveContext().recursive().generatePredicateExistsRelatedSubquery();
        AdaptiveRecordScalar adaptiveRecordScalar = statement.toAdaptiveRecordScalar();
        Symbol symbol = probability(50) ? Comparator.EXISTS : Comparator.NOT_EXISTS;
        return new Qualification(symbol).newChild(adaptiveRecordScalar);
    }

    default Qualification predicateInSubquery(){
        List<Scalar> scalars = new ArrayList<>();
        int maxCandidates = getStatistic().getAllCandidates().size();
        if(maxCandidates == 0){
            LOGGER.error("Cannot find any candidates whiling generate in-predicate");
            return fallback();
        }
        do{
            scalars.add(getStatistic().findAny());
        }while (scalars.size() < maxCandidates && probability(Conf.MULTI_FIELD_IN_PREDICATE_SUBQUERY_PROBABILITY));
        AdaptiveRecordScalar left = AdaptiveRecordScalar.adaptScalarList(scalars);
        Projectable statement = retrieveContext().recursive().generatePredicateInSubquery(left.getTypeList());
        AdaptiveRecordScalar right = statement.toAdaptiveRecordScalar();
        Symbol symbol = probability(50) ? Comparator.NOT_IN : Comparator.IN;
        return new Qualification(symbol).newChild(left).newChild(right);
    }

    default Qualification predicateBetweenAnd(){
        // todo: optimize code
        final GeneratorStatistic statistic = getStatistic();
        final List<Scalar> dateCandidates = getStatistic().findMatch(TypeCategory.DATE);
        final List<Scalar> numberCandidates = getStatistic().findMatch(TypeCategory.NUMBER);
        final List<Scalar> targetCandidates;
        if(dateCandidates.isEmpty() && numberCandidates.isEmpty()){
            return fallback();
        } else if (dateCandidates.isEmpty()) {
            targetCandidates = numberCandidates;
        } else if (numberCandidates.isEmpty()) {
            targetCandidates = dateCandidates;
        }else {
            targetCandidates = probability(50)? dateCandidates : numberCandidates;
        }
        Scalar targetField = RandomUtils.randomlyChooseFrom(targetCandidates);

        if(targetCandidates.size() <= 1 || probability(50)){
            return compareToRangeLiteral(targetField);
        }
        return compareToRangeLiteral(targetField, RandomUtils.randomlyChooseFrom(targetCandidates));
    }

}
