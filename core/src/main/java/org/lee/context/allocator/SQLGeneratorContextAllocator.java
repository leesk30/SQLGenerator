package org.lee.context.allocator;

import org.lee.context.SQLGeneratorContext;

public interface SQLGeneratorContextAllocator {
    void set(SQLGeneratorContext context);
    SQLGeneratorContext get();
}
