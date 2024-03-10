package com.erlong.spring.io.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 8888;
        ByteBuffer buffer = ByteBuffer.wrap("hello xxh".getBytes());

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        //设置非阻塞模式
        ssc.configureBlocking(true);
        while (true) {
            System.out.println("wait channel...");
            SocketChannel accept = ssc.accept();
            if (accept == null) {
                System.out.println("null");
                Thread.sleep(2000);
            } else {
                System.out.println("connect from:" + accept.socket().getRemoteSocketAddress());
                buffer.rewind();
                accept.write(buffer);
                accept.close();
            }


        }


    }
}
