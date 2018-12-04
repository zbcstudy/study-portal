package com.wondertek.baiying.util.NIO;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * Created by wd on 2018/1/19.
 */
public class BlockNIOTest {

    //客户端
    @Test
    public void client() {
        try {
            //获取通道
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
            //分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            /**
             *  从本地读取文件，并发送到客户端
             *  StandardOpenOption.READ 给定操作方式：只读
             */
            FileChannel fileChannel = FileChannel.open(Paths.get("E:\\Z-test\\Tulips.jpg"), StandardOpenOption.READ);
            while (fileChannel.read(buffer) != -1) {
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }
            System.out.println("文件发送成功");

            /**
             * 在阻塞IO的情况下，如果关闭socketChannel,那么服务端不知道客户端是否把所有数据发完
             * 所以会一直阻塞.
             * 另一种方式：改为非阻塞传输
             */
            socketChannel.shutdownOutput();

            //接收服务端的消息
            int len;
            while ((len = socketChannel.read(buffer)) != -1) {
                buffer.flip();
                System.out.println(new String(buffer.array(),0,len));
            }

            //关闭通道
            fileChannel.close();
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //服务端
    @Test
    public void server() {
        try {
            //获取端口
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //绑定连接
            serverSocketChannel.bind(new InetSocketAddress(8080));
            //获取客户端连接的通道
            SocketChannel socketChannel = serverSocketChannel.accept();
            //接收客户端的数据，保存到本地（保存到本地，需要用到FileChannel）
            FileChannel fileChannel = FileChannel.open(Paths.get("E:\\Z-test\\1.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(buffer) != -1) {
                buffer.flip();
                fileChannel.write(buffer);
                buffer.clear();
            }
            System.out.println("文件接收成功");

            buffer.put("服务端接收数据成功".getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            //关闭服务端通道连接
            fileChannel.close();
            socketChannel.close();
            serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
