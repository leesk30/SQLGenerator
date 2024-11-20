package org.lee.context;

public interface SQLGeneratorContextAllocator {
    void set(SQLGeneratorContext context);
    SQLGeneratorContext get();
}
