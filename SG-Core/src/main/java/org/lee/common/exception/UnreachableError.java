package org.lee.common.exception;

public class UnreachableError extends InternalError{

    public UnreachableError(){
        super(ErrorCode.UNREACHABLE_ERROR, "[BUG] Cannot reach here.");
    }

    public UnreachableError(String message) {
        super(message);
    }
}
