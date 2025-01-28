package dynamic_programming.strings;

/*
Problem:
    https://takeuforward.org/data-structure/longest-common-substring-dp-27/
    https://www.geeksforgeeks.org/problems/longest-common-substring1452/1
 */
public class LongestCommonSubstring {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are two nested loops

        Space Complexity: O(N*M)
            We are using an external array of size ‘N*M)’. Stack Space is eliminated.
     */
    public int longestCommonSubstrTabulation(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int j = 0; j < m + 1; j++)
            dp[0][j] = 0;
        for (int i = 0; i < n + 1; i++)
            dp[i][0] = 0;

        int longestSubString = 0;
        for (int ind1 = 1; ind1 < n + 1; ind1++) {
            for (int ind2 = 1; ind2 < m + 1; ind2++) {
                /* chacters matched, so can be longest substring.
                 since current characters are matching adding 1
                 get the longest substring till previous character is given by dp[ind1 - 1][ind2 - 1]*/
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                    longestSubString = Math.max(longestSubString, dp[ind1][ind2]);
                }
                /* else means, current characters in s1 and s2 are not matching
                 in that case, the previous substring found so far, cannot be considered to form substring in future
                 so we set the current index dp cell value to 0*/
                else
                    dp[ind1][ind2] = 0;
            }
        }
        return longestSubString;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are two nested loops.

        Space Complexity: O(M)
            We are using an external array of size ‘M+1’ to store only two rows.
     */
    public int longestCommonSubstrSpaceOptimization(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[] prev = new int[m + 1];

        for (int j = 0; j < m + 1; j++)
            prev[j] = 0;

        int longestSubString = 0;
        for (int ind1 = 1; ind1 < n + 1; ind1++) {
            int[] cur = new int[m + 1];
            for (int ind2 = 1; ind2 < m + 1; ind2++) {
                /* chacters matched, so can be longest substring.
                 since current characters are matching adding 1
                 get the longest substring till previous character is given by dp[ind1 - 1][ind2 - 1]*/
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
                    cur[ind2] = 1 + prev[ind2 - 1];
                    longestSubString = Math.max(longestSubString, cur[ind2]);
                }
                /* else means, current characters in s1 and s2 are not matching
                 in that case, the previous substring found so far, cannot be considered to form substring in future
                 so we set the current index dp cell value to 0*/
                else
                    cur[ind2] = 0;
            }
            prev = cur;
        }
        return longestSubString;
    }
}
