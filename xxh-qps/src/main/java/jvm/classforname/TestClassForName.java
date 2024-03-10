package jvm.classforname;

public class TestClassForName {

    private static int s = 1;
    private static final  int mm =100;
    static {
        System.out.println(s);
        System.out.println("static 代码块 s:"+s+" staticFinalM:"+mm);
        s = 2;
        System.out.println(s);
    }
    {
        System.out.println("动态方法 代码块 s:"+s+" staticFinalM:"+mm);
    }

    private static int t = getANum();
    public TestClassForName() {
        System.out.println("调用了构造函数");
        System.out.println("初始化 代码块 s:"+s+" staticFinalM:"+mm);
    }

    public static int getANum() {
        System.out.println("静态方法getANum执行了");
        return 2;
    }

}
