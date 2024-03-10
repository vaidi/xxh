package sort.tensort;

public abstract class Sort {

    public static int[] arr = {99, 1, 5, 4, 2, 6, 9, 3};
    public static int[] array = {3,2,7,4,9,11,12,23,22,12,123,131,1};
    public static void swapArr(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
