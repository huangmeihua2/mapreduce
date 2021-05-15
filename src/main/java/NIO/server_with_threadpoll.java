package NIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class server_with_threadpoll {
    private ServerSocket server;//f服务端只要一个socket即可了。
    private Executor poll;

    public server_with_threadpoll(int port, int pollsize){//这是构造器。
        try {
            server = new ServerSocket(port);
            poll = Executors.newFixedThreadPool(pollsize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        server_with_threadpoll server=new server_with_threadpoll(8088,1000);
        server.startservice();//形成一个服务，进行解耦开来。
    }

    private void startservice() {

            try {
                while (true){

                Socket s=server.accept();
                poll.execute(new readrunnable(s));
                //将得到的连接socket传入runnable读任务中，然后传入线程池中由里面的线程去执行。
                    // 异步解耦开来
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
