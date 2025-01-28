package dynamic_programming.strings;

/*
Problem:
    https://takeuforward.org/data-structure/minimum-insertions-to-make-string-palindrome-dp-29/
    https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
 */
public class MinimumInsertionsToMakeStringPalindrome {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*N)
            There are two nested loops

        Space Complexity: O(N*N)
            We are using an external array of size (N*N). Stack Space is eliminated.

        Note: You can also, use Space Optimized method of `LongestCommonSubsequence.java`

        Note: if they ask for maximum insertions to make palindrome
            answer will be length of String s
            because, if we add same string s after it
            it will become palindrome
     */
    public int minInsertions(String s) {
        return s.length() - longestPalindromeSubsequence(s);
    }

    public int longestPalindromeSubsequence(String s){
        String t = new StringBuilder(s).reverse().toString();

        return longestCommonSubSequenceTabulation(s, t);
    }

    public int longestCommonSubSequenceTabulation(String s, String t){
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
