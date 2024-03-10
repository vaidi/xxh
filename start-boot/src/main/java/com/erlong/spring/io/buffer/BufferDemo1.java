package com.erlong.spring.io.buffer;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo1 {


    @Test
    public void readBuffer() throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("C:\\Users\\86176\\Desktop\\toutiao\\a.txt", "rw");
        FileChannel channel = accessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        while(read !=-1){
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.println((char)buffer.get());
            }
            buffer.clear();
            channel.read(buffer);
        }
        accessFile.close();
        System.out.println("end over");
    }


    @Test
    public void buffer02(){
        //创建buffer
        IntBuffer buffer = IntBuffer.allocate(8);
        for (int i=0; i< buffer.capacity() ;i++ ) {
            int j = 2 *(i+1);
            buffer.put(j);
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            int i = buffer.get();
            System.out.println("value:"+i);
        }
    }








}
