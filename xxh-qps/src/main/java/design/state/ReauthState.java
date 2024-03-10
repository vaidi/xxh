package design.state;

public class ReauthState  extends State{
    @Override
    void favourite() {
        System.out.println("收藏成功！");
    }

    @Override
    void updatePwd() {
        System.out.println("密码修改成功！");
    }
}
