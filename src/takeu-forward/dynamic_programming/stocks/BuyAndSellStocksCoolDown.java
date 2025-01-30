package dynamic_programming.stocks;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/buy-and-sell-stocks-with-cooldown-dp-39/
    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 */
public class BuyAndSellStocksCoolDown {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2^n)
            in each index there are two options
            either buy or sell

        Space Complexity: O(n)
            stack space
     */
    public int maxProfitRecursiveMain(int[] prices) {

        /* parameters,
         first parameter - index - we start recursion from index 0
         second parameter indicates can we buy or sell
         value 0 means buy is allowed
         value 1 means buy is not allowed*/
        return maxProfitRecursive(0, 0, prices, prices.length);
    }

    public int maxProfitRecursive(int ind, int buy, int[] prices, int n){

        /* Base case:
         if you have completed array traversal
         you cannot buy or sell, so return 0 as profit which can be made*/
        if(ind >= n)
            return 0;

        int profit = 0;
        // buy 0 means, buy option is available
        if(buy == 0){
            /* even if buy is enabled, there are two options
              i will not buy,
                  in this case -
                  i need not spend anything and buy option will be still available with me, so we set 0
                  and we simply move to next index/day
              i will buy
                  in this case
                  i need to spend money to buy, this will be shown as -(prices[ind])
                  indicating amount spent to buy stock on `ind` day
                  also, buy option will be blocked, so we send 1*/
            profit = Math.max(
                        0 + maxProfitRecursive(ind+1, 0, prices, n),
                        -prices[ind] + maxProfitRecursive(ind+1, 1, prices, n)
                    );
        }
        // else means buy 1 indicates only sell option is available
        else{
            /* even if sell is enabled, there are two options
              i will not sell,
                  in this case -
                  i need not earn anything and sell option will be still available with me, so we set 1
                  and we simply move to next index/day
              i will sell
                  in this case
                  i will earn money on selling, this will be shown as +(prices[ind])
                  indicating amount earned by selling stock on `ind` day
                  also, sell option will be blocked, so we send 0*/
            profit = Math.max(
                        0 + maxProfitRecursive(ind+1, 1, prices, n),
                        prices[ind] + maxProfitRecursive(ind+2, 0, prices, n)
                    );
        }
        return profit;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * 2)
            fill DP array

        Space Complexity: O(n*2) + O(n)
            stack space - O(n)
            2D DP array - O(n*2)
     */
    public int maxProfitMemoizationMain(int[] prices) {
        int n = prices.length;
        /* dp of size n * 2 because
         n days and each day, we can either buy or sell*/
        int[][] dp = new int[n][2];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return maxProfitMemoization(0, 0, prices, n, dp);
    }

    public int maxProfitMemoization(int ind, int buy, int[] prices, int n, int[][] dp){

        /* Base case:
         if you have completed array traversal
         you cannot buy or sell, so return 0 as profit which can be made*/
        if(ind >= n)
            return 0;

        if(dp[ind][buy] != -1)
            return dp[ind][buy];

        int profit = 0;
        // buy 0 means, buy option is available
        if(buy == 0){
            /* even if buy is enabled, there are two options
              i will not buy,
                  in this case -
                  i need not spend anything and buy option will be still available with me, so we set 0
                  and we simply move to next index/day
              i will buy
                  in this case
                  i need to spend money to buy, this will be shown as -(prices[ind])
                  indicating amount spent to buy stock on `ind` day
                  also, buy option will be blocked, so we send 1*/
            profit = Math.max(
                    0 + maxProfitMemoization(ind+1, 0, prices, n, dp),
                    -prices[ind] + maxProfitMemoization(ind+1, 1, prices, n, dp)
            );
        }
        // else means buy 1 indicates only sell option is available
        else{
            /* even if sell is enabled, there are two options
              i will not sell,
                  in this case -
                  i need not earn anything and sell option will be still available with me, so we set 1
                  and we simply move to next index/day
              i will sell
                  in this case
                  i will earn money on selling, this will be shown as +(prices[ind])
                  indicating amount earned by selling stock on `ind` day
                  also, sell option will be blocked, so we send 0*/
            profit = Math.max(
                    0 + maxProfitMemoization(ind+1, 1, prices, n, dp),
                    prices[ind] + maxProfitMemoization(ind+2, 0, prices, n, dp)
            );
        }
        return dp[ind][buy] = profit;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * 2)
            fill DP array

        Space Complexity: O(n*2)
            2D DP array - O(n)
     */
    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        /* dp of size n * 2 because
         n days and each day, we can either buy or sell*/
        int[][] dp = new int[n+2][2];

        // Base case: when day == n, return 0
        for(int j = 0; j < 2; j++)
            dp[n][j] = 0;

        // opposite order of memoization
        for(int ind = n-1; ind >= 0; ind--){
            for(int buy = 0; buy < 2; buy++){
                int profit = 0;
                // buy 0 means, buy option is available
                if(buy == 0){
                    profit = Math.max(
                            0 + dp[ind+1][0],
                            -prices[ind] + dp[ind+1][1]
                    );
                }
                // else means buy 1 indicates only sell option is available
                else{
                    profit = Math.max(
                            0 + dp[ind+1][1],
                            prices[ind] + dp[ind+2][0]
                    );
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * 2)
            fill DP array

        Space Complexity: O(n)
            1D DP array - O(n)
     */
    public int maxProfitSpaceOptimization(int[] prices) {
        int n = prices.length;
        /* dp of size n * 2 because
         n days and each day, we can either buy or sell
         initially front2 will represent (n+1)th row of DP array
         initially front1 will represent nth row of DP array*/
        int[] front1 = new int[2];
        int[] front2 = new int[2];

        // Base case: when day >= n, return 0
        for(int j = 0; j < 2; j++) {
            front2[j] = 0;
            front1[j] = 0;
        }

        // opposite order of memoization
        for(int ind = n-1; ind >= 0; ind--){
            int[] cur = new int[2];
            for(int buy = 0; buy < 2; buy++){
                int profit = 0;
                // buy 0 means, buy option is available
                if(buy == 0){
                    profit = Math.max(
                            0 + front1[0],
                            -prices[ind] + front1[1]
                    );
                }
                // else means buy 1 indicates only sell option is available
                else{
                    profit = Math.max(
                            0 + front1[1],
                            prices[ind] + front2[0]
                    );
                }
                cur[buy] = profit;
            }
            front2 = front1;
            front1 = cur;
        }
        return front1[0];
    }
}
