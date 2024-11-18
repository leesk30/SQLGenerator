package org.lee.common.exception;

public enum ErrorCode {
    RUNTIME_EXCEPTION(900000, "An runtime exception occurred."),
    OTHER_EXCEPTION_ERROR(900001, "An exception occurred."),
    BAD_CONFIGURATION_ERROR(100001, "bad configuration"),
    NOT_IMPLEMENTED_ERROR(100002, "The class or method does not implement yet."),
    UNREACHABLE_ERROR(100003, "Unreachable code"),

    ;
    private final int code;
    private final String description;
    ErrorCode(int code, String description){
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
