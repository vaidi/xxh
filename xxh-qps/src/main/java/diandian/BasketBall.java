package diandian;

import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;
import lombok.Data;

@Data
public class BasketBall implements Cloneable {
    private String num;
    private int size;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
class MainTest{


    public static void main(String[] args) throws CloneNotSupportedException {
        BasketBall basketBall = new BasketBall();
        basketBall.setNum("10");
        basketBall.setSize(1);
        BasketBall clone = (BasketBall)basketBall.clone();
        System.out.println(basketBall.toString());


    }

}