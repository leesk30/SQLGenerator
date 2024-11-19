package org.lee.common.exception;

public class UnrecognizedValueException extends InternalError{

    public UnrecognizedValueException(Object e){
        super(ErrorCode.UNRECOGNIZED_ERROR, String.format("Unrecognized value type for '%s' of the class '%s'", e.toString(), e.getClass().getName()));
    }
}
