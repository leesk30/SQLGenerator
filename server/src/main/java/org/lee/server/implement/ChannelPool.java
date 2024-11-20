package org.lee.server.implement;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.lee.common.NamedLoggers;
import org.slf4j.Logger;

public class ChannelPool {
    private static final Logger LOGGER = NamedLoggers.getServerLogger(ChannelPool.class);

    private static final GenericKeyedObjectPool<String, Object> pool = null;

    static {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();

    }
}
