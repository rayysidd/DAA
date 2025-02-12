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

        public String toString() {
            return "(" + x + ", " + y + ")";
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
    static double bruteForce(Point[] points, int left, int right, Point[] closestPair) {
        double minDist = Double.MAX_VALUE;
        for (int i = left; i < right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double dist = distance(points[i], points[j]);
                if (dist < minDist) {
                    minDist = dist;
                    closestPair[0] = points[i];
                    closestPair[1] = points[j];
                }
            }
        }
        return minDist;
    }

    // Function to calculate Euclidean distance
    static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    // Find the closest pair in a strip
    static double closestInStrip(Point[] strip, int size, double d, Point[] closestPair) {
        double minDist = d;
        Arrays.sort(strip, 0, size, new YComparator());

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < minDist; j++) {
                double dist = distance(strip[i], strip[j]);
                if (dist < minDist) {
                    minDist = dist;
                    closestPair[0] = strip[i];
                    closestPair[1] = strip[j];
                }
            }
        }
        return minDist;
    }

    // Main divide and conquer function
    static double closestPairRecursive(Point[] points, int left, int right, Point[] closestPair) {
        if (right - left <= 3) { // Base case: If 3 or fewer points, use brute force
            return bruteForce(points, left, right, closestPair);
        }

        int mid = (left + right) / 2;
        Point midPoint = points[mid];

        Point[] leftClosestPair = new Point[2];
        Point[] rightClosestPair = new Point[2];

        double dL = closestPairRecursive(points, left, mid, leftClosestPair);
        double dR = closestPairRecursive(points, mid + 1, right, rightClosestPair);

        double d;
        if (dL < dR) {
            d = dL;
            closestPair[0] = leftClosestPair[0];
            closestPair[1] = leftClosestPair[1];
        } else {
            d = dR;
            closestPair[0] = rightClosestPair[0];
            closestPair[1] = rightClosestPair[1];
        }

        // Create a strip containing points within distance d of the middle line
        Point[] strip = new Point[right - left + 1];
        int j = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < d) {
                strip[j++] = points[i];
            }
        }

        return Math.min(d, closestInStrip(strip, j, d, closestPair));
    }

    // Wrapper function to sort points and call recursive function
    static void findClosestPair(Point[] points) {
        Arrays.sort(points, new XComparator()); // Sort points by X
        Point[] closestPair = new Point[2];

        double minDist = closestPairRecursive(points, 0, points.length - 1, closestPair);

        System.out.println("All points:");
        for (Point p : points) {
            System.out.println(p);
        }
        System.out.println("\nThe closest pair is: " + closestPair[0] + " and " + closestPair[1]);
        System.out.println("The closest pair distance is: " + minDist);
    }

    // Driver Code
    public static void main(String[] args) {
        Point[] points = { new Point(2, 3), new Point(12, 30), new Point(40, 50),
                new Point(5, 1), new Point(12, 10), new Point(3, 4) };

        findClosestPair(points);
    }
}
