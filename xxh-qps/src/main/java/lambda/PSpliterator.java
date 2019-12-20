package lambda;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/19 20:41
 * @Description:
 */
public interface PSpliterator<T> {


    boolean tryAdvance(Consumer<? super T> action);


    PSpliterator<T> trySplit();

}
