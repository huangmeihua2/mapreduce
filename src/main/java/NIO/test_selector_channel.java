package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class test_selector_channel {
    public static void main(String[] args) {
        try {
            ServerSocketChannel sc=ServerSocketChannel.open();
            sc.configureBlocking(false);
            ServerSocket s=sc.socket();
            s.bind(new InetSocketAddress("localhost",8888));
            Selector sl=Selector.open();
            sc.isOpen();
            sc.supportedOptions();
            SelectionKey key=sc.register(sl,SelectionKey.OP_ACCEPT);//将通道注册到指定的selectionkey上面。

            System.out.println("selector="+sl);
            System.out.println("key="+key);
            s.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
