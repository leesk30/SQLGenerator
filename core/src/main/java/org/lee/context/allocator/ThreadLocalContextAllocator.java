package org.lee.context.allocator;

import org.lee.context.SQLGeneratorContext;

public class ThreadLocalContextAllocator implements SQLGeneratorContextAllocator{

    private final ThreadLocal<SQLGeneratorContext> context = new ThreadLocal<>();

    public ThreadLocalContextAllocator(){

    }

    @Override
    public void set(SQLGeneratorContext context) {
        this.context.set(context);
    }

    @Override
    public SQLGeneratorContext get() {
        return this.context.get();
    }
}
