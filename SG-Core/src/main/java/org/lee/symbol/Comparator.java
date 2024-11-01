package org.lee.symbol;

import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;

import java.util.List;

public enum Comparator implements Signature{
    NOT_EQ("%s != %s"),
    EQ("%s = %s"),
    GT("%s > %s"),
    GT_EQ("%s >= %s"),
    LT("%s < %s"),
    LT_EQ("%s <= %s"),

    LIKE("%s LIKE %s"),
    NOT_LIKE("%s NOT LIKE %s"),

    ILIKE("%s ILIKE %s"),
    NOT_ILIKE("%s NOT ILIKE %s"),

    IS_NOT_NULL(1, "%s IS NOT NULL"),
    IS_NULL(1, "%s IS NULL"),
    BETWEEN_AND(3, "%s BETWEEN %s AND %s"),
    ;

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

    private final String symbols;
    private final int argNum;
    Comparator(int argNum, String symbols){
        this.symbols = symbols;
        this.argNum = argNum;
        check();
    }
    Comparator(String symbols){
        this.symbols = symbols;
        this.argNum = 2;
        check();
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
