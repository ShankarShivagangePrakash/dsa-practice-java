package dynamic_programming.twodimensional_threedimensional_grid_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/3-d-dp-ninja-and-his-friends-dp-13/
    https://www.geeksforgeeks.org/problems/chocolates-pickup/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=chocolates-pickup
 */
public class ThreeDimensionalNinjaAndFriends {

    /*
    Approach:
        Explained in notes, check
            one note, since we know the fixed point
            we start recursion from there itself
            fixed points are 0 and last column of first row

        Time Complexity: O(3^n * 3^n)
            since there are 3 options at every cell for two people
        Space Complexity: O(n)
            stack space
     */
    public int chocolatesPickupRecursiveMain(int n, int m, int grid[][]) {
        return chocolatesPickupRecursive(0, 0, m-1, grid, n, m);
    }

    public int chocolatesPickupRecursive(int i, int j1, int j2, int[][] grid, int n, int m){

        // if it is invalid index, we return large negative value such that this path will not be considered
        if(j1 < 0 || j1 >= m || j2 < 0 || j2 >= m)
            return (int)-1e8;

        if(i == n-1){
            // if j1 and j2 both are same means, same cell has been entered/chosen by both people
            // we have to add the cost only once
            if(j1 == j2)
                return grid[i][j1];
            else
                return grid[i][j1] + grid[i][j2];
        }

        int maxi = Integer.MIN_VALUE;
        /* we can move in all three directions
         down-left-diagonal, down, down-right-diagonal
         but we have to do for both bob and alice simultaneously
         3 * 3 = 9 options in total
         `i` value will be same for both bob and alice
         `j` can range from -1 to 1
         so we are running two for loops in this range.*/
        for(int dj1 = -1; dj1 <= 1; dj1++){
            for(int dj2 = -1; dj2 <= 1; dj2++){
                int value = 0;
                // if the current cell index of two people are same, then that cell value must be added only once to path
                if(j1 == j2)
                    value = grid[i][j1];
                else
                    value = grid[i][j1] + grid[i][j2];

                // compute the cost to reach n-1(last row) from the new index
                value += chocolatesPickupRecursive(i+1, j1+dj1, j2+dj2, grid, n, m);

                // store maximum value in `maxi`
                maxi = Math.max(maxi, value);
            }
        }

        return maxi;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M*M) * 9
            At max, there will be N*M*M calls of recursion to solve a new problem and in every call, two nested loops together run for 9 times.

        Space Complexity: O(N) + O(N*M*M)
            We are using a recursion stack space: O(N), where N is the path length and an external DP Array of size ‘N*M*M’.
     */
    public int chocolatesPickupMemoizationMain(int n, int m, int grid[][]){
        int[][][] dp = new int[n][m][m];

        for(int i = 0; i < n; i++){
            for(int[] row : dp[i])
                Arrays.fill(row, -1);
        }
        return chocolatesPickupMemoization(0, 0, m-1, grid, n, m, dp);
    }

    public int chocolatesPickupMemoization(int i, int j1, int j2, int[][] grid, int n, int m, int[][][] dp){
        if(j1 < 0 || j1 >= m || j2 < 0 || j2 >= m)
            return (int)-1e8;

        if(i == n-1){
            if(j1 == j2)
                return grid[i][j1];
            else
                return grid[i][j1] + grid[i][j2];
        }

        if(dp[i][j1][j2] != -1)
            return dp[i][j1][j2];

        int maxi = (int)-1e8;
        for(int dj1 = -1; dj1 <= 1; dj1++){
            for(int dj2 = -1; dj2 <= 1; dj2++){
                int value = 0;

                if(j1 == j2)
                    value = grid[i][j1];
                else
                    value = grid[i][j1] + grid[i][j2];

                value += chocolatesPickupMemoization(i+1, j1+dj1, j2+dj2, grid, n, m, dp);
                maxi = Math.max(maxi, value);
            }
        }
        return dp[1][j1][j2] = maxi;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M*M)*9
            The outer nested loops run for (N*M*M) times and the inner two nested loops run for 9 times.

        Space Complexity: O(N*M*M)
            We are using an external array of size ‘N*M*M’. The stack space will be eliminated.
     */
    public int chocolatesPickupTabulation(int n, int m, int grid[][]){
        int[][][] dp = new int[n][m][m];

        for(int i = 0; i < n; i++){
            for(int[] row : dp[i])
                Arrays.fill(row, -1);
        }

        for(int j1 = 0; j1 < m; j1++){
            for(int j2 = 0; j2 < m; j2++){
                if(j1 == j2)
                    dp[n-1][j1][j2] = grid[n-1][j1];
                else
                    dp[n-1][j1][j2] = grid[n-1][j1] + grid[n-1][j2];
            }
        }

        // exactly opposite to memoization approach
        for(int i = n-2; i >= 0; i--){
            for(int j1 = 0; j1 < m; j1++){
                for(int j2 = 0; j2 < m; j2++){

                    int maxi = (int)-1e8;
                    // Inner nested loops to try out 9 options
                    for(int dj1 = -1; dj1 <= 1; dj1++){
                        for(int dj2 = -1; dj2 <= 1; dj2++){
                            int value = 0;

                            if(j1 == j2)
                                value = grid[i][j1];
                            else
                                value = grid[i][j1] + grid[i][j2];

                            // Check if the indices are valid
                            if ((j1 + dj1 < 0 || j1 + dj1 >= m) || (j2 + dj2 < 0 || j2 + dj2 >= m))
                                value += (int) Math.pow(-10, 9);
                            else
                                value += dp[i+1][j1+dj1][j2+dj2];

                            // Update maxi with the maximum result
                            maxi = Math.max(maxi, value);
                        }
                    }
                    // Store the result in the dp array
                    dp[i][j1][j2] = maxi;
                }
            }
        }
        return dp[0][0][m-1];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M*M)*9
            The outer nested loops run for (N*M*M) times and the inner two nested loops run for 9 times.

        Space Complexity: O(M*M)
            We are using an external array of size ‘M*M’.
     */
    public int chocolatesPickupSpaceOptimization(int n, int m, int grid[][]){
        int[][] front = new int[m][m];

        for(int j1 = 0; j1 < m; j1++){
            for(int j2 = 0; j2 < m; j2++){
                if(j1 == j2)
                    front[j1][j2] = grid[n-1][j1];
                else
                    front[j1][j2] = grid[n-1][j1] + grid[n-1][j2];
            }
        }

        // exactly opposite to memoization approach
        for(int i = n-2; i >= 0; i--){
            int[][] cur = new int[m][m];
            for(int j1 = 0; j1 < m; j1++){
                for(int j2 = 0; j2 < m; j2++){

                    int maxi = (int)-1e8;
                    // Inner nested loops to try out 9 options
                    for(int dj1 = -1; dj1 <= 1; dj1++){
                        for(int dj2 = -1; dj2 <= 1; dj2++){
                            int value = 0;

                            if(j1 == j2)
                                value = grid[i][j1];
                            else
                                value = grid[i][j1] + grid[i][j2];

                            // Check if the indices are valid
                            if ((j1 + dj1 < 0 || j1 + dj1 >= m) || (j2 + dj2 < 0 || j2 + dj2 >= m))
                                value += (int) Math.pow(-10, 9);
                            else
                                value += front[j1+dj1][j2+dj2];

                            // Update maxi with the maximum result
                            maxi = Math.max(maxi, value);
                        }
                    }
                    // Store the result in the dp array
                    cur[j1][j2] = maxi;
                }
            }
            front = cur;
        }
        return front[0][m-1];
    }
}
