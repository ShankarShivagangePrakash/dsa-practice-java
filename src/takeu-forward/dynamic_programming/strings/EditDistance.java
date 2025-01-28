package dynamic_programming.strings;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/edit-distance-dp-33/
    https://leetcode.com/problems/edit-distance/description/
 */
public class EditDistance {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential
            At each cell, we can perform three operations
            it will be O(3^n * 3^m)
            so exponential

        Space optimization: O(n + m)
            stack space
     */
    public int minDistanceRecursiveMain(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // represents the minimum distance required to convert string word1 to string word2 considering the ranges (0...n-1) and (0....m-1)
        return minDistanceRecursive(n-1, m-1, word1, word2);
    }

    public int minDistanceRecursive(int ind1, int ind2, String s, String t){

        // Base case:
        /* if first string `s` is empty, then we can new character to make it as string `t`
         how much to add, how many characters available in string t
         it is provided by ind2 + 1
         + 1 is because it is zero based index*/
        if(ind1 < 0)
            return ind2 + 1;

        /* if second string `t` is empty, then we have delete characters from string `s` to make it as `t`
         how many characters to delete, all characters present in string `s`
         it will be ind1 + 1*/
        if(ind2 < 0)
            return ind1 + 1;

        /* if the characters are matching, no need to do any operation, so add + 0
         and shrink both strings, to match next/previous characters.*/
        if(s.charAt(ind1) == t.charAt(ind2))
            return 0 + minDistanceRecursive(ind1-1, ind2-1, s, t);

        // else means, current characters are not matching, you can perform three operations
        else{
            /* you insert character to string `s` present at t.charAt(ind2)
             so, it will match and ind2 will reduce
             but since you added a character, ind1 might have incremented and reducing will result
             in same ind,
             so for, insertion ind2 will reduce*/
            int insertion = 1 + minDistanceRecursive(ind1, ind2-1, s, t);

            /*// deletion is an operation, add + 1
            // you are deleting current character in the assumption that, in previous index i might match character
            // actually match is not yet found
            // so we just reduce ind1 */
            int deletion = 1 + minDistanceRecursive(ind1-1, ind2, s, t);

            /*// replace means, replace current character in string s
            // with current character of string t
            // so it matches, so reduce both ind1 and ind2
            // it is an operation, add + 1*/
            int replace = 1 + minDistanceRecursive(ind1-1, ind2-1, s, t);

            // consider minimum of all three operations
            return Math.min(
                        replace,
                        Math.min(insertion, deletion)
                    );
        }
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are N*M states therefore at max ‘N*M’ new problems will be solved.

        Space Complexity: O(N*M) + O(N+M)
            We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
     */
    public int minDistanceMemoizationMain(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n][m];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        // represents the minimum distance required to convert string word1 to string word2 considering the ranges (0...n-1) and (0....m-1)
        return minDistanceMemoization(n-1, m-1, word1, word2, dp);
    }

