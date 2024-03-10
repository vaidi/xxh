package com.erlong.spring.io.channel;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class DatagramChannelDemo {




    public static void main(String[] args) {



    }

    @Test
    public void sendDatagram() throws IOException, InterruptedException {

        DatagramChannel sendChannel = DatagramChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9999);
        while (true){
            ByteBuffer buffer = ByteBuffer.wrap("发送 erlong".getBytes("utf-8"));
            sendChannel.send(buffer,inetSocketAddress);
            System.out.println("send ok");
            Thread.sleep(1000);
        }
    }

    @Test
    public void accetDatagram() throws IOException, InterruptedException {
        DatagramChannel receiveChannel = DatagramChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9999);
        receiveChannel.bind(inetSocketAddress);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true){
            buffer.clear();
            SocketAddress socketAddress = receiveChannel.receive(buffer);
            buffer.flip();
            System.out.println(socketAddress.toString());
            System.out.println(Charset.forName("utf-8").decode(buffer));
        }
    }


    @Test
    public void testConnect() throws IOException {
        DatagramChannel connChannel = DatagramChannel.open();
        connChannel.bind(new InetSocketAddress(9999));
        connChannel.connect(new InetSocketAddress("127.0.0.1",9999));
        connChannel.write(ByteBuffer.wrap("发送 xxh".getBytes("utf-8")));
        //buffer
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        while(true){
            readBuffer.clear();
            connChannel.read(readBuffer);
            readBuffer.flip();
            System.out.println(Charset.forName("utf-8").decode(readBuffer));
        }


    }


}
