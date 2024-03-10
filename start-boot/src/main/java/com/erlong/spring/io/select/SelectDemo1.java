package com.erlong.spring.io.select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

public class SelectDemo1 {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        //bling
        serverSocketChannel.bind(new InetSocketAddress(9999));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        for (SelectionKey selectionKey : selectionKeys) {
            if(selectionKey.isAcceptable()){

            }else if(selectionKey.isConnectable()){

            }

        }




    }
    
}
