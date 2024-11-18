package org.lee.sql.symbol;

import org.lee.sql.symbol.common.Aggregator;
import org.lee.sql.type.TypeTag;

public class Aggregation extends Function implements Aggregator {

    public Aggregation(String aggregation, TypeTag returnType, TypeTag... argsType){
        super(aggregation, returnType, argsType);
    }
}
