package classload;

public class ClassLoadMain {

    public static void main(String[] args) throws ClassNotFoundException {

//        Class.forName("classload.ClassMain");
//        System.out.println(ClassMain.num+"num1:"+ClassMain.num11+",num33;");
        ClassLoader.getSystemClassLoader().loadClass("classload.ClassMain");
        System.out.println(ClassMain.num+"num1:"+ClassMain.num11+",num33;");

    }


}
