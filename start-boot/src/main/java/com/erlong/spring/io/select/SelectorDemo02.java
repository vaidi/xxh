package com.erlong.spring.io.select;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class SelectorDemo02 {

    public static void main(String[] args) throws IOException {
        //1，获取通道 绑定主机和端口
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        //2，到非阻塞模式
        socketChannel.configureBlocking(false);
        //3，创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.next();
            //4，写入buffer数据
            buffer.put((new Date().toString()+":"+next).getBytes());
            //5，切换模式
            buffer.flip();
            //6，写入通道
            socketChannel.write(buffer);
            //7，清空关闭通道
            buffer.clear();
        }






    }



    @Test
    public void client() throws IOException {
        //1，获取通道 绑定主机和端口
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        //2，到非阻塞模式
        socketChannel.configureBlocking(false);

        //3，创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //4，写入buffer数据
        buffer.put(new Date().toString().getBytes());
        //5，切换模式
        buffer.flip();
        //6，写入通道
        socketChannel.write(buffer);
        //7，清空关闭通道
        buffer.clear();
    }


    @Test
    public void server() throws IOException {
        //1，获取服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2，切换通道非阻塞
        serverSocketChannel.configureBlocking(false);
        //3，创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //4，绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //5，获取selector选择器
        Selector open = Selector.open();

        //6，通道注册到选择器上 进行监听
        serverSocketChannel.register(open, SelectionKey.OP_ACCEPT);

        //7，选择器轮询，进行后续操作
        while(open.select() >0){
            Set<SelectionKey> selectionKeys = open.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey next = iterator.next();
                if(next.isAcceptable()){
                    SocketChannel accept = serverSocketChannel.accept();
                    accept.configureBlocking(false);
                    accept.register(open,SelectionKey.OP_READ);
                }else if(next.isReadable()){
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    int length =0;
                    while((length = channel.read(allocate)) >0){
                        allocate.flip();
                        System.out.println(new String(allocate.array(),0,length));
                        allocate.clear();
                    }

                }
                iterator.remove();
            }

        }


    }

}
