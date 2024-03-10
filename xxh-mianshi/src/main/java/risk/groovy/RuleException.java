package risk.groovy;

public class RuleException extends RuntimeException {

    private static final long serialVersionUID = 6799975089755988273L;

    public RuleException() {
        super("规则异常");
    }

    public RuleException(String message) {
        super(message);
    }

    public RuleException(String message, Throwable e) {
        super(message, e);
    }

    public RuleException(Throwable e) {
        super(e);
    }

}
