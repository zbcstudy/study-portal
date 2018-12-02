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

        try {
            inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            //读取客户端的数据
            while ((len = inputStream.read(bytes)) > 0) {
                System.out.println(new String(bytes, 0, len));
            }
            //向客户端写数据
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hello everyBody".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
