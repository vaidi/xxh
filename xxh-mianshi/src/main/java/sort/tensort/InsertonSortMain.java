package sort.tensort;

import com.alibaba.fastjson.JSONObject;

/**
 * 插入排序
 * 1，第一个元素开始已经排序，
 * 2，取出下一个原属开始，在已经排序的原属中从后向前扫描
 */
public class InsertonSortMain extends Sort{


    public static void main(String[] args) {
        int[] ints = insertSortArray(array);
        System.out.println(JSONObject.toJSON(ints));
    }

    public static int[] insertSortArray(int[] array){
        for(int low =1; low < array.length;low++){
            int t = array[low];
            int i = low-1;
            while(i >= 0 && t < array[i]){
                array[i + 1] = array[i];
                i--;
            }
            if(i != low-1){
                array[i + 1] = t;
            }
        }
        return array;

    }



    public static int[] insertSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int current;
        for (int i = 1; i < array.length; i++) {
            current = array[i];
            int preIndex = i-1;
            while (preIndex >=0 && current < array[preIndex]){
                array[preIndex+1] = array[preIndex];
                preIndex--;
            }
            array[preIndex+1] = current;
        }
        return array;

    }


}
