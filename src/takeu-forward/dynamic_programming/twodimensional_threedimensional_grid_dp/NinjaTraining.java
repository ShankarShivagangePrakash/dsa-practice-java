package dynamic_programming.twodimensional_threedimensional_grid_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/dynamic-programming-ninjas-training-dp-7/
    https://www.geeksforgeeks.org/problems/geeks-training/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=geeks-training
 */
public class NinjaTraining {

    /*
    Approach:
        Explained in the notes, check

        Time Complexity: O(3 * 2^n)
            In the recursive solution, for each day n and each activity (0, 1, 2),
            the function makes recursive calls to the previous day for all other activities except the last one chosen.

        Here's the breakdown:
            Number of Subproblems:
                For each day, there are 3 choices (activities),
                and we solve this for each day until the first day. So, if there are N days,
                we are solving the problem for N × 3 subproblems.

            Recursive Calls:
                Each subproblem makes 2 recursive calls (excluding the last activity).
                Therefore, the number of calls expands exponentially because each subproblem calls two more subproblems.

                For the last day, there are 3 initial calls.
                Each of these calls results in 2 further recursive calls.
                This pattern continues until the base case (day 0).

        Hence, Time Complexity = O(3 × 2^N)

        Space Complexity: O(n)
            stack space
     */
    public int maximumPointsRecursiveMain(int arr[][]) {
        int size = arr.length;
        int n= size - 1;
        return maximumPointsRecursive(n, 3, arr);
    }

    public int maximumPointsRecursive(int day, int last, int[][] arr){
        if(day == 0){
            int maxi = 0;
            for(int i = 0; i < 3; i++){

                if(i != last) {
                    // alternatively you can use arr[day][i]
                    maxi = Math.max(maxi, arr[0][i]);
                }
            }
            return maxi;
        }

        int maxi = 0;
        for(int i = 0; i < 3; i++){
            if(i != last){
                // call maximumPointsRecursive() for previous day indicating not to choose ith task
                // as it is done today
                int points = arr[day][i] + maximumPointsRecursive(day-1, i, arr);
                maxi = Math.max(maxi, points);
            }
        }
        return maxi;

    }

    /*
    Approach:
        Time Complexity: O(N*4*3)
            There are N*4 states and for every state, we are running a for loop iterating three times.

        Space Complexity: O(N) + O(N*4)
            We are using a recursion stack space(O(N)) and a 2D array (again O(N*4)).
            Therefore total space complexity will be O(N) + O(N) ≈ O(N)
     */
    public int maximumPointsMemoizationMain(int arr[][]) {
        int size = arr.length;
        int n= size - 1;

        /* reason for size (n+1) and (4) is
         we start with day with 0 and we have to compute till n-1 (0 based indexing)
         when it comes to task, we can do either 0, 1, or 2
         but initally when you start, you have all three options
         you can do any of the task
         so one more column is required
         also dp[row][3] stores the maximum points which can be earned till that rowth day*/
        int[][] dp = new int[n+1][4];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return maximumPointsMemoization(n, 3, arr, dp);
    }

    public int maximumPointsMemoization(int day, int last, int[][] arr, int[][] dp){

        if(day == 0){
            int maxi = 0;
            for(int i = 0; i < 3; i++){

                if(i != last) {
                    // alternatively you can use arr[day][i]
                    maxi = Math.max(maxi, arr[0][i]);
                }
            }
            // store the result in DP 2D array and return it
            return dp[day][last] = maxi;
        }

        // if the cell in DP 2D array is already computed then return it
        if(dp[day][last] != -1)
            return dp[day][last];

        int maxi = 0;
        for(int i = 0; i < 3; i++){
            if(i != last){
                // call maximumPointsRecursive() for previous day indicating not to choose ith task
                // as it is done today
                int points = arr[day][i] + maximumPointsMemoization(day-1, i, arr, dp);
                maxi = Math.max(maxi, points);
            }
        }
        // store the result in DP 2D array
        return dp[day][last] = maxi;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*4*3)
            Reason: There are three nested loops

        Space Complexity: O(N*4)
            We are using an external array of size N*4
     */
    public int maximumPointsTabulation(int arr[][]) {
        int size = arr.length;
        int n = size - 1;

        int[][] dp = new int[n+1][4];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        dp[0][0] = Math.max(arr[0][1], arr[0][2]);
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);
        dp[0][3] = Math.max(
                        Math.max(
                                arr[0][0], arr[0][1]
                        ),
                        arr[0][2]
                    );

        // Iterate through each day and each activity
        for(int day = 1; day <= n; day++){
            for(int last = 0; last < 4; last++){
                // maxi represents maximum value possible for dp[day][last]
                int maxi = 0;

                // Consider each possible task for the current day
                for(int task = 0; task < 3; task++){
                    if(task != last){
                        // Calculate the points for the current activity and add it to the maximum points from the previous day
                        int points = arr[day][task] + dp[day-1][task];
                        maxi = Math.max(maxi, points);
                    }
                }
                // Update the maximum points for the current day and last activity
                dp[day][last] = maxi;
            }
        }

        // last row, last column cell is the result
        return dp[n][3];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*4*3)
            There are three nested loops

        Space Complexity: O(4)
            We are using an external array of size ‘4’ to store only one row.
     */
    public int maximumPointsSpaceOptimization(int arr[][]) {
        int size = arr.length;
        int n = size - 1;

        int[] prev = new int[4];

        prev[0] = Math.max(arr[0][1], arr[0][2]);
        prev[1] = Math.max(arr[0][0], arr[0][2]);
        prev[2] = Math.max(arr[0][0], arr[0][1]);
        prev[3] = Math.max(
                Math.max(
                        arr[0][0], arr[0][1]
                ),
                arr[0][2]
        );

        // Iterate through each day and each activity
        for(int day = 1; day <= n; day++){
            int[] cur = new int[4];
            for(int last = 0; last < 4; last++){
                // maxi represents maximum value possible for dp[day][last]
                int maxi = 0;

                // Consider each possible task for the current day
                for(int task = 0; task < 3; task++){
                    if(task != last){
                        // Calculate the points for the current activity and add it to the maximum points from the previous day
                        int points = arr[day][task] + prev[task];
                        maxi = Math.max(maxi, points);
                    }
                }
                // Update the maximum points for the current day and last activity
                cur[last] = maxi;
            }
            // assign current row to previous
            prev = cur;
        }
        return prev[3];
    }
}
