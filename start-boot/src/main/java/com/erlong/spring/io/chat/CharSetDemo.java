package com.erlong.spring.io.chat;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharSetDemo {


    public static void main(String[] args) throws CharacterCodingException {
        //获取charset对象
        Charset charset = Charset.forName("utf-8");

        CharsetEncoder charsetEncoder = charset.newEncoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("二龙");
        charBuffer.flip();

        ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.println(byteBuffer.get());
        }
        byteBuffer.flip();
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharBuffer decode = charsetDecoder.decode(byteBuffer);

        System.out.println("解码后的："+decode.toString());


    }
}
