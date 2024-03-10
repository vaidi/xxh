package design.state;

public class GuestState extends State {

    @Override
    void favourite() {
        forwardToLoginPage();
        this.getContext().favourite();
    }

    @Override
    void updatePwd() {
        forwardToLoginPage();
        this.getContext().updatePwd();
    }

    private void forwardToLoginPage() {
        System.out.println("跳转到登陆页面");
        this.getContext().setState(AppContext.STATE_LOGIN);
    }

}
