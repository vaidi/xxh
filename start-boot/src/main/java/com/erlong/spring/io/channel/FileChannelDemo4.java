package com.erlong.spring.io.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelDemo4 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\86176\\Desktop\\toutiao\\a.txt","rw");
        FileChannel fromChannel = aFile.getChannel();

        RandomAccessFile bFile = new RandomAccessFile("C:\\Users\\86176\\Desktop\\toutiao\\b.txt","rw");
        FileChannel toChannel = bFile.getChannel();
        long startPosition = 0;
        long size = fromChannel.size();
        fromChannel.transferTo(startPosition,size,toChannel);
        aFile.close();
        toChannel.close();
        bFile.close();
        toChannel.close();



    }

}
