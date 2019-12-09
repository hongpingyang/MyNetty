package com.hong.py.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: MyNetty
 * @Package: com.hong.py.nio
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/12/7 22:48
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/12/7 22:48
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class NioTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("nio1.txt");
        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        channel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.hasRemaining()) {
            byte b=byteBuffer.get();
            System.out.println("Character:" + (char) b);
        }

        fileInputStream.close();
    }
}
