package org.lee.symbol;

import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;

public class Aggregation extends Function implements Aggregator {

    private Aggregation(String aggregation, TypeTag returnType, TypeTag ... argsType){
        super(aggregation, returnType, argsType);
    }


    protected static Aggregation buildSum(TypeTag input){
        return buildNumberAgg("SUM(%s)", input);
    }

    protected static Aggregation buildAvg(TypeTag input){
        return buildNumberAgg("AVG(%s)", input);
    }

    protected static Aggregation buildCount(TypeTag input){
        return new Aggregation("COUNT(%s)", TypeTag.bigint, input);
    }

    protected static Aggregation buildMax(TypeTag input){
        assert input.isComparable();
        return new Aggregation("MAX(%s)", input, input);
    }

    protected static Aggregation buildMin(TypeTag input){
        assert input.isComparable();
        return new Aggregation("MIN(%s)", input, input);
    }

    protected static Aggregation buildNumberAgg(String body, TypeTag input){
        final TypeTag returns;
        assert input.getCategory() == TypeCategory.NUMBER;
        if(input == TypeTag.decimal || input == TypeTag.float_) {
            returns = TypeTag.decimal;
        }else {
            returns = TypeTag.bigint;
        }
        return new Aggregation(body, returns, input);
    }
}
