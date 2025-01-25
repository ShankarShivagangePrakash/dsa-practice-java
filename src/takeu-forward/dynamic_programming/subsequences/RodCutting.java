package dynamic_programming.subsequences;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/rod-cutting-problem-dp-24/
    https://www.geeksforgeeks.org/problems/rod-cutting0840/1
 */
public class RodCutting {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential ~ O(2^n)
            same as KnapSack Unbound problem

        Space Complexity: O(N)
            stack space
     */
    public int cutRodRecursiveMain(int[] price) {
        int n = price.length;
        int N = n;

        return cutRodRecursive(n-1, N, price);
    }

    public int cutRodRecursive(int ind, int N, int[] price){

        if(ind == 0){
            int rodLength = ind + 1; // 0 + 1 = 1
            // so amount earned will be number of pieces of rod at index 0 sold
            // number of pieces which can be sold, is given by ((int)(N/rodLength))
            return ((int)(N/rodLength)) * price[ind];
        }

        int notTake = 0 + cutRodRecursive(ind-1, N, price);

        int take = Integer.MIN_VALUE;

        // rod length will be current array index value + 1
        // and you can chose same rod piece more than once
        int rodLength = ind + 1;
        if(rodLength <= N)
            take = price[ind] + cutRodRecursive(ind, N - rodLength, price);

        return Math.max(take, notTake);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * n)
            explore all states possible

        Space Complexity: O(n * n) + O(n)
            stack space - O(n)
            n * n - DP array
     */
    public int cutRodMemoizationMain(int[] price) {
        int n = price.length;
        int N = n;

        int[][] dp = new int[n][N+1];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return cutRodMemoization(n-1, N, price, dp);
    }

    public int cutRodMemoization(int ind, int N, int[] price, int[][] dp){

        if(ind == 0){
            int rodLength = ind + 1; // 0 + 1 = 1
            /* so amount earned will be number of pieces of rod at index 0 sold
             number of pieces which can be sold, is given by ((int)(N/rodLength))*/
            return ((int)(N/rodLength)) * price[ind];
        }

        if(dp[ind][N] != -1)
            return dp[ind][N];

        int notTake = 0 + cutRodMemoization(ind-1, N, price, dp);

        int take = Integer.MIN_VALUE;

        /* rod length will be current array index value + 1
         and you can chose same rod piece more than once*/
        int rodLength = ind + 1;
        if(rodLength <= N)
            take = price[ind] + cutRodMemoization(ind, N - rodLength, price, dp);

        return dp[ind][N] = Math.max(take, notTake);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * n)
            explore all states possible

        Space Complexity: O(n * n)
            n * n - DP array
     */
    public int cutRodTabulation(int[] price) {
        int n = price.length;
        int N = n;

        int[][] dp = new int[n][N+1];

        /* actual base case is ((int)(N/rodLength)) * price[ind];
         i represents N here means range of N values
         since rodLength will be 1 always at index 0 there is no need to divide*/
        for(int i = 0; i <= N; i++)
            dp[0][i] = i * price[0];

        for(int ind = 1; ind < n; ind++){
            for(int i = 0; i <= N; i++){
                int notTake = 0 + dp[ind-1][i];

                int take = Integer.MIN_VALUE;

                /* rod length will be current array index value + 1
         and you can chose same rod piece more than once*/
                int rodLength = ind + 1;
                if(rodLength <= i && i - rodLength >= 0)
                    take = price[ind] + dp[ind][i - rodLength];

                dp[ind][i] = Math.max(take, notTake);
            }
        }

        return dp[n-1][N];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * n)
            explore all states possible

        Space Complexity: O(n)
            n - array
     */
    public int cutRodSpaceOptimization(int[] price) {
        int n = price.length;
        int N = n;

        int[] prev = new int[N+1];

        /* actual base case is ((int)(N/rodLength)) * price[ind];
         i represents N here means range of N values
         since rodLength will be 1 always at index 0 there is no need to divide*/
        for(int i = 0; i <= N; i++)
            prev[i] = i * price[0];

        for(int ind = 1; ind < n; ind++){
            int[] cur = new int[N+1];
            for(int i = 0; i <= N; i++){
                int notTake = 0 + prev[i];

                int take = Integer.MIN_VALUE;

                /* rod length will be current array index value + 1
         and you can chose same rod piece more than once*/
                int rodLength = ind + 1;
                if(rodLength <= i && i - rodLength >= 0)
                    take = price[ind] + cur[i - rodLength];

                cur[i] = Math.max(take, notTake);
            }
            prev = cur;
        }

        return prev[N];
    }

}
