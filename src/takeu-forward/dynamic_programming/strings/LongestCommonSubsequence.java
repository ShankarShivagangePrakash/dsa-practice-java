package dynamic_programming.strings;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/longest-common-subsequence-dp-25/
    https://leetcode.com/problems/longest-common-subsequence/description/
 */
public class LongestCommonSubsequence {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2^n) + O(2^m)
            we generate all subsequences of both strings and compare

        Space Complexity: O(n + m)
            In case of not match case, we try removing a character from both strings seperately
            recursion tree length can go upto O(n + m)
     */
    public int longestCommonSubsequenceRecursiveMain(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        return longestCommonSubsequenceRecursive(n-1, m-1, text1, text2);
    }

    public int longestCommonSubsequenceRecursive(int ind1, int ind2, String text1, String text2){

        // Base case:
        /* if any the string has reached end,
         there is nothing more to compare, stop recursion*/
        if(ind1 < 0 || ind2 < 0)
            return 0;

        /* in all String comparision problem
         there can be two cases
         match or not match*/

        // match case:
        /* since the current characters have matched
         we have to add 1 to the result
         then find longest subsequence in the remaining part of the strings.*/
        if(text1.charAt(ind1) == text2.charAt(ind2))
            return 1 + longestCommonSubsequenceRecursive(ind1 - 1, ind2 - 1, text1, text2);

        /* not match case
         in this case, we try both options
         try eliminating a character on both the strings seperately
         which option gives longest subsequence take that.

         since current characters are not matching, we have to add 0(stating not match)
         and try both the options and consider maximum among them*/
        else
            return 0 + Math.max(
                        longestCommonSubsequenceRecursive(ind1 - 1, ind2, text1, text2),
                        longestCommonSubsequenceRecursive(ind1, ind2 -1, text1, text2)
                    );
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * m)
            explore all states

         Space Complexity: O(n * m) + O(n + m)
            O(n * m) - DP array
            O(n + m) - stack space
     */
    public int longestCommonSubsequenceMemoizationMain(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n][m];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return longestCommonSubsequenceMemoization(n-1, m-1, text1, text2, dp);
    }

    public int longestCommonSubsequenceMemoization(int ind1, int ind2, String text1, String text2, int[][] dp){

        // Base case:
        /* if any the string has reached end,
         there is nothing more to compare, stop recursion*/
        if(ind1 < 0 || ind2 < 0)
            return 0;

        if(dp[ind1][ind2] != -1)
            return dp[ind1][ind2];

        // match case:
        /* since the current characters have matched
         we have to add 1 to the result
         then find longest subsequence in the remaining part of the strings.*/
        if(text1.charAt(ind1) == text2.charAt(ind2))
            return dp[ind1][ind2] = 1 + longestCommonSubsequenceMemoization(ind1 - 1, ind2 - 1, text1, text2, dp);

        /* not match case
         in this case, we try both options
         try eliminating a character on both the strings seperately
         which option gives longest subsequence take that.

         since current characters are not matching, we have to add 0(stating not match)
         and try both the options and consider maximum among them*/
        else
            return dp[ind1][ind2] = 0 + Math.max(
                    longestCommonSubsequenceMemoization(ind1 - 1, ind2, text1, text2, dp),
                    longestCommonSubsequenceMemoization(ind1, ind2 -1, text1, text2, dp)
            );
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * m)
            explore all states

         Space Complexity: O(n * m) + O(n + m)
            O(n * m) - DP array
            O(n + m) - stack space
     */
    public int longestCommonSubsequenceMemoizationShiftIndexMain(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        /* shift index by 1
         index 0 refers -1,
         index 1 refers 0
         index n refers n-1*/
        int[][] dp = new int[n+1][m+1];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return longestCommonSubsequenceMemoizatioShiftIndex(n, m, text1, text2, dp);
    }

    public int longestCommonSubsequenceMemoizatioShiftIndex(int ind1, int ind2, String text1, String text2, int[][] dp){

        // Base case:
        /* if any the string has reached end,
         there is nothing more to compare, stop recursion*/
        // in the shift indexed a index 0 refers to -1
        if(ind1 == 0 || ind2 == 0)
            return 0;

        if(dp[ind1][ind2] != -1)
            return dp[ind1][ind2];

        // match case:
        /* since the current characters have matched
         we have to add 1 to the result
         then find longest subsequence in the remaining part of the strings.*/
        // since every index refers to -1 in the string perform comparison on ind-1 index
        if(text1.charAt(ind1-1) == text2.charAt(ind2-1))
            return dp[ind1][ind2] = 1 + longestCommonSubsequenceMemoizatioShiftIndex(ind1 - 1, ind2 - 1, text1, text2, dp);

        /* not match case
         in this case, we try both options
         try eliminating a character on both the strings seperately
         which option gives longest subsequence take that.

         since current characters are not matching, we have to add 0(stating not match)
         and try both the options and consider maximum among them*/
        else
            return dp[ind1][ind2] = 0 + Math.max(
                    longestCommonSubsequenceMemoizatioShiftIndex(ind1 - 1, ind2, text1, text2, dp),
                    longestCommonSubsequenceMemoizatioShiftIndex(ind1, ind2 -1, text1, text2, dp)
            );
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * m)
            explore all states

         Space Complexity: O(n * m)
            O(n * m) - DP array
     */
    public int longestCommonSubsequenceTabulation(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        /* shift index by 1
         index 0 refers -1,
         index 1 refers 0
         index n refers n-1*/
        int[][] dp = new int[n+1][m+1];

        // ind1 == 0, we return 0
        // this means, first row
        for(int j = 0; j < m + 1; j++)
            dp[0][j] = 0;

        // ind2 == 0, we return 0
        // this means, first column
        for(int i = 0; i < n+1; i++)
            dp[i][0] = 0;

        for(int ind1 = 1; ind1 < n+1; ind1++){
            for(int ind2 = 1; ind2 < m+1; ind2++){
                if(text1.charAt(ind1-1) == text2.charAt(ind2-1))
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ ind2 - 1];

                /* not match case
             in this case, we try both options
             try eliminating a character on both the strings seperately
             which option gives longest subsequence take that.

             since current characters are not matching, we have to add 0(stating not match)
             and try both the options and consider maximum among them*/
                else
                    dp[ind1][ind2] = 0 + Math.max(
                            dp[ind1 - 1][ind2],
                            dp[ind1][ind2 -1]
                    );
            }
        }

        return dp[n][m];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * m)
            explore all states

         Space Complexity: O(m)
            O(m) - DP array
     */
    public int longestCommonSubsequenceSpaceOptimization(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        /* shift index by 1
         index 0 refers -1,
         index 1 refers 0
         index n refers n-1*/
        // create a 1D array same as number of column in the 2D DP array
        int[] prev = new int[m+1];

        // ind1 == 0, we return 0
        // this means, first row
        for(int j = 0; j < m + 1; j++)
            prev[j] = 0;

        for(int ind1 = 1; ind1 < n+1; ind1++){
            int[] cur = new int[m+1];
            for(int ind2 = 1; ind2 < m+1; ind2++){
                if(text1.charAt(ind1-1) == text2.charAt(ind2-1))
                    cur[ind2] = 1 + prev[ ind2 - 1];

                /* not match case
             in this case, we try both options
             try eliminating a character on both the strings seperately
             which option gives longest subsequence take that.

             since current characters are not matching, we have to add 0(stating not match)
             and try both the options and consider maximum among them*/
                else
                   cur[ind2] = 0 + Math.max(
                            prev[ind2],
                            cur[ind2 -1]
                    );
            }
            prev = cur;
        }

        return prev[m];
    }
}
