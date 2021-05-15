package NIO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class client {
    public static void main(String[] args) {

        try {
            System.out.println("socket begin" + System.currentTimeMillis());
            Socket socket=new Socket("192.168.1.113",8088);
            System.out.println("socket end" + System.currentTimeMillis());
            Thread.sleep(3000);
            OutputStream out=socket.getOutputStream();
            out.write("我是外星人".getBytes());
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
