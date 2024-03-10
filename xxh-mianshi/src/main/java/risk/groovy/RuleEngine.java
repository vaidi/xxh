package risk.groovy;

import groovy.lang.GroovyObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class RuleEngine {

    /**
     * 规则执行
     *
     * @param ruleId  规则id
     * @param context 规则上下文
     *
     * @return 执行结果
     */
    public static RuleResult execute(Long ruleId, Map<String, Object> context) {
        Class ruleClass = RuleCacheFactory.getRuleClass(ruleId);
        if (ruleClass == null) {
            log.error("规则class缓存中不存在！ruleId={}", ruleId);
            return null;
        }

        // 获取groovy对象
        GroovyObject groovyObject = null;
        try {
            groovyObject = (GroovyObject) ruleClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("创建GroovyObject实例异常！ruleId={}", ruleId, e);
            throw new RuleException("创建GroovyObject实例异常");
        }

        // 调用脚本的run方法且传参
        Object result = null;
        try {
            result = groovyObject.invokeMethod("run", new Object[]{ruleId, context});
        } catch (Exception e) {
            log.error("规则执行异常！ruleId={}, context={}", ruleId, context, e);
            throw new RuleException("规则执行异常");
        }

        return (RuleResult) result;
    }

}
