package dynamic_programming.strings;

/*
Problem:
    https://takeuforward.org/data-structure/minimum-insertions-deletions-to-convert-string-dp-30/
    https://leetcode.com/problems/delete-operation-for-two-strings/description/
 */
public class MinimumInsertionsOrDeletionsToConvertString1To2 {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * m)
            explore all states

        Space Complexity: O(n * m)
            O(n * m) - DP array

        Note: You can also, use Space Optimized method of `LongestCommonSubsequence.java`
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        return (n + m) - 2 * longestCommonSubsequence(word1, word2);
    }

    public int longestCommonSubsequence(String s, String t){
        int n = s.length();
        int m =  t.length();

        int dp[][] = new int[n+1][m+1];
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
