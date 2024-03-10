package sort.tensort;

import com.alibaba.fastjson.JSONObject;

/**
 * 选择排序：
 * 不稳定排序
 * 交换次数少于冒泡
 * 先选择最大的放到最后，然后再进行交换
 */
public class SelectSortMain extends Sort {


    public static void main(String[] args) {
        sort(array);
        System.out.println(JSONObject.toJSON(array));
    }

    public static void sort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int max = i;
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[max]){
                    max = j;
                }
            }
            swapArr(arr,max,i);
        }
    }

}
