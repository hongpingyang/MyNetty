package com.hong.py.nio;

import java.nio.ByteBuffer;

/**
 * buffer.slice()
 * Creates a new byte buffer whose content is a shared subsequence of
 * this buffer's content.
 */
public class NioTest4 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(6);

        ByteBuffer sliceBuffer = buffer.slice();
        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get(i);
            b*=2;
            sliceBuffer.put(b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
