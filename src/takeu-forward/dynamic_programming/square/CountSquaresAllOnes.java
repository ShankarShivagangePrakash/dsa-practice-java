package dynamic_programming.square;

/*
Problem:
    https://takeuforward.org/data-structure/count-square-submatrices-with-all-1s-dp-on-rectangles-dp-56/
    https://leetcode.com/problems/count-square-submatrices-with-all-ones/description/
 */
public class CountSquaresAllOnes {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            where N = total no. of rows and M = total no. of columns
            We are basically traversing a 2D matrix with N rows and M columns.

        Space Complexity: O(N*M)
            where N = total no. of rows and M = total no. of columns
            We are using a 2D dp array with N rows and M columns.
     */
    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];

        for(int i = 0; i < n; i++)
            dp[i][0] = matrix[i][0];

        for(int j = 0; j < m; j++)
            dp[0][j] = matrix[0][j];

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(matrix[i][j] == 0)
                    continue;
                dp[i][j] = 1 + Math.min(dp[i-1][j-1],
                                        Math.min(dp[i-1][j], dp[i][j-1])
                                );
            }
        }

        int sum = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++)
                sum += dp[i][j];
        }
        return sum;
    }
}
