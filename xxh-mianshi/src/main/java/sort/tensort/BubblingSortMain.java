package sort.tensort;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

/**
 * 冒泡排序:
 * 其实排序排的是最后面的，最后面的在一轮就固定啦
 */
public class BubblingSortMain extends Sort {


    public static void main(String[] args) {
        sort(arr);
        System.out.println(JSONObject.toJSON(arr));
        int[] arr = {3,2,7,4,9,11,12,23,22,12,123,131,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        for(int i =0;i < arr.length-1;i++){
            for(int j =0;j < arr.length-i-1;j++){
                if(arr[j] > arr[j+1]){
                    swapArr(arr,j+1,j);
                }
            }
        }
    }

}
