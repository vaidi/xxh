package jvm;

import org.openjdk.jol.info.ClassLayout;

public class SmallObjMain {

    /**
     * 用来查看small对象占用的字节
     * @param args
     * jvm.SmallObj object internals:
     * OFF  SZ   TYPE DESCRIPTION               VALUE
     *   0   8        (object header: mark)     0x0000000000000001 (non-biasable; age: 0) 对象头
     *   8   4        (object header: class)    0x2000c143 类型指针
     *  12   4    int SmallObj.mm               0
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
     *
     */
    public static void main(String[] args) {
        SmallObj smallObj = new SmallObj();
        String toPrintable = ClassLayout.parseInstance(smallObj).toPrintable();
        System.out.println(toPrintable);
    }

}
