package com.hong.py.nio;

import java.nio.ByteBuffer;

public class NioTest3 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byteBuffer.putInt(13);
        byteBuffer.putLong(5000000L);
        byteBuffer.putChar('c');
        byteBuffer.putShort((short) 2);
        byteBuffer.putDouble(15.11);

        byteBuffer.flip();

        //读取要求顺序一致
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getDouble());

    }
}

