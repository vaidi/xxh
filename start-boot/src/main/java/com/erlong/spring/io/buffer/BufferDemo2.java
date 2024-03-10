package com.erlong.spring.io.buffer;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo2 {



    @Test
    public void buffer04() throws IOException {


    }


    @Test
    public void buffer03() throws IOException {
        String infile ="C:\\Users\\86176\\Desktop\\toutiao\\a.txt";
        FileInputStream fin = new FileInputStream(infile);
        FileChannel inChannel = fin.getChannel();
        
        
        String outFile ="C:\\Users\\86176\\Desktop\\toutiao\\a2.txt";
        FileOutputStream outputStream = new FileOutputStream(outFile);
        FileChannel ouChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        
        while(true){
            buffer.clear();
            int read = inChannel.read(buffer);
            if(read == -1){
                break;
            }
            buffer.flip();
            ouChannel.write(buffer);
        }
        
    }

    @Test
    public void buffer02(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i <buffer.capacity() ; i++) {
            buffer.put((byte)i);
        }
        ByteBuffer readOnly = buffer.asReadOnlyBuffer();
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.get(i);
            b *=10;
            buffer.put(i,b);
        }
        readOnly.position(6);
        readOnly.limit(buffer.capacity());
        while (readOnly.remaining() >0){
            System.out.println(readOnly.get());
        }

    }

    @Test
    public void buffer01(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i <buffer.capacity() ; i++) {
            buffer.put((byte)i);
        }
        //创建
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();
        for (int i = 0; i <slice.capacity() ; i++) {
            byte b = slice.get(i);
            b *=10;
            slice.put(i,b);
        }
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining() >0){
            System.out.println(buffer.get());
        }
    }

}
