package risk.groovy;

import lombok.Data;

import java.util.List;

@Data
public class RuleResult {
    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 是否命中
     */
    private Boolean isHit;

    /**
     * 命中的表达式id集合
     */
    private List<Long> hitExpList;

}
