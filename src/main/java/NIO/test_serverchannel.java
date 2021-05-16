package NIO;

import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class test_serverchannel {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serversocketchannel=ServerSocketChannel.open();
        ServerSocket serversocket=serversocketchannel.socket();
        serversocket.bind(new InetSocketAddress("localhost",8888));
        Socket socket=serversocket.accept();
        SocketChannel cr=serversocketchannel.accept();
        InputStream input=socket.getInputStream();
        InputStreamReader inputreader=new InputStreamReader(input);
        char[] charr=new char[1024];
        int line=inputreader.read(charr);//返回的是读取得到的字节的个数。
        while(line!=-1){
            String cur=new String(charr,0,line);
            System.out.println(cur);
            line=inputreader.read(charr);
        }
        inputreader.close();
        input.close();
        socket.close();
        serversocket.close();
        serversocketchannel.close();
    }
}
