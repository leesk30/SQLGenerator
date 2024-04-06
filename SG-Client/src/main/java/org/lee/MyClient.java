package org.lee;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;
import org.lee.protos.Hello.MyRequest;
import org.lee.protos.Hello.MyResponse;
import org.lee.protos.MyServiceGrpc;

public class MyClient {
    private final ManagedChannel channel;
    private final MyServiceGrpc.MyServiceBlockingStub blockingStub;

    public MyClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = MyServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void sendMessage(String name) {
        MyRequest request = MyRequest.newBuilder().setName(name).build();
        MyResponse response = blockingStub.myMethod(request);
        System.out.println("Response received from server: " + response.getMessage());
    }

    public static void main(String[] args) throws InterruptedException {
        MyClient myClient = new MyClient("localhost", 9090);
        myClient.sendMessage("Alice");
        myClient.sendMessage("Bob");
        myClient.shutdown();
    }
}