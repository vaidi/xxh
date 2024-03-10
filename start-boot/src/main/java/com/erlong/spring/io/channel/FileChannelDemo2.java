package com.erlong.spring.io.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo2 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile afile = new RandomAccessFile("C:\\Users\\86176\\Desktop\\toutiao\\a.txt", "rw");
        FileChannel channel = afile.getChannel();
        channel.position(12123);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        String newData = "data erlongyuan";
        buffer.clear();
        buffer.put(newData.getBytes());
        buffer.flip();
        while (buffer.hasRemaining()){
            channel.write(buffer);
        }
        channel.close();
    }

}
