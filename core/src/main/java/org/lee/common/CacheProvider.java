package org.lee.common;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.lee.sql.statement.Projectable;
import org.lee.sql.type.TypeTag;

public class CacheProvider {
    // todo: async provider implementation
    private final static Cache<TypeTag, Projectable> GLOBAL_SCALAR_CACHE =
            Caffeine.newBuilder().build();

    public void getCaffeineCache(){

    }
}
