package org.lee.common.exception;

public class ValueCheckFailedException extends RuntimeException{
    public ValueCheckFailedException(String title, Object expected, Object actual){
        super(String.format("[" + title + "] Left hand side: '%s'. Right hand side: '%s'.", expected.toString(), actual.toString()));
    }

    public ValueCheckFailedException(String message){
        super(message);
    }
}

