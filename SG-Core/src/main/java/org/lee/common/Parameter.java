package org.lee.common;

import org.lee.rules.RuleSet;
import org.lee.rules.SparkRuleSet;

public class Parameter {
    public final SyntaxType syntaxMode;
    public final int probSubquery;
    public final int probSetOp;
    public final int probCTE;
    public final int probTryWithParentheses;

    public static Parameter getDefaultParameter(){
        // todo read from config file
        return null;
    }

    public Parameter(){
        syntaxMode = SyntaxType.spark;
        probSubquery = 10;
        probSetOp = 3;
        probCTE = 1;
        probTryWithParentheses = 40;
    }

    public int getProbSetOp() {
        return probSetOp;
    }

    public int getProbSubquery() {
        return probSubquery;
    }

    public int getProbCTE() {
        return probCTE;
    }

    public int getProbTryWithParentheses() {
        return probTryWithParentheses;
    }

    public RuleSet toRuleSet(){
        switch (syntaxMode){
            case spark:
                return new SparkRuleSet();
            case rain:
                return null;
            case postgres:
                return new RuleSet();
        }
        return null;
    }
}
