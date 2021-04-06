package NIO;

import java.io.IOException;
import java.net.Socket;

public class testclient {
    public static void main(String[] args) {
        try {
            System.out.println("client链接开始="+System.currentTimeMillis());
            Socket socket=new Socket("www.csdn.net",80);
            System.out.println("server连接结束="+System.currentTimeMillis());
            socket.close();
        } catch (IOException e) {
            System.out.println("server连接结束="+System.currentTimeMillis());
            e.printStackTrace();
        }
    }
}
