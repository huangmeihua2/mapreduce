package AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AioServer {
    public static void main(String[] args) throws IOException {
        System.out.println("completed threadname=" + Thread.currentThread().getName());
        AsynchronousServerSocketChannel asynchronousServerSocketChanne = AsynchronousServerSocketChannel.open();
        asynchronousServerSocketChanne.bind(new InetSocketAddress(8088));
        asynchronousServerSocketChanne.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            // 这里会启动另一个线程来回调执行该handler.
            @Override
            public void completed(AsynchronousSocketChannel result, Void attachment) {
                try {
                    asynchronousServerSocketChanne.accept(null, this);//可能还有下一个连接，持续进行监听。
                    System.out.println("completed threadname=" + Thread.currentThread().getName());
                    ByteBuffer byteBuffer = ByteBuffer.allocate(300);
                    // 也可以启动进行异步的读取操作，也是进行handle操作。
                    Future<Integer> integerFuture = result.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array(), 0, integerFuture.get()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("method faile");
            }
        });
        System.out.println("completed threadname=" + Thread.currentThread().getName() + " 做其他事情");
        while (true) {

        }
    }
}
