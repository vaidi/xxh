package com.erlong.spring.io.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeDemo {

    public static void main(String[] args) throws IOException {
        //1，获取管道
        Pipe open = Pipe.open();
        //2，获取sink通道
        Pipe.SinkChannel sinkChannel = open.sink();

        //3，创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("erlong".getBytes());
        byteBuffer.flip();

        //4，写入数据
        sinkChannel.write(byteBuffer);
        //5 获取source通道
        Pipe.SourceChannel source = open.source();
        //6 创建缓冲区，读取数据
    //    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.flip();
        int read = source.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(),0, read));
        //7 关闭通道
        sinkChannel.close();
        source.close();

    }
}
