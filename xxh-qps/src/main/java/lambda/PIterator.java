package lambda;

import java.util.Objects;

/**
 * @Auther: YUANERL
 * @Date: 2019/12/19 18:38
 * @Description:
 */
public interface PIterator<T> {

    boolean hasNext();

    T next();


    default void forEachRemaining(PConsumer<? super T> pConsumer){
        Objects.requireNonNull(pConsumer);
        while (hasNext()){
            pConsumer.accept(next());
        }
    }


}
