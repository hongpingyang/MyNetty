package com.hong.py.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 类支持“随机访问”方式，这里“随机”是指可以跳转到文件的任意位置处读写数据。
 * 在访问一个文件的时候，不必把文件从头读到尾，
 * 而是希望像访问一个数据库一样“随心所欲”地访问一个文件的某个部分，
 * 这时使用RandomAccessFile类就是最佳选择。
 */
public class NioTest8 {
    public static void main(String[] args) throws IOException {

        RandomAccessFile file = new RandomAccessFile("NioTest8", "rw");
        FileChannel fileChannel = file.getChannel();
        //true 表名是共享锁还是排他锁
        FileLock lock = fileChannel.lock(3,6,true);

        System.out.println("vaild:" + lock.isValid());
        System.out.println("lock type:" + lock.isShared());

        lock.release();

        file.close();

    }
}
