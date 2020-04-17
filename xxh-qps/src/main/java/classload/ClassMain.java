package classload;
/*
 Class.forName 加载类是将类进了初始化，而 ClassLoader 的 loadClass 并没有对类进行初始化，只是把类加载到了虚拟机中。
 */
public class ClassMain {

    public static int  num =23;
    public static final int  num11 =23;
    public int num33 =0;
    static {
        System.out.println("执行了静态方法");
    }

    public int getNum33() {
        return num33;
    }

    public void setNum33(int num33) {
        System.out.println("执行了初始化操作");
        this.num33 = 33;
    }
}
