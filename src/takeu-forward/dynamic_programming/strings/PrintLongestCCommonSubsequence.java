package dynamic_programming.strings;

import java.util.List;

/*
Problem:
    https://takeuforward.org/data-structure/print-longest-common-subsequence-dp-26/
    https://www.geeksforgeeks.org/problems/print-all-lcs-sequences3413/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=print-all-lcs-sequences
 */
public class PrintLongestCCommonSubsequence {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * m) + O(n + m)
            O(n * m) for filling DP array
            O(n + m) for backtracking DP array to find LCS string
                since we can move either left, up or right
                we will be not visit each cell of DP array
                at max we would visit n + m cells

        Space Complexity: O(n * m) + O(len)
            O(n * m) - DP array
            O(len) - LCS string
                len can be max(n, m)
     */
    public String print_longest_common_subsequencesTabulation(String s, String t) {
       int n = s.length();
       int m = s.length();

       int[][] dp = new int[n+1][m+1];

       for(int j = 0; j < m+1; j++)
           dp[0][j] = 0;

       for(int i = 0; i < n+1; i++)
           dp[i][0] = 0;

       for(int ind1 = 1; ind1 < n+1; ind1++){
           for(int ind2 = 1; ind2 < m+1; ind2++){
               if(s.charAt(ind1-1) == t.charAt(ind2-1))
                   dp[ind1][ind2] = 1 + dp[ind1 - 1][ ind2 - 1];
               else
                   dp[ind1][ind2] = 0 + Math.max(
                           dp[ind1 - 1][ind2],
                           dp[ind1][ind2 -1]
                   );
           }
       }

       // length of common subsequence will be
       int len = dp[n][m];

       // create a character arrray to store the longes subsequence
       char[] chars = new char[len];

       /* we will start traversing from last row last column of DP array
        initialize accordingly*/
       int i = n, j = m;

       /* since we are moving from back, we will find the last characters of the LCS
        so initalize char array pointer accordingly*/
       int index = len-1;

       while(i > 1 && j > 1){
          /*  if both the characters matches, it belongs to LCS
            add it
            we know, if the character matches, we were doing
            1 + dp[i-1][j-1]
            so we will decrement both i and j*/
           if(s.charAt(i) == t.charAt(j)){
               chars[index--] = s.charAt(i);
               i--;
               j--;
           }
           /* below two refers to not match case
            in case of not match
            we were doing 0 + max(dp[i-1][j], dp[i][j-1])
            in else if we are assuming dp[i-1][j] is greater means
            we got the value from upper cell, we move there*/
           else if(dp[i-1][j] > dp[i][j-1])
               i = i - 1;
           // else means either equal or left cell is greater
           else
               j = j - 1;
       }

       // convert char Array to String and return it
       return new String(chars);
    }
}
