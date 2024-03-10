package jvm.classforname;

public class ClassForMain {

    public static void main(String[] args) {
        System.out.println("-----开始forName-------");
        try {
            Class.forName("jvm.classforname.TestClassForName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-----结束forName-------");

//        System.out.println("-----开始forName init=false-------");
//        try {
//            Class.forName("com.test.TestClassForName",false,ClassLoader.getSystemClassLoader());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("-----结束forName init=false-------");
//
        System.out.println("-----开始loadClass-------");
        try {
            ClassLoader.getSystemClassLoader().loadClass("jvm.classforname.TestClassForName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-----结束loadClass-------");
    }

}
