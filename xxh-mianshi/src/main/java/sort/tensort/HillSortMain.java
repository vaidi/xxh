package sort.tensort;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * todo
 */
public class HillSortMain extends Sort{

    public static void main(String[] args) {
        System.out.println(Character.isDigit('h')+":"+Character.isLetter(';'));
        LinkedList<Integer> startLinked = new LinkedList();
        String mm ="kfkadshi";
        System.out.println(mm.charAt(0));
        String[] kk ={};
        Arrays.sort(kk, new MyCompator());
        Map<Integer,Integer> map = new HashMap<>();
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();

        String str =   Long.toBinaryString(Long.parseLong("167773121"));
        System.out.println(str+":"+str.length()+";;;"+Long.parseLong("10110111000111110100101100010110",2));
        int[] ints = hillSort(array);
        System.out.println(JSONObject.toJSON(ints));
    }

    private static int[] hillSort(int[] arr) {
        return arr;
    }
    static class MyCompator implements Comparator<String> {

        @Override
        public  int compare(String aa, String bb) {
            if(aa.length() <1 || bb.length() <1){
                return 1;
            }
            Character aChar = aa.charAt(0);
            Character bChar = bb.charAt(0);
            if (aChar.compareTo(bChar) > 0) {
                return 1;
            } else {
                return -1;
            }
        }

    }

}
