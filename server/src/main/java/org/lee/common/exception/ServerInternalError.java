package org.lee.common.exception;

public class ServerInternalError extends InternalError{

    public ServerInternalError(String message) {
        super(ErrorCode.SERVER_INTERNAL_ERROR, message);
    }

    public ServerInternalError(Throwable t) {
        super(ErrorCode.SERVER_INTERNAL_ERROR, t);
    }
}
