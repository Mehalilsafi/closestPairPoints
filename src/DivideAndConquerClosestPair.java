import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

 public class DivideAndConquerClosestPair {

    public static double findClosestPair(List<Point> points) {
        List<Point> pointsByX = new ArrayList<>(points);
        List<Point> pointsByY = new ArrayList<>(points);

        // Sort points by x-coordinate and y-coordinate
        pointsByX.sort(Comparator.comparingDouble(p -> p.x));
        pointsByY.sort(Comparator.comparingDouble(p -> p.y));

        return closestPairRecursive(pointsByX, pointsByY);
    }

    private static double closestPairRecursive(List<Point> Px, List<Point> Py) {
        int n = Px.size();

        if (n <= 3) {
            return bruteForce(Px);
        }

        int mid = n / 2;
        Point midpoint = Px.get(mid);

        List<Point> leftPx = new ArrayList<>(Px.subList(0, mid));
        List<Point> rightPx = new ArrayList<>(Px.subList(mid, n));

        List<Point> leftPy = new ArrayList<>();
        List<Point> rightPy = new ArrayList<>();

        for (Point p : Py) {
            if (p.x <= midpoint.x) {
                leftPy.add(p);
            } else {
                rightPy.add(p);
            }
        }

        double dl = closestPairRecursive(leftPx, leftPy);
        double dr = closestPairRecursive(rightPx, rightPy);

        double delta = Math.min(dl, dr);

        List<Point> strip = new ArrayList<>();
        for (Point p : Py) {
            if (Math.abs(p.x - midpoint.x) < delta) {
                strip.add(p);
            }
        }

        return Math.min(delta, stripClosest(strip, delta));
    }

    private static double bruteForce(List<Point> points) {
        double minDist = Double.MAX_VALUE;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = Point.distance(points.get(i), points.get(j));
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }

    private static double stripClosest(List<Point> strip, double delta) {
        double minDist = delta;

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < minDist; j++) {
                double dist = Point.distance(strip.get(i), strip.get(j));
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }
}

