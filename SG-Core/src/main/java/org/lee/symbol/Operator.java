package org.lee.symbol;

import org.lee.statement.node.NodeTag;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Operator implements Signature{
    // todo use type category

    num_plus_num(Pattern.plus, NodeTag.operator, TypeTag.int_, TypeTag.int_, TypeTag.int_),
    i_plus_dec(Pattern.plus, NodeTag.operator, TypeTag.decimal, TypeTag.int_, TypeTag.decimal),
    i_plus_f(Pattern.plus, NodeTag.operator, TypeTag.float_, TypeTag.int_, TypeTag.float_),
    i_sub_i(Pattern.sub, NodeTag.operator, TypeTag.int_, TypeTag.int_, TypeTag.int_),
    i_sub_dec(Pattern.sub, NodeTag.operator, TypeTag.decimal, TypeTag.int_, TypeTag.decimal),
    i_sub_f(Pattern.sub, NodeTag.operator, TypeTag.float_, TypeTag.int_, TypeTag.float_),
    i_mul_i(Pattern.mul, NodeTag.operator, TypeTag.int_, TypeTag.int_, TypeTag.int_),
    i_mul_dec(Pattern.mul, NodeTag.operator, TypeTag.decimal, TypeTag.int_, TypeTag.decimal),
    i_mul_f(Pattern.mul, NodeTag.operator, TypeTag.float_, TypeTag.int_, TypeTag.float_),
    i_div_i(Pattern.div, NodeTag.operator, TypeTag.int_, TypeTag.int_, TypeTag.int_),
    i_div_dec(Pattern.div, NodeTag.operator, TypeTag.decimal, TypeTag.int_, TypeTag.decimal),
    i_div_f(Pattern.div, NodeTag.operator, TypeTag.float_, TypeTag.int_, TypeTag.float_),
    dec_plus_i(Pattern.plus, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.int_),
    dec_plus_dec(Pattern.plus, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.decimal),
    dec_plus_f(Pattern.plus, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.float_),
    dec_sub_i(Pattern.sub, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.int_),
    dec_sub_dec(Pattern.sub, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.decimal),
    dec_sub_f(Pattern.sub, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.float_),
    dec_mul_i(Pattern.mul, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.int_),
    dec_mul_dec(Pattern.mul, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.decimal),
    dec_mul_f(Pattern.mul, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.float_),
    dec_div_i(Pattern.div, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.int_),
    dec_div_dec(Pattern.div, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.decimal),
    dec_div_f(Pattern.div, NodeTag.operator, TypeTag.decimal, TypeTag.decimal, TypeTag.float_),
    f_plus_i(Pattern.plus, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.int_),
    f_plus_dec(Pattern.plus, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.decimal),
    f_plus_f(Pattern.plus, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.float_),
    f_sub_i(Pattern.sub, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.int_),
    f_sub_dec(Pattern.sub, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.decimal),
    f_sub_f(Pattern.sub, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.float_),
    f_mul_i(Pattern.mul, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.int_),
    f_mul_dec(Pattern.mul, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.decimal),
    f_mul_f(Pattern.mul, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.float_),
    f_div_i(Pattern.div, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.int_),
    f_div_dec(Pattern.div, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.decimal),
    f_div_f(Pattern.div, NodeTag.operator, TypeTag.decimal, TypeTag.float_, TypeTag.float_),
    ;

    private final String body;
    private final List<TypeTag> arguments;
    private final NodeTag nodeTag;
    private final TypeTag returnType;
    Operator(String body, NodeTag nodeTag, TypeTag returnType, TypeTag ... arguments){
        this.body = body;
        this.nodeTag = nodeTag;
        this.returnType = returnType;
        this.arguments = Arrays.asList(arguments);

        SymbolFinder.put(this);
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
