package dynamic_programming.stocks;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/buy-and-sell-stock-iii-dp-37/
    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
 */
public class BuyAndSellStocks3 {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential
            At each day, we can either buy or cell O((2*3)^n)

        Space Complexity: O(n)
            stack space
     */
    public int maxProfitRecursiveMain(int[] prices) {
        int n = prices.length;

        return maxProfitRecursive(0, 0, 2, prices, n);
    }

    public int maxProfitRecursive(int ind, int buy, int cap, int[] prices, int n){

        /* Base case:
         if you have completed array traversal
         you cannot buy or sell, so return 0 as profit which can be made
         another scenario, you have completed two transactions, so you cannot
         make further transactions indicated by cap == 0*/
        if(ind == n || cap == 0)
            return 0;

        int profit = 0;
        // buy 0 means, buy option is available
        if(buy == 0){
            /* even if buy is enabled, there are two options
              i will not buy,
                  in this case -
                  i need not spend anything and buy option will be still available with me, so we set 0
                  and we simply move to next index/day
                  also, transaction is not made, so no changes to cap
              i will buy
                  in this case
                  i need to spend money to buy, this will be shown as -(prices[ind])
                  indicating amount spent to buy stock on `ind` day
                  also, buy option will be blocked, so we send 1
                  even though we have bought, transaction will be completed upon selling
                  so no changes to cap
                  */
            profit = Math.max(
                    0 + maxProfitRecursive(ind+1, 0, cap, prices, n),
                    -prices[ind] + maxProfitRecursive(ind+1, 1, cap, prices, n)
            );
        }
        // else means buy 1 indicates only sell option is available
        else{
            /* even if sell is enabled, there are two options
              i will not sell,
                  in this case -
                  i need not earn anything and sell option will be still available with me, so we set 1
                  and we simply move to next index/day
                   also, transaction is not made, so no changes to cap
              i will sell
                  in this case
                  i will earn money on selling, this will be shown as +(prices[ind])
                  indicating amount earned by selling stock on `ind` day
                  also, sell option will be blocked, so we send 0
                  we have sold, so transaction completed so decrement cap by 1
                  */
            profit = Math.max(
                    0 + maxProfitRecursive(ind+1, 1, cap, prices, n),
                    prices[ind] + maxProfitRecursive(ind+1, 0, cap-1, prices, n)
            );
        }
        return profit;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*2*3)
            There are N*2*3 states therefore at max ‘N*2*3’ new problems will be solved.

        Space Complexity: O(N*2*3) + O(N)
            We are using a recursion stack space(O(N)) and a 3D array ( O(N*2*3)).
     */
    public int maxProfitMemoizationMain(int[] prices) {
        int n = prices.length;

        /* There are three changing parameters
         ind/day, buy, cap
         so we have to create 3D array
         day can range from 0 to n-1  --- n states
         buy or sell means 0 or 1     --- 2 states
         at most 2 transactions means, 0, 1, 2  ---- 3 states*/
        int[][][] dp = new int[n][2][3];
        for(int i = 0; i < n; i++){
            for(int[] row : dp[i])
                Arrays.fill(row, -1);
        }

        return maxProfitMemoization(0, 0, 2, prices, n, dp);
    }

    public int maxProfitMemoization(int ind, int buy, int cap, int[] prices, int n, int[][][] dp){

        /* Base case:
         if you have completed array traversal
         you cannot buy or sell, so return 0 as profit which can be made
         another scenario, you have completed two transactions, so you cannot
         make further transactions indicated by cap == 0*/
        if(ind == n || cap == 0)
            return 0;

        if(dp[ind][buy][cap] != -1)
            return dp[ind][buy][cap];

        int profit = 0;
        // buy 0 means, buy option is available
        if(buy == 0){
            /* even if buy is enabled, there are two options
              i will not buy,
                  in this case -
                  i need not spend anything and buy option will be still available with me, so we set 0
                  and we simply move to next index/day
                  also, transaction is not made, so no changes to cap
              i will buy
                  in this case
                  i need to spend money to buy, this will be shown as -(prices[ind])
                  indicating amount spent to buy stock on `ind` day
                  also, buy option will be blocked, so we send 1
                  even though we have bought, transaction will be completed upon selling
                  so no changes to cap
                  */
            profit = Math.max(
                    0 + maxProfitMemoization(ind+1, 0, cap, prices, n, dp),
                    -prices[ind] + maxProfitMemoization(ind+1, 1, cap, prices, n, dp)
            );
        }
        // else means buy 1 indicates only sell option is available
        else{
            /* even if sell is enabled, there are two options
              i will not sell,
                  in this case -
                  i need not earn anything and sell option will be still available with me, so we set 1
                  and we simply move to next index/day
                   also, transaction is not made, so no changes to cap
              i will sell
                  in this case
                  i will earn money on selling, this will be shown as +(prices[ind])
                  indicating amount earned by selling stock on `ind` day
                  also, sell option will be blocked, so we send 0
                  we have sold, so transaction completed so decrement cap by 1
                  */
            profit = Math.max(
                    0 + maxProfitMemoization(ind+1, 1, cap, prices, n, dp),
                    prices[ind] + maxProfitMemoization(ind+1, 0, cap-1, prices, n, dp)
            );
        }
        return dp[ind][buy][cap] = profit;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*2*3)
            There are three nested loops that account for O(N*2*3) complexity.

