package com.erlong.spring.io.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo1 {

    public static void main(String[] args) throws IOException {
        //创建filechannel
        RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\86176\\Desktop\\toutiao\\a.txt", "rw");
        FileChannel channel = aFile.getChannel();
        //创建buffer
        ByteBuffer buf = ByteBuffer.allocate(4);
        //读取数据到buffer中
        int read = channel.read(buf);
        while(read != -1){
            System.out.println("读取了："+read);
            buf.flip();
            while (buf.hasRemaining()){
                System.out.println((char)buf.get());
            }
            buf.clear();
            read =channel.read(buf);

        }
        aFile.close();
        System.out.println("结束了");
    }


}
