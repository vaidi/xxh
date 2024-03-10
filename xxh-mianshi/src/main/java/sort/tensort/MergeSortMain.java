package sort.tensort;

import com.alibaba.fastjson.JSONObject;

/**
 * 分组排序 分治和
 */
public class MergeSortMain extends Sort {
    public static void main(String[] args) {
        int[] ints = mergeSort(array);
        System.out.println(JSONObject.toJSON(ints));
     //  System.arraycopy();
    }






    private static int[] mergeSort(int[] array) {
        split( array, 0,array.length-1,new int[array.length]);
        return array;
    }
    private static void split(int[] arr,int left,int right,int[] newArr){
        if(left == right){
            return;
        }
        int m = (left+right)/2;
        split(arr,left,m,newArr);
        split(arr,m+1,right,newArr);
        mergeArray(arr,left,m,m+1,right,newArr);
        System.arraycopy(newArr,left,arr,left,right-left+1);
    }

    private static void mergeArray(int[] a1, int i, int iEnd, int j, int jEnd, int[] newArr) {
        int k = i;
        while (i <= iEnd || j <= jEnd){
            if(i > iEnd && j > jEnd){
                break;
            }
            if(i > iEnd){
                newArr[k++] = a1[j++];
                continue;
            }
            if(j > jEnd){
                newArr[k++] = a1[i++];
                continue;
            }
            if(a1[i] > a1[j]){
                newArr[k++] = a1[j++];
            }else{
                newArr[k++] = a1[i++];
            }
        }
    }

}
