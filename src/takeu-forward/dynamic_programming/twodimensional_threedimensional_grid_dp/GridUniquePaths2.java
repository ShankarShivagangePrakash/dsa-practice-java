package dynamic_programming.twodimensional_threedimensional_grid_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/grid-unique-paths-2-dp-9/
    https://leetcode.com/problems/unique-paths-ii/description/
 */
public class GridUniquePaths2 {

    /*
    Approach:
        Explained in notes, check
            We can move in two directions from every cell (up and left)

        Time Complexity: O(2^(m* n))
            From every cell there will be 2 branches
        Space Complexity: O(m * n)
            stack space
     */
    public int uniquePathsRecursiveMain(int[][] obstacleGrid) {
        int m  = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        return uniquePathsRecursive(m-1, n-1, obstacleGrid);
    }

    public int uniquePathsRecursive(int i, int j, int[][] mat){
        // If there's an obstacle at this cell or out of bounds, return 0
        if(i >= 0 && j >= 0 && mat[i][j] == 1)
            return 0;

        // indicates cell (0,0), there has to be only 1 way to reach this so return 1
        if(i == 0 && j == 0)
            return 1;

        if(i < 0 || j < 0)
            return 0;

        int left = uniquePathsRecursive(i-1, j, mat);
        int up = uniquePathsRecursive(i, j-1, mat);

        return left + up;
    }

    /*
    Approach:
        Explained in notes, check

            Since there is overlapping subproblems, we can use memoization

        Time Complexity: O(n * m)
            we compute answers for subproblems only once
        Space Complexity: O(n*m) + O(n * m)
            stack space
            DP 2D array
     */
    public int uniquePathsMemoizationMain(int[][] obstacleGrid) {
        int m  = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        return uniquePathsMemoization(m-1, n-1, obstacleGrid, dp);
    }

    public int uniquePathsMemoization(int i, int j, int[][] mat, int[][] dp){
        // If there's an obstacle at this cell or out of bounds, return 0
        if(i >= 0 && j >= 0 && mat[i][j] == 1)
            return 0;

        if(i == 0 && j == 0)
            return 1;

        if(i < 0 || j < 0)
            return 0;

        if(dp[i][j] != -1)
            return dp[i][j];

        int left = uniquePathsMemoization(i-1, j, mat, dp);
        int up = uniquePathsMemoization(i, j-1, mat, dp);

        return dp[i][j] = left + up;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * m)
            two for loops
        Space Complexity: O(n*m)
            DP 2D array
     */
    public int uniquePathsTabulation(int[][] obstacleGrid) {
        int m  = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        // Base case:
        dp[0][0] = 1;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i >= 0 && j >= 0 && obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }

                // only one way to reach cell(0, 0)
                if(i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                int left = 0;
                int up = 0;

                // new cell has to valid, so we are checking i > 0 and j > 0
                // because we are doing (i-1) and (j-1)
                if(i > 0)
                    up = dp[i-1][j];
                if(j > 0)
                    left = dp[i][j-1];

                dp[i][j] = up + left;
            }
        }

        return dp[m-1][n-1];
    }

    /*
    Approach:
        Explained in the notes, check
            Actually we don't require 2D array

            while calculating result for any cell,
            we just need its up and left cell values
            so, we can say we require only its immediate upper row
                for computing results for a particular row cells

            So, we will create a 1D DP array

        Time Complexity: O(M*N)
            There are two nested loops

        Space Complexity: O(N)
            We are using an external array of size ‘N’ to store only one row.
     */
    public int uniquePathsSpaceOptimization(int[][] obstacleGrid) {
        int m  = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // create 1D DP array with number of columns
        int[] prev = new int[n];

        for(int i = 0; i < m; i++){
            int[] cur = new int[n];
            for(int j = 0; j < n; j++){
                if(i >= 0 && j >= 0 && obstacleGrid[i][j] == 1) {
                    cur[j] = 0;
                    continue;
                }

                // only one way to reach cell(0, 0)
                if(i == 0 && j == 0) {
                    cur[j] = 1;
                    continue;
                }

                int left = 0;
                int up = 0;

                // new cell has to valid, so we are checking i > 0 and j > 0
                if(i > 0)
                    up = prev[j];
                if(j > 0)
                    left = cur[j-1];

               cur[j] = up + left;
            }
            prev = cur;
        }

        return prev[n-1];
    }
}
