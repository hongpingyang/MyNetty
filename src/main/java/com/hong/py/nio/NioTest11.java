package com.hong.py.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioTest11 {
    public static void main(String[] args) throws IOException {
       int[] ports=new int[5];

       ports[0]=5000;
       ports[1]=5001;
       ports[2]=5002;
       ports[3]=5003;
       ports[4]=5004;

       Selector selector = Selector.open();


        for (int i = 0; i < ports.length; i++) {

            ServerSocketChannel channel = ServerSocketChannel.open();
            //设置为非阻塞模式
            channel.configureBlocking(false);
            //绑定地址
            ServerSocket socket=channel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            socket.bind(address);

            //开始注册 ServerSocketChannel
            //A token representing the registration of a  SelectableChannel with a Selector
            channel.register(selector, SelectionKey.OP_ACCEPT);
        }


        while (true) {
            int select = selector.select();
            System.out.println("numbers:" + select);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            System.out.println("selectionKeys:" + selectionKeys);
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel)selectionKey.channel();
                    //注意是SocketChannel
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                } else if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    int byteRead=0;
                    while (true) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();

                        int read = channel.read(byteBuffer);
                        if (read <=0) {
                            break;
                        }

                        byteBuffer.flip();

                        channel.write(byteBuffer);

                        byteRead++;
                    }

                    iterator.remove();
                }

            }
        }

    }
}
