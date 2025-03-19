import java.util.Arrays;
import java.util.Comparator;

class ActivitySelection {

    static void selectActivities(int[][] activities) {
        Arrays.sort(activities, Comparator.comparingInt(a -> a[1]));

        System.out.println("Selected Activities:");
        int lastFinishTime = activities[0][1];
        System.out.println("(" + activities[0][0] + ", " + activities[0][1] + ")");

        for (int i = 1; i < activities.length; i++) {
            if (activities[i][0] >= lastFinishTime) {
                System.out.println("(" + activities[i][0] + ", " + activities[i][1] + ")");
                lastFinishTime = activities[i][1];
            }
        }
    }

    public static void main(String[] args) {

        int[][] activities = {
                new int[] { 1, 3 }, new int[] { 2, 5 }, new int[] { 3, 9 }, new int[] { 4, 8 }, new int[] { 5, 7 },
                new int[] { 8, 9 },
        };
        selectActivities(activities);
    }
}
