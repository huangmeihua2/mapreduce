package AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioServer {
    public static void main(String[] args) throws IOException {
        AsynchronousServerSocketChannel asynchronousServerSocketChanne = AsynchronousServerSocketChannel.open();
        asynchronousServerSocketChanne.bind(new InetSocketAddress(8088));
        asynchronousServerSocketChanne.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Void attachment) {
                
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("method faile");
            }
        });
    }
}
