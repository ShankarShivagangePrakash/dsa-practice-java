package dynamic_programming.lis;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/longest-increasing-subsequence-dp-41/
    https://leetcode.com/problems/longest-increasing-subsequence/description/
 */
public class LongestIncreasingSubsequence {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2^n)
            at each index, we have two options, take or not take

         Space Complexity: O(n)
            stack space
     */
    public int lengthOfLISRecursiveMain(int[] nums) {
        int n = nums.length;

        return lengthOfLISRecursive(0, -1, nums, n);
    }

    public int lengthOfLISRecursive(int ind, int prevIndex, int[] nums, int n){

        if(ind == n)
            return 0;

        int notTake = 0 + lengthOfLISRecursive(ind+1, prevIndex, nums, n);

        int take = 0;
        if(prevIndex == -1 || nums[ind] > nums[prevIndex])
            take = 1 + lengthOfLISRecursive(ind+1, ind, nums, n);

        return Math.max(take, notTake);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * n)
            fill each states of DP array

         Space Complexity: O(n * n) + O(n)
            O(n * n) - DP array
            stack space -O(n)
     */
    public int lengthOfLISMemoizationMain(int[] nums) {
        int n = nums.length;

        /* prevIndex can range from -1 to n-1
         but, how to represent -1 in the array
         so, we do coordinate shifting for prevIndex in DP array
         so we initialize a 2D DP array
         of size [n] * [n+1]
         first parameter is for ind
         second parameter is for prevIndex
         but prevIndex value -1 will be available in dp[ind][0]
         similarly
         prevIndex value n-1 will be available in dp[ind][n]*/
        int[][] dp = new int[n][n+1];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return lengthOfLISMemoization(0, -1, nums, n, dp);
    }

    public int lengthOfLISMemoization(int ind, int prevIndex, int[] nums, int n, int[][] dp){

        if(ind == n)
            return 0;

        if(dp[ind][prevIndex+1] != -1)
            return dp[ind][prevIndex+1];

        int notTake = 0 + lengthOfLISMemoization(ind+1, prevIndex, nums, n, dp);

        int take = 0;
        if(prevIndex == -1 || nums[ind] > nums[prevIndex])
            take = 1 + lengthOfLISMemoization(ind+1, ind, nums, n, dp);

        return dp[ind][prevIndex+1] = Math.max(take, notTake);
    }

    /*
    Approach:
        Ask Chat gpt

        Time Complexity: O(n * n)
            fill each states of DP array

         Space Complexity: O(n * n)
            O(n * n) - DP array
     */
    public int lengthOfLISTTabulation(int[] nums) {
        int n = nums.length;

        // DP array with coordinate shifting for prevIndex
        /* prevIndex can range from -1 to n-1
         but, how to represent -1 in the array
         so, we do coordinate shifting for prevIndex in DP array
         so we initialize a 2D DP array
         of size [n] * [n+1]
         first parameter is for ind
         second parameter is for prevIndex
         but prevIndex value -1 will be available in dp[ind][0]
         similarly
         prevIndex value n-1 will be available in dp[ind][n]*/
        int[][] dp = new int[n+1][n+1];

        // Base case: When ind == n, LIS length is 0
        for (int j = 0; j < n+1; j++)
            dp[n][j] = 0;

        /* changing parameters in reverse order
         day from 0 to n-1
         previous choosen subsequence index can range from -1 to n-1 but in reverse order
         but previous index has be coordinate shifted so perform +1 each time
         when accessing or setting previous index*/
        for (int ind = n-1; ind >= 0; ind--) {
            for (int prevIndex = ind-1; prevIndex >= -1; prevIndex--) {
                // Case 1: Not take the current element
                int notTake = dp[ind+1][prevIndex+1];

                // Case 2: Take the current element (if valid)
                int take = 0;
                if (prevIndex == -1 || nums[ind] > nums[prevIndex]) {
                    take = 1 + dp[ind+1][ind+1];  // Corrected prevIndex shift
                }

                // Store the maximum LIS length in DP table
                dp[ind][prevIndex+1] = Math.max(take, notTake);
            }
        }

        /* actually we were returning f(0, -1)
         but prev index has to be co ordinate shifted by +1, add +1*/
        return dp[0][-1+1];
    }

    /*
    Approach:
        Ask Chat gpt

        Time Complexity: O(n * n)
            fill each states of DP array

         Space Complexity: O(n)
            O(n) - 1D DP array
     */
    public int lengthOfLISTSpaceOptimization(int[] nums) {
        int n = nums.length;

        // DP array with coordinate shifting for prevIndex
        int[] front = new int[n+1];

        // Base case: When ind == n, LIS length is 0
        for (int j = 0; j < n+1; j++)
            front[j] = 0;

        /* changing parameters in reverse order
         day from 0 to n-1
         previous choosen subsequence index can range from -1 to n-1 but in reverse order
         but previous index has be coordinate shifted so perform +1 each time
         when accessing or setting previous index*/
        for (int ind = n-1; ind >= 0; ind--) {
            int[] cur = new int[n+1];
            for (int prevIndex = ind-1; prevIndex >= -1; prevIndex--) {
                // Case 1: Not take the current element
                int notTake = 0 + front[prevIndex+1];

                // Case 2: Take the current element (if valid)
                int take = 0;
                if (prevIndex == -1 || nums[ind] > nums[prevIndex]) {
                    take = 1 + front[ind+1];
                }

                cur[prevIndex+1] = Math.max(take, notTake);
            }
            front = cur;
        }

        /* actually we were returning f(0, -1)
         but prev index has to be co ordinate shifted by +1, add +1*/
        return front[-1+1];
    }
}
