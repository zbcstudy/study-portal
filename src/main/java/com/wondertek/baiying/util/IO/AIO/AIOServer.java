package com.wondertek.baiying.util.IO.AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by wd on 2018/9/7.
 * AIO是异步IO的缩写，虽然NIO在网络操作中，提供了非阻塞的方法，但是NIO的IO行为还是同步的。
 * 对于NIO来说，我们的业务线程是在IO操作准备好时，得到通知，接着就由这个线程自行进行IO操作，IO操作本身是同步的。
 * 但是对AIO来说，则更加进了一步，它不是在IO准备好时再通知线程，而是在IO操作已经完成后，再给线程发出通知。
 * 因此AIO是不会阻塞的，此时我们的业务逻辑将变成一个回调函数，等待IO操作完成后，由系统自动触发。
 * 下面的程序是通过AIO来实现一个简单的EchoServer以及对应的客户端。
 *  在AIO socket编程中，服务端通道是AsynchronousServerSocketChannel
 *      这个类提供了一个open()静态工厂，一个bind()方法用于绑定服务端IP地址（还有端口号）
 *      另外还提供了accept()用于接收用户连接请求。在客户端使用的通道是AsynchronousSocketChannel
 *      这个通道处理提供open静态工厂方法外，还提供了read和write方法。
 * 在AIO编程中，发出一个事件（accept read write等）之后要指定事件处理类（回调函数），AIO中的事件处理类是CompletionHandler<V,A>，
 *      这个接口定义了如下两个方法，分别在异步操作成功和失败时被回调。
 *      void completed(V result, A attachment);
 *      void failed(Throwable exc, A attachment);
 * java AIO学习地址
 *  https://blog.csdn.net/zhongweijian/article/details/8005444
 *  https://blog.csdn.net/x_i_y_u_e/article/details/52223406
 */
public class AIOServer {

    public static final String IP = "127.0.0.1";

    public static final int port = 8081;

    private AsynchronousServerSocketChannel serverSocketChannel = null;

    /**
     * 在构造函数中初始化AIO服务端
     */
    public AIOServer() {

        try {
            //利用工厂方法产生一个服务端通道
            serverSocketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(IP, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //利用这个通道进行客户端的接收和处理
    public void start() {
        System.out.println("server listen to: " + port);

        //注册事件和事件完成之后的处理器,这个CompletionHandler就是事件完成之后的处理器
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

            final ByteBuffer buffer = ByteBuffer.allocate(1024);

            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {

                System.out.println(Thread.currentThread().getName());
                Future<Integer> writeResult = null;

                try {
                    buffer.clear();
                    result.read(buffer).get(100, TimeUnit.SECONDS);
                    System.out.println("in server " + new String(buffer.array()));

                    //将数据写回客户端
                    buffer.flip();
                    writeResult = result.write(buffer);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }finally {
                    serverSocketChannel.accept(null, this);
                    try {
                        writeResult.get();
                        result.close();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("failed " + exc);
            }
        });
    }

    public static void main(String[] args) {
        new AIOServer().start();
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
