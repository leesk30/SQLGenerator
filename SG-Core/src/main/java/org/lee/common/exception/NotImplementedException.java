package org.lee.common.exception;

public class NotImplementedException extends InternalError {
    public NotImplementedException(String message) {
        super(ErrorCode.NOT_IMPLEMENTED_ERROR, message);
    }
}
