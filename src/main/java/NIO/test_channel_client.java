package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class test_channel_client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 8088));
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        boolean isRun = true;
        while (isRun) {
            if (socketChannel.isOpen()) {
                selector.select();
                Set<SelectionKey> selectionKeySet = selector.keys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeySet.iterator();
                while (selectionKeyIterator.hasNext()) {
                    SelectionKey selectionKey = selectionKeyIterator.next();
                    selectionKeyIterator.remove();
                    if (selectionKey.isConnectable()) {
                        // 如果已经连接上了的话 ，就将该通道注册上 read事件
                        while (!socketChannel.finishConnect()) {
                        } //if, and only if, this channel's socket is nowconnected。
                        //这里要用该方法完成连接，因为socketChannel是非阻塞模式。
                        socketChannel.register(selector,SelectionKey.OP_READ);//对该通道注册读事件
                    }
                    if(selectionKey.isReadable()){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(5000);
                        int readLength = socketChannel.read(byteBuffer);
                        byteBuffer.flip();
                        long count = 0;
                        while (readLength!=-1){
                            count += readLength;
                            readLength = socketChannel.read(byteBuffer);
                            byteBuffer.clear();

                        }
                    }else {
                        break;
                    }
                }
            }
        }
    }
}
