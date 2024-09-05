package org.lee.server;

import io.grpc.*;
import io.grpc.stub.StreamObserver;
//import org.lee.protos.Hello.MyRequest;
//import org.lee.protos.Hello.MyResponse;
//import org.lee.protos.MyServiceGrpc;
import org.lee.session.SessionContext;
import org.lee.session.SessionContextManager;

import java.io.IOException;

public class SGServer {
    private Server server;
    private final ServerProperty serverProperty = new ServerProperty();

    private static class SessionInterceptor implements ServerInterceptor {

        private static final Context.Key<SessionContext> SESSION_KEY = Context.key("session");
        private static final SessionContextManager serverContextManager = new SessionContextManager();

        @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
            String sessionToken = headers.get(Metadata.Key.of("session-token", Metadata.ASCII_STRING_MARSHALLER));
            SessionContext sessionContext = serverContextManager.getSession(sessionToken);
            Context context = Context.current().withValue(SESSION_KEY, sessionContext);
            return Contexts.interceptCall(context, call, headers, next);
        }

        public static SessionContext getSessionContext() {
            return SESSION_KEY.get();
        }
    }

    public void start() throws IOException {
//        server = ServerBuilder.forPort(serverProperty.getSgPort())
//                .addService((BindableService) new MyServiceImpl())
//                .intercept(new SessionInterceptor())
//                .build()
//                .start();

        System.out.println("Server started, listening on port 9090");
        Runtime.getRuntime()
                .addShutdownHook(
                        new Thread(() -> {
                        System.out.println("Shutting down server");
                        SGServer.this.stop();
                        })
                );
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SGServer sgServer = new SGServer();
        sgServer.start();
        sgServer.blockUntilShutdown();
    }

//    static class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {
//        @Override
//        public void myMethod(MyRequest request, StreamObserver<MyResponse> responseObserver) {
//
//            MyResponse response = MyResponse.newBuilder().setMessage("Hello " + request.getName()).build();
//            responseObserver.onNext(response);
//            responseObserver.onCompleted();
//            System.out.println("I am calling by " + request.getName());
//        }
//    }
}