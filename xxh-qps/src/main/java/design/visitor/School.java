package design.visitor;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/19 15:54
 * @Description:
 */
@Data
public class School extends Entry {
    private final String name = "学校";
    private final String code = UUID.randomUUID().toString();
    private ArrayList list = new ArrayList();

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
