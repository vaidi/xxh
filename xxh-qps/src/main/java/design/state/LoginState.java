package design.state;

public class LoginState extends State {
    @Override
    void favourite() {
        System.out.println("登陆成功，跳转到首页");
        System.out.println("收藏成功!");
    }

    @Override
    void updatePwd() {
        System.out.println("修改安全信息，需要再次验证您的身份！");
        this.getContext().setState(AppContext.STATE_REAUTH);
        this.getContext().updatePwd();
    }
}
