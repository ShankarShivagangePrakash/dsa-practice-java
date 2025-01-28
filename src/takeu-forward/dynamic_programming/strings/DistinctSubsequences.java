package dynamic_programming.strings;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/distinct-subsequences-dp-32/
    https://leetcode.com/problems/distinct-subsequences/description/
 */
public class DistinctSubsequences {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential
            at each index, we have two options
                character match or not
                when character matches, we can take it or not
                so definitely, it will be more than O(2^n) + O(2^m)

        Space Complexity: O(n + m)
            stack space
     */
    public int numDistinctRecursiveMain(String s, String t) {
        int n = s.length();
        int m = t.length();

        return numDistinctRecursive(n-1, m-1, s, t);
    }

    public int numDistinctRecursive(int ind1, int ind2, String s, String t){
        if(ind2 < 0)
            return 1;
        if(ind1 < 0)
            return 0;

        if(s.charAt(ind1) == t.charAt(ind2))
            return numDistinctRecursive(ind1-1, ind2-1, s, t)
                    + numDistinctRecursive(ind1-1, ind2, s, t);

        else
            return numDistinctRecursive(ind1-1, ind2, s, t);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are N*M states therefore at max ‘N*M’ new problems will be solved.

        Space Complexity: O(N*M) + O(N+M)
            We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
     */
    public int numDistinctMemoizationMain(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n][m];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return numDistinctMemoization(n-1, m-1, s, t, dp);
    }

    public int numDistinctMemoization(int ind1, int ind2, String s, String t, int[][] dp){
        if(ind2 < 0)
            return 1;
        if(ind1 < 0)
            return 0;

        if(dp[ind1][ind2] != -1)
            return dp[ind1][ind2];

        if(s.charAt(ind1) == t.charAt(ind2))
            return dp[ind1][ind2] = numDistinctMemoization(ind1-1, ind2-1, s, t, dp)
                    + numDistinctMemoization(ind1-1, ind2, s, t, dp);

        else
            return dp[ind1][ind2] = numDistinctMemoization(ind1-1, ind2, s, t, dp);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential
            at each index, we have two options
                character match or not
                when character matches, we can take it or not
                so definitely, it will be more than O(2^n) + O(2^m)

        Space Complexity: O(n + m)
            stack space
     */
    public int numDistinctRecursiveShiftIndexMain(String s, String t) {
        int n = s.length();
        int m = t.length();

        return numDistinctRecursiveShiftIndex(n, m, s, t);
    }

    public int numDistinctRecursiveShiftIndex(int ind1, int ind2, String s, String t){
        if(ind2 == 0)
            return 1;
        if(ind1 == 0)
            return 0;

        if(s.charAt(ind1-1) == t.charAt(ind2-1))
            return numDistinctRecursiveShiftIndex(ind1-1, ind2-1, s, t)
                    + numDistinctRecursiveShiftIndex(ind1-1, ind2, s, t);

        else
            return numDistinctRecursiveShiftIndex(ind1-1, ind2, s, t);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are N*M states therefore at max ‘N*M’ new problems will be solved.

        Space Complexity: O(N*M) + O(N+M)
            We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
     */
    public int numDistinctMemoizationShiftIndexMain(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n+1][m+1];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return numDistinctMemoizationShiftIndex(n, m, s, t, dp);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are N*M states therefore at max ‘N*M’ new problems will be solved.

        Space Complexity: O(N*M) + O(N+M)
            We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
     */
    public int numDistinctMemoizationShiftIndex(int ind1, int ind2, String s, String t, int[][] dp){
        if(ind2 == 0)
            return 1;
        if(ind1 == 0)
            return 0;

        if(dp[ind1][ind2] != -1)
            return dp[ind1][ind2];

        if(s.charAt(ind1-1) == t.charAt(ind2-1))
            return dp[ind1][ind2] = numDistinctMemoizationShiftIndex(ind1-1, ind2-1, s, t, dp)
                    + numDistinctMemoizationShiftIndex(ind1-1, ind2, s, t, dp);

        else
            return dp[ind1][ind2] = numDistinctMemoizationShiftIndex(ind1-1, ind2, s, t, dp);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are two nested loops

        Space Complexity: O(N*M)
            We are using an external array of size ‘N*M’. Stack Space is eliminated.
     */
    public int numDistinctTabulation(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n+1][m+1];

        /* Base case:
         if j is zero, then value will be 1
         if i is zero, then value will be 0*/
        for(int i = 0; i < n+1; i++)
            dp[i][0] = 1;

        /* edge case: if i = 0 and j = 0 cell value has to be 1
         so start j from 1, to avoid modification to dp[0][0]*/
        for(int j = 1; j < m+1; j++)
            dp[0][j] = 0;

        for(int ind1 = 1; ind1 < n+1; ind1++){
            for(int ind2 = 1; ind2 < m+1; ind2++){
                if(s.charAt(ind1-1) == t.charAt(ind2-1))
                    dp[ind1][ind2] = dp[ind1-1][ind2-1] + dp[ind1-1][ind2];
                else
                    dp[ind1][ind2] = dp[ind1-1][ind2];
            }
        }

        return dp[n][m];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
        Reason: There are two nested loops.

        Space Complexity: O(M)
            We are using an external array of size ‘M+1’ to store only one row.
     */
    public int numDistinctSpaceOptimization(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[] prev = new int[m+1];

        /* Base case:
         if j is zero, then value will be 1
         if i is zero, then value will be 0
         but in first row, only first column will be 1*/
        prev[0] = 1;

        for(int ind1 = 1; ind1 < n+1; ind1++){
            int[] cur = new int[m+1];
            // first cell of every row value is 1 as per base case
            cur[0] = 1;
            for(int ind2 = 1; ind2 < m+1; ind2++){
                if(s.charAt(ind1-1) == t.charAt(ind2-1))
                    cur[ind2] = prev[ind2-1] + prev[ind2];
                else
                    cur[ind2] = prev[ind2];
            }
            prev = cur;
        }

        return prev[m];
    }
}
