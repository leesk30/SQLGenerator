package org.lee.common;

public class SGException {

    public static class NotImplementedException extends RuntimeException {
        public NotImplementedException(String message){
            super(message);
        }

        public NotImplementedException(){
            super("The method is not implemented.");
        }
    }

    public static class ValueCheckFailedException extends RuntimeException {
        public ValueCheckFailedException(Object expected, Object actual){
            super(String.format("Value check failed. Expected: '%s'. Actual: '%s'.", expected.toString(), actual.toString()));
        }

        public ValueCheckFailedException(String message){
            super(message);
        }
    }

}
