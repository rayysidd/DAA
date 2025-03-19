import java.util.Arrays;
import java.util.Comparator;

class ActivitySelection {
    static class Activity {
        int start, finish;

        Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    static void selectActivities(Activity[] activities) {
        Arrays.sort(activities, Comparator.comparingInt(a -> a.finish));

        System.out.println("Selected Activities:");
        int lastFinishTime = activities[0].finish;
        System.out.println("(" + activities[0].start + ", " + activities[0].finish + ")");

        for (int i = 1; i < activities.length; i++) {
            if (activities[i].start >= lastFinishTime) {
                System.out.println("(" + activities[i].start + ", " + activities[i].finish + ")");
                lastFinishTime = activities[i].finish;
            }
        }
    }

    public static void main(String[] args) {
        Activity[] activities = {
                new Activity(1, 3), new Activity(2, 5),
                new Activity(3, 9), new Activity(6, 8),
                new Activity(5, 7), new Activity(8, 9)
        };

        selectActivities(activities);
    }
}
