import java.util.Arrays;
import java.util.Comparator;

class ClosestPair {

    // Point class to represent coordinates
    static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Comparator to sort points by X coordinate
    static class XComparator implements Comparator<Point> {
        public int compare(Point a, Point b) {
            return Double.compare(a.x, b.x);
        }
    }

    // Comparator to sort points by Y coordinate
    static class YComparator implements Comparator<Point> {
        public int compare(Point a, Point b) {
            return Double.compare(a.y, b.y);
        }
    }

    // Brute-force method for small sets
    static double bruteForce(Point[] points, int left, int right) {
        double minDist = Double.MAX_VALUE;
        for (int i = left; i < right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double dist = distance(points[i], points[j]);
                minDist = Math.min(minDist, dist);
            }
        }
        return minDist;
    }

    // Function to calculate Euclidean distance
    static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    // Find the closest pair in a strip
    static double closestInStrip(Point[] strip, int size, double d) {
        double minDist = d;
        Arrays.sort(strip, 0, size, new YComparator());

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < minDist; j++) {
                minDist = Math.min(minDist, distance(strip[i], strip[j]));
            }
        }
        return minDist;
    }

    // Main divide and conquer function
    static double closestPairRecursive(Point[] points, int left, int right) {
        if (right - left <= 3) { // Base case: If 3 or fewer points, use brute force
            return bruteForce(points, left, right);
        }

        int mid = (left + right) / 2;
        Point midPoint = points[mid];

        double dL = closestPairRecursive(points, left, mid);
        double dR = closestPairRecursive(points, mid + 1, right);
        double d = Math.min(dL, dR);

        // Create a strip containing points within distance d of the middle line
        Point[] strip = new Point[right - left + 1];
        int j = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < d) {
                strip[j++] = points[i];
            }
        }

        return Math.min(d, closestInStrip(strip, j, d));
    }

    // Wrapper function to sort points and call recursive function
    static double findClosestPair(Point[] points) {
        Arrays.sort(points, new XComparator()); // Sort points by X
        return closestPairRecursive(points, 0, points.length - 1);
    }

    // Driver Code
    public static void main(String[] args) {
        Point[] points = {
                new Point(2.1, 3.2), new Point(12.3, 30.4), new Point(40.5, 50.1),
                new Point(5.8, 1.2), new Point(12.1, 10.5), new Point(3.9, 4.8)
        };

        double minDist = findClosestPair(points);
        System.out.println("The closest pair distance is: " + minDist);
    }
}
