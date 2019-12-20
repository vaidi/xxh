package design.visitor;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/19 15:47
 * @Description:
 * 访问者
 */
public abstract class PVector {

    abstract void visit(School school);

    abstract void visit(Company company);

}
