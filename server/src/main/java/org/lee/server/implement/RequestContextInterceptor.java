package org.lee.server.implement;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.grpc.*;
import org.lee.common.NamedLoggers;
import org.lee.common.config.ServerConfig;
import org.lee.context.SQLGeneratorContext;
import org.slf4j.Logger;

import java.util.UUID;

public class RequestContextInterceptor implements ServerInterceptor {
    private static final ServerConfig config = ServerConfig.PROPERTY;
    private static final Logger LOGGER = NamedLoggers.getServerLogger(RequestContextInterceptor.class);
    private static final Cache<UUID, SQLGeneratorContext> CACHE
            = CacheBuilder.newBuilder()
            .maximumSize(config.getCacheSize())
            .expireAfterAccess(config.getExpireTime(), config.getTimeunit())
            .concurrencyLevel(4)
            .initialCapacity(config.getCacheBucketSize())
            .build();

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        LOGGER.info("Header received from client: " + headers);
        return next.startCall(
                new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(serverCall){

                }, headers
        );
    }
}
