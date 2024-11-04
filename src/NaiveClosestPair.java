
import java.util.List;
public class NaiveClosestPair {
    public static double findClosetPair(List<Point> points){
        double minDist=Double.MIN_VALUE;

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                double dist = Point.distance(points.get(i), points.get(j));
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }

        return minDist;
    }
}
