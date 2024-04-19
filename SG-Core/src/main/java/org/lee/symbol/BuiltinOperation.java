package org.lee.symbol;

import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BuiltinOperation implements Signature{
    i_plus_i(Pattern.plus, NodeTag.operator, TypeTag.INTEGER, TypeTag.INTEGER, TypeTag.INTEGER),
    i_plus_dec(Pattern.plus, NodeTag.operator, TypeTag.DECIMAL, TypeTag.INTEGER, TypeTag.DECIMAL),
    i_plus_f(Pattern.plus, NodeTag.operator, TypeTag.FLOAT, TypeTag.INTEGER, TypeTag.FLOAT),
    i_sub_i(Pattern.sub, NodeTag.operator, TypeTag.INTEGER, TypeTag.INTEGER, TypeTag.INTEGER),
    i_sub_dec(Pattern.sub, NodeTag.operator, TypeTag.DECIMAL, TypeTag.INTEGER, TypeTag.DECIMAL),
    i_sub_f(Pattern.sub, NodeTag.operator, TypeTag.FLOAT, TypeTag.INTEGER, TypeTag.FLOAT),
    i_mul_i(Pattern.mul, NodeTag.operator, TypeTag.INTEGER, TypeTag.INTEGER, TypeTag.INTEGER),
    i_mul_dec(Pattern.mul, NodeTag.operator, TypeTag.DECIMAL, TypeTag.INTEGER, TypeTag.DECIMAL),
    i_mul_f(Pattern.mul, NodeTag.operator, TypeTag.FLOAT, TypeTag.INTEGER, TypeTag.FLOAT),
    i_div_i(Pattern.div, NodeTag.operator, TypeTag.INTEGER, TypeTag.INTEGER, TypeTag.INTEGER),
    i_div_dec(Pattern.div, NodeTag.operator, TypeTag.DECIMAL, TypeTag.INTEGER, TypeTag.DECIMAL),
    i_div_f(Pattern.div, NodeTag.operator, TypeTag.FLOAT, TypeTag.INTEGER, TypeTag.FLOAT),
    dec_plus_i(Pattern.plus, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.INTEGER),
    dec_plus_dec(Pattern.plus, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.DECIMAL),
    dec_plus_f(Pattern.plus, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.FLOAT),
    dec_sub_i(Pattern.sub, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.INTEGER),
    dec_sub_dec(Pattern.sub, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.DECIMAL),
    dec_sub_f(Pattern.sub, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.FLOAT),
    dec_mul_i(Pattern.mul, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.INTEGER),
    dec_mul_dec(Pattern.mul, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.DECIMAL),
    dec_mul_f(Pattern.mul, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.FLOAT),
    dec_div_i(Pattern.div, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.INTEGER),
    dec_div_dec(Pattern.div, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.DECIMAL),
    dec_div_f(Pattern.div, NodeTag.operator, TypeTag.DECIMAL, TypeTag.DECIMAL, TypeTag.FLOAT),
    f_plus_i(Pattern.plus, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.INTEGER),
    f_plus_dec(Pattern.plus, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.DECIMAL),
    f_plus_f(Pattern.plus, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.FLOAT),
    f_sub_i(Pattern.sub, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.INTEGER),
    f_sub_dec(Pattern.sub, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.DECIMAL),
    f_sub_f(Pattern.sub, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.FLOAT),
    f_mul_i(Pattern.mul, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.INTEGER),
    f_mul_dec(Pattern.mul, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.DECIMAL),
    f_mul_f(Pattern.mul, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.FLOAT),
    f_div_i(Pattern.div, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.INTEGER),
    f_div_dec(Pattern.div, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.DECIMAL),
    f_div_f(Pattern.div, NodeTag.operator, TypeTag.DECIMAL, TypeTag.FLOAT, TypeTag.FLOAT),
    ;

    private final String body;
    private final List<TypeTag> arguments;
    private final NodeTag nodeTag;
    private final TypeTag returnType;
    BuiltinOperation(String body, NodeTag nodeTag, TypeTag returnType, TypeTag ... arguments){
        this.body = body;
        this.nodeTag = nodeTag;
        this.returnType = returnType;
        this.arguments = new ArrayList<>(Arrays.asList(arguments));
    }

    @Override
    public String getString() {
        return this.body;
    }

    @Override
    public TypeTag getReturnType() {
        return returnType;
    }

    @Override
    public List<TypeTag> getArgType() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return nodeTag;
    }
}
