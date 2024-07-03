package org.lee.common;

public class Parameter {
    private final int probSubquery;
    private final int probSetOp;
    private final int probCTE;
    private final int probTryWithParentheses;

    public static Parameter getDefaultParameter(){
        // todo read from config file
        return null;
    }

    public Parameter(){
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
}
