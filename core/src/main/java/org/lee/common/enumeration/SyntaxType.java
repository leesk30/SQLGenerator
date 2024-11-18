package org.lee.common.enumeration;

public enum SyntaxType {
    spark,
    rain,
    postgres,
    ;

    public static SyntaxType fromString(String syntax){
        switch (syntax.toLowerCase()){
            case "rain":
                return rain;
            case "spark":
                return spark;
            case "pg":
            case "postgres":
            case "postgresql":
                return postgres;
        }
        throw new RuntimeException("Unrecognized syntax type: " + syntax);
    }
}
