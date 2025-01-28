package dynamic_programming.strings;

/*
Problem:
    https://takeuforward.org/data-structure/shortest-common-supersequence-dp-31/
    https://leetcode.com/problems/shortest-common-supersequence/description/
 */
public class ShortestSuperSequence {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*M)
            There are two nested loops

        Space Complexity: O(N*M)
            We are using an external array of size (N*M).
     */
    public String shortestCommonSupersequence(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n+1][m+1];
        for(int j = 0; j < m+1; j++)
            dp[0][j] = 0;
        for(int i = 0; i < n+1; i++)
            dp[i][0] = 0;

        // finding longest common subsequence
        for(int ind1 = 1; ind1 < n+1; ind1++){
            for(int ind2 = 1; ind2 < m+1; ind2++){
                if(s.charAt(ind1-1) == t.charAt(ind2-1))
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                else
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1-1][ind2], dp[ind1][ind2-1]);
            }
        }

        /* If they ask for finding the length of shortest supersequence
        return n + m - dp[n][m];*/

        int i = n, j = m;
        StringBuilder stringBuilder = new StringBuilder();

        while(i > 0 && j > 0){
            /* characters at current index in string s and t are matching
             means it is part of subsequence, so we have to add it only once
             and move to upper left diagonal cell*/
            if(s.charAt(i-1) == t.charAt(j-1)){
                stringBuilder.append(s.charAt(i - 1));
                i--;
                j--;
            }
            /* else and elese if means characters didn't match
             current cell value was taken from max of (left or upper cell)
             else if case refers to upper cell is larger than left cell
             so we move up, while moving up
             if we are moving up, we might loose the character at current index
             since row represents String `s` and column represents string `t`
             we will loose a character of string `s`
             so add it to answer*/
            else if(dp[i-1][j] > dp[i][j-1]){
                stringBuilder.append(s.charAt(i - 1));
                i--;
            }
            /* else means either
             left cell is larger or equal to upper cell
             if we move left, we will loose a character of string `t`
             so add it*/
            else{
                stringBuilder.append(t.charAt(j - 1));
                j--;
            }
        }

       /*  after completing above while loop
         if any characters of string `s` or `t` are not added to answer
         add it*/
        while(i > 0){
            stringBuilder.append(s.charAt(i-1));
            i--;
        }

        while(j > 0){
            stringBuilder.append(t.charAt(j-1));
            j--;
        }

        /* we have the answer, but it is in reverse order
         so reverse it and return*/
        return stringBuilder.reverse().toString();
    }
}
