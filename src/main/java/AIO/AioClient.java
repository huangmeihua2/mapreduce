package AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AioClient {
    public static void main(String[] args) {
        System.out.println("completed threadname"+Thread.currentThread().getName());
        try {
            AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
            asynchronousSocketChannel.connect(new InetSocketAddress("localhost", 8088), null, new CompletionHandler<Void, Void>() {
                @Override
                public void completed(Void result, Void attachment) {
                    Future<Integer> integerFuture = asynchronousSocketChannel.write(ByteBuffer.wrap("你好，服务端，我来自客户端".getBytes()));
                    System.out.println("completed threadname"+Thread.currentThread().getName());
                    try {
                        System.out.println(integerFuture.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, Void attachment) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("completed threadname"+Thread.currentThread().getName());
    }
}
