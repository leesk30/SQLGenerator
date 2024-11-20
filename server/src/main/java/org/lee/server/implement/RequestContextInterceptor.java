package org.lee.server.implement;

import io.grpc.*;
import org.lee.common.NamedLoggers;
import org.slf4j.Logger;

public class RequestContextInterceptor implements ServerInterceptor {

    private static final Logger LOGGER = NamedLoggers.getServerLogger(RequestContextInterceptor.class);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        LOGGER.info("Header received from client: " + headers);
        return next.startCall(
                new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(serverCall){

                }, headers
        );
    }
}
