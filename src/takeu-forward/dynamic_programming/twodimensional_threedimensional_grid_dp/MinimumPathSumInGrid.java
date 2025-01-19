package dynamic_programming.twodimensional_threedimensional_grid_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/minimum-path-sum-in-a-grid-dp-10/
    https://leetcode.com/problems/minimum-path-sum/description/
 */
public class MinimumPathSumInGrid {

    /*
    Approach:
        Explained in notes, check
            We can move in two directions from every cell (up and left)

        Time Complexity: O(2^(m* n))
            From every cell there will be 2 branches
        Space Complexity: O(m * n)
            stack space
     */
    public int minimumPathSumInGridRecursiveMain(int[][] obstacleGrid) {
        int m  = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        return minimumPathSumInGridRecursive(m-1, n-1, obstacleGrid);
    }

    public int minimumPathSumInGridRecursive(int i, int j, int[][] mat){

        // base case returnt this cell value as the cost for this cell.
        if(i == 0 && j == 0)
            return mat[i][j];

        // invalid index, return (1e9) indicating there is no path (1e9) indicates 10^9
        if(i < 0 || j < 0)
            return (int)(1e9);

        // cost of left path will be current cell value and cost to reach index (0, 0) by choosing left cell from current cell
        int left = mat[i][j] + minimumPathSumInGridRecursive(i-1, j, mat);

        // cost of up path will be current cell value and cost to reach index (0, 0) by choosing upper cell from current cell
        int up = mat[i][j] + minimumPathSumInGridRecursive(i, j-1, mat);

        // choose minimum cost path
        return Math.min(up, left);
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
    public int minimumPathSumInGridMemoizationMain(int[][] obstacleGrid) {
        int m  = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        return minimumPathSumInGridMemoization(m-1, n-1, obstacleGrid, dp);
    }

    public int minimumPathSumInGridMemoization(int i, int j, int[][] mat, int[][] dp){
        // base case returnt this cell value as the cost for this cell.
        if(i == 0 && j == 0)
            return mat[i][j];

        // invalid index, return (1e9) indicating there is no path (1e9) indicates 10^9
        if(i < 0 || j < 0)
            return (int)(1e9);

        if(dp[i][j] != -1)
            return dp[i][j];

        // cost of left path will be current cell value and cost to reach index (0, 0) by choosing left cell from current cell
        int left = mat[i][j] + minimumPathSumInGridMemoization(i-1, j, mat, dp);

        // cost of up path will be current cell value and cost to reach index (0, 0) by choosing upper cell from current cell
        int up = mat[i][j] + minimumPathSumInGridMemoization(i, j-1, mat, dp);

        // choose minimum cost path, also store in DP array
        return dp[i][j] = Math.min(up, left);
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

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) {
                    dp[i][j] = obstacleGrid[i][j];
                    continue;
                }

                // if the path doesn't exist, then we have to set it as (1e9) so initalize with it
                int left = (int) 1e9;
                int up = (int) 1e9;

                // left and up will be initalized, if the path exist only
                if(i > 0)
                    up = obstacleGrid[i][j] + dp[i-1][j];
                if(j > 0)
                    left = obstacleGrid[i][j] + dp[i][j-1];

                // choose minimum cost path, also store in DP array
                dp[i][j] = Math.min(up, left);
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
                if(i == 0 && j == 0) {
                    cur[j] = obstacleGrid[i][j];
                    continue;
                }

                // if the path doesn't exist, then we have to set it as (1e9) so initalize with it
                int left = (int) 1e9;
                int up = (int) 1e9;

                // left and up will be initalized, if the path exist only
                if(i > 0)
                    up = obstacleGrid[i][j] + prev[j];
                if(j > 0)
                    left = obstacleGrid[i][j] + cur[j-1];

                // choose minimum cost path, also store in DP array
                cur[j] = Math.min(up, left);
            }
            prev = cur;
        }

        return prev[n-1];
    }
}
