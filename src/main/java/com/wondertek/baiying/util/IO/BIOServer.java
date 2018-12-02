package com.wondertek.baiying.util.IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wd on 2018/9/6.
 */
public class BIOServer {

    /**
     * 单线程网络编程
     */
    public void server1() {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            OutputStream outputStream = socket.getOutputStream();
            //写点东西给服务端
            outputStream.write("hello server! i'm client ".getBytes());
            outputStream.flush();

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1034];
            int length = 0;
            while ((length = inputStream.read(bytes)) > 0) {
                System.out.println(new String(bytes, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 多线程操作
     */
    public void server2() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("服务端启动成功，监听端口为8080，等待客户端连接");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 线程池操作
     */
    public void server3() {

        ServerSocket serverSocket = null;
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(60);
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("服务端启动成功，监听端口为8080，等待客户端连接");

            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
