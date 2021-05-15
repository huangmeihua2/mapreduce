package NIO;

import java.io.IOException;
import java.net.ServerSocket;

public class server_reciveobject {
    public static void main(String[] args) {
        try {
            ServerSocket server=new ServerSocket(8088);//服务端的Socket的创建。
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
