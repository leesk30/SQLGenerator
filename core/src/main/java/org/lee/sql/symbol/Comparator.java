package org.lee.sql.symbol;

import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.type.TypeCategory;
import org.lee.sql.type.TypeTag;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public enum Comparator implements Symbol {
    NOT_EQ(2, "%s != %s"),
    EQ(2,"%s = %s"),
    GT(2,"%s > %s"),
    GT_EQ(2,"%s >= %s"),
    LT(2,"%s < %s"),
    LT_EQ(2,"%s <= %s"),

    LIKE(2,"%s LIKE %s"),
    NOT_LIKE(2,"%s NOT LIKE %s"),

    ILIKE(2, "%s ILIKE %s"),
    NOT_ILIKE(2, "%s NOT ILIKE %s"),

    IS_NOT_NULL(1, "%s IS NOT NULL"),
    IS_NULL(1, "%s IS NULL"),
    BETWEEN_AND(3, "%s BETWEEN %s AND %s"),

    // subquery
    EXISTS(1, "EXISTS %s"),
    NOT_EXISTS(1, "NOT EXISTS %s"),

    IN(2, "%s IN %s"),
    NOT_IN(2, "%s NOT IN %s"),
    ;

    public final static int DEFAULT_WEIGHT = 100;

    public final static Comparator[] STRING_USABLE_COMPARATOR = {LIKE, NOT_LIKE, ILIKE, NOT_ILIKE};
    public final static Comparator[] ALL = {NOT_EQ, EQ, GT, GT_EQ, LT, LT_EQ};
    public final static Comparator[] BASE_EQ = {EQ, NOT_EQ};

    public static Comparator fastGetComparatorByCategory(TypeCategory category){
        if(category == TypeCategory.STRING && Utility.probability(90)){
            return Utility.randomlyChooseFrom(Comparator.STRING_USABLE_COMPARATOR);
        }
        final int prob = category == TypeCategory.STRING ? 99: 90;
        if(category.isComparable() && Utility.probability(prob)){
            return Utility.randomlyChooseFrom(Comparator.ALL);
        }else {
            return Utility.randomlyChooseFrom(Comparator.BASE_EQ);
        }
    }

    // TODO: fill the weight map
    private final Map<ExpressionLocation, Integer> weightMap = new EnumMap<>(ExpressionLocation.class);
    private final String symbols;
    private final int argNum;
    Comparator(int argNum, String symbols){
        this.symbols = symbols;
        this.argNum = argNum;
        check();
    }

    public int getWeight(ExpressionLocation expressionLocation) {
        return weightMap.getOrDefault(expressionLocation, DEFAULT_WEIGHT);
    }

    @Override
    public String getString() {
        return symbols;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.operator;
    }

    @Override
    public int argsNum() {
        return argNum;
    }

    @Override
    public TypeTag getReturnType() {
        return TypeTag.boolean_;
    }

    @Override
    public List<TypeTag> getArgumentsTypes() {
        // just hack implements for comparator
        switch (argNum){
            case 1:
                return UNCONFIRMED_INPUT1;
            case 2:
                return UNCONFIRMED_INPUT2;
            case 3:
                return UNCONFIRMED_INPUT3;
            default:
                throw new RuntimeException("The comparator receive two many arguments");
        }
    }

    public boolean isLikeCompare(){
        switch (this){
            case LIKE:
            case ILIKE:
            case NOT_LIKE:
            case NOT_ILIKE:
                return true;
        }
        return false;
    }
}
