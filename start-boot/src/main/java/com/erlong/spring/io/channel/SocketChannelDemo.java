package com.erlong.spring.io.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {

    public static void main(String[] args) throws IOException {
        //创建socket channel
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
        socketChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(16);
        socketChannel.read(buffer);
        socketChannel.close();
        System.out.println("read over");

    }
}
