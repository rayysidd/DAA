import java.util.Arrays;

class JobScheduling {
    static int[] jobScheduling(int[][] jobs, int n) {
        // Sort jobs in descending order of profit
        Arrays.sort(jobs, (a, b) -> b[2] - a[2]);

        // Find the maximum deadline
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            maxDeadline = Math.max(maxDeadline, jobs[i][1]);
        }

        // Array to track free slots (-1 means empty)
        int[] schedule = new int[maxDeadline + 1];
        Arrays.fill(schedule, -1);

        int countJobs = 0, jobProfit = 0;

        // Iterate through sorted jobs
        for (int i = 0; i < n; i++) {
            int jobId = jobs[i][0];
            int deadline = jobs[i][1];
            int profit = jobs[i][2];

            // Find a free slot from deadline backwards
            for (int j = deadline; j > 0; j--) {
                if (schedule[j] == -1) {
                    schedule[j] = jobId;
                    countJobs++;
                    jobProfit += profit;
                    break;
                }
            }
        }

        return new int[] { countJobs, jobProfit };
    }

    public static void main(String[] args) {
        int[][] jobs = {
                { 1, 4, 20 },
                { 2, 1, 10 },
                { 3, 2, 40 },
                { 4, 2, 30 }
        };

        int[] result = jobScheduling(jobs, jobs.length);
        System.out.println("Jobs completed: " + result[0]);
        System.out.println("Total profit: " + result[1]);
    }
}
