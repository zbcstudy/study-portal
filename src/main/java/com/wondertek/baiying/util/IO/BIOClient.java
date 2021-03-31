package com.wondertek.baiying.util.IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by wd on 2018/9/6.
 */
public class BIOClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream outputStream = socket.getOutputStream();
            //写点东西给服务端
            outputStream.write("hello server! i'm client".getBytes());
            outputStream.flush();

            InputStream stream = socket.getInputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = stream.read(bytes)) > 0) {
                System.out.println(new String(bytes, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
