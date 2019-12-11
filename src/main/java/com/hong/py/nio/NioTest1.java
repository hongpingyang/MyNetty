package com.hong.py.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
 * @CreateDate: 2019/12/7 23:06
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/12/7 23:06
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class NioTest1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("NotiTest1.txt");
        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] data = "Hello World,this is a nioTest".getBytes();

        for (int i = 0; i < data.length; i++) {
            byteBuffer.put(data[i]);
        }

        byteBuffer.flip();

        channel.write(byteBuffer);

        fileOutputStream.close();



    }
}
