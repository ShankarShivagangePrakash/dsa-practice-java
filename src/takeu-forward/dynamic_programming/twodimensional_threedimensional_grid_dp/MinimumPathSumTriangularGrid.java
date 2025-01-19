package dynamic_programming.twodimensional_threedimensional_grid_dp;

import java.util.Arrays;
import java.util.List;

/*
Problem:
    https://takeuforward.org/data-structure/minimum-path-sum-in-triangular-grid-dp-11/
    https://leetcode.com/problems/triangle/description/
 */
public class MinimumPathSumTriangularGrid {

    /*
    Approach:
        Explained in notes, check
            One note, but we are starting recursion from index (0, 0)
            and we have kept base case in last row

        Time Complexity: O(2^(n*n))
            at each cell, we have two options (move down and diagonal)
            but how many cells
                0th row - 1 cell
                1st row - 2 cells
                2nd row - 3 cells
                ..
                ..
                ..
                nth row - (n+1) cells

                if we sum up all  (sum upto N natural numbers)
                it comes around = n * (n+1)/2
                summary = n * n

        Space Complexity: O(n)
            stack space
     */
    public int minimumTotalRecursiveMain(List<List<Integer>> triangle) {
        return minimumTotalRecursive(0, 0, triangle,  triangle.size()-1);
    }

    public int minimumTotalRecursive(int i, int j, List<List<Integer>> triangle, int numberOfRows){
        // base case: reached last row
        if(i == numberOfRows)
            return triangle.get(i).get(j);

        int down = triangle.get(i).get(j) + minimumTotalRecursive(i+1, j, triangle, numberOfRows);

        int diagonal = triangle.get(i).get(j) + minimumTotalRecursive(i+1, j+1, triangle, numberOfRows);

        return Math.min(down, diagonal);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*N)
            we eliminated solving overlapping subproblems
            so 2 branches for every cell is avoided
            but we visit every node in triangular grid
            so it comes to O(n*n)

        Space Complexity: O(N*N) + O(n)
            We are using an external array of size ‘N*N’.
            The stack space.
     */
    public int minimumTotalMemoizationMain(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(n-1).size();

        int[][] dp = new int[n][m];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        return minimumTotalMemoization(0, 0, triangle, dp, triangle.size()-1);
    }

    public int minimumTotalMemoization(int i, int j, List<List<Integer>> triangle, int[][] dp, int numberOfRows){
        // base case: reached last row
        if(i == numberOfRows)
            return triangle.get(i).get(j);

        if(dp[i][j] != -1)
            return dp[i][j];

        int down = triangle.get(i).get(j) + minimumTotalMemoization(i+1, j, triangle, dp, numberOfRows);

        int diagonal = triangle.get(i).get(j) + minimumTotalMemoization(i+1, j+1, triangle, dp, numberOfRows);

        return dp[i][j] = Math.min(down, diagonal);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*N)
            There are two nested loops

        Space Complexity: O(N*N)
            We are using an external array of size ‘N*N’.
     */
    public int minimumTotalTabulation(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(n-1).size();

        int[][] dp = new int[n][m];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        // fill base case:
        for(int j = 0; j < m; j++)
            dp[n-1][j] = triangle.get(n-1).get(j);

        /* in memoization, we moved from 0th row to n-1
         in each row, we moved from left to right
         In Tabulation, do exactly opposite
         sinc base case row has been already filled in DP 2D array
         start from last but 1 row*/
        for(int i = n-2; i >= 0; i--){
            // iterate each cell in that row from right to left
            for(int j = triangle.get(i).size()-1; j >= 0; j--){

                int down = triangle.get(i).get(j) + dp[i+1][j];

                int diagonal = triangle.get(i).get(j) + dp[i+1][j+1];

                dp[i][j] = Math.min(down, diagonal);
            }
        }

        /* since we are moving from last rows to first row first column
         final answer will be stored in dp[0][0]*/
        return dp[0][0];
    }

    /*
    Approach:
        Time Complexity: O(N*N)
            There are two nested loops

        Space Complexity: O(N)
            We are using an external array of size ‘N’ to store only one row.
     */
    public int minimumTotalSpaceOptimization(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(n-1).size();

        // it is exactly same as `prev` array used in other DP programs
        // it will store the values of base case, we will store last row values in this array initally
        int[] front = new int[n];

        // fill base case:
        for(int j = 0; j < m; j++)
            front[j] = triangle.get(n-1).get(j);

        /* in memoization, we moved from 0th row to n-1
         in each row, we moved from left to right
         In Tabulation, do exactly opposite
         sinc base case row has been already filled in DP 2D array
         start from last but 1 row*/
        for(int i = n-2; i >= 0; i--){
            int[] cur = new int[n];
            // iterate each cell in that row from right to left
            for(int j = triangle.get(i).size()-1; j >= 0; j--){

                int down = triangle.get(i).get(j) + front[j];

                int diagonal = triangle.get(i).get(j) + front[j+1];

                cur[j] = Math.min(down, diagonal);
            }
            front = cur;
        }

        /* since we are moving from last rows to first row first column
         final answer will be stored in front[0]*/
        return front[0];
    }
}
