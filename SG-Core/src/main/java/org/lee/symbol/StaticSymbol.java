package org.lee.symbol;

import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StaticSymbol implements Signature {

    private static final List<TypeTag> AND_OR_INPUT = Arrays.asList(TypeTag.boolean_, TypeTag.boolean_);

    public static final StaticSymbol AND = new StaticSymbol("%s and %s", AND_OR_INPUT, TypeTag.boolean_);
    public static final StaticSymbol OR = new StaticSymbol("%s or %s", AND_OR_INPUT, TypeTag.boolean_);
    public static final StaticSymbol NOT = new StaticSymbol("NOT %s", Collections.singletonList(TypeTag.boolean_), TypeTag.boolean_);
    public static final StaticSymbol PARENTHESES = new StaticSymbol("( %s )", UNCONFIRMED_INPUT, TypeTag.null_);
    public static final StaticSymbol BETWEEN = new StaticSymbol("between %s and %s", UNCONFIRMED_INPUT, TypeTag.boolean_);
    public static final StaticSymbol EQUAL_ASSIGN = new StaticSymbol("%s = %s", UNCONFIRMED_INPUT, TypeTag.boolean_);

    private final List<TypeTag> arguments;
    private final String symbolBody;
    private final TypeTag returnType;
    private StaticSymbol(String symbolBody, List<TypeTag> arguments, TypeTag returnType){
        this.arguments = arguments;
        this.symbolBody = symbolBody;
        this.returnType = returnType;
    }

    @Override
    public String getString() {
        return symbolBody;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.operator;
    }

    @Override
    public List<TypeTag> getArgType() {
        return arguments;
    }

    @Override
    public TypeTag getReturnType() {
        return returnType;
    }
}
