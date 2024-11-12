package org.lee.common.enumeration;

public enum Mode {
    normal,
    diff,
    ;
    public static Mode fromString(String input){
        switch (input.toLowerCase()){
            case "n":
            case "normal":
                return normal;
            case "d":
            case "diff":
                return diff;
        }
        throw new RuntimeException("Unrecognized mode: " + normal);
    }

}