    public int minDistanceMemoization(int ind1, int ind2, String s, String t, int[][] dp){

        // Base case:
        /* if first string `s` is empty, then we can new character to make it as string `t`
         how much to add, how many characters available in string t
         it is provided by ind2 + 1
         + 1 is because it is zero based index*/
        if(ind1 < 0)
            return ind2 + 1;

        /* if second string `t` is empty, then we have delete characters from string `s` to make it as `t`
         how many characters to delete, all characters present in string `s`
         it will be ind1 + 1*/
        if(ind2 < 0)
            return ind1 + 1;


        if(dp[ind1][ind2] != -1)
            return dp[ind1][ind2];

        /* if the characters are matching, no need to do any operation, so add + 0
         and shrink both strings, to match next/previous characters.*/
        if(s.charAt(ind1) == t.charAt(ind2))
            return dp[ind1][ind2] = 0 + minDistanceMemoization(ind1-1, ind2-1, s, t, dp);

            // else means, current characters are not matching, you can perform three operations
        else{
            /* you insert character to string `s` present at t.charAt(ind2)
             so, it will match and ind2 will reduce
             but since you added a character, ind1 might have incremented and reducing will result
             in same ind,
             so for, insertion ind2 will reduce*/
            int insertion = 1 + minDistanceMemoization(ind1, ind2-1, s, t, dp);

            /*// deletion is an operation, add + 1
            // you are deleting current character in the assumption that, in previous index i might match character
            // actually match is not yet found
            // so we just reduce ind1 */
            int deletion = 1 + minDistanceMemoization(ind1-1, ind2, s, t, dp);

            /*// replace means, replace current character in string s
            // with current character of string t
            // so it matches, so reduce both ind1 and ind2
            // it is an operation, add + 1*/
            int replace = 1 + minDistanceMemoization(ind1-1, ind2-1, s, t, dp);

            // consider minimum of all three operations
            return dp[ind1][ind2] = Math.min(
                    replace,
                    Math.min(insertion, deletion)
            );
        }
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential
            At each cell, we can perform three operations
            it will be O(3^n * 3^m)
            so exponential

        Space optimization: O(n + m)
            stack space
     */
    public int minDistanceRecursiveShiftIndexMain(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // represents the minimum distance required to convert string word1 to string word2 considering the ranges (0...n-1) and (0....m-1)
        return minDistanceRecursiveShiftIndex(n, m, word1, word2);
    }

    public int minDistanceRecursiveShiftIndex(int ind1, int ind2, String s, String t){

        // Base case:
        /* if first string `s` is empty, then we can new character to make it as string `t`
         how much to add, how many characters available in string t
         it is provided by ind2 + 1
         + 1 is because it is zero based index*/
        /* index has been shifted, now 0 means -1 in the string
         also it has become 1 based indexing so we need not add +1 in return statements*/
        if(ind1 == 0)
            return ind2;

        /* if second string `t` is empty, then we have delete characters from string `s` to make it as `t`
         how many characters to delete, all characters present in string `s`
         it will be ind1 + 1*/
        if(ind2 == 0)
            return ind1;

        /* if the characters are matching, no need to do any operation, so add + 0
         and shrink both strings, to match next/previous characters.*/
        if(s.charAt(ind1-1) == t.charAt(ind2-1))
            return 0 + minDistanceRecursiveShiftIndex(ind1-1, ind2-1, s, t);

            // else means, current characters are not matching, you can perform three operations
        else{
            /* you insert character to string `s` present at t.charAt(ind2)
             so, it will match and ind2 will reduce
             but since you added a character, ind1 might have incremented and reducing will result
             in same ind,
             so for, insertion ind2 will reduce*/
            int insertion = 1 + minDistanceRecursiveShiftIndex(ind1, ind2-1, s, t);

            /*// deletion is an operation, add + 1
            // you are deleting current character in the assumption that, in previous index i might match character
            // actually match is not yet found
            // so we just reduce ind1 */
            int deletion = 1 + minDistanceRecursiveShiftIndex(ind1-1, ind2, s, t);

            /*// replace means, replace current character in string s
            // with current character of string t
            // so it matches, so reduce both ind1 and ind2
            // it is an operation, add + 1*/
            int replace = 1 + minDistanceRecursiveShiftIndex(ind1-1, ind2-1, s, t);

            // consider minimum of all three operations
            return Math.min(
                    replace,
                    Math.min(insertion, deletion)
            );
        }
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are N*M states therefore at max ‘N*M’ new problems will be solved.

        Space Complexity: O(N*M) + O(N+M)
            We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
     */
    public int minDistanceMemoizationShiftIndexMain(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n+1][m+1];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        // represents the minimum distance required to convert string word1 to string word2 considering the ranges (0...n-1) and (0....m-1)
        return minDistanceMemoizationShiftIndex(n, m, word1, word2, dp);
    }

    public int minDistanceMemoizationShiftIndex(int ind1, int ind2, String s, String t, int[][] dp){

        // Base case:
        /* if first string `s` is empty, then we can new character to make it as string `t`
         how much to add, how many characters available in string t
         it is provided by ind2 + 1
         + 1 is because it is zero based index*/
        if(ind1 == 0)
            return ind2;

        /* if second string `t` is empty, then we have delete characters from string `s` to make it as `t`
         how many characters to delete, all characters present in string `s`
         it will be ind1 + 1*/
        if(ind2 == 0)
            return ind1;


        if(dp[ind1][ind2] != -1)
            return dp[ind1][ind2];

        /* if the characters are matching, no need to do any operation, so add + 0
         and shrink both strings, to match next/previous characters.*/
        if(s.charAt(ind1-1) == t.charAt(ind2-1))
            return dp[ind1][ind2] = 0 + minDistanceMemoizationShiftIndex(ind1-1, ind2-1, s, t, dp);

            // else means, current characters are not matching, you can perform three operations
        else{
            /* you insert character to string `s` present at t.charAt(ind2)
             so, it will match and ind2 will reduce
             but since you added a character, ind1 might have incremented and reducing will result
             in same ind,
             so for, insertion ind2 will reduce*/
            int insertion = 1 + minDistanceMemoizationShiftIndex(ind1, ind2-1, s, t, dp);

            /*// deletion is an operation, add + 1
            // you are deleting current character in the assumption that, in previous index i might match character
            // actually match is not yet found
            // so we just reduce ind1 */
            int deletion = 1 + minDistanceMemoizationShiftIndex(ind1-1, ind2, s, t, dp);

            /*// replace means, replace current character in string s
            // with current character of string t
            // so it matches, so reduce both ind1 and ind2
            // it is an operation, add + 1*/
            int replace = 1 + minDistanceMemoizationShiftIndex(ind1-1, ind2-1, s, t, dp);

            // consider minimum of all three operations
            return dp[ind1][ind2] = Math.min(
                    replace,
                    Math.min(insertion, deletion)
            );
        }
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are two nested loops

        Space Complexity: O(N*M)
            We are using an external array of size ‘N*M’. Stack Space is eliminated.
     */
    public int minDistanceTabulation(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n+1][m+1];

        /* base case:
         ind1 represents `i`
         ind2 represents `j`*/
        for(int j = 0; j < m+1; j++)
            dp[0][j] = j;
        for(int i = 0; i < n+1; i++)
            dp[i][0] = i;

        for(int ind1 = 1; ind1 < n+1; ind1++){
            for(int ind2 = 1; ind2 < m+1; ind2++){

                 /* if the characters are matching, no need to do any operation, so add + 0
         and shrink both strings, to match next/previous characters.*/
                if(word1.charAt(ind1-1) == word2.charAt(ind2-1))
                    dp[ind1][ind2] = 0 + dp[ind1-1][ind2-1];

                    // else means, current characters are not matching, you can perform three operations
                else{
                    /* you insert character to string `s` present at t.charAt(ind2)
             so, it will match and ind2 will reduce
             but since you added a character, ind1 might have incremented and reducing will result
             in same ind,
             so for, insertion ind2 will reduce*/
                    int insertion = 1 + dp[ind1][ind2-1];

                    /*// deletion is an operation, add + 1
            // you are deleting current character in the assumption that, in previous index i might match character
            // actually match is not yet found
            // so we just reduce ind1 */
                    int deletion = 1 + dp[ind1-1][ind2];

                    /*// replace means, replace current character in string s
            // with current character of string t
            // so it matches, so reduce both ind1 and ind2
            // it is an operation, add + 1*/
                    int replace = 1 + dp[ind1-1][ind2-1];

                    // consider minimum of all three operations
                    dp[ind1][ind2] = Math.min(
                            replace,
                            Math.min(insertion, deletion)
                    );
                }
            }
        }
        return dp[n][m];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are two nested loops.

        Space Complexity: O(M)
            We are using an external array of size ‘M+1’ to store two rows.
     */
    public int minDistanceSpaceOptimization(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] prev = new int[m+1];

        /* base case:
         ind1 represents `i`
         ind2 represents `j`*/
        for(int j = 0; j < m+1; j++)
            prev[j] = j;

        for(int ind1 = 1; ind1 < n+1; ind1++){
            int[] cur = new int[m+1];
            // base case: every row first column value will be `i`
            cur[0] = ind1;
            for(int ind2 = 1; ind2 < m+1; ind2++){

                 /* if the characters are matching, no need to do any operation, so add + 0
         and shrink both strings, to match next/previous characters.*/
                if(word1.charAt(ind1-1) == word2.charAt(ind2-1))
                    cur[ind2] = 0 + prev[ind2-1];

                    // else means, current characters are not matching, you can perform three operations
                else{
                    /* you insert character to string `s` present at t.charAt(ind2)
             so, it will match and ind2 will reduce
             but since you added a character, ind1 might have incremented and reducing will result
             in same ind,
             so for, insertion ind2 will reduce*/
                    int insertion = 1 + cur[ind2-1];

                    /*// deletion is an operation, add + 1
            // you are deleting current character in the assumption that, in previous index i might match character
            // actually match is not yet found
            // so we just reduce ind1 */
                    int deletion = 1 + prev[ind2];

                    /*// replace means, replace current character in string s
            // with current character of string t
            // so it matches, so reduce both ind1 and ind2
            // it is an operation, add + 1*/
                    int replace = 1 + prev[ind2-1];

                    // consider minimum of all three operations
                    cur[ind2] = Math.min(
                            replace,
                            Math.min(insertion, deletion)
                    );
                }
            }
            prev = cur;
        }
        return prev[m];
    }
}
