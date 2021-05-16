package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class test_socketchannel {
    public static void main(String[] args) {
        try {
            ServerSocketChannel serversocketchannel=ServerSocketChannel.open();
            serversocketchannel.bind(new InetSocketAddress("localhost",8888));
            SocketChannel sc=serversocketchannel.accept();
            ByteBuffer byteBuffer=ByteBuffer.allocate(10);
            int line=sc.read(byteBuffer);

            while(line!=-1){
                String cur=new String(byteBuffer.array());
                System.out.println(cur);
                byteBuffer.flip();
                line=sc.read(byteBuffer);
            }
            serversocketchannel.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
    }
}
