package NIO;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class readtask implements  Runnable{
    private Socket s;
    public readtask(Socket socket){
        super();
        this.s=socket;
    }
    @Override
    public void run() {
        try {
            InputStream input=s.getInputStream();
            byte[] tmp=new byte[1000];
            int line=input.read(tmp);
            String sp="";
            while(line!=-1){
                sp=new String(tmp,0,line);
                System.out.println(sp);
                line=input.read(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
