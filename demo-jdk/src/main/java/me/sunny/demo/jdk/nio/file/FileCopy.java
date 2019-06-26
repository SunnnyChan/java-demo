package me.sunny.demo.jdk.nio.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileCopy {

    public boolean copy(String filePathIn, String filePathOut) {

        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            // 获取 FileChannel
            in  = new FileInputStream(filePathIn);
            out = new FileOutputStream(filePathOut);
            FileChannel inChannel = in.getChannel();
            FileChannel outChannel = out.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int byteRead = inChannel.read(byteBuffer);

            while (byteRead != -1) {
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
                byteRead = inChannel.read(byteBuffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
