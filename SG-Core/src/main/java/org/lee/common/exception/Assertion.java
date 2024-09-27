package org.lee.common.exception;

import org.lee.entry.RangeTableReference;

import java.util.Objects;
import java.util.function.Supplier;

public class Assertion {

    public static final Supplier<GeneratorInternalError> IMPOSSIBLE = GeneratorInternalError::new;


    public static void requiredTrue(final boolean boolValue){
        if(!boolValue){
            throw new ValueCheckFailedException("[requiredTrue] The bool value cannot be true");
        }
    }

    public static void requiredFalse(final boolean boolValue){
        if(boolValue){
            throw new ValueCheckFailedException("[requiredFalse] The bool value cannot be false");
        }
    }

    public static void requiredNonNull(final Object check){
        if(Objects.isNull(check)){
            throw new ValueCheckFailedException("[requiredNonNull] The object of class cannot be null");
        }
    }

    public static void requireEquals(final int actually, final int required){
        if(actually != required){
            throw new ValueCheckFailedException("requireEquals", actually, required);
        }
    }

    public static void requireNonEquals(final int actually, final int required){
        if(actually == required){
            throw new ValueCheckFailedException("requireNonEquals", actually, required);
        }
    }

    public static void requireEquals(final String actually, final String required){
        if(!actually.equals(required)){
            throw new ValueCheckFailedException("requireEquals", actually, required);
        }
    }

    public static void requireEquals(final RangeTableReference actually, final RangeTableReference required){
        if(!actually.getUniqueName().equals(required.getUniqueName())){
            throw new ValueCheckFailedException("requireEquals", actually, required);
        }
    }

    public static void requireNonEquals(final String actually, final String required){
        if(actually.equals(required)){
            throw new ValueCheckFailedException("requireNonEquals", actually, required);
        }
    }

    public static void requireNonEquals(final RangeTableReference actually, final RangeTableReference required){
        if(actually.getUniqueName().equals(required.getUniqueName())){
            throw new ValueCheckFailedException("requireNonEquals", actually, required);
        }
    }
}
