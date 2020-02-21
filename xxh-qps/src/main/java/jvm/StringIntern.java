package jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class StringIntern {

    //运行如下代码探究运行时常量池的位置
    public static void main(String[] args) throws Throwable {
        //用list保持着引用 防止full gc回收常量池
        List<String> list = new ArrayList<String>();
        int i = 0;
        while(true){
            System.out.println("t:");
            list.add(String.valueOf(i++).intern());
        }
    }
}
