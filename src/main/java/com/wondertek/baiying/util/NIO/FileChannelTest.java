package com.wondertek.baiying.util.NIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author aaron
 * @since 2021/3/31
 */
public class FileChannelTest {

    public static final String PATH = "D:\\file.txt";
    public static final String READ_PATH = "D:\\read.txt";
    public static final String WRITE_PATH = "D:\\write.txt";

    public static void main(String[] args) {
//        read();
//        write();
        readWrite();
    }

    public static void read() {
        String str = "file channel 文件通道";
        try( FileOutputStream fileOutputStream = new FileOutputStream(PATH)) {
            //实际对象是 FileChannelImpl
            FileChannel channel = fileOutputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(64);
            //将数据放到缓冲区
            buffer.put(str.getBytes());
            buffer.flip();
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write() {
        try (FileInputStream fileInputStream = new FileInputStream(PATH)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(64);

            //将通道中的数据读取到buffer
            fileChannel.read(buffer);
            System.out.println(new String(buffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readWrite() {
        try (FileInputStream inputStream = new FileInputStream(READ_PATH);
             FileOutputStream outputStream = new FileOutputStream(WRITE_PATH)) {

            FileChannel inputStreamChannel = inputStream.getChannel();
            FileChannel outputStreamChannel = outputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (true) {
                //这步操作一定要
                buffer.clear();

                int read = inputStreamChannel.read(buffer);
                System.out.println("read: " + read);

                if (read == -1) { //表示读完了
                    break;
                }
                buffer.flip();

                outputStreamChannel.write(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
