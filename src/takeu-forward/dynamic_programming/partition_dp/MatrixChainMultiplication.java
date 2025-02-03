package dynamic_programming.partition_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/dynamic-programming/matrix-chain-multiplication-dp-48/
    https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=matrix-chain-multiplication
 */
public class MatrixChainMultiplication {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential
            multiple branches at each level raning from 0 to n-1

        Space Complexity: O(n)
            stack space
     */
    static int matrixMultiplicationRecursiveMain(int arr[]) {
        int n = arr.length;

        return matrixMultiplicationRecursive(1, n-1, arr);
    }

    public static int matrixMultiplicationRecursive(int i, int j, int[] arr){

        // Base case:
        /* If there is only one matrix,
         There is no need to multiply,
         then number of operations required for multiplication is zero*/
        if(i == j)
            return 0;

        int minimumOperations = Integer.MAX_VALUE;

        /* we know, we have selected block as 1 to n-1
         and we are partitioning as (i-1) to k and (k+1) to j
         but if k is equal to n-1
         and if we try to partition as (k+1) to j
         (n-1 + 1) to (n-1) it will be invalid
         so we run loop till <= j-1*/
        for(int k = i; k <= j-1; k++){
            /*
            We just assume there are two matrix
            one from index (i-1) to k and (k+1) to j
            in order to multiply these two number of operations required are
            = arr[i-1] * arr[k] * arr[j]

            but these matrix can be more than one internally,
            to know number of operations required for each left and right matrix internally
            will be given by
            matrixMultiplicationRecursive(i, k, arr) and
            matrixMultiplicationRecursive(k+1, j, arr)
             */
            int operations =
                    (arr[i-1] * arr[k] * arr[j])
                    + matrixMultiplicationRecursive(i, k, arr)
                    + matrixMultiplicationRecursive(k+1, j, arr);

            // store minimumNumber of operations in `minimumOperations`
            minimumOperations = Math.min(minimumOperations, operations);

        }

        return minimumOperations;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*N*N)
            There are N*N states
            and we explicitly run a loop inside the function which will run for N (actually K but generalizing) times,
            therefore at max ‘N*N*N’ new problems will be solved.

        Space Complexity: O(N*N) + O(N)
            We are using an auxiliary recursion stack space(O(N))and a 2D array ( O(N*N)).
     */
    static int matrixMultiplicationMemoizationMain(int arr[]) {
        int n = arr.length;

        int[][] dp = new int[n][n];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return matrixMultiplicationMemoization(1, n-1, arr, dp);
    }

    public static int matrixMultiplicationMemoization(int i, int j, int[] arr, int[][] dp){

        // Base case:
        /* If there is only one matrix,
         There is no need to multiply,
         then number of operations required for multiplication is zero*/
        if(i == j)
            return 0;

        if(dp[i][j] != -1)
            return dp[i][j];

        int minimumOperations = Integer.MAX_VALUE;

        /* we know, we have selected block as 1 to n-1
         and we are partitioning as (i-1) to k and (k+1) to j
         but if k is equal to n-1
         and if we try to partition as (k+1) to j
         (n-1 + 1) to (n-1) it will be invalid
         so we run loop till <= j-1*/
        for(int k = i; k <= j-1; k++){
            /*
            We just assume there are two matrix
            one from index (i-1) to k and (k+1) to j
            in order to multiply these two number of operations required are
            = arr[i-1] * arr[k] * arr[j]

            but these matrix can be more than one internally,
            to know number of operations required for each left and right matrix internally
            will be given by
            matrixMultiplicationRecursive(i, k, arr) and
            matrixMultiplicationRecursive(k+1, j, arr)
             */
            int operations =
                    (arr[i-1] * arr[k] * arr[j])
                            + matrixMultiplicationMemoization(i, k, arr, dp)
                            + matrixMultiplicationMemoization(k+1, j, arr, dp);

            // store minimumNumber of operations in `minimumOperations`
            minimumOperations = Math.min(minimumOperations, operations);

        }

        return dp[i][j] = minimumOperations;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*N*N)
            There are N*N states
            and we explicitly run a loop inside the function which will run for N (actually K but generalizing) times,
            therefore at max ‘N*N*N’ new problems will be solved.

        Space Complexity: O(N*N)
            2D array ( O(N*N)).
     */
    static int matrixMultiplicationTabulation(int arr[]) {
        int n = arr.length;

        int[][] dp = new int[n][n];

        // Base case:
        for(int i = 1; i < n; i++)
            dp[i][i] = 0;

        for(int i = n-1; i >= 1; i--){
            /* exact opposite of j range according to memoization is
             1 to n-1
             but we know that i will be left matrix and j will be right matrix
             if we simply start j from 1 then j will be left matrix of i which is wrong
             to avoid that, we start j with i+1*/
            for(int j = i+1; j < n; j++){
                int minimumOperations = Integer.MAX_VALUE;

                for(int k = i; k <= j-1; k++){
                    int operations =
                            (arr[i-1] * arr[k] * arr[j])
                                    + dp[i][k]
                                    + dp[k+1][j];

                    // store minimumNumber of operations in `minimumOperations`
                    minimumOperations = Math.min(minimumOperations, operations);

                }

                dp[i][j] = minimumOperations;
            }
        }

        return dp[1][n-1];
    }

}
