package com.erlong.spring.io.filelock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLockDemo {

    public static void main(String[] args) throws IOException {
        String input ="xxh";
        System.out.println("inupt"+input);
        ByteBuffer buffer = ByteBuffer.wrap(input.getBytes());

        String filePath = "C:\\Users\\86176\\Desktop\\toutiao\\b.txt";
        Path path = Paths.get(filePath);
        FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        channel.position(channel.size()-1);
        FileLock lock = channel.lock();
        System.out.println("是否是共享锁"+lock.isShared());
        channel.write(buffer);
        channel.close();
        readFile(filePath);


    }

    private static void readFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = bufferedReader.readLine();
        System.out.println("读取的内容："+s);
        while (s != null){
            System.out.println("s+"+s);
            s = bufferedReader.readLine();
        }
        fileReader.close();
        bufferedReader.close();

    }

}
