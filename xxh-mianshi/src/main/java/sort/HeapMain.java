package sort;

import java.util.Arrays;

/**
 * 堆相关的
 */
public class HeapMain {

    public static void main(String[] args) {
        int[] array = {99, 1, 5, 4, 2, 6, 9, 3};
        MaxHeap maxHeap = new MaxHeap(array);
        System.out.println("最大堆："+ Arrays.toString(maxHeap.array));
        int[] arr = new int[99];
        for (int i = 98; i >= 0; i--) {
            arr[i] = i;
        }
        MinHeap minHeap = new MinHeap(arr);
        System.out.println(Arrays.toString(minHeap.array));

    }


    static class MaxHeap {
        int[] array;
        int size;

        public MaxHeap(int capacity) {
            this.array = new int[capacity];
        }

        public MaxHeap(int[] array) {
            this.array = array;
            this.size = array.length;
            hepify();

        }

        /**
         * 健堆
         */
        private void hepify() {
            //1,找到最后一个非叶子节点  size/2-1
            for (int i = size / 2 - 1; i >= 0; i--) {
                //下潜
                down(i);
            }
        }

        private boolean offer(int num) {
            if (size == array.length) {
                return false;
            }
            up(num);
            size++;
            return true;
        }

        /**
         * 上浮操作
         * @param offered
         */
        private void up(int offered) {
            //最小的
            int child = size;
            while (child > 0) {
                int parent = (child - 1) / 2;
                if (offered > array[parent]) {
                    //置换
                    array[child] = array[parent];
                } else {
                    break;
                }
                child = parent;
            }
            array[child] = offered;
        }


        private void down(int parent) {
            int left = parent * 2 + 1;
            int right = parent * 2 + 2;
            int max = parent;
            if (left < size && array[left] < array[max]) {
                max = left;
            }
            if (right < size && array[right] < array[max]) {
                max = right;
            }
            if (max != parent) {
                swap(max, parent);
                down(max);
            }
        }

        private void swap(int max, int parent) {
            int min = array[parent];
            array[parent] = array[max];
            array[max] = min;
        }

        public int remove(int index) {
            int remove = array[index];
            swap(index, size - 1);
            size--;
            down(index);
            return remove;
        }

        public int pop() {
            int top = array[0];
            swap(0, size - 1);
            size--;
            down(0);
            return top;

        }

        public int peek() {
            return array[0];
        }
    }


    static class MinHeap {
        int[] array;
        int size;

        public MinHeap(int capition) {
            array = new int[capition];
        }

        public MinHeap(int[] array) {
            this.array = array;
            this.size = array.length;
            hepify();
        }

        private void hepify() {
            int noLeaf = size;
            for (int i = noLeaf - 1; i >= 0; i--) {
                upNode(i);
            }


        }

        private void upNode(int child) {
            if (child < 1) {
                return;
            }
            int parent = (child - 1) / 2;
            int min = child;
            System.out.println(parent + ":" + min);
            if (array[parent] > array[min]) {
                min = parent;
            }
            if (min != child) {
                swap(min, child);
                upNode(min);
            }

        }

        private void swap(int max, int parent) {
            int min = array[parent];
            array[parent] = array[max];
            array[max] = min;
        }
    }

}
