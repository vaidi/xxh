package design.state;

public abstract class State {
    private AppContext context;
    public void setContext(AppContext context) {
        this.context = context;
    }
    public AppContext getContext() {
        return context;
    }

    abstract void favourite();
    abstract void updatePwd();
}
