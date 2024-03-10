package algoritohm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArry implements Iterable<Integer> {
    public static void main(String[] args) {
        int size =0;
        System.out.println(size++);


    }

    private int size =0;
    private int capacity =8;
    private int[] array = new int[capacity];

    public void addLast(int element){
        add(size++,element);
    }
    public void add(int index,int element){
      //  array= Arrays.copyOf(array, index + 1);
        array[index] = element;
        size++;
    }

    public void forEach1(Consumer<Integer> consumer){
        for (int i : array) {
            consumer.accept(i);
        }
    }
    public int remove(int index){
        int i = array[index];
        System.arraycopy(array,index+1,array,index,size-index-1);

        return i;


    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i =0;
            @Override
            public boolean hasNext() {
                return i < size;
            }
            @Override
            public Integer next() {
                return array[i++];
            }
        };
    }

    public IntStream stream(){
        return IntStream.of(Arrays.copyOfRange(array,0,size));
    }

}