        Space Complexity: O(N*2*3)
            We are using an external array of size ‘N*2*3’. Stack Space is eliminated.
     */
    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;

        /* There are three changing parameters
         ind/day, buy, cap
         so we have to create 3D array
         day can range from 0 to n-1  --- n states
         furthermore, base is for value == n, so we are creating n+1 size
         buy or sell means 0 or 1     --- 2 states
         at most 2 transactions means, 0, 1, 2  ---- 3 states*/
        int[][][] dp = new int[n+1][2][3];

        /* Base case:
         when ind/day == n
         value is zero irrespective of buy and cap value*/
        for(int buy = 0; buy < 2; buy++){
            for(int cap = 0; cap < 3; cap++){
                dp[n][buy][cap] = 0;
            }
        }

        // when cap == 0
        // value is zero irrespective of buy and ind/day value
        for(int ind = 0; ind < n+1; ind++){
            for(int buy = 0; buy < 2; buy++){
                dp[ind][buy][0] = 0;
            }
        }

        for(int ind = n-1; ind >= 0; ind--){
            for(int buy = 0; buy < 2; buy++){
                /* there is a base case that, when cap == 0
                 answer will be zero, we should not compute it again
                 so that's why we start cap loop from 1*/
                for(int cap = 1; cap < 3; cap++){

                    int profit = 0;
                    if(buy == 0){
                        profit = Math.max(
                                0 + dp[ind+1][0][cap],
                                -prices[ind] + dp[ind+1][1][cap]
                        );
                    }
                    else{
                        profit = Math.max(
                                0 + dp[ind+1][1][cap],
                                prices[ind] + dp[ind+1][0][cap-1]
                        );
                    }
                    dp[ind][buy][cap] = profit;
                }
            }
        }

        return dp[0][0][2];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*2*3)
            There are three nested loops that account for O(N*2*3) complexity

        Space Complexity: O(1)
            We are using two external arrays of size ‘2*3’.
     */
    public int maxProfitSpaceOptimization(int[] prices) {
        int n = prices.length;

        /*
         buy or sell means 0 or 1     --- 2 states
         at most 2 transactions means, 0, 1, 2  ---- 3 states*/
        int[][] after = new int[2][3];

        /* Base case:
         when ind/day == n
         value is zero irrespective of buy and cap value*/
        for(int buy = 0; buy < 2; buy++){
            for(int cap = 0; cap < 3; cap++){
                after[buy][cap] = 0;
            }
        }

         /*since the base case is also setting the value 0 and
         DP default value is also 0, we can ignore these base cases
         when cap == 0
         value is zero irrespective of buy and ind/day value
        for(int ind = 0; ind < n+1; ind++){
            for(int buy = 0; buy < 2; buy++){
                dp[buy][0] = 0;
            }
        }*/

        for(int ind = n-1; ind >= 0; ind--){
            int[][] cur = new int[2][3];
            for(int buy = 0; buy < 2; buy++){
                /* there is a base case that, when cap == 0
                 answer will be zero, we should not compute it again
                 so that's why we start cap loop from 1*/
                for(int cap = 1; cap < 3; cap++){

                    int profit = 0;
                    if(buy == 0){
                        profit = Math.max(
                                0 + after[0][cap],
                                -prices[ind] + after[1][cap]
                        );
                    }
                    else{
                        profit = Math.max(
                                0 + after[1][cap],
                                prices[ind] + after[0][cap-1]
                        );
                    }
                    cur[buy][cap] = profit;
                }
            }
            after = cur;
        }

        return after[0][2];
    }


}
