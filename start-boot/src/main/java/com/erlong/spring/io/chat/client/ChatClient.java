package com.erlong.spring.io.chat.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ChatClient {




    public void startClient(String name) throws IOException {
        SocketChannel sockerChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        Selector open = Selector.open();
        sockerChannel.configureBlocking(false);
        sockerChannel.register(open, SelectionKey.OP_READ);
        System.out.println("进入客户端这里");
        //创建线程
        new Thread(new ClientThread(open)).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.next();
            if(next.length() >0){
                sockerChannel.write(Charset.forName("utf-8").encode(name+":"+next));
            }
        }
    }
}
