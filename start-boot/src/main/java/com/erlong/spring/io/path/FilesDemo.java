package com.erlong.spring.io.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesDemo {

    public static void main(String[] args) throws IOException {
//        Path path = Paths.get("C:\\Users\\86176\\Desktop\\toutiao\\erlong");
//        Path directory = Files.createDirectory(path);
        Path path1 = Paths.get("C:\\Users\\86176\\Desktop\\toutiao\\a.txt");

        Path path2 = Paths.get("C:\\Users\\86176\\Desktop\\toutiao\\aa.txt");
        Files.copy(path1,path2);

    }

}
