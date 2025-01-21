package dynamic_programming.subsequences;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/
    https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
 */
public class SumSumEqualToTarget {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2^n)
            At every array element, we make 2 choices either take or not take
            recursion will stop, when either target becomes zero or when we visit all array elements
            so O(2^n)


        Space Complexity: O(n)
            stack space
     */
    static Boolean isSubsetSumRecursiveMain(int arr[], int target) {
        int n = arr.length;
        return isSubsetSumRecursive(n-1, target, arr);
    }

    public static Boolean isSubsetSumRecursive(int ind, int target, int[] arr){
        if(target == 0)
            return true;
        if(ind == 0)
            return arr[ind] == target;

        boolean notTake = isSubsetSumRecursive(ind-1, target, arr);
        boolean take = false;

        if(arr[ind] <= target)
            take = isSubsetSumRecursive(ind-1, target - arr[ind], arr);

        return take || notTake;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*K)
            There are N*K states therefore at max ‘N*K’ new problems will be solved.

        Space Complexity: O(N*K) + O(N)
            We are using a recursion stack space(O(N)) and a 2D array ( O(N*K)).
     */
    static Boolean isSubsetMemoizationMain(int arr[], int target) {
        int n = arr.length;
        int[][] dp = new int[n][target+1];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        return isSubsetSumMemoization(n-1, target, arr, dp);
    }

    public static Boolean isSubsetSumMemoization(int ind, int target, int[] arr, int[][] dp){
        if(target == 0)
            return true;

        if(ind == 0)
            return arr[ind] == target;

        if(dp[ind][target] != -1)
            return dp[ind][target] == 1;

        boolean notTake = isSubsetSumMemoization(ind-1, target, arr, dp);
        boolean take = false;

        if(arr[ind] <= target)
            take = isSubsetSumMemoization(ind-1, target - arr[ind], arr, dp);

        dp[ind][target] = take || notTake ? 1 : 0;

        return take || notTake;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*K)
            There are two nested loops

        Space Complexity: O(N*K)
            We are using an external array of size ‘N*K’. Stack Space is eliminated.
     */
    static Boolean isSubsetSumTabulation(int arr[], int target) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][target+1];

        // Base case:
        // first column of DP will be true
        for(int i = 0; i < n; i++){
            dp[i][0] = true;
        }

        // another base case: if there is an element equal to `target`
        // then corrsponding index in DP should be true
        if (arr[0] <= target) {
            dp[0][arr[0]] = true;
        }

        for(int ind = 1; ind < n; ind++){
            // k represents value from range 0 to `target`
            for(int k = 1; k < target + 1; k++){
                // Calculate if the current target can be achieved without taking the current element
                boolean notTake = dp[ind-1][k];

                // Calculate if the current target can be achieved by taking the current element
                boolean take = false;
                if(arr[ind] <= k)
                    take = dp[ind-1][k - arr[ind]];

                // Store the result in the DP table
                dp[ind][k] = take || notTake;
            }
        }

        /* The final result is stored in the bottom-right cell of the DP table
         represents can we achieve target considering eleements in the range of index [0 to n-1]
         i.e the entire array*/
        return dp[n-1][target];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*K)
            There are three nested loops

        Space Complexity: O(K)
            We are using an external array of size ‘K+1’ to store only one row.
     */
    static Boolean isSubsetSumSpaceOptimization(int arr[], int target) {
        int n = arr.length;
        boolean[] prev = new boolean[target+1];

        // Base case: first column of the 2D DP will be true
        // since we don't have multiple rows, we will initalize first cell value to true
        prev[0] = true;

        // another base case: if there is an element equal to `target`
        // then corrsponding index in DP should be true
        if (arr[0] <= target) {
            prev[arr[0]] = true;
        }

        for(int ind = 1; ind < n; ind++){

            boolean[] cur = new boolean[target+1];
            // base case:
            cur[0] = true;
            // k represents value from range 0 to `target`
            for(int k = 1; k < target + 1; k++){
                // Calculate if the current target can be achieved without taking the current element
                boolean notTake = prev[k];

                // Calculate if the current target can be achieved by taking the current element
                boolean take = false;
                if(arr[ind] <= k)
                    take = prev[k - arr[ind]];

                // Store the result in the DP table
                cur[k] = take || notTake;
            }
            prev = cur;
        }

        /* The final result is stored in the bottom-right cell of the DP table
         represents can we achieve target considering eleements in the range of index [0 to n-1]
         i.e the entire array*/
        return prev[target];
    }

}
