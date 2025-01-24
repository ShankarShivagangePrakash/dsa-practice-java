package dynamic_programming.subsequences;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/coin-change-2-dp-22/
    https://leetcode.com/problems/coin-change-ii/description/
 */
public class CoinChange2 {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2^n)
            At every index, we have two possibilites
            either take or not take
            but when we take, instead of moving to next index
            we again start recursion from same, index,
            so, time complexity will be more than O(2^n)

        Space Complexity: O(n)
            actually it will be more than O(n)
            since, we will go to same index again and again
            so we can say, worst case it would be O(amount)
     */
    public int coinChange2RecursiveMain(int[] coins, int amount) {
        int n = coins.length;

        return coinChange2Recursive(n-1, amount, coins);
    }

    public int coinChange2Recursive(int ind, int amount, int[] coins){

        if(ind == 0){
            // base case explained clearly in notes, check
            return (amount % coins[ind]) == 0
                    ? 1
                    : 0;
        }

        int notTake = coinChange2Recursive(ind-1, amount, coins);

        int take = 0;
        /* if the current coin is smaller than amount, then we can pick
         after picking we stay at the same index, because
         we can choose same coin again */
        if(coins[ind] <= amount)
            take = coinChange2Recursive(ind, amount - coins[ind], coins);

        return take + notTake;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*T)
            There are N*T states therefore at max ‘N*T’ new problems will be solved.

        Space Complexity: O(N*T) + O(N)
            We are using a recursion stack space(O(N)) and a 2D array ( O(N*T)).
     */
    public int coinChange2MemoizationMain(int[] coins, int amount) {
        int n = coins.length;

        int[][] dp = new int[n][amount + 1];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        return coinChange2Memoization(n-1, amount, coins, dp);
    }

    public int coinChange2Memoization(int ind, int amount, int[] coins, int[][] dp){

        if(ind == 0){
            // base case explained clearly in notes, check
            return (amount % coins[ind]) == 0
                    ? 1
                    : 0;
        }

        if(dp[ind][amount] != -1)
            return dp[ind][amount];

        int notTake = coinChange2Memoization(ind-1, amount, coins, dp);

        int take = 0;
        /* if the current coin is smaller than amount, then we can pick
         after picking we stay at the same index, because
         we can choose same coin again */
        if(coins[ind] <= amount)
            take = coinChange2Memoization(ind, amount - coins[ind], coins, dp);

        return dp[ind][amount] = take + notTake;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*T)
            There are two nested loops

        Space Complexity: O(N*T)
            We are using an external array of size ‘N*T’. Stack Space is eliminated.
     */
    public int coinChange2Tabulation(int[] coins, int amount) {
        int n = coins.length;

        int[][] dp = new int[n][amount + 1];

        /* base case:
         when index is 0, we check can we reach that amount using coin at index 0
         but we should check for specific amount
         we have to check for all possible amount from range 0 to amount
         because, while recursion reaches index 0 (which was started at index n-1)
         amount may not be still original amount
         so we have to evaluate for all possible amounts*/
        for(int t = 0; t <= amount; t++){
            if(t % coins[0] == 0)
                dp[0][t] = 1;
            else
                dp[0][t] = 0;
        }

        for(int ind = 1; ind < n; ind++){
            for(int t = 0; t <= amount; t++){
                int notTake = dp[ind-1][t];

                int take = 0;
                if(coins[ind] <= amount && t - coins[ind] >= 0)
                    take = dp[ind][t - coins[ind]];

                dp[ind][t] = take + notTake;
            }
        }
        return dp[n-1][amount];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*T)
            There are two nested loops.

        Space Complexity: O(T)
            We are using two external arrays of size ‘T+1’.
     */
    public int coinChange2SpaceOptimization(int[] coins, int amount) {
        int n = coins.length;

        int[] prev = new int[amount + 1];

        /* base case:
         when index is 0, we check can we reach that amount using coin at index 0
         but we should check for specific amount
         we have to check for all possible amount from range 0 to amount
         because, while recursion reaches index 0 (which was started at index n-1)
         amount may not be still original amount
         so we have to evaluate for all possible amounts*/
        for(int t = 0; t <= amount; t++){
            if(t % coins[0] == 0)
                prev[t] = 1;
            else
                prev[t] = 0;
        }

        for(int ind = 1; ind < n; ind++){
            int[] cur = new int[amount + 1];
            for(int t = 0; t <= amount; t++){
                int notTake = prev[t];

                int take = 0;

                if(coins[ind] <= amount && t - coins[ind] >= 0)
                    take = cur[t - coins[ind]];

                cur[t] = take + notTake;
            }
            prev = cur;
        }
        return prev[amount];
    }

}
