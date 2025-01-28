package dynamic_programming.strings;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/longest-palindromic-subsequence-dp-28/
    https://leetcode.com/problems/longest-palindromic-subsequence/description/
 */
public class LongestPalindromicSubsequence {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*N)
            There are two nested loops

        Space Complexity: O(N*N)
            We are using an external array of size ‘(N*N)’. Stack Space is eliminated.

        Note: You can also, use Space Optimized method of `LongestCommonSubsequence.java`
     */
    public int longestPalindromeSubsequenceTabulation(String s) {
        String t = new StringBuilder(s).reverse().toString();
        return longestSubSequenceTabulation(s, t);
    }

    public int longestSubSequenceTabulation(String s, String t){
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n+1][m+1];

        for(int j = 0; j < m+1; j++)
            dp[0][j] = 0;

        for(int i = 0; i < n+1; i++)
            dp[i][0] = 0;

        for(int ind1 = 1; ind1 < n+1; ind1++){
            for(int ind2 = 1; ind2 < m+1; ind2++){
                if(s.charAt(ind1-1) == t.charAt(ind2-1))
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                else
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1-1][ind2], dp[ind1][ind2-1]);
            }
        }

        return dp[n][m];
    }
}
