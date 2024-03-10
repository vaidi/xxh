package sort.tensort;

import com.alibaba.fastjson.JSONObject;

/**
 * 快速排序
 */
public class QuickSortMain extends Sort {

    public static void main(String[] args) {

        Integer.parseInt("ww",12);

        quickSort(arr);
        System.out.println(JSONObject.toJSON(arr));

    }


    public static void quickSort(int[] arr) {
        quickSortSon(arr, 0, arr.length - 1);
    }

    private static void quickSortSon(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        //基准点元素的索引
        int p = partitionArr(arr, left, right);
        quickSortSon(arr, left, p-1);
        quickSortSon(arr, p+1, right);
    }

    private static int partitionArr(int[] arr, int left, int right) {
        int pv = arr[right];
        int i = left;
        for (int j = i; j < right; j++) {
            if (arr[j] < pv){
                swapArr(arr,i++,j);
            }
        }
        swapArr(arr,right,i);
        return i;
    }

    public static void swapArr(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
