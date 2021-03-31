package com.wondertek.baiying.util.NIO;

import java.nio.IntBuffer;

/**
 * @author aaron
 * @since 2021/3/31
 */
public class BasicBuffer {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(5);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i * i);
        }
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());
        //读写切换
        buffer.flip();
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());
        while (buffer.hasRemaining()) {
            System.out.println("-------------");
            System.out.println("position:" + buffer.position());
            System.out.println("limit:" + buffer.limit());
            System.out.println("capacity:" + buffer.capacity());
            System.out.println(buffer.get());
        }
    }
}
