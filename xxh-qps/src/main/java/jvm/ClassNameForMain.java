package jvm;


/**
 * 简单的说java的class.forname()和classloader.loadClass()方法去别
 * 答：一个Java类加载到jvm中会经过三个步骤，装载（查询和导入类或接口的二进制数据），链接
 * （校验：检查导入类或接口的二进制数据的正确性；准备：给类的静态变量分配并初始化存储空间；解析：将符号引用转成直接引用），
 * 初始化（激化类的静态变量的初始化java代码和静态java代码块）
 * 所以通过传入的参数可以知道Class。forName方法执行之后已经对被 加载类的静态变量 分配完成了存储空间，而ClassLoader。loadClass方法并没有一定执行完
 * 链接这 一步，当想动态 加载一个类且这个类又存在静态代码块或者静态 变量而 你在加载时就想同时初始化这些静态代码块 则 应 偏向于使用 class。forname方法。
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class ClassNameForMain {

    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("");

    }

}
