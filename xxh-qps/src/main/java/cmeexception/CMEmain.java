package cmeexception;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 *
 * fail-fast快速失效机制
 *
 *
 */
public class CMEmain {


    public static void main(String[] args) {
        List<String> userNames = new CopyOnWriteArrayList<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};
        userNames.iterator();
        for (String userName : userNames) {
            if (userName.equals("Hollis")) {
                userNames.remove(userName);
            }
        }

        System.out.println(userNames);
    }
}
