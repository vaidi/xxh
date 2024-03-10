package risk.groovy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

import static risk.groovy.ScriptConstant.*;


@Slf4j
public class RuleScriptUtils {

    /**
     * 构建脚本模板
     *
     * @return 脚本模板
     */
    public static String buildScriptTemplate() {
        // %s
        // @groovy.transform.CompileStatic
        // public class Rule_%s {
        //  private static final Logger log = LoggerFactory.getLogger(Rule_%s.class);
        // 	// 上下文
        // 	private Map<String, Object> context = null;
        //  // 命中的表达式id集合
        //  private List<Long> hitExpList = new ArrayList<>();
        // 	// 执行入口
        // 	public RuleResult run(Long ruleId, Map<String, Object> context) {
        // %s
        // 	}
        // %s
        // }
        StringBuilder stringBuilder = new StringBuilder();

        // 1-%s：导包语句占位符
        stringBuilder.append("%s").append(NEWLINE);

        // 使用groovy的静态编译：禁用了Groovy动态编程特征，例如：生成的字节码文件中不再含有实现动态编程的字节码指令"invoke dynamic"；
        // 字节码文件也更小；生成的字节码将会和javac生成的字节码很相似，jvm执行性能接近。
        stringBuilder.append("@groovy.transform.CompileStatic").append(NEWLINE);

        // 类名，2-%s：规则code占位符
        stringBuilder.append("public class Rule_%s {").append(NEWLINE);

        // 成员变量声明，3-%s：规则code占位符
        stringBuilder.append(TAB).append("private static final Logger log = LoggerFactory.getLogger(Rule_%s.class);").append(NEWLINE);
        stringBuilder.append(TAB).append("// 上下文").append(NEWLINE);
        stringBuilder.append(TAB).append("private Map<String, Object> ").append(CONTEXT).append(" = null;").append(NEWLINE);
        stringBuilder.append(TAB).append("// 命中的表达式id集合").append(NEWLINE);
        stringBuilder.append(TAB).append("private List<Long> ").append(HIT_EXP_LIST).append(" = new ArrayList<>();").append(NEWLINE);

        // 执行入口 run()方法声明
        stringBuilder.append(TAB).append("// 执行入口").append(NEWLINE);
        stringBuilder.append(TAB).append("public ").append(RULE_RESULT_CLASS_NAME).append(" run(Long ruleId, Map<String, Object> context) {").append(NEWLINE);
        // 4-%s：run()方法体占位符
        stringBuilder.append("%s").append(NEWLINE);
        stringBuilder.append(TAB).append("}").append(NEWLINE);

        // 5-%s：所有方法的占位符
        stringBuilder.append("%s").append(NEWLINE);

        stringBuilder.append("}").append(NEWLINE);

        return stringBuilder.toString();
    }

    /**
     * 构建导包语句
     *
     * @param packageList 包名集合
     *
     * @return 导包语句
     */
    public static String buildImportPackage(List<String> packageList) {
        StringBuilder stringBuilder = new StringBuilder();
        // 公共类库
        stringBuilder.append("import java.util.*;").append(NEWLINE);
        stringBuilder.append("import org.slf4j.Logger;").append(NEWLINE);
        stringBuilder.append("import org.slf4j.LoggerFactory;").append(NEWLINE);
        stringBuilder.append("import risk.groovy.RuleResult;").append(NEWLINE);
        if (CollectionUtils.isEmpty(packageList)) {
            return stringBuilder.toString();
        }
        for (String packageName : packageList) {
            stringBuilder.append("import ").append(packageName).append(";").append(NEWLINE);
        }
        return stringBuilder.toString();
    }

    /**
     * 构建run()方法体
     *
     * @return run()方法体
     */
    public static String buildRunBody() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(TAB2).append("this.").append(CONTEXT).append(" = context;").append(NEWLINE);

        stringBuilder.append(NEWLINE);
        stringBuilder.append(TAB2).append("boolean isHit = exp_1() && exp_2();").append(NEWLINE);
        stringBuilder.append(NEWLINE);

        stringBuilder.append(TAB2).append("RuleResult ruleResult = new RuleResult();").append(NEWLINE);
        stringBuilder.append(TAB2).append("ruleResult.setRuleId(ruleId);").append(NEWLINE);
        stringBuilder.append(TAB2).append("ruleResult.setIsHit(isHit);").append(NEWLINE);
        stringBuilder.append(TAB2).append("ruleResult.setHitExpList(hitExpList);").append(NEWLINE);
        stringBuilder.append(TAB2).append("log.info(\"规则执行，入参：{}, 结果：{}\", context, ruleResult);").append(NEWLINE);
        stringBuilder.append(TAB2).append("return ruleResult;");

