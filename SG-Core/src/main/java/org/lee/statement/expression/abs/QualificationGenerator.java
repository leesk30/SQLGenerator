package org.lee.statement.expression.abs;

import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.common.global.SymbolTable;
import org.lee.common.structure.Pair;
import org.lee.entry.FieldReference;
import org.lee.entry.record.AdaptiveRecordScalar;
import org.lee.entry.scalar.Scalar;
import org.lee.portal.SQLGeneratorContext;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.Qualification;
import org.lee.statement.expression.statistic.UnrelatedStatistic;
import org.lee.statement.generator.ProjectableGenerator;
import org.lee.statement.support.Projectable;
import org.lee.symbol.Comparator;
import org.lee.symbol.Operator;
import org.lee.symbol.Symbol;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;
import org.lee.type.literal.Literal;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface QualificationGenerator extends IExpressionGenerator<Qualification> {
    Qualification generate();
    Qualification fallback();

    Pair<Scalar, Scalar> getPair();

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
        Pair<Scalar, Scalar> twoSide = getPair();
        return predicateScalarAndScalar(twoSide);
    }

    default Qualification predicateScalarAndQuery(int width){
        return null;
    }

    default Qualification predicateQueryExists(){
        return null;
    }

    default Qualification predicateScalarAndScalar(Pair<Scalar, Scalar> pair){
        if(pair == null || pair.getFirst()!=null || pair.getSecond() != null){
            return fallback();
        }
        Scalar left = pair.getFirst();
        Scalar right = pair.getSecond();
        Symbol symbol = getCompareOperator(left.getType(), right.getType());
        if(symbol == null){
            return fallback();
        }
        // todo: handle like
        return new Qualification(symbol).newChild(left).newChild(right);
    }

    default <T> Qualification compareToLiteral(Scalar fieldReference){
        TypeTag typeTag = fieldReference.getType();
        Literal<T> literal = Literal.fromType(typeTag);
        // check the value is same category
        Assertion.requiredTrue(typeTag.getCategory() == literal.getType().getCategory());
        Comparator comparator = Comparator.fastGetComparatorByCategory(typeTag.getCategory());
        return new Qualification(comparator)
                .newChild(fieldReference)
                .newChild(literal);
    }

    default <T> Qualification compareToRangeLiteral(Scalar fieldReference){
        if(!(fieldReference instanceof FieldReference)){
            return compareToLiteral(fieldReference);
        }
        final TypeTag typeTag = fieldReference.getType();
        final Literal<T> b1 = Literal.fromType(typeTag);
        final Literal<T> b2 = Literal.fromType(typeTag);
        Pair<Literal<T>, Literal<T>> ordered = Pair.OrderedPair(b1, b2);
        if(ordered == null){
            return fallback();
        }
        return new Qualification(Comparator.BETWEEN_AND)
                .newChild(fieldReference)
                .newChild(ordered.getFirst())
                .newChild(ordered.getFirst());
    }

    default <T> Qualification compareToRangeLiteral(Scalar source, Scalar compared){
        Assertion.requiredTrue(source.getType().getCategory() == compared.getType().getCategory());
        if(!(source instanceof FieldReference)){
            return compareToLiteral(source);
        }
        final TypeTag typeTag = source.getType();
        final Literal<T> start = Literal.fromType(typeTag);
        final Qualification qualification = new Qualification(Comparator.BETWEEN_AND).newChild(source);

        if(probability(50)){
            if(probability(50)){
                return qualification.newChild(compared).newChild(start);
            }
            return qualification.newChild(start).newChild(compared);
        }

        // complex
        final SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        final List<Symbol> operators = symbolTable.getOperator(typeTag, typeTag)
                .stream()
                .filter(s -> s.argsNum() == 2).collect(Collectors.toList());

        if(operators.isEmpty()){
            if(probability(50)){
                return qualification.newChild(compared).newChild(start);
            }
            return qualification.newChild(start).newChild(compared);
        }
        Literal<T> partial = Literal.fromType(typeTag);
        Expression expression = new Expression(Utility.randomlyChooseFrom(operators));
        expression.newChild(compared).newChild(partial);
        if(probability(50)){
            return qualification.newChild(expression).newChild(start);
        }
        return qualification.newChild(start).newChild(expression);
    }

    default Qualification tryWithPredicateAddition(final Qualification qualification){
        if(!Utility.probability(5)){
            return qualification;
        }
        final List<FieldReference> referenceList = qualification.extractField();
        final FieldReference addPredicateLhs = Utility.randomlyChooseFrom(referenceList);

        if(addPredicateLhs == null)
            return qualification;

        final Qualification rhs = Utility.probability(50) ?
                compareToRangeLiteral(addPredicateLhs): compareToLiteral(addPredicateLhs);

        if(Utility.probability(80)){
            return qualification.and(rhs);
        }
        return qualification.or(rhs);
    }

    default Qualification predicateSubqueryExists(){
        // An exists statement shouldn't have the raw values
        Projectable statement = ProjectableGenerator.newExistsPredicateProjectable(this.retrieveStatement());
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
        Projectable statement = ProjectableGenerator.newExistsPredicateProjectable(this.retrieveStatement());
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
            return fallback();
        } else if (dateCandidates.isEmpty()) {
            targetCandidates = numberCandidates;
        } else if (numberCandidates.isEmpty()) {
            targetCandidates = numberCandidates;
        }else {
            targetCandidates = probability(50)? dateCandidates : numberCandidates;
        }
        Scalar targetField = Utility.randomlyChooseFrom(targetCandidates);

        if(targetCandidates.size() <= 1 || probability(50)){
            return compareToRangeLiteral(targetField);
        }
        return compareToRangeLiteral(targetField, Utility.randomlyChooseFrom(targetCandidates));
    }

}
