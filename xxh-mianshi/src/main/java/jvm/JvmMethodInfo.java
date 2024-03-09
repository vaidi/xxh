package jvm;

public class JvmMethodInfo {

    private int number;

    public void printHello(int age){
        String mm ="mm";
        int n =2;

        System.out.println("hello world");
    }

    /**
     *  Last modified 2023-9-9; size 642 bytes
     *   MD5 checksum 842cd77e4a1ce02c042cb64cdfa6ff81
     *   Compiled from "JvmMethodInfo.java"
     * public class jvm.JvmMethodInfo
     *   minor version: 0
     *   major version: 52
     *   flags: ACC_PUBLIC, ACC_SUPER
     * Constant pool: // 常量池5
     *    #1 = Methodref          #7.#26         // java/lang/Object."<init>":()V
     *    #2 = String             #20            // mm
     *    #3 = Fieldref           #27.#28        // java/lang/System.out:Ljava/io/PrintStream;
     *    #4 = String             #29            // hello world
     *    #5 = Methodref          #30.#31        // java/io/PrintStream.println:(Ljava/lang/String;)V
     *    #6 = Class              #32            // jvm/JvmMethodInfo
     *    #7 = Class              #33            // java/lang/Object
     *    #8 = Utf8               number
     *    #9 = Utf8               I
     *   #10 = Utf8               <init>
     *   #11 = Utf8               ()V
     *   #12 = Utf8               Code
     *   #13 = Utf8               LineNumberTable
     *   #14 = Utf8               LocalVariableTable
     *   #15 = Utf8               this
     *   #16 = Utf8               Ljvm/JvmMethodInfo;
     *   #17 = Utf8               printHello
     *   #18 = Utf8               (I)V
     *   #19 = Utf8               age
     *   #20 = Utf8               mm
     *   #21 = Utf8               Ljava/lang/String;
     *   #22 = Utf8               n
     *   #23 = Utf8               MethodParameters
     *   #24 = Utf8               SourceFile
     *   #25 = Utf8               JvmMethodInfo.java
     *   #26 = NameAndType        #10:#11        // "<init>":()V
     *   #27 = Class              #34            // java/lang/System
     *   #28 = NameAndType        #35:#36        // out:Ljava/io/PrintStream;
     *   #29 = Utf8               hello world
     *   #30 = Class              #37            // java/io/PrintStream
     *   #31 = NameAndType        #38:#39        // println:(Ljava/lang/String;)V
     *   #32 = Utf8               jvm/JvmMethodInfo
     *   #33 = Utf8               java/lang/Object
     *   #34 = Utf8               java/lang/System
     *   #35 = Utf8               out
     *   #36 = Utf8               Ljava/io/PrintStream;
     *   #37 = Utf8               java/io/PrintStream
     *   #38 = Utf8               println
     *   #39 = Utf8               (Ljava/lang/String;)V
     * {
     *   public jvm.JvmMethodInfo();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=1, locals=1, args_size=1
     *          0: aload_0
     *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *          4: return
     *       LineNumberTable:
     *         line 3: 0
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0       5     0  this   Ljvm/JvmMethodInfo;
     *
     *   public void printHello(int);
     *     descriptor: (I)V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=2, locals=4, args_size=2
     *          0: ldc           #2                  // String mm
     *          2: astore_2
     *          3: iconst_2
     *          4: istore_3
     *          5: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *          8: ldc           #4                  // String hello world
     *         10: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *         13: return
     *       LineNumberTable:
     *         line 8: 0
     *         line 9: 3
     *         line 11: 5
     *         line 13: 13
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      14     0  this   Ljvm/JvmMethodInfo;
     *             0      14     1   age   I
     *             3      11     2    mm   Ljava/lang/String;
     *             5       9     3     n   I
     *     MethodParameters:
     *       Name                           Flags
     *       age
     * }
     * SourceFile: "JvmMethodInfo.java"
     */
}
