package NIO;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class serverchannel_threadpool {
    private ServerSocketChannel server;
    private ServerSocket serversocket;
    private Executor poll;
    public serverchannel_threadpool(int port, int pollsize) throws IOException {
        server=ServerSocketChannel.open();
        serversocket=server.socket();
        serversocket.bind(new InetSocketAddress("localhost",port));
        poll= Executors.newFixedThreadPool(pollsize);
    }
    public static void main(String[] args) throws IOException {
        serverchannel_threadpool serverpoint=new serverchannel_threadpool(8888,10);
        serverpoint.readservices();//静态方法里面可以通过对象来调用非静态方法,但是不能直接调用非静态方法。

    }
    public  void readservices(){//服务解耦开来

            try {
                while(true){
                Socket cur=serversocket.accept();
                poll.execute(new readtask(cur));
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
    }
}
