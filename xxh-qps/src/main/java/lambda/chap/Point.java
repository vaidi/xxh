package lambda.chap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Point {
    public final static Comparator<Point> compareByXAndThenY = comparing(Point::getX);
    private final int x;
    private final int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point moveRightBy(int x) {
        return new Point(this.x + x, this.y);
    }









    public static void main(String[] args) {
//        Point p1 = new Point(5, 5);
//        Point p2 = p1.moveRightBy(10);
//        assertEquals(15, p2.getX());
//        assertEquals(5, p2.getY());
        System.out.println("#$$$$$$$$$$$$$$$$$$$$$$############");
//        Point p1 = new Point(10, 15);
//        Point p2 = new Point(10, 20);
//        int result = Point.compareByXAndThenY.compare(p1 , p2);
//        System.out.println("result:"+result);
//        assertEquals(-1, result);

        List<Point> points = Arrays.asList(new Point(12, 2), null);
        points.stream().map(p -> p.getX()).forEach(System.out::println);

    }


}
