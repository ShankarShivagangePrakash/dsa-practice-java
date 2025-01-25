package dynamic_programming.subsequences;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/unbounded-knapsack-dp-23/
    https://www.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=knapsack-with-duplicate-items
 */
public class UnboundKnapsack {
    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2^n) ~ Exponential
            every node has two possibilities
            there are chances, even if we take 2 possibilites
            we add new recursion with same, current values
            so it will be more than O(2^n)
            so say it as exponential

        Space Complexity: O(n) ~ O(capacity)
            stack space
            since time complexity has increased
            the recursion tree will  be bigger
            so it will be more than O(n)
            worst case, we can say O(capacity)
     */
    static int knapSackRecursiveMain(int val[], int wt[], int capacity) {
        int n= val.length;
        return knapSackRecursive(n-1, val, wt, capacity);
    }

    public static int knapSackRecursive(int ind, int[] val, int[] wt, int capacity){

        if(ind == 0){
            return ((int)(capacity/wt[0])) * val[0];
        }

        int notTake = 0 + knapSackRecursive(ind-1, val, wt, capacity);

        int take = Integer.MIN_VALUE;

        /*
        There is an infinite supply of items, means each product can be picked any number of times we want
        So, whenever we take a product once, we don't move to previous index
        we just reduce the capacity, but we stay at the same index, so that if we can pick the same item again
        we wil.
         */
        if(wt[ind] <= capacity)
            take = val[ind] + knapSackRecursive(ind, val, wt, capacity - wt[ind]);

        return Math.max(take, notTake);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*W)
            There are N*W states therefore at max ‘N*W’ new problems will be solved.
            W refers to capacity.

        Space Complexity: O(N*W) + O(N)
            We are using a recursion stack space(O(N)) and a 2D array ( O(N*W)).
     */
    static int knapSackMemoizationMain(int val[], int wt[], int capacity) {
        int n= val.length;

        int[][] dp = new int[n][capacity+1];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return knapSackMemoization(n-1, capacity, val, wt, dp);
    }

    public static int knapSackMemoization(int ind, int capacity, int[] val, int[] wt, int[][] dp){

        if(ind == 0){
            return ((int)(capacity/wt[0])) * val[0];
        }

        if(dp[ind][capacity] != -1)
            return dp[ind][capacity];

        int notTake = 0 + knapSackMemoization(ind-1, capacity, val, wt, dp);

        int take = Integer.MIN_VALUE;

        /*
        There is an infinite supply of items, means each product can be picked any number of times we want
        So, whenever we take a product once, we don't move to previous index
        we just reduce the capacity, but we stay at the same index, so that if we can pick the same item again
        we wil.
         */
        if(wt[ind] <= capacity)
            take = val[ind] + knapSackMemoization(ind,capacity - wt[ind], val, wt, dp);

        return dp[ind][capacity] = Math.max(take, notTake);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*W)
            There are two nested loops

        Space Complexity: O(N*W)
            We are using an external array of size ‘N*W’. Stack Space is eliminated.
     */
    static int knapSackTabulation(int val[], int wt[], int capacity) {
        int n= val.length;

        int[][] dp = new int[n][capacity+1];

        for(int w = 0; w <= capacity; w++){
            dp[0][w] = ((int)(w/wt[0])) * val[0];
        }

        for(int ind = 1; ind < n; ind++){
            for(int w = 0; w <= capacity; w++){
                int notTake = 0 + dp[ind-1][w];

                int take = Integer.MIN_VALUE;

                /*
                There is an infinite supply of items, means each product can be picked any number of times we want
                So, whenever we take a product once, we don't move to previous index
                we just reduce the capacity, but we stay at the same index, so that if we can pick the same item again
                we wil.
                 */
                if(wt[ind] <= capacity && w - wt[ind] >= 0)
                    take = val[ind] + dp[ind][w - wt[ind]];

                dp[ind][w] = Math.max(take, notTake);
            }
        }

        return dp[n-1][capacity];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*W)
            There are two nested loops.

        Space Complexity: O(W)
            We are using an external array of size ‘W+1’ to store only one row.
     */
    static int knapSackSpaceOptimization(int val[], int wt[], int capacity) {
        int n= val.length;

        int[] prev = new int[capacity+1];

        for(int w = 0; w <= capacity; w++){
            prev[w] = ((int)(w/wt[0])) * val[0];
        }

        for(int ind = 1; ind < n; ind++){
            int[] cur = new int[capacity+1];
            for(int w = 0; w <= capacity; w++){
                int notTake = 0 + prev[w];

                int take = Integer.MIN_VALUE;

                /*
                There is an infinite supply of items, means each product can be picked any number of times we want
                So, whenever we take a product once, we don't move to previous index
                we just reduce the capacity, but we stay at the same index, so that if we can pick the same item again
                we wil.
                 */
                if(wt[ind] <= capacity && w - wt[ind] >= 0)
                    take = val[ind] + cur[w - wt[ind]];

                cur[w] = Math.max(take, notTake);
            }
            prev = cur;
        }

        return prev[capacity];
    }
}
