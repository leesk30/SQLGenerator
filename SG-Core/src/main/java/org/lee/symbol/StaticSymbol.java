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
    public static final StaticSymbol PARENTHESES = new StaticSymbol("( %s )", UNCONFIRMED_INPUT1, TypeTag.null_);
    public static final StaticSymbol BETWEEN = new StaticSymbol("%s between %s and %s", UNCONFIRMED_INPUT3, TypeTag.boolean_);
    public static final StaticSymbol EQUALS = new StaticSymbol("%s = %s", UNCONFIRMED_INPUT2, TypeTag.boolean_);
    public static final StaticSymbol ASSIGN = new StaticSymbol("%s = %s", UNCONFIRMED_INPUT2, TypeTag.null_);

    private final List<TypeTag> arguments;
    private final String symbolBody;
    private final TypeTag returnType;

    private StaticSymbol(String symbolBody, List<TypeTag> arguments, TypeTag returnType){
        this.arguments = arguments;
        this.symbolBody = symbolBody;
        this.returnType = returnType;
        check();
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
    public int argsNum() {
        return arguments.size();
    }

    @Override
    public TypeTag getReturnType() {
        return returnType;
    }

    @Override
    public List<TypeTag> getArgumentsTypes() {
        return arguments;
    }
}
