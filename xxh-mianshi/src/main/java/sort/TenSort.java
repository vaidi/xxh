package sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 十大排序算法
 */
public class TenSort {

    public static void main(String[] args) {

        int[] arr = {99, 1, 5, 4, 2, 6, 9, 3};
        heapMaxSort(arr);
        System.out.println( "堆排序结果:"+JSONObject.toJSON(arr));
        quickSortLomuto(arr);
    }


    /**
     * 快速排序
     * 单边循环快排
     *
     * @param a 基准点
     *          j 找到比基准点的小  找到小的和i交换
     *          i的左侧是比基准点小的   当前指针属于代交换
     */
    public static void quickSortLomuto(int[] a) {
        quck(a, 0, a.length - 1);
    }

    public static void quck(int[] a, int left, int h) {
        if (left >= h) {
            return;
        }
        int p = partition(a, left, h);
        quck(a, left, p - 1);
        quck(a, p + left, h);

    }

    public static int partition(int[] a, int k, int h) {
        int pv = a[h];
        int i = k;
        for (int j = i; j < h; j++) {
            if (a[j] < pv) {
                swap(a, i++, j);
            }
        }
        //基准点与i交换
        swap(a, h, i);
        return i;
    }


    public static void insert(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int t = a[i];
            int j = i - 1;
            while (j >= 0) {
                if (t < a[j]) {
                    a[j + 1] = a[j];
                }
                j--;
            }
        }
    }


    /**
     * 选择排序 不稳定
     *
     * @param a
     */
    public static void selection(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int s = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[s] > a[j]) {
                    s = j;
                }
            }
            if (s != i) {
                swap(a, s, i);
            }
        }
    }

    /**
     * 简单排序
     *
     * @param data
     */
    public static void simpleSort(int[] data) {
        int len = data.length;
        for (int i = 0; i < len; i++) {
            int position = i;
            for (int j = i + 1; j < len; j++) {
                if (data[position] - data[j] > 0) {
                    position = j;
                }
                int temp = data[i];
                data[i] = data[position];
                data[position] = temp;
            }
        }


    }


    /**
     * 插入排序
     *
     * @param arry
     */
    public static void insetSort(int[] arry) {
        int length = arry.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && arry[j] - arry[j - 1] < 0; j--) {
                int temp = arry[j];
                arry[j] = arry[j - 1];
                arry[j - 1] = temp;
            }
        }
    }


    /**
     * 最大堆
     * @param arr
     */
    public static void heapMaxSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            downHeap(i, arr);
        }
    }

    /**
     * 对弹出去的那个进行交换
     * @param arr
     * @return
     */
    public static int popMaxHeapy(int[] arr){
        int popValue = arr[0];
        swap(arr,arr.length-1,0);
        downHeap(0,arr);
        return popValue;
    }


    /**
     * 下潜
     *
     * @param parent
     * @param arr
     */
    private static void downHeap(int parent, int[] arr) {
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;
        int max = parent;
        if(left <= arr.length-1 && arr[left] > arr[max]){
            max = left;
        }
        if(right <= arr.length-1 && arr[right] > arr[max]){
            max = right;
        }
        if(max != parent){
            swap(arr,max,parent);
            downHeap(max,arr);
        }
    }


    /**
     * 堆排序
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);

        }
        // 上述逻辑，建堆结束
        // 下面，开始排序逻辑
        for (int j = arr.length - 1; j > 0; j--) {
            // 元素交换,作用是去掉大顶堆
            // 把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
            swap(arr, 0, j);
            // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
            // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
            // 而这里，实质上是自上而下，自左向右进行调整的
            adjustHeap(arr, 0, j);
        }

    }

    public static void adjustHeap(int[] array, int i, int length) {
        // 先把当前元素取出来，因为当前元素可能要一直移动
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {  //2*i+1为左子树i的左子树(因为i是从0开始的),2*k+1为k的左子树
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            //如果发现结点(左右子结点)大于根结点，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，循环对子节点所在的树继续进行判断
                i = k;
            } else {  //不用交换，直接终止循环
                break;
            }
        }
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a   元素的下标
     * @param b   元素的下标
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void maopao(int[] numArr) {
        for (int i = 0; i < numArr.length - 1; i++) {
            for (int j = 0; j < numArr.length - i - 1; j++) {
                if (numArr[j] > numArr[j + 1]) {
                    int temp = numArr[j];
                    numArr[j] = numArr[j + 1];
                    numArr[j + 1] = temp;
                }
            }
        }
    }

}
