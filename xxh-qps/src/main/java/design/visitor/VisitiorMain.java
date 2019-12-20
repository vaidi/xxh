package design.visitor;

/**
 * @Auther: YUANEL
 * @Date: 2019/12/19 16:40
 * @Description:
 */
public class VisitiorMain {

    public static void main(String[] args) {
        /**
         * 访客模式
         * 优点：如果新增东西，只增加Element的继承类就可以了 符合开闭原则：对增加开放，对修改关闭
         * 缺点：就是对每一个访客元素进行修改
         */
        PVector vector = new ListVisitor();
        vector.visit(new School());//参观学校
        vector.visit(new Company());//参观企业
    }


}
