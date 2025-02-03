package dynamic_programming.partition_dp;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/burst-balloons-partition-dp-dp-51/
    https://leetcode.com/problems/burst-balloons/description/
 */
public class BurstBalloons {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential

        Space Complexity: O(n)
            stack space
     */
    public int maxCoinsRecursiveMain(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> numsList = new ArrayList<>();

        // add 1 to begining of the list
        numsList.add(1);

        // add nums array elements to list
        for(int ele : nums)
            numsList.add(ele);

        // add 1 to end of the list
        numsList.add(1);

        return maxCoinsRecursive(1, n, numsList);
    }

    public int maxCoinsRecursive(int i, int j, ArrayList<Integer> numsList){

        if(i > j)
            return 0;

        int maxCoins = Integer.MIN_VALUE;
        for(int k = i; k <= j; k++){
            int coins = (numsList.get(i-1) * numsList.get(k) * numsList.get(j+1))
                        + maxCoinsRecursive(i, k-1, numsList)
                        + maxCoinsRecursive(k+1, j, numsList);

            maxCoins = Math.max(maxCoins, coins);
        }
        return maxCoins;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N^3),
            There are total N^2 no. of states.
            And for each state, we are running a partitioning loop roughly for N times.

        Space Complexity: O(N^2) + Auxiliary stack space of O(N)
            N^2 for the dp array we are using.
     */
    public int maxCoinsMemoizationMain(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> numsList = new ArrayList<>();

        // add 1 to begining of the list
        numsList.add(1);

        // add nums array elements to list
        for(int ele : nums)
            numsList.add(ele);

        // add 1 to end of the list
        numsList.add(1);

        int[][] dp = new int[n+1][n+1];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return maxCoinsMemoization(1, n, numsList, dp);
    }

    public int maxCoinsMemoization(int i, int j, ArrayList<Integer> numsList, int[][] dp){

        if(i > j)
            return 0;

        if(dp[i][j] != -1)
            return dp[i][j];

        int maxCoins = Integer.MIN_VALUE;
        for(int k = i; k <= j; k++){
            int coins = (numsList.get(i-1) * numsList.get(k) * numsList.get(j+1))
                    + maxCoinsMemoization(i, k-1, numsList, dp)
                    + maxCoinsMemoization(k+1, j, numsList, dp);

            maxCoins = Math.max(maxCoins, coins);
        }
        return dp[i][j] = maxCoins;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N^3),
            There are total N^2 no. of states.
            And for each state, we are running a partitioning loop roughly for N times.

        Space Complexity: O(N^2)
            N^2 for the dp array we are using.
     */
    public int maxCoinsTabulation(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> numsList = new ArrayList<>();

        // add 1 to begining of the list
        numsList.add(1);

        // add nums array elements to list
        for(int ele : nums)
            numsList.add(ele);

        // add 1 to end of the list
        numsList.add(1);

        int[][] dp = new int[n+2][n+2];

        for(int i = n; i >= 1; i--){
            for(int j = 1; j <= n; j++){

                if(i > j)
                    continue;

                int maxCoins = Integer.MIN_VALUE;
                for(int k = i; k <= j; k++){
                    int coins = (numsList.get(i-1) * numsList.get(k) * numsList.get(j+1))
                            + dp[i][k-1]
                            + dp[k+1][j];

                    maxCoins = Math.max(maxCoins, coins);
                }
                dp[i][j] = maxCoins;
            }
        }

        return dp[1][n];
    }
}
