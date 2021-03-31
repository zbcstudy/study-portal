package com.wondertek.baiying.util.IO;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by wd on 2018/9/6.
 */
public class ServerHandler implements Runnable {

    //维护一个socket成员变量，记录传来的socket
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        InputStream inputStream = null;
        long id = Thread.currentThread().getId();
        String name = Thread.currentThread().getName();
        System.out.println("当前线程的id为：" + id + "当前线程的name为" + name);
        try {
            inputStream = socket.getInputStream();
            //向客户端写数据
            OutputStream outputStream = socket.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            //读取客户端的数据
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read)); //输出客户端发送的数据
                } else {
                    outputStream.write("数据获取完成".getBytes());
                    outputStream.flush();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭和客户端的链接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
