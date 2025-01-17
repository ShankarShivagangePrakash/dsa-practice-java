package dynamic_programming.one_dimensional_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/dynamic-programming-climbing-stairs/
    https://leetcode.com/problems/climbing-stairs/description/
 */
public class ClimbingStairs {

    /*
    Approach:
        Recursive approach to find total number of ways to reach nth step

        But small change, we approach like we are downstairing from nth stair to 0th stair

        Time Complexity: O(2^n)
            At each level, we have two possibilities,
                either take one step or two step

        Space Complexity: O(n)
            stack space
     */
    public int claimStairsRecursiveMain(int n){
    return climbStairsRecursive(n);
    }

    public int climbStairsRecursive(int ind){
        // To go to 0th step there is only one way
        if(ind == 0)
            return 1;
        // To go to 1th step there is only one way
        if(ind == 1)
            return 1;

        return climbStairsRecursive(ind-1) + climbStairsRecursive(ind - 2);
    }

    /*
    Approach:
        converted recursive approach to memoization

        Time Complexity: O(n)
            if you write recursion tree,
            you can notice many operations which are repeating (subproblems

            So, we can say, it is a overlapping subproblems
            so, if we store the results of subproblems,
            we need not store results of those problems, we need not solve it again

            For that reason, we create DP array, all cells initalized with value -1

            We start with bigger problems, make it small
            and then when we reach the base case
            recursively compute answer for bigger problems
            finally arrive at the result

        Space Complexity: O(n) + O(n)
            stack space - O(n)
            DP array - O(n)
     */
    public int climbStairsMemoizationMain(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return climbStairsMemoization(n, dp);
    }

    public int climbStairsMemoization(int ind, int[] dp){
            if(ind == 0)
                return 1;
            if(ind == 1)
                return 1;

            if(dp[ind] != -1)
                return dp[ind];

            return dp[ind] = climbStairsMemoization(ind-1, dp) + climbStairsMemoization(ind-2, dp);
    }

    /*
    Approach:
        we know the base case
        so we start with base case
        we start solving slightly bigger problems
        and eventually we solve the actual problem

        Time Complexity: O(n)
            for loop
        Space Complexity: O(n)
            DP array
     */
    public int climbStairsTabulation(int n){
        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /*
    Approach:
        if you notice, we are not using the entire DP array each time
        we just use two previous cells value
        so instead of creating a DP array, let's replace them with variables

        Time Complexity: O(n)
            for loop
        Space Complexity: O(1)
     */
    public int climbStairsSpaceOptimization(int n){
        int prev0 = 1, prev1 = 1;

        for(int i = 2; i <= n; i++){
            int cur = prev0 + prev1;
            prev0 = prev1;
            prev1 = cur;
        }
        return prev1;
    }


}
