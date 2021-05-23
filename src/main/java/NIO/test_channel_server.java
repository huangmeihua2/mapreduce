package NIO;



import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class test_channel_server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel1=ServerSocketChannel.open();
        channel1.configureBlocking(false);
        channel1.bind(new InetSocketAddress("localhost", 8088));
        Selector selector=Selector.open();
        channel1.register(selector, SelectionKey.OP_ACCEPT);
        boolean isrun=true;
        while(isrun){
            selector.select();
            Set<SelectionKey> set=selector.selectedKeys();//返回各个注册事件所对应的各就绪通道的集合
            Iterator<SelectionKey> iterator=set.iterator();
            while(iterator.hasNext()){
                SelectionKey key=iterator.next();
                iterator.remove();
                if(key.isAcceptable()){//这个key不是下面那个key的，代表者的是不同的key的。
                    SocketChannel socketChannel=channel1.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_WRITE);
                }
                if(key.isWritable()){
                    SocketChannel socketChannel=(SocketChannel) key.channel();//次key可能并非是上面那个key因为上面那个key是serverchannel通道的。

                    FileInputStream file=new FileInputStream("C:\\Users\\Administrator\\Desktop\\983120823456564.docx");
                    FileChannel fileChannel=file.getChannel();
                    ByteBuffer bytefuer=ByteBuffer.allocate(524288000);
                    while(fileChannel.position()<fileChannel.size()){
                        fileChannel.read(bytefuer);
                        bytefuer.flip();
                        while(bytefuer.hasRemaining()){
                            socketChannel.write(bytefuer);
                        }
                        bytefuer.clear();
                        System.out.println(fileChannel.position()+" "+fileChannel.size());
                    }
                    socketChannel.close();
                }
            }
        }
    }
}
