package guava;

import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 用来测试google guava包下一些好用的api
 */
public class GuavaTest {















    @Test
    public void test01(){
        String[] array = {"1","asdas","yatou","xinhua","iloveyou","我爱你"};
        List list = Arrays.asList(array);
        String joinStr = Joiner.on(" || ").join(list);
        System.out.println("joinStr:"+joinStr);
    }

}
