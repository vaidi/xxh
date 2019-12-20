package lambda;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @Auther: YUANERL
 * @Date: 2019/12/19 14:59
 * @Description:
 */
@PFunctionalInterface
public interface PConsumer<T> {

    void accept(T t);



    default PConsumer<T> addThen(Consumer<? super T> after){
        Objects.requireNonNull(after);
        return (T t) ->{accept(t);after.accept(t);};
    }

}
