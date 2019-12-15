package com.hong.py.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于Buffer的Scattering和Gathering
 */
public class NioTest9 {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel channel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        channel.bind(address);

        int message=9;

        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0]=ByteBuffer.allocate(2);
        byteBuffers[1]=ByteBuffer.allocate(3);
        byteBuffers[2]=ByteBuffer.allocate(4);

        SocketChannel socketChannel = channel.accept();

        while (true) {
            int byteRead=0;

            while (byteRead < message) {
                long r = socketChannel.read(byteBuffers);
                byteRead++;

                System.out.println("bytesRead:"+byteRead);

                Arrays.asList(byteBuffers).stream().
                        map(buffer->"position:"+buffer.position()+" limit:"+buffer.limit()).
                        forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(buffer->buffer.flip());

            //回写
            int byteWrite=0;
            while (byteWrite < message) {
                long write = socketChannel.write(byteBuffers);
                byteWrite++;
            }

            Arrays.asList(byteBuffers).forEach(buffer->buffer.clear());
        }
    }
}
