package org.lee.symbol;

import org.lee.base.Node;
import org.lee.type.TypeTag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Signature extends Node {
    List<TypeTag> UNCONFIRMED_INPUT1 = Collections.singletonList(TypeTag.null_);
    List<TypeTag> UNCONFIRMED_INPUT2 = Arrays.asList(TypeTag.null_, TypeTag.null_);
    List<TypeTag> UNCONFIRMED_INPUT3 = Arrays.asList(TypeTag.null_, TypeTag.null_, TypeTag.null_);
    TypeTag UNCONFIRMED_RETURN = TypeTag.null_;
    String EQUAL_OR_ASSIGN = "%s = %s";
    String PLACEHOLDER = "%s";

    int argsNum();
    TypeTag getReturnType();
    List<TypeTag> getArgumentsTypes();

    default Parentheses toParentheses(){
        return new Parentheses(this);
    }

    default void check(){
        if(!isMatchArguments()){
            throw new RuntimeException(
                    this.getClass().getName() + String.format(
                            ": The number of arguments is mismatched with input body. " +
                                    "Input: %s. Arguments num: %d", getString(), argsNum())
            );
        }
        if(this.getReturnType() == TypeTag.null_){
            if(argsNum() != 1){
                if(!getString().equals(EQUAL_OR_ASSIGN)){
                    throw new RuntimeException(this.getClass().getName() + ": Only equals or assign can be one null return.");
                }
            }
        }
    }

    default boolean isMatchArguments(){
        final int argumentsNum = argsNum();
        String templateBody = getString();
        int counter = 0;
        while (templateBody.contains(PLACEHOLDER)){
            counter++;
            final int indexPh = templateBody.indexOf(PLACEHOLDER);
            templateBody = templateBody.substring(indexPh+1);
        }
        return counter == argumentsNum;
    }
}
