package AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AIOAcAndWriteClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("client Threadname=" + Thread.currentThread().getName());
        final AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
        // 进行异步连接操作，就是当连接成功后会启用另一个线程来执行该handle的。
        asynchronousSocketChannel.connect(new InetSocketAddress("localhost", 8087), null,
                new CompletionHandler<Void, Void>() {
                    @Override
                    public void completed(Void result, Void attachment) {
                        System.out.println("clientwrite Threadname1=" + Thread.currentThread().getName());
                        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.MAX_VALUE / 100);
                        for (int i = 0; i < Integer.MAX_VALUE / 100 - 3; i++) {
                            byteBuffer.put("1".getBytes());
                        }
                        byteBuffer.put("end".getBytes());
                        byteBuffer.flip();
                        // 进行异步写操作，该handle是在写操作完成时会调用的。
                        asynchronousSocketChannel.write(byteBuffer, 10000, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Void>() {
                            @Override
                            public void completed(Integer result, Void attachment) {
                                System.out.println(result);
                                System.out.println("clientwrite Threadname2=" + Thread.currentThread().getName());
                                try {
                                    asynchronousSocketChannel.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(Throwable exc, Void attachment) {
                                System.out.println("写失败了");
                                System.out.println(exc.getMessage());
                            }
                        });
//                        int line = 0;
//                        while (line < byteBuffer.limit()) {
//                            Future<Integer> future = asynchronousSocketChannel.write(byteBuffer);
//                            Integer cur = 0;
//                            try {
//                                cur = future.get();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            } catch (ExecutionException e) {
//                                e.printStackTrace();
//                            }
//                            line += cur;
//                        }
                    }

                    @Override
                    public void failed(Throwable exc, Void attachment) {
                        System.out.println("clientwrite Threadname1=" + Thread.currentThread().getName());
                        System.out.println("写数据失败");
                        System.out.println(exc.getMessage() + " = " + exc.getClass().getName());
                    }
                });
        System.out.println("client Threadname=" + Thread.currentThread().getName());
        Thread.sleep(10000);
    }
}
