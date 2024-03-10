package risk.groovy;

import lombok.Data;

@Data
public class Rule {

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 规则编码
     */
    private String ruleCode;

    /**
     * 规则名
     */
    private String ruleName;


}
