package org.lee.statement.select;

import org.lee.common.SyntaxType;
import org.lee.rules.Rule;
import org.lee.rules.RuleSet;
import org.lee.rules.SparkRuleSet;

public enum SetOperation {
    UNION,
    INTERSECT,
    MINUS,
    EXPECTED,
    ;


    public String toString(boolean withAll, SyntaxType syntaxType){
        return withAll ? toString(syntaxType) + " ALL" : toString(syntaxType);
    }

    public String toString(SyntaxType syntaxType){
        return (syntaxType == SyntaxType.spark || syntaxType == SyntaxType.rain) && this == MINUS ? "SETMINUS" : toString();
    }
}
