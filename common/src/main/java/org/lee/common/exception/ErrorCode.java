package org.lee.common.exception;

public enum ErrorCode {
    RUNTIME_EXCEPTION(900000, "An runtime exception occurred."),
    OTHER_EXCEPTION_ERROR(900001, "An exception occurred."),
    BAD_CONFIGURATION_ERROR(100001, "bad configuration"),
    NOT_IMPLEMENTED_ERROR(100002, "The class or method does not implement yet."),
    UNREACHABLE_ERROR(100003, "Unreachable code"),
    UNRECOGNIZED_ERROR(100004, "Unrecognized value"),


    SERVER_INTERNAL_ERROR(500001, "An internal server error occur"),
    SERVER_REQUEST_ERROR(500002, "Whiling handle a server request an exception occur"),
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
