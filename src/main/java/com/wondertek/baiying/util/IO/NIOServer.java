package com.wondertek.baiying.util.IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wd on 2018/9/6.
 */
public class NIOServer {

    private int port = 8080;
    private InetSocketAddress address = null;
    private Selector selector;

    public NIOServer(int port) {

        this.port = port;
        address = new InetSocketAddress(this.port);
        try {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.socket().bind(address);
            //服务端通道设置成非阻塞模式
            /**
             * 如果设置成阻塞模式，在创建服务端的时候会抛出异常：如果设置成阻塞模式，在创建服务端的时候会抛出异常：
             */
            socketChannel.configureBlocking(false);

            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器启动成功：" + this.port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //select开始轮询
    public void listen() {
        try {
            while (true) {
                int select = this.selector.select();
                if (select == 0)
                    continue;
                Set<SelectionKey> selectionKeys = this.selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    //针对每一个客户端进行相应的操作
                    process(selectionKey);
                    iterator.remove();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理每一个客户端
     * @param selectionKey 不同客户端的标识
     */
    private void process(SelectionKey selectionKey) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        if (selectionKey.isAcceptable()) {
            ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
            try {
                SocketChannel channel = socketChannel.accept();

                channel.configureBlocking(false);
                //客户端一旦连接链接上来
                //往这个selector上注册key Read 接下来可以读
                channel.register(selector, SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (selectionKey.isReadable()) {

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            int len = socketChannel.read(buffer);

            if (len > 0) {
                buffer.flip(); //固定
                String content = new String(buffer.array(), 0, len);
                System.out.println(content);
                socketChannel.register(selector, SelectionKey.OP_WRITE);
            }

            buffer.clear();
        } else if (selectionKey.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            socketChannel.write(buffer.wrap("hello world".getBytes()));
            socketChannel.close();
        }

    }

    public static void main(String[] args) {
        new NIOServer(8080).listen();
    }
}
