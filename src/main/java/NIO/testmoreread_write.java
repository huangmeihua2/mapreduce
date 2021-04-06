package NIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class testmoreread_write {
    static FileInputStream fin;
    static FileOutputStream fos;
    static FileChannel fin1;
    static FileChannel fos1;
    public static void main(String[] args) throws FileNotFoundException {
        fos=new FileOutputStream(new File("G:\\niodata.txt"));
        fos1=fos.getChannel();
    }
}
