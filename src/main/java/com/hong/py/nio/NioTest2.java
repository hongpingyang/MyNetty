package com.hong.py.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通过NIO读取文件一般有3个步骤：
 * 1：从FileInputStream中获取到Channel
 * 2: 创建buffer
 * 3：将数据从Channel读取到buffer中
 *
 *
 */
public class NioTest2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");
        FileChannel inputChannel=fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();
        //1024为每一次读的字节数
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
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
