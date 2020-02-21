package jvm;

public class Finalizer {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizer-->finalize()");
    }

    public static void main(String[] args) {
        Finalizer f = new Finalizer();
        f = null;

        System.gc();//手动请求gc
    }
}
