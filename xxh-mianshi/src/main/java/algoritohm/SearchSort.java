package algoritohm;

public class SearchSort {


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch(arr, 1));
        System.out.println(binarySearch(arr, 6));


    }


    /**
     * 二分查找
     * @param taget
     * @param num
     * @return
     */
    public static int binarySearch(int[] taget, int num) {
        int length = taget.length - 1;
        int index = 0;
        while (index <= length) {
            int middle = (length + index) / 2;
            if (num == taget[middle]) {
                return middle;
            }
            if (num > taget[middle]) {
                index = middle + 1;
            } else {
                length = middle - 1;
            }
        }
        return -1;
    }
}
