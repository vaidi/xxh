package design.visitor;

import lombok.Data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/19 15:55
 * @Description:
 */
@Data
public class Company extends Entry {

    private final String name = "xxh公司";

    private final String code =new Random().nextInt(Integer.MAX_VALUE)+"";

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
