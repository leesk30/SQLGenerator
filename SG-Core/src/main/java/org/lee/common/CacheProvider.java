package org.lee.common;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.lee.statement.support.Projectable;
import org.lee.type.TypeTag;

public class CacheProvider {
    // todo: async provider implementation
    private final static Cache<TypeTag, Projectable> GLOBAL_SCALAR_CACHE =
            Caffeine.newBuilder().build();

    public void getCaffeineCache(){

    }
}
