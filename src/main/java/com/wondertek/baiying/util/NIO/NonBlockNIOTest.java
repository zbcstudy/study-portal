package com.wondertek.baiying.util.NIO;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by wd on 2018/1/19.
 */
public class NonBlockNIOTest {

    //客户端
    @Test
    public void client() {
        try {
            //获取通道
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));

            //修改传输模式为非阻塞模式
            socketChannel.configureBlocking(false);

            //分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //发送数据
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String input = scanner.next();
                buffer.put((new Date().toString() +"\n"+ input).getBytes());
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }
            scanner.close();
           /* buffer.put(new Date().toString().getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();*/

            //关闭通道
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void server() {
        try {
            //获取通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            //设置非堵塞
            serverSocketChannel.configureBlocking(false);

            //绑定连接
            serverSocketChannel.bind(new InetSocketAddress(8888));

            //获取选择器
            Selector selector = Selector.open();

            /**
             * 将通道注册到选择器上
             * SelectionKey.OP_ACCEPT:选择键 用来告诉选择器需要监控这个通道的什么状态或者说什么事件（读，写。接收，连接）
             * 这里服务端首先要监听客户端的接收状态
             */
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            //轮询的获取选择器上已经“准备就绪”的事件
            while (selector.select() > 0) {

                //获取当前选择中所有注册的‘选择键(已就绪的监听事件)’
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                if (iterator.hasNext()) {
                    //获取准备就绪的事件
                    SelectionKey key = iterator.next();

                    //判断是什么事件准备就绪
                    if (key.isAcceptable()) {
                        //若接收就绪，获取客户端连接
                        SocketChannel socketChannel = serverSocketChannel.accept();

                        //客户端连接同样要设置为非阻塞模式
                        socketChannel.configureBlocking(false);

                        //将该客户端注册到选择器上，监听客户端socketChannel的读就绪事件
                        socketChannel.register(selector, SelectionKey.OP_READ);

                    } else if (key.isReadable()) {
                        //获取当前选择器上读就绪状态的通道
                        SocketChannel socketChannel = (SocketChannel) key.channel();

                        //读取数据
                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        int len;
                        while ((len = socketChannel.read(buffer)) > 0) {
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, len));
                            buffer.clear();
                        }
                    }
                    //取消选择键
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
