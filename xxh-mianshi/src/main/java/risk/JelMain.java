package risk;

import org.apache.commons.jexl3.*;

import java.util.HashMap;
import java.util.Map;

/**
 * jexl3
 */
public class JelMain {
    public static void main(String[] args) {



        JexlEngine JEXL = new JexlBuilder().cache(1000).strict(true).create();

// 根据表达式字符串来创建一个关于年龄的规则
        JexlExpression ageExpression = JEXL.createExpression("age > 30 || name > 20");

// 获取需要的参数，java 代码太长了，简写一下
        Map<String, Object> parameters  = new HashMap<>();
        parameters.put("age",10);
        parameters.put("name",40);
        // 执行一下
        JexlContext jexlContext = new MapContext(parameters);
        boolean result = (boolean) ageExpression.evaluate(jexlContext);
        System.out.println(result);


    }

}
