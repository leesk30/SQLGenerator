package org.lee.common;

import org.lee.common.exception.UnreachableError;
import org.lee.common.exception.ValueCheckFailedException;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.function.Supplier;

public class Assertion {

    public static final Supplier<UnreachableError> IMPOSSIBLE = UnreachableError::new;
    private final static Logger logger = NamedLoggers.getCommonLogger(Assertion.class);

    public static void requiredTrue(final boolean boolValue){
        if(!boolValue){
            logger.error("[requiredTrue] The bool value must be true");
            throw new ValueCheckFailedException("[requiredTrue] The bool value must be true");
        }
    }

    public static void requiredFalse(final boolean boolValue){
        if(boolValue){
            logger.error("[requiredFalse] The bool value must be false");
            throw new ValueCheckFailedException("[requiredFalse] The bool value must be false");
        }
    }

    public static void requiredNonNull(final Object check){
        if(Objects.isNull(check)){
            logger.error("[requiredNonNull] The object of class cannot be null");
            throw new ValueCheckFailedException("[requiredNonNull] The object of class cannot be null");
        }
    }

    public static void requiredEquals(final int actually, final int required){
        if(actually != required){
            logger.error(ValueCheckFailedException.formatErrorMessage("requireEquals", actually, required));
            throw new ValueCheckFailedException("requireEquals", actually, required);
        }
    }

    public static void requiredNonEquals(final int actually, final int required){
        if(actually == required){
            logger.error(ValueCheckFailedException.formatErrorMessage("requireNonEquals", actually, required));
            throw new ValueCheckFailedException("requireNonEquals", actually, required);
        }
    }

    public static void requiredEquals(final String actually, final String required){
        if(!actually.equals(required)){
            logger.error(ValueCheckFailedException.formatErrorMessage("requireEquals", actually, required));
            throw new ValueCheckFailedException("requireEquals", actually, required);
        }
    }

    public static void requiredNonEquals(final String actually, final String required){
        if(actually.equals(required)){
            logger.error(ValueCheckFailedException.formatErrorMessage("requireNonEquals", actually, required));
            throw new ValueCheckFailedException("requireNonEquals", actually, required);
        }
    }
}
