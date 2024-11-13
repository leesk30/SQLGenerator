package org.lee.sql.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum TypeCategory {
    NUMBER(true, true, 0),
    STRING(true, false, 3),
    DATE(true, false, 1),
    BOOLEAN(false, false, 4),
    TIMESTAMP(true, false, 2),
    NIL(false, false, 5),
    ;

    private final boolean comparable;
    private final boolean computable;
    private final int priority;
    private TypeCategory(boolean comparable, boolean computable, int categoryPriority){
        this.comparable = comparable;
        this.computable = computable;
        this.priority = categoryPriority;
    }

    public boolean isComparable() {
        return comparable;
    }

    public boolean isComputable() {
        return computable;
    }

    public int getPriority() {
        return priority;
    }

    public static TypeCategory compatible(TypeCategory lhs, TypeCategory rhs){
        if(lhs == rhs){
            return lhs;
        }
        boolean swap = lhs.priority > rhs.priority;
        TypeCategory left = swap?rhs:lhs;
        TypeCategory right = swap?lhs:rhs;

        if(right == STRING || right == BOOLEAN){
            return STRING;
        }
        if(right == TIMESTAMP){
            if(left == DATE){
                return DATE;
            }
            return TIMESTAMP;
        }
        if(right == DATE){
            return DATE;
        }
        if(right == NUMBER){
            // almost cannot be here
            Logger logger = LoggerFactory.getLogger(TypeCategory.class);
            logger.error("The right category convert to compatible state may has wrong path.");
            return NUMBER;
        }
        return NIL;
    }

}
