package com.hong.py.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer
 * 不能从只读buffer转换为可读写buffer
 */
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        //输出class java.nio.HeapByteBuffer
        System.out.println(buffer.getClass());

        ByteBuffer readBuffer=buffer.asReadOnlyBuffer();

        //输出class java.nio.HeapByteBufferR
        System.out.println(readBuffer.getClass());

        //会抛出异常 java.nio.ReadOnlyBufferException
        readBuffer.put((byte) 1);
    }
}
