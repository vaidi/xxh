package ali;

import java.util.*;

public class Demo {

    public static void main(String[] args) {


    }


    public List<List<Integer>> contain(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return null;
        }
        List<List<Integer>> sumList = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    int m = arr[i];
                    int n = arr[j];
                    int l = arr[k];
                    if (m + l + n == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(m);
                        list.add(m);
                        list.add(l);
                        sumList.add(list);
                    }
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        Object object = new Object();
        Iterator<List<Integer>> iterator = sumList.iterator();
        while (iterator.hasNext()) {
            List<Integer> mm = iterator.next();
            Collections.sort(mm);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mm.size(); i++) {
                sb.append(mm.get(i) + ",");
            }
            if (map.containsKey(sb.toString())) {
                iterator.remove();
            } else {
                map.put(sb.toString(), object);
            }
        }
        return sumList;


    }

}
