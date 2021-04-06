package NIO;

import java.io.IOException;
import java.net.ServerSocket;

public class server {
    public static void main(String[] args) {

        try {
            ServerSocket socket=new ServerSocket(8088);
            System.out.print("server阻塞开始="+System.currentTimeMillis());
            socket.accept();
            System.out.print("server阻塞结束="+System.currentTimeMillis());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

