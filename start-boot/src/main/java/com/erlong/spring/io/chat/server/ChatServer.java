package com.erlong.spring.io.chat.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

//聊天室服务器
public class ChatServer {


    public static void main(String[] args) {
        try {
            new ChatServer().startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startServer() throws IOException {
        //1，创建一个selector选择器
        Selector selector = Selector.open();
        //2，创建serversocketchannel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3，绑定端口
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        //4，循环等待新的链接接入
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器正常启动");
        for (; ; ) {
            int selectChannels = selector.select();
            if(selectChannels ==0){
                continue;
            }
            //获取可用channel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if(selectionKey.isAcceptable()){
                    acceptOperator(serverSocketChannel,selector);
                }
                if (selectionKey.isReadable()){
                    readOperator(selector,selectionKey);
                }
            }
        }

        //5，根据状态调用具体的业务方法


        //51，如果是accept状态

        //52，可读状态


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
        //5 客户端发送的消息 广播到其他客户端
        socketChannel.register(selector,SelectionKey.OP_READ);
        if(message.length()>0){
            System.out.println("服务端消息:"+message);
            castOtherClient(message,selector,socketChannel);
        }


    }

    private void castOtherClient(String message, Selector selector, SocketChannel socketChannel) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            SelectableChannel targetChannel = key.channel();
            if(targetChannel instanceof SocketChannel && targetChannel != socketChannel){
                ((SocketChannel) targetChannel).write(Charset.forName("utf-8").encode(message));
            }
        }
    }

    /**
     * 处理接入状态
     * @param serverSocketChannel
     * @param selector
     */
    private void acceptOperator(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,SelectionKey.OP_READ);
        socketChannel.write(Charset.forName("utf-8").encode("欢迎进入聊聊天室"));
    }


}
