package NIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class readrunnable implements Runnable{
    private Socket s;
    public readrunnable(Socket socket) {
        super();
        this.s=socket;
    }

    @Override
    public void run() {
        try {
            InputStream in1=s.getInputStream();//都是相对于程序而言的。
            byte[] tmp=new byte[100];
            int line=in1.read(tmp);
           while(line!=-1){
               String cur=new String(tmp,0,line);//针对一个数组中的有效位置创建字符
               System.out.println(cur);
               line=in1.read(tmp);//从流中读数据到tmp数组里面
           }
            in1.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
