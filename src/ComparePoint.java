import java.util.Comparator;

public class ComparePoint implements Comparator<Point> {

    /**
     * compares two points
     * @param p1 the first point
     * @param p2 the second point
     * @return compare indicator
     */
    public int compare(Point p1, Point p2) {
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY())
            return 0;
        else if (p1.getX() < p2.getX() || (p1.getX() == p2.getX() && p1.getY() < p2.getY()))
            return -1;
        else
            return 1;
    }
}
