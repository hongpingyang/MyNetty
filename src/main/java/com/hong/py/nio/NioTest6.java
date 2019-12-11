package com.hong.py.nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * DirectByteBuffer(直接缓存区) 堆外内存，零拷贝（如果在jvm内存模型中需要拷贝到内核内存中，然后进行IO）。
 *
 */
public class NioTest6 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");
        FileChannel inputChannel=fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        //1024为每一次读的字节数
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        while (true) {

            byteBuffer.clear();//这句话没有的话会导致一直循环。

            int read = inputChannel.read(byteBuffer);
            if(read==-1) //没有字节数读取
                break;

            byteBuffer.flip();//会影响position和limit的位置。

            outputChannel.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
