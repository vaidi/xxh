package com.erlong.spring.io.chat.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class ClientThread implements Runnable {
    private Selector selector;

    public ClientThread(Selector selector){
        this.selector=selector;
    }

    @Override
    public void run() {
        try {
            for (; ; ) {
                int selectChannels = selector.select();
                if(selectChannels ==0){
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isReadable()){
                        readOperator(selector,selectionKey);
                    }
                }
            }

        }catch (Exception e){
        }
    }
    private void readOperator(Selector selector, SelectionKey selectionKey) throws IOException {
        //1,从selectkey获取就绪通道
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //2 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //3 循环读取客户端
        int readLine = socketChannel.read(byteBuffer);
        String message ="";
        if(readLine >0){
            //切换读模式
            byteBuffer.flip();
            message+= Charset.forName("utf-8").decode(byteBuffer);
        }
        //4 监听其他事件
        //5 客户端发送的消息 广播到其他客户端
        socketChannel.register(selector,SelectionKey.OP_READ);
        if(message.length()>0){
            System.out.println(message);
        }


    }
}
