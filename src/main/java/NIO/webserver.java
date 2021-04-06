package NIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class webserver {
    public static void main(String[] args) {

        try {
            ServerSocket serversocket=new ServerSocket(6666);
            System.out.print("server阻塞开始="+System.currentTimeMillis());
            Socket socket=serversocket.accept();
            InputStream input=socket.getInputStream();
            InputStreamReader inputstream=new InputStreamReader(input);
            BufferedReader buffer=new BufferedReader(inputstream);
            String gets="";
            while(!"".equals(gets=buffer.readLine())){
                System.out.print(gets);
            }

            OutputStream out=socket.getOutputStream();
            out.write("HTTP/1.1 200 ok\r\n\r\n".getBytes());
            out.write("<html><body><a href='http://www.baidu.com'>i am bai.dum</a></body></html>".getBytes());
            out.flush();
            out.close();
            input.close();
            System.out.print("server阻塞结束="+System.currentTimeMillis());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
