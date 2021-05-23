package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class test_serverandsocketchannel_connect {
    public static void main(String[] args) throws IOException {
        long begin=0;
        long end=0;
        SocketChannel socket=SocketChannel.open();
        socket.configureBlocking(false);//设置为非阻塞模式。
        boolean isconnect=socket.connect(new InetSocketAddress("localhost",8888));
        Thread t=new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                    ServerSocketChannel server=ServerSocketChannel.open();
                    server.bind(new InetSocketAddress("localhost",8888));
                    SocketChannel sc=server.accept();
                    sc.close();
                    server.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        if(isconnect==false){
          System.out.println("false");
            while(!socket.finishConnect()){
                System.out.println("一直尝试连接");
            }
        }
    }
}
