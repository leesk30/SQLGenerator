package org.lee.common.exception;

public class ServerRequestError extends InternalError{

    public ServerRequestError(String message) {
        super(message);
    }
}
