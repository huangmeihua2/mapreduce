package NIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class testwrite {
    public static void main(String[] args) throws IOException {
        FileInputStream fos=new FileInputStream(new File("G:\\niodata.txt"));
        FileOutputStream fin=new FileOutputStream(new File("G:\\niodata.txt"));
        FileChannel writefilechannel=fos.getChannel();
        MappedByteBuffer cur=writefilechannel.map(FileChannel.MapMode.READ_ONLY,0,5);
        writefilechannel.lock(1,10,false);
        try{
            ByteBuffer buffer=ByteBuffer.allocate(40);
            //这种方式写入buffer的时候position不变的。
            System.out.println(buffer.position());
            int isremain= writefilechannel.read(buffer);
            System.out.println(buffer.position());
           while(isremain!=-1){
               buffer.flip();
               System.out.println(buffer.position());
               while(buffer.hasRemaining()){
                   System.out.print((char) buffer.get());
               }
               System.out.println(buffer.position());
               buffer.clear();
               isremain= writefilechannel.read(buffer);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writefilechannel.close();
        fos.close();
    }
}
