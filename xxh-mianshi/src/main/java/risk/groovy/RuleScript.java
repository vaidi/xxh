package risk.groovy;

import lombok.Data;

import java.util.List;

@Data
public class RuleScript {


    /**
     * 包名集合
     */
    private List<String> packageList;
    /**
     * 规则
     */
    private Rule rule;
    /**
     * run方法体
     */
    private String runBody;
    /**
     * 所有方法
     */
    private String allMethod;

    /**
     * 获取完整规则脚本
     *
     * @return 规则脚本
     */
    public String getFullScript() {
        String scriptTemplate = RuleScriptUtils.buildScriptTemplate();
        String importPackage = RuleScriptUtils.buildImportPackage(packageList);
        String ruleCode = rule.getRuleCode();
        return String.format(scriptTemplate, importPackage, ruleCode, ruleCode, runBody, allMethod);
    }


}
