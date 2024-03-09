package design.visitor;

import lombok.Data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/19 15:55
 * @Description:
 */
@Data
public class Company extends Entry {

    private final String name = "xxh公司";

    private final String code =new Random().nextInt(Integer.MAX_VALUE)+"";

    /**
     * l
     * @param args
     */
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList();
        IntStream.range(0,100).forEach(e->{
            new Thread(()->{
                linkedList.add(e);
            }).start();
        });
        System.out.println(linkedList.size());






    }



    private List list = new LinkedList();

    @Override
    Iterator iterator() {
        return list.iterator();
    }

    @Override
    Entry add(Entry entry) {
        list.add(entry);
        return this;
    }

    @Override
    public void accept(PVector vector) {
        vector.visit(this);
    }
}
