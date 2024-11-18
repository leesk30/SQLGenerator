package org.lee.common.exception;

import org.lee.common.Utility;

public class ValueCheckFailedException extends RuntimeException{
    public ValueCheckFailedException(String title, Object expected, Object actual){
        this(formatErrorMessage(title, expected, actual));
    }

    public ValueCheckFailedException(String message){
        super(message);
        Utility.recordLocalFrameInfo4DebugInLog(null);
    }

    public static String formatErrorMessage(String title, Object e, Object a){
        return String.format("[" + title + "] Left hand side: '%s'. Right hand side: '%s'.", e.toString(), a.toString());
    }
}

