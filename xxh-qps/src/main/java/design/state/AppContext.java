package design.state;

public class AppContext {
    protected static final State STATE_GUEST = new GuestState();
    protected static final State STATE_LOGIN = new LoginState();
    protected static final State STATE_REAUTH = new ReauthState();
    private State currentSate = STATE_GUEST;

    {
        STATE_GUEST.setContext(this);
        STATE_LOGIN.setContext(this);
        STATE_REAUTH.setContext(this);
    }

    public void setState(State state) {
        this.currentSate = state;
    }

    public void favourite() {
        this.currentSate.favourite();
    }
    public void updatePwd() {
        this.currentSate.updatePwd();
    }


}
