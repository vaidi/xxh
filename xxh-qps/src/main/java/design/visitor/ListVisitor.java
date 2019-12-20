package design.visitor;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/19 15:49
 * @Description:
 */
public class ListVisitor extends PVector {

    @Override
    void visit(School school) {
        System.out.println(school.toString());
    }

    @Override
    void visit(Company company) {
        System.out.println(company.toString());
    }
}
