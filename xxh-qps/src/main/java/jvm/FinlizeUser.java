package jvm;

public class FinlizeUser {

    private static FinlizeUser user = null;
    @Override
    protected void finalize() throws Throwable {
        System.out.println("fullgc触发了这个方法运行"+user);
        user = this;
    }

    public static void main(String[] args) {
        FinlizeUser user = new FinlizeUser();
        System.out.println(user==null);
        user = null;
        System.gc();
        System.out.println(user==null);
        System.out.println(user==null);
        System.gc();
        System.out.println(user==null);
    }


}
