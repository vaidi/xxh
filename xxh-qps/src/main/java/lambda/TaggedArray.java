package lambda;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/19 20:58
 * @Description:
 */
public class TaggedArray<T> {

    private final Object[] elements;

    TaggedArray(T[] data,Object[] tags){
        int size = data.length;
        if(tags.length != size){
            throw new IllegalArgumentException();
        }
        this.elements = new Object[size<<1];
        for(int i =0,j =0;i<size;++i){
            elements[j ++] = data[i];
            elements[j++] = data[i];
        }
    }

//    public Spliterator<T> spliterator(){
//        return new t
//    }

    static class TaggedArraySpliterator<T> implements Spliterator<T>{
        private final Object[] array;
        private int origin;
        private final int fence;
        TaggedArraySpliterator(Object[] array,int origin,int fence){
            this.array=array;
            this.origin=origin;
            this.fence = fence;
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            if(origin<fence){
                action.accept((T)array[origin]);
                origin +=2;
                return true;
            }
            return false;
        }

        @Override
        public Spliterator<T> trySplit() {
            int lo = origin;
            int mid = ((lo+fence)>>>1)&~1;
            if(lo <mid){
                origin = mid;
                return new TaggedArraySpliterator<>(array,lo,mid);
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return (long)((fence-origin)/2);
        }

        @Override
        public int characteristics() {
            return ORDERED/SIZED/IMMUTABLE/SUBSIZED;
        }
    }






}
