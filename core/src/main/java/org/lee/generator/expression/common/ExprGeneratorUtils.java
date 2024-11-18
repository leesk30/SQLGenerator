package org.lee.generator.expression.common;

import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.global.SymbolTable;
import org.lee.common.structure.Pair;
import org.lee.sql.SQLGeneratorContext;
import org.lee.sql.entry.FieldReference;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.expression.Qualification;
import org.lee.sql.literal.Literal;
import org.lee.sql.symbol.Comparator;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExprGeneratorUtils {

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

    public static <T> Qualification compareToRangeLiteral(Scalar source, Scalar compared){
        Assertion.requiredTrue(source.getType().getCategory() == compared.getType().getCategory());
        if(!(source instanceof FieldReference)){
            return compareToLiteral(source);
        }
        final TypeTag typeTag = source.getType();
        final Literal<T> start = Literal.fromType(typeTag);
        final Qualification qualification = new Qualification(Comparator.BETWEEN_AND).newChild(source);

        if(Utility.probability(50)){
            if(Utility.probability(50)){
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
            if(Utility.probability(50)){
                return qualification.newChild(compared).newChild(start);
            }
            return qualification.newChild(start).newChild(compared);
        }
        Literal<T> partial = Literal.fromType(typeTag);
        Expression expression = new Expression(Utility.randomlyChooseFrom(operators));
        expression.newChild(compared).newChild(partial);
        if(Utility.probability(50)){
            return qualification.newChild(expression).newChild(start);
        }
        return qualification.newChild(start).newChild(expression);
    }

    public static Qualification tryWithPredicateAddition(final Qualification qualification){
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

    public static Optional<Expression> nullableCast(Expression expression, TypeTag targetType){
        final SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        final List<Symbol> candidateSymbols = symbolTable.getCaster(expression.getType(), targetType);
        Symbol symbol = Utility.randomlyChooseFrom(candidateSymbols);
        if(symbol == null){
            return Optional.empty();
        }
        return Optional.of(new Expression(symbol, Collections.singletonList(expression)));
    }
}
