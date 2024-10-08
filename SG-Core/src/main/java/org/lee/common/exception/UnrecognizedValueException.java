package org.lee.common.exception;

public class UnrecognizedValueException extends RuntimeException{

    public UnrecognizedValueException(Object e){
        super(String.format("Unrecognized value type for '%s' of the class '%s'", e.toString(), e.getClass().getName()));
    }
}
