package org.lee.integration.client;

public interface Client<R> {
    Execution<R> createExecution(String code);
}
