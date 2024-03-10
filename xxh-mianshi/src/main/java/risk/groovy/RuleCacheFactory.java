package risk.groovy;

import groovy.lang.GroovyClassLoader;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.control.CompilationFailedException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class RuleCacheFactory {
    private RuleCacheFactory() {}


    /**
     * 规则脚本类缓存map  key-规则id value-规则脚本类
     */
    private static final Map<Long, Class> ruleClassMap = new ConcurrentHashMap<>();

    /**
     * 添加或更新规则脚本
     *
     * @param ruleId       规则id
     * @param groovyScript 规则的groovy脚本
     */
    public static void addOrUpdateRuleClass(Long ruleId, String groovyScript) {
        Class groovyClass = buildGroovyClass(groovyScript);
        ruleClassMap.put(ruleId, groovyClass);
    }

    /**
     * 获取规则脚本类
     *
     * @param ruleId 规则id
     *
     * @return 规则脚本类
     */
    public static Class getRuleClass(Long ruleId) {
        return ruleClassMap.get(ruleId);
    }

    /**
     * 删除规则脚本
     *
     * @param ruleId 规则id
     */
    public static void deleteRuleClass(Long ruleId) {
        ruleClassMap.remove(ruleId);
    }

    /**
     * 构建groovy类
     *
     * @param groovyScript groovy脚本
     *
     * @return groovy类
     *
     * @throws RuleException
     */
    private static Class buildGroovyClass(String groovyScript) throws RuleException {
        // 每个class都new一个loader，便于垃圾回收
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        try {
            // groovy脚本解析为class
            return groovyClassLoader.parseClass(groovyScript);
        } catch (CompilationFailedException e) {
            log.error("groovy脚本解析为class异常！groovyScript={}", groovyScript, e);
            throw new RuleException("groovy脚本解析为class异常！");
        } finally {
            try {
                groovyClassLoader.close();
            } catch (IOException e) {
                log.error("GroovyClassLoader.close()异常！", e);
            }
        }
    }




}