        return stringBuilder.toString();
    }

    /**
     * 构建除run()方法外的其它所有方法
     *
     * @return 所有方法
     */
    public static String buildAllMethod() {
        StringBuilder stringBuilder = new StringBuilder();

        String expMethod = buildExpMethod(1L);
        String expMethod2 = buildExpMethod2(2L);
        String operatorMethod = buildOperatorMethod();
        String operatorMethod2 = buildOperatorMethod2();

        stringBuilder.append(expMethod);
        stringBuilder.append(expMethod2);
        stringBuilder.append(operatorMethod);
        stringBuilder.append(operatorMethod2);

        return stringBuilder.toString();
    }

    public static String buildExpMethod(Long expId) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(TAB).append("// 表达式").append(expId).append(NEWLINE);
        stringBuilder.append(TAB).append("private boolean exp_").append(expId).append("() {").append(NEWLINE);
        stringBuilder.append(TAB2).append("String leftVar = (String) context.get(\"name\");").append(NEWLINE);
        stringBuilder.append(TAB2).append("String rightVar = \"程序员\";").append(NEWLINE);
        stringBuilder.append(TAB2).append("boolean expHit = equalsString(leftVar, rightVar);").append(NEWLINE);
        stringBuilder.append(TAB2).append("if (expHit) {").append(NEWLINE);
        stringBuilder.append(TAB3).append("hitExpList.add(1L);").append(NEWLINE);
        stringBuilder.append(TAB2).append("}").append(NEWLINE);
        stringBuilder.append(TAB2).append("return expHit;").append(NEWLINE);
        stringBuilder.append(TAB).append("}").append(NEWLINE);

        return stringBuilder.toString();
    }

    public static String buildExpMethod2(Long expId) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(TAB).append("// 表达式").append(expId).append(NEWLINE);
        stringBuilder.append(TAB).append("private boolean exp_").append(expId).append("() {").append(NEWLINE);
        stringBuilder.append(TAB2).append("Long leftVar = (Long) context.get(\"age\");").append(NEWLINE);
        stringBuilder.append(TAB2).append("Long rightVar = 35L;").append(NEWLINE);
        stringBuilder.append(TAB2).append("boolean expHit = greaterThanLong(leftVar, rightVar);").append(NEWLINE);
        stringBuilder.append(TAB2).append("if (expHit) {").append(NEWLINE);
        stringBuilder.append(TAB3).append("hitExpList.add(2L);").append(NEWLINE);
        stringBuilder.append(TAB2).append("}").append(NEWLINE);
        stringBuilder.append(TAB2).append("return expHit;").append(NEWLINE);
        stringBuilder.append(TAB).append("}").append(NEWLINE);

        return stringBuilder.toString();
    }

    public static String buildOperatorMethod() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(TAB).append("// 操作符函数").append(NEWLINE);
        stringBuilder.append(TAB).append("private boolean equalsString(String leftVar, String rightVar) {").append(NEWLINE);
        stringBuilder.append(TAB2).append("if (leftVar == null) {").append(NEWLINE);
        stringBuilder.append(TAB3).append("return rightVar == null;").append(NEWLINE);
        stringBuilder.append(TAB2).append("}").append(NEWLINE);
        stringBuilder.append(TAB2).append("return leftVar.equals(rightVar);").append(NEWLINE);
        stringBuilder.append(TAB).append("}").append(NEWLINE);

        return stringBuilder.toString();
    }

    public static String buildOperatorMethod2() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(TAB).append("// 操作符函数").append(NEWLINE);
        stringBuilder.append(TAB).append("private boolean greaterThanLong(Long leftVar, Long rightVar) {").append(NEWLINE);
        stringBuilder.append(TAB2).append("if (leftVar == null) {").append(NEWLINE);
        stringBuilder.append(TAB3).append("return false;").append(NEWLINE);
        stringBuilder.append(TAB2).append("}").append(NEWLINE);
        stringBuilder.append(TAB2).append("return leftVar.compareTo(rightVar) > 0;").append(NEWLINE);
        stringBuilder.append(TAB).append("}").append(NEWLINE);

        return stringBuilder.toString();
    }

}
