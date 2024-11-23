package org.lee.server.implement;

import com.google.common.cache.CacheBuilder;
import org.lee.context.SQLGeneratorContext;

import java.util.UUID;

public class ResourceContext {

    private SQLGeneratorContext context;
    private UUID uuid;

    public ResourceContext(){

    }

    public UUID uuid() {
        return uuid;
    }
}
