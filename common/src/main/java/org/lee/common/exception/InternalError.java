package org.lee.common.exception;

public class InternalError extends Error{
    private final ErrorCode errorCode;

    public InternalError(String message){
        super(message);
        this.errorCode = ErrorCode.RUNTIME_EXCEPTION;
    }
    public InternalError(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public InternalError(Throwable t){
        super(t);
        this.errorCode = ErrorCode.OTHER_EXCEPTION_ERROR;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
