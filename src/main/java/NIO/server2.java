package NIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server2 {
    public static void main(String[] args) {
        try {
            ServerSocket server=new ServerSocket(8088);
            Socket socket=server.accept();
            InputStream input=socket.getInputStream();
            ObjectInputStream object=new ObjectInputStream(input);
            int line=object.readInt();//查看流中有多少数据，创建对应的byte【】数组，一次读出。
            byte[] bytearray=new byte[line];
            object.readFully(bytearray);
            String cur=new String(bytearray);
            System.out.println(cur);

            OutputStream out=socket.getOutputStream();
            ObjectOutputStream rout=new ObjectOutputStream(out);
            String a="客户端a你好\n";
            String b="客户端b你好\n";
            String c="客户端c你好\n";
            rout.writeInt((a+b+c).getBytes().length);
            rout.flush();
            rout.write(a.getBytes());
            rout.write(b.getBytes());
            rout.write(c.getBytes());
            rout.flush();


            InputStreamReader rd=new InputStreamReader(input);
            char[] chars=new char[4];
            int cline=rd.read(chars);
            while(cline!=-1){
                //定容量的char[]缓冲区，分多次读出数据。
                String sinput=new String(chars,0,cline);
                System.out.print(sinput);
                cline=rd.read(chars);
            }

            a="客户端e你好\n";
            b="客户端f你好\n";
            c="客户端g你好\n";

            out.write(a.getBytes());
            out.flush();
            out.write(b.getBytes());
            out.flush();
            out.write(c.getBytes());
            out.flush();

            input.close();
            out.close();
            server.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
