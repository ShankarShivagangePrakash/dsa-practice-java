package dynamic_programming.twodimensional_threedimensional_grid_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/minimum-maximum-falling-path-sum-dp-12/
    https://leetcode.com/problems/minimum-falling-path-sum/description/
 */
public class MinimumFallingPathSum {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(3 * (n*m)
            for every cell we visit, we have to try out 3 possible options
        Space Complexity: O(n)
            stack space - length of the matrix
     */
    public int minFallingPathSumRecursiveMain(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // we have to get minimum path sum, so initialize mini to some max value
        int mini = Integer.MAX_VALUE;
        for(int j = 0; j < m; j++){
            mini = Math.min(mini, minFallingPathSumRecursive(n-1, j, matrix));
        }
        return mini;
    }

    public int minFallingPathSumRecursive(int i, int j, int[][] matrix){

        if(j < 0 || j >= matrix[0].length)
            return (int)1e9;

        if(i == 0)
            return matrix[i][j];

        int up = matrix[i][j] + minFallingPathSumRecursive(i-1, j, matrix);
        int leftUpperDiagonal =  matrix[i][j] + minFallingPathSumRecursive(i-1, j-1, matrix);
        int rightUpperDiagonal =  matrix[i][j] + minFallingPathSumRecursive(i-1, j+1, matrix);

        int mini = Math.min(up,
                            Math.min(leftUpperDiagonal, rightUpperDiagonal)
                            );

        return mini;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            At max, there will be M*N calls of recursion to solve a new problem,
            we can also say as we have to fill DP 2D array which is of size M * N

        Space Complexity: O(N) + O(N*M)
            We are using a recursion stack space: O(N)
                where N is the path length
            an external DP Array of size ‘N*M’.
     */
    public int minFallingPathSumMemoizationMain(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        // we have to get minimum path sum, so initialize mini to some max value
        int mini = Integer.MAX_VALUE;
        for(int j = 0; j < m; j++){
            mini = Math.min(mini, minFallingPathSumMemoization(n-1, j, matrix, dp));
        }
        return mini;
    }

    public int minFallingPathSumMemoization(int i, int j, int[][] matrix, int[][] dp){

        if(j < 0 || j >= matrix[0].length)
            return (int)1e9;

        if(i == 0)
            return matrix[i][j];

        if(dp[i][j] != -1)
            return dp[i][j];

        int up = matrix[i][j] + minFallingPathSumMemoization(i-1, j, matrix, dp);
        int leftUpperDiagonal =  matrix[i][j] + minFallingPathSumMemoization(i-1, j-1, matrix, dp);
        int rightUpperDiagonal =  matrix[i][j] + minFallingPathSumMemoization(i-1, j+1, matrix, dp);

        int mini = Math.min(up,
                Math.min(leftUpperDiagonal, rightUpperDiagonal)
        );

        return dp[i][j] = mini;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are two nested loops

        Space Complexity: O(N*M)
            We are using an external array of size ‘N*M’. The stack space will be eliminated.
     */
    public int minFallingPathSumTabulation(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];

        // since the problem is to compute minimum sum, we initalize with some max value
        for(int[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE);

        // Base case:
        for(int j = 0; j < m; j++)
            dp[0][j] = matrix[0][j];

        for(int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // if there is no valid path in that direction, then we don't compute values
                // to avoid incorrect comparision in those scenario we will initialize to some max value
                int up = Integer.MAX_VALUE;
                int leftUpperDiagonal = Integer.MAX_VALUE;
                int rightUpperDiagonal = Integer.MAX_VALUE;

                up = matrix[i][j] + dp[i-1][j];

                if(j-1 < 0 == false)
                    leftUpperDiagonal =  matrix[i][j] + dp[i-1][j-1];

                if(j+1 >= m == false)
                    rightUpperDiagonal =  matrix[i][j] + dp[i-1][j+1];

                dp[i][j] = Math.min(up,
                                        Math.min(leftUpperDiagonal, rightUpperDiagonal)
                                    );
            }

        }
        int mini = Integer.MAX_VALUE;

        /* Find the minimum value in the last row of dp
         because these represents minimum path from each index of last row to any index of first row
         but we need minimum of all these, because we just have to find minimum path from last row to first row
         (may be from any column to any column) - variable starting point and variable ending point*/
        for(int j = 0; j < m; j++){
            mini = Math.min(mini, dp[n-1][j]);
        }

        return mini;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are two nested loops

        Space Complexity: O(M)
            We are using an external array of size ‘M’ to store only one row.
     */
    public int minFallingPathSumSpaceOptimization(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[] prev = new int[m];

        // Base case:
        for(int j = 0; j < m; j++)
            prev[j] = matrix[0][j];

        for(int i = 1; i < n; i++) {
            int[] cur = new int[n];
            for (int j = 0; j < m; j++) {

                // if there is no valid path in that direction, then we don't compute values
                // to avoid incorrect comparision in those scenario we will initialize to some max value
                int up = Integer.MAX_VALUE;
                int leftUpperDiagonal = Integer.MAX_VALUE;
                int rightUpperDiagonal = Integer.MAX_VALUE;

                up = matrix[i][j] + prev[j];

                if(j-1 < 0 == false)
                    leftUpperDiagonal =  matrix[i][j] + prev[j-1];

                if(j+1 >= m == false)
                    rightUpperDiagonal =  matrix[i][j] + prev[j+1];

                cur[j] = Math.min(up,
                        Math.min(leftUpperDiagonal, rightUpperDiagonal)
                );
            }
            prev = cur;

        }
        int mini = Integer.MAX_VALUE;

        /* Find the minimum value in the last row of dp
         because these represents minimum path from each index of last row to any index of first row
         but we need minimum of all these, because we just have to find minimum path from last row to first row
         (may be from any column to any column) - variable starting point and variable ending point*/
        for(int j = 0; j < m; j++){
            mini = Math.min(mini, prev[j]);
        }

        return mini;
    }
}
