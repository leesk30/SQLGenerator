package org.lee.server;

import io.grpc.Server;
import io.grpc.ServerInterceptors;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import org.lee.common.NamedLoggers;
import org.lee.common.config.ServerConfig;
import org.lee.server.implement.RequestContextInterceptor;
import org.lee.server.implement.SQLGeneratorService;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServerBootstrap {
    private Server server;
    private final ServerConfig property = ServerConfig.PROPERTY;
    private final Logger logger = NamedLoggers.getServerLogger(ServerBootstrap.class);


    public void start() throws IOException {
        server = NettyServerBuilder.forPort(property.getListenPort())
                .addService(ServerInterceptors.intercept(new SQLGeneratorService(), new RequestContextInterceptor()))
                .keepAliveTime(60, TimeUnit.SECONDS)
                .keepAliveTimeout(1, TimeUnit.SECONDS)
                .permitKeepAliveTime(60, TimeUnit.SECONDS)
                .permitKeepAliveWithoutCalls(true)
                .maxConnectionIdle(180, TimeUnit.SECONDS)
                .maxConnectionAge(360, TimeUnit.SECONDS)
                .maxConnectionAgeGrace(60, TimeUnit.SECONDS)
                .build()
                .start();

        System.out.println("Server started, listening on port 9090");
        Runtime.getRuntime()
                .addShutdownHook(
                        new Thread(() -> {
                            logger.error("*** shutting down gRPC server since JVM is shutting down");
                            ServerBootstrap.this.stop();
                        })
                );
    }

    public void stop() {
        logger.info("Stopping server");
        if (server != null && (server.isTerminated() || server.isShutdown())) {
            server.shutdown();
            logger.info("Server shutdown successfully.");
        }else {
            logger.error("Server is null or already terminated/shutdown.");
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            logger.info("Await ... ");
            server.awaitTermination();
        }else {
            logger.error("Server is not initialized.");
        }
    }
}