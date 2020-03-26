package my;

import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;

import javax.print.DocFlavor;
import java.util.*;

public class RemoveEqual {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add(",1234adwadwq");
        list.add("123adwadwq");
        List list1 = removeEqualStr(list);
        System.out.println(list1.size());


    }


    /**
     * 存在这一个bug要修复一下
     *
     * @param list
     * @return
     */
    public static List<String> removeEqualStr(List<String> list) {
        if (list == null || list.size() == 0) {
            return Collections.emptyList();
        }
        String[] array = {"012", "123", "234", "345", "456", "567", "678", "789"};
        List copyList = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (StringUtils.isEmpty(str)) {
                continue;
            }
            //可以对是否全是字符串检查进行一步continue
            for (String s : array) {
                if (str.contains(s)) {
                    Integer num = map.getOrDefault(s, 0);
                    if (num != 0) {
                        copyList.add(str);
                    }
                    map.put(s, ++num);
                    break;
                }
            }
        }
        if (copyList != null && copyList.size() > 0) {
            list.removeAll(copyList);
        }
        return list;
    }


}
