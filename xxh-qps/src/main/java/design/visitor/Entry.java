package design.visitor;

import java.util.Iterator;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/19 16:05
 * @Description:
 */
public abstract class Entry<E> implements Element {

    abstract String getName();

    abstract String getCode();

    Entry add(Entry entry){
        throw new UnsupportedOperationException();
    }

    Iterator<E> iterator(){
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(){
        return "名字："+getName()+",code:"+getCode();
    }



}
