package org.lee.symbol;

import org.lee.type.TypeTag;

public class Aggregation extends Function implements Aggregator {

    public Aggregation(String aggregation, TypeTag returnType, TypeTag... argsType){
        super(aggregation, returnType, argsType);
    }
}
