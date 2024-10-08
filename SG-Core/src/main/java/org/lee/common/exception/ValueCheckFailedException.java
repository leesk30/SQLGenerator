package org.lee.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ValueCheckFailedException extends RuntimeException{
    public ValueCheckFailedException(String title, Object expected, Object actual){
        this(formatErrorMessage(title, expected, actual));
    }

    public ValueCheckFailedException(String message){
        super(message);
        StackTraceElement[] localStackElement = Thread.getAllStackTraces().get(Thread.currentThread());
        LoggerFactory.getLogger(this.getClass()).error(StringUtils.joinWith("\n", localStackElement));

    }

    public static String formatErrorMessage(String title, Object e, Object a){
        return String.format("[" + title + "] Left hand side: '%s'. Right hand side: '%s'.", e.toString(), a.toString());
    }
}

