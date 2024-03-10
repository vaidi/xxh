package risk.groovy;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RuleEngineTest {

    /**
     * 规则执行测试
     */
    public static void main(String[] args) {
        // 获取规则
        Rule rule = new Rule();
        rule.setRuleId(1L);
        rule.setRuleCode("code1");
        rule.setRuleName("规则1");

        // 获取规则脚本
        RuleScript ruleScript = new RuleScript();

        ruleScript.setRule(rule);

        String runBody = RuleScriptUtils.buildRunBody();
        ruleScript.setRunBody(runBody);

        String allMethod = RuleScriptUtils.buildAllMethod();
        ruleScript.setAllMethod(allMethod);

        String groovyScript = ruleScript.getFullScript();
        log.info("脚本：\n{}", groovyScript);
        // 缓存规则class对象
        RuleCacheFactory.addOrUpdateRuleClass(rule.getRuleId(), groovyScript);

        // 获取规则上下文
        Map<String, Object> context = new HashMap<>();
        context.put("name", "程序员");
        context.put("age", 18);

        // 执行规则
        RuleResult ruleResult = RuleEngine.execute(rule.getRuleId(), context);
        System.out.println("result:"+ JSONObject.toJSONString(ruleResult));

        context.put("name", "程序员");
        context.put("age", 40);
        RuleResult ruleResult2 = RuleEngine.execute(rule.getRuleId(), context);
        System.out.println("ruleResult2:"+ JSONObject.toJSONString(ruleResult2));
    }



}
