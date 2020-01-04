package lambda.chap2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class PFilteringApples {

    public static void main(String[] args) {
        List nums = Arrays.asList("1","2","3","4","5");
        PFilter pFilter = new PFilterImplter();
        Collection collection = pFilter.filter(nums,e->Integer.valueOf(e.toString())-3>0);
        collection.forEach(System.out::println);
        Collection collection1 = FilterUtil.filter(nums, e -> Integer.valueOf(e.toString()) - 3 > 0);
        collection1.forEach(System.out::println);
    }


}


interface PFilter<T> {
    <T> Collection<T> filter(Collection<T> c, Predicate<T> p);
}

class PFilterImplter implements PFilter {
    @Override
    public Collection filter(Collection c, Predicate p) {
        Collection collection = new ArrayList();
        for (Object o : c) {
            if (p.test(o)) {
                collection.add(o);
            }
        }
        return collection;
    }
}

class FilterUtil {

    public static Collection filter(Collection c, Predicate p) {
        Collection collection = new ArrayList();
        for (Object o : c) {
            if (p.test(o)) {
                collection.add(o);
            }
        }
        return collection;
    }

}