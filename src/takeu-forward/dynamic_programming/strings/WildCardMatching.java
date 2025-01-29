package dynamic_programming.strings;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/wildcard-matching-dp-34/
    https://leetcode.com/problems/wildcard-matching/description/
 */
public class WildCardMatching {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential
            At each index, if it is *
                we can stay at the same location or move
                so definitely more than O(2^n * 2^m)

        Space Complexity: O(n + m)
            stack space
     */
    public boolean isMatchRecursiveMain(String s, String t) {
        /* this string transferring is done because,
         s will have only characters, t will have wild characters
         according to problem statement
         but our code will handle if s has wild characters and t with only alphabets*/
        String temp = s;
        s = t;
        t = temp;
        int n = s.length();
        int m = t.length();

        return isMatchRecursive(n-1, m-1, s, t);
    }

    public boolean isMatchRecursive(int i, int j, String s, String t){

        // both strings are exhausted
        if(i < 0 && j < 0)
            return true;
        /* string `s` having wild characters is exhausted
         but string `t` is still left
         so matching was unsuccessful, return false*/
        if(i < 0 && j >= 0)
            return false;

        // string `t` exhausted
        if(j < 0){
            /* if the remaining characters in string `s`
             are only * then return true (because * can be replaced with "")
             else return false*/
            for(int it = 0; it <= i; it++){
                if(s.charAt(it) != '*')
                    return false;
            }
            return true;
        }

        /* chacters in string `s` and `t` matched
         so shrink the range of string to be matched*/
        if(s.charAt(i) == t.charAt(j) || s.charAt(i) == '?')
            return isMatchRecursive(i-1, j-1, s, t);

        /* current character is *
         so we can replace * with 0 to ... n number of characters
         first case - isMatchRecursive(i-1, j, s, t)
             we are replacing with "" empty character
             so string `s` will move, but match didn't happen so string `t` stays there
         second case -
             we are replacing with one character to match current character of string `t`
             but we can add any number of characters we want for *
             so we stay there only and since current character of `t` is matched
             we decrement j
         in next recursion, it will add or remove one more character */
        else if(s.charAt(i) == '*')
            return isMatchRecursive(i-1, j, s, t) || isMatchRecursive(i, j-1, s, t);

        // else means current characters didn't match, so return false
        else
            return false;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * m)
            fill DP array

        Space Complexity: O(n * m) +  O(n + m)
            O(n + m) - stack space
            O(n * m) - 2D DP array
     */
    public boolean isMatchMemoizationMain(String s, String t) {
        /* this string transferring is done because,
         s will have only characters, t will have wild characters
         according to problem statement
         but our code will handle if s has wild characters and t with only alphabets*/
        String temp = s;
        s = t;
        t = temp;
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n][m];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        int res = isMatchMemoization(n-1, m-1, s, t, dp);

        return (res == 1) ? true : false;
    }

    public int isMatchMemoization(int i, int j, String s, String t, int[][] dp){

        // both strings are exhausted
        if(i < 0 && j < 0)
            return 1;
        /* string `s` having wild characters is exhausted
         but string `t` is still left
         so matching was unsuccessful, return false*/
        if(i < 0 && j >= 0)
            return 0;

        // string `t` exhausted
        if(j < 0){
            /* if the remaining characters in string `s`
             are only * then return true (because * can be replaced with "")
             else return false*/
            for(int it = 0; it <= i; it++){
                if(s.charAt(it) != '*')
                    return 0;
            }
            return 1;
        }

        if(dp[i][j] != -1)
            return dp[i][j];

        /* chacters in string `s` and `t` matched
         so shrink the range of string to be matched*/
        if(s.charAt(i) == t.charAt(j) || s.charAt(i) == '?')
            return dp[i][j] = isMatchMemoization(i-1, j-1, s, t, dp);

        /* current character is *
         so we can replace * with 0 to ... n number of characters
         first case - isMatchRecursive(i-1, j, s, t)
             we are replacing with "" empty character
             so string `s` will move, but match didn't happen so string `t` stays there
         second case -
             we are replacing with one character to match current character of string `t`
             but we can add any number of characters we want for *
             so we stay there only and since current character of `t` is matched
             we decrement j
         in next recursion, it will add or remove one more character */
        else if(s.charAt(i) == '*')
            return dp[i][j] = isMatchMemoization(i-1, j, s, t, dp) + isMatchMemoization(i, j-1, s, t, dp);

            // else means current characters didn't match, so return false
        else
            return  dp[i][j] = 0;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential
            At each index, if it is *
                we can stay at the same location or move
                so definitely more than O(2^n * 2^m)

        Space Complexity: O(n + m)
            stack space
     */
    public boolean isMatchRecursiveShiftIndexMain(String s, String t) {
        /* this string transferring is done because,
         s will have only characters, t will have wild characters
         according to problem statement
         but our code will handle if s has wild characters and t with only alphabets*/
        String temp = s;
        s = t;
        t = temp;
        int n = s.length();
        int m = t.length();

        return isMatchRecursiveShiftIndex(n, m, s, t);
    }

    public boolean isMatchRecursiveShiftIndex(int i, int j, String s, String t){

        // both strings are exhausted
        if(i == 0 && j == 0)
            return true;
        /* string `s` having wild characters is exhausted
         but string `t` is still left
         so matching was unsuccessful, return false*/
        if(i == 0 && j > 0)
            return false;

        // string `t` exhausted
        if(j == 0){
            /* if the remaining characters in string `s`
             are only * then return true (because * can be replaced with "")
             else return false*/
            for(int it = 1; it <= i; it++){
                if(s.charAt(it-1) != '*')
                    return false;
            }
            return true;
        }

        /* chacters in string `s` and `t` matched
         so shrink the range of string to be matched*/
        if(s.charAt(i-1) == t.charAt(j-1) || s.charAt(i-1) == '?')
            return isMatchRecursiveShiftIndex(i-1, j-1, s, t);

        /* current character is *
         so we can replace * with 0 to ... n number of characters
         first case - isMatchRecursive(i-1, j, s, t)
             we are replacing with "" empty character
             so string `s` will move, but match didn't happen so string `t` stays there
         second case -
             we are replacing with one character to match current character of string `t`
             but we can add any number of characters we want for *
             so we stay there only and since current character of `t` is matched
             we decrement j
         in next recursion, it will add or remove one more character */
        else if(s.charAt(i-1) == '*')
            return isMatchRecursiveShiftIndex(i-1, j, s, t) || isMatchRecursiveShiftIndex(i, j-1, s, t);

            // else means current characters didn't match, so return false
        else
            return false;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * m)
            fill DP array

        Space Complexity: O(n * m) +  O(n + m)
            O(n + m) - stack space
            O(n * m) - 2D DP array
     */
    public boolean isMatchMemoizationShiftIndexMain(String s, String t) {
        /* this string transferring is done because,
         s will have only characters, t will have wild characters
         according to problem statement
         but our code will handle if s has wild characters and t with only alphabets*/
        String temp = s;
        s = t;
        t = temp;
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n+1][m+1];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        int res = isMatchMemoizationShiftIndex(n, m, s, t, dp);

        return (res == 1) ? true : false;
    }

    public int isMatchMemoizationShiftIndex(int i, int j, String s, String t, int[][] dp){

        // both strings are exhausted
        if(i == 0 && j == 0)
            return 1;
        /* string `s` having wild characters is exhausted
         but string `t` is still left
         so matching was unsuccessful, return false*/
        if(i == 0 && j > 0)
            return 0;

        // string `t` exhausted
        if(j == 0){
            /* if the remaining characters in string `s`
             are only * then return true (because * can be replaced with "")
             else return false*/
            for(int it = 1; it <= i; it++){
                if(s.charAt(it-1) != '*')
                    return 0;
            }
            return 1;
        }

        if(dp[i][j] != -1)
            return dp[i][j];

        /* chacters in string `s` and `t` matched
         so shrink the range of string to be matched*/
        if(s.charAt(i-1) == t.charAt(j-1) || s.charAt(i-1) == '?')
            return dp[i][j] = isMatchMemoizationShiftIndex(i-1, j-1, s, t, dp);

        /* current character is *
         so we can replace * with 0 to ... n number of characters
         first case - isMatchRecursive(i-1, j, s, t)
             we are replacing with "" empty character
             so string `s` will move, but match didn't happen so string `t` stays there
         second case -
             we are replacing with one character to match current character of string `t`
             but we can add any number of characters we want for *
             so we stay there only and since current character of `t` is matched
             we decrement j
         in next recursion, it will add or remove one more character */
        else if(s.charAt(i-1) == '*')
            return dp[i][j] = isMatchMemoizationShiftIndex(i-1, j, s, t, dp) + isMatchMemoizationShiftIndex(i, j-1, s, t, dp);

        // else means current characters didn't match, so return false
        else
            return  dp[i][j] = 0;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * m)
            fill DP array

        Space Complexity: O(n * m)
            O(n * m) - 2D DP array
     */
    public boolean isMatchTabulation(String s, String t) {
        /* this string transferring is done because,
         s will have only characters, t will have wild characters
         according to problem statement
         but our code will handle if s has wild characters and t with only alphabets*/
        String temp = s;
        s = t;
        t = temp;
        int n = s.length();
        int m = t.length();

        boolean[][] dp = new boolean[n+1][m+1];

        dp[0][0] = true;

        // Base case: every column of first row
        for(int j = 1; j < m+1; j++)
            dp[0][j] = false;

        // Base case: every row first column
        for(int i = 1; i < n+1; i++){
            boolean flag = true;
            for(int it = 1; it <= i && flag; it++){
                if(s.charAt(it-1) != '*')
                    flag = false;
            }
            dp[i][0] = flag;
        }

        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < m+1; j++){
                if(s.charAt(i-1) == t.charAt(j-1) || s.charAt(i-1) == '?')
                    dp[i][j] = dp[i-1][j-1];

                else if(s.charAt(i-1) == '*')
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];

                else
                    dp[i][j] = false;
            }
        }
        return dp[n][m];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * m)
            fill DP array

        Space Complexity: O(m)
            O(m) - 1D DP array
     */
    public boolean isMatchSpaceOptimization(String s, String t) {
        /* this string transferring is done because,
         s will have only characters, t will have wild characters
         according to problem statement
         but our code will handle if s has wild characters and t with only alphabets*/
        String temp = s;
        s = t;
        t = temp;
        int n = s.length();
        int m = t.length();

        boolean[] prev = new boolean[m+1];

        prev[0] = true;

        for(int j = 1; j < m+1; j++)
            prev[j] = false;

        for(int i = 1; i < n+1; i++){
            // every row first column base case:
            boolean[] cur = new boolean[m+1];
            boolean flag = true;
            for(int it = 1; it <= i && flag; it++){
                if(s.charAt(it-1) != '*')
                    flag = false;
            }
            cur[0] = flag;

            for(int j = 1; j < m+1; j++){
                if(s.charAt(i-1) == t.charAt(j-1) || s.charAt(i-1) == '?')
                    cur[j] = prev[j-1];

                else if(s.charAt(i-1) == '*')
                    cur[j] = prev[j] || cur[j-1];

                else
                    cur[j] = false;
            }
            prev = cur;
        }
        return prev[m];
    }

}
