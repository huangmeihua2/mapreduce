package AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.TimeUnit;

public class AIOCoAndReadServer {
    public static void main(String[] args) throws IOException {
        System.out.println("completed server threadname=" + Thread.currentThread().getName());
        final AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open()
                .bind(new InetSocketAddress(8087));
        // 异步进行连接监听连接，会启动新线程来执行主动连接的socket。
        asynchronousServerSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {

                    @Override
                    public void completed(AsynchronousSocketChannel result, Void attachment) {
                        asynchronousServerSocketChannel.accept(null, this);
                        System.out.println("completed threadname1=" + Thread.currentThread().getName());
                        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.MAX_VALUE / 100);
                        result.read(byteBuffer, 100, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Void>() {
                            // 进行异步的读操作
                            @Override
                            public void completed(Integer re, Void attachment) {
                                System.out.println("completed threadname2=" + Thread.currentThread().getName());
                                if (re == -1) {
                                    System.out.println("客户端直接关闭了，没有传输数据" + Thread.currentThread().getName());
                                }
                                System.out.println(result);
                                System.out.println(new String(byteBuffer.array(), 0, re));
                                if (re == byteBuffer.limit()) {
                                    System.out.println("客户端传输数据完成=" + Thread.currentThread().getName() + " " + new String(byteBuffer.array(), 0, re));
                                }
                                try {
                                    result.close();
                                    System.out.println("服务端关闭");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(Throwable exc, Void attachment) {

                            }
                        });
                        try {
                            result.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failed(Throwable exc, Void attachment) {

                    }
                }
        );
        while (true) {

        }
    }
}

//class SelfHandler implements CompletionHandler<AsynchronousSocketChannel, Void> {
//    AsynchronousServerSocketChannel asynchronousServerSocketChannel = null;
//
//    public SelfHandler(AsynchronousServerSocketChannel asynchronousServerSocketChannel) {
//        this.asynchronousServerSocketChannel = asynchronousServerSocketChannel;
//    }
//
//    @Override
//    public void completed(AsynchronousSocketChannel result, Void attachment) {
//        asynchronousServerSocketChannel.accept(null, this);
//        System.out.println("completed threadname1=" + Thread.currentThread().getName());
//        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.MAX_VALUE / 100);
//        result.read(byteBuffer, 10, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Void>() {
//            // 进行异步的读操作
//            @Override
//            public void completed(Integer result, Void attachment) {
//                System.out.println("completed threadname2=" + Thread.currentThread().getName());
//                if (result == -1) {
//                    System.out.println("客户端直接关闭了，没有传输数据" + Thread.currentThread().getName());
//                }
//                if (result == byteBuffer.limit()) {
//                    System.out.println("客户端传输数据完成=" + Thread.currentThread().getName() + " " + new String(byteBuffer.array(), 0, result));
//                }
//            }
//
//            @Override
//            public void failed(Throwable exc, Void attachment) {
//
//            }
//        });
//        try {
//            result.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void failed(Throwable exc, Void attachment) {
//
//    }
//}
