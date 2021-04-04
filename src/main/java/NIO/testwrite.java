package NIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class testwrite {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos=new FileOutputStream(new File("G:\\niodata.txt"));
        FileChannel writefilechannel=fos.getChannel();
        try{
            ByteBuffer buffer=ByteBuffer.wrap("abcdefgeeee".getBytes());
            System.out.println(buffer.position());
            writefilechannel.position(18);
            System.out.println(buffer.position());
            writefilechannel.write(buffer);
            System.out.println(buffer.position());
        } catch (IOException e) {
            e.printStackTrace();
        }
        writefilechannel.close();
        fos.close();
    }
}
