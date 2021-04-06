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
            InputStream in1=s.getInputStream();
            byte[] tmp=new byte[100];
            int line=in1.read(tmp);
           while(line!=-1){
               String cur=new String(tmp,0,line);
               System.out.println(cur);
               line=in1.read(tmp);
           }
            in1.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
