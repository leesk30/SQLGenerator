package org.lee.common.exception;

public class InternalError extends Error{
    private final ErrorCode errorCode;

    public InternalError(String message){
        this(ErrorCode.OTHER_EXCEPTION_ERROR, message);
    }

    public InternalError(Throwable t){
        this(ErrorCode.OTHER_EXCEPTION_ERROR, t);
    }

    public InternalError(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public InternalError(ErrorCode errorCode, Throwable t){
        super(t);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
