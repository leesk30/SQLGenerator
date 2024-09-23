package org.lee.exception;

import java.util.function.Supplier;

public class GeneratorInternalError extends RuntimeException{
    public GeneratorInternalError(){
        super("[BUG]Cannot be here");
    }
}
