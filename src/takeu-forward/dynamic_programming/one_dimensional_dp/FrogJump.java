package dynamic_programming.one_dimensional_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/dynamic-programming-frog-jump-dp-3/
    https://www.geeksforgeeks.org/problems/geek-jump/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=geek-jump
 */
public class FrogJump {

    /*
    Approach:
        We have to find the cost to reach last index of the heights array starting from 0th index

        Time Complexity: O(2^n)
            At each level, we have two possibilities,
                either take one step or two step

        Space Complexity: O(n)
            stack space
     */
    int minCostRecursiveMain(int[] height) {
        return minCostRecursive(height.length - 1, height);
    }

    int minCostRecursive(int n, int[] height) {
        if(n == 0)
            return 0;

        // cost to reach 0th step from n-1 + (cost to reach n-1 from nth node)
        int oneStep = minCostRecursive(n-1, height) + Math.abs(height[n] - height[n-1]);

        // cost to reach 0th step from n-2 + (cost to reach n-2 from nth node)
        int twoStep = Integer.MAX_VALUE;
        if(n > 1)
            twoStep = minCostRecursive(n-2, height) + Math.abs(height[n] - height[n-2]);

        return Math.min(oneStep, twoStep);
    }

    /*
    Approach:
        We have to find the cost to reach last index of the heights array starting from 0th index

       Time Complexity: O(N)
            The overlapping subproblems will return the answer in constant time O(1).
            Therefore the total number of new subproblems we solve is ‘n’.
            Hence total time complexity is O(N).

        Space Complexity: O(N) + O(N)
            Reason: We are using a recursion stack space(O(N)) and an array (again O(N)).
            Therefore total space complexity will be O(N) + O(N) ≈ O(N)
     */
    int minCostMemoizationMain(int[] height) {
        int n = height.length - 1;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return minCostMemoization(n, height, dp);
    }

    int minCostMemoization(int n, int[] height, int[] dp) {
        if(n == 0)
            return 0;

        if(dp[n] != -1)
            return dp[n];

        int oneStep = minCostMemoization(n-1, height , dp) + Math.abs(height[n] - height[n-1]);

        int twoStep = Integer.MAX_VALUE;
        // you can't take two steps when you are at index 1
        // if you take two steps, you will reach -1 which is invalid
        // so check for that condition
        if(n > 1)
            twoStep = minCostMemoization(n-2, height, dp) + Math.abs(height[n] - height[n-2]);

        return Math.min(oneStep, twoStep);
    }

    /*
    Approach:
        We know the base case
        Cost to reach 0th index starting from 0th index is 0
        then compute the cost to next indexes

        Time Complexity: O(N)
            We are running a simple iterative loop

        Space Complexity: O(N)
            We are using an external array of size ‘n+1’.
     */
    int minCostTabulation(int[] height) {
        int n = height.length - 1;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        dp[0] = 0;

        for(int i = 1; i <= n; i++){
            int oneStep = dp[i-1] + Math.abs(height[i] - height[i-1]);

            int twoStep = Integer.MAX_VALUE;
            if(i > 1)
                twoStep = dp[i-2] + Math.abs(height[i] - height[i-2]);

            dp[i] = Math.min(oneStep, twoStep);
        }

        return dp[n];
    }

    /*
    Approach:
        since we are using only cost required to reach (i-1) and (i-2)th index
        we don't need an array

        Time Complexity: O(N)
            We are running a simple iterative loop

        Space Complexity: O(1)
            We are not using any extra space.
     */
    int minCostSpaceOptimization(int[] height) {
        int n = height.length - 1;
        int prev = 0;
        int prev2 = 0;

        for(int i = 1; i <= n; i++){
            int oneStep = prev + Math.abs(height[i] - height[i-1]);

            int twoStep = Integer.MAX_VALUE;
            if(i > 1)
                twoStep = prev2 + Math.abs(height[i] - height[i-2]);

            int cur = Math.min(oneStep, twoStep);
            prev2 = prev;
            prev = cur;
        }
        return prev;

    }
}
