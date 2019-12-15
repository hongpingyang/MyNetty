package com.hong.py.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 类支持“随机访问”方式，这里“随机”是指可以跳转到文件的任意位置处读写数据。
 * 在访问一个文件的时候，不必把文件从头读到尾，
 * 而是希望像访问一个数据库一样“随心所欲”地访问一个文件的某个部分，
 * 这时使用RandomAccessFile类就是最佳选择。
 */
public class NioTest7 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("NioTest7", "rw");
        FileChannel fileChannel = file.getChannel();
        MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);

        mappedByteBuffer.put(0, (byte) 'a');
        mappedByteBuffer.put(3, (byte) 'b');

        file.close();

    }
}
