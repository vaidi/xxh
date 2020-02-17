package ali;

import java.awt.*;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Point p = new Point(100, 200);
        sum(p);
        System.gc();
        System.out.println("Press any key to continue");
        System.in.read();
        long sum = sum(p);
        System.out.println(sum);
        System.out.println("Press any key to continue2");
        System.in.read();
        sum = sum(p);
        System.out.println(sum);
        System.out.println("Press any key to exit");
        System.in.read();
    }
    private static long sum(Point p) {
        long sumLen = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sumLen += p.toString().length();
        }
        return sumLen;

    }
}
