import java.util.Arrays;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Point> points = Arrays.asList(
                new Point(2, 3), new Point(12, 30), new Point(40, 50),
                new Point(5, 1), new Point(12, 10), new Point(3, 4)
        );
        double naiveResult = NaiveClosestPair.findClosetPair(points);
        System.out.println("Naive approach result: " + naiveResult);
        double divideAndConquerResult = DivideAndConquerClosestPair.findClosestPair(points);
        System.out.println("Divide and Conquer approach result: " + divideAndConquerResult);
    }
}