package NIO;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class testchannel {
    public static void main(String[] args) throws IOException {
        //1.创建一个RandomAccessFile（随机访问文件）对象，
        RandomAccessFile raf=new RandomAccessFile("G:\\niodata.txt", "rw");
        //通过RandomAccessFile对象的getChannel()方法。FileChannel是抽象类。
        FileChannel inChannel=raf.getChannel();//该通道是和文件进行关联了
        //2.创建一个读数据缓冲区对象
        ByteBuffer buf=ByteBuffer.allocate(48);
        //3.从通道中读取数据，并且将数据缓存到缓冲区中
        int bytesRead = inChannel.read(buf);
        //创建一个写数据缓冲区对象
        ByteBuffer buf2=ByteBuffer.allocate(48);
        //写入数据,并将数据写入到通道里面，通道数据流向文件中。
        buf2.put("filechannel test".getBytes());//将数据写入缓冲的buffer中。
        buf2.flip();//为了从buffer读出数据写入到通道中，需要将
        inChannel.write(buf2);//向通道中写入数据的。
        inChannel.position();
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            //Buffer有两种模式，写模式和读模式。在写模式下调用flip()之后，Buffer从写模式变成读模式。
            buf.flip();
            //如果还有未读内容
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());//不断从buffer中读出数据。
            }
            //清空缓存区
            buf.clear();//向缓存区写入数据的时候都要先清空再说，将position设置为0，limit设置为capatity
            bytesRead =inChannel.read(buf);
        }
        //关闭RandomAccessFile（随机访问文件）对象
        raf.close();
    }
}
