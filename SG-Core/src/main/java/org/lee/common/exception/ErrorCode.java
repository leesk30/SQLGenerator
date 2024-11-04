package org.lee.common.exception;

public enum ErrorCode {
    RUNTIME_EXCEPTION(900000, "An runtime exception occurred."),
    OTHER_EXCEPTION_ERROR(900001, "An exception occurred.")

    ;
    private final int code;
    private final String description;
    ErrorCode(int code, String description){
        this.code = code;
        this.description = description;
    }
}
