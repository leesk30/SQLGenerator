package org.lee.common.exception;

public class GeneratorInternalError extends RuntimeException{
    public GeneratorInternalError(){
        super("[BUG]Cannot be here");
    }
}
