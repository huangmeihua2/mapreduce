package NIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class server_withthread {
    public static void main(String[] args) {
        try {
            ServerSocket server=new ServerSocket(8088);
            int rung=1;
            while(rung==1){
                Socket socket=server.accept();
                bethread thread=new bethread(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
  static   class bethread extends  Thread{
        //每一个来连接的socket，创建一个线程进行异步的处理
        private Socket socket;
        public bethread(Socket s){
            super();
            this.socket=s;
        }

        @Override
        public void run() {
            try {
                InputStream input=socket.getInputStream();
                InputStreamReader reader=new InputStreamReader(input);
                char[] tmp=new char[1000];
                int line=reader.read(tmp);
                while(line!=-1){
                    String cur=new String(tmp,0,line);
                    System.out.println(cur);
                    line=reader.read(tmp);
                }
                reader.close();
                input.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
