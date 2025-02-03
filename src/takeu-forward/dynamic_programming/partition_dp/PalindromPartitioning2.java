package dynamic_programming.partition_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/palindrome-partitioning-ii-front-partition-dp-53/
    https://leetcode.com/problems/palindrome-partitioning-ii/description/
 */
public class PalindromPartitioning2 {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential
            at each index, there are n possibilites

        Space Complexity: O(n)
            stack space
     */
    public int minCutRecursiveMain(String s) {
        int n = s.length();

        /* it will make parition after last character of the string,
         we should not consider that, so subtract -1, read notes.*/
        return minCutRecursive(0, n, s) -1;
    }

    public int minCutRecursive(int i, int n, String s){

        // Base case:
        if(i == n)
            return 0;

        int minCost = Integer.MAX_VALUE;
        for(int j = i; j < n; j++){
            int cost = 0;

            // String [i.. j]
            if(isPalindrome(i, j, s)) {
                /* since left string is palindrome 1 cut for left string
                 we need to cut further to make right string palindrome
                 so call method for it*/
                cost = 1 + minCutRecursive(j + 1, n, s);

                minCost = Math.min(minCost, cost);
            }
        }

        return minCost;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N^2)
            There are a total of N states and inside each state, a loop of size N(apparently) is running.

        Space Complexity: O(N) + Auxiliary stack space O(N)
            The first O(N) is for the dp array of size N.
     */
    public int minCutMemoizationMain(String s) {
        int n = s.length();

        // There is only one changing paramer, i
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        /* it will make parition after last character of the string,
         we should not consider that, so subtract -1, read notes.*/
        return minCutMemoization(0, n, s, dp) -1;
    }

    public int minCutMemoization(int i, int n, String s, int[] dp){

        // Base case:
        if(i == n)
            return 0;

        if(dp[i] != -1)
            return dp[i];

        int minCost = Integer.MAX_VALUE;
        for(int j = i; j < n; j++){
            int cost = 0;

            // String [i.. j]
            if(isPalindrome(i, j, s)) {
                /* since left string is palindrome 1 cut for left string
                 we need to cut further to make right string palindrome
                 so call method for it*/
                cost = 1 + minCutMemoization(j + 1, n, s, dp);

                minCost = Math.min(minCost, cost);
            }
        }

        return dp[i] = minCost;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N^2)
            There are a total of N states and inside each state, a loop of size N(apparently) is running.

        Space Complexity: O(N)
            The first O(N) is for the dp array of size N.
     */
    public int minCutTabulation(String s) {
        int n = s.length();

        // There is only one changing paramer, i
        int[] dp = new int[n+1];

        // Base case:
        dp[n] = 0;

        for(int i = n-1; i >= 0; i--){
            int minCost = Integer.MAX_VALUE;
            for(int j = i; j < n; j++){
                int cost = 0;

                // String [i.. j]
                if(isPalindrome(i, j, s)) {
                /* since left string is palindrome 1 cut for left string
                 we need to cut further to make right string palindrome
                 so call method for it*/
                    cost = 1 + dp[j+1];

                    minCost = Math.min(minCost, cost);
                }
            }
            dp[i] = minCost;
        }

        /* it will make parition after last character of the string,
         we should not consider that, so subtract -1, read notes.*/
        return dp[0] -1;
    }

    public boolean isPalindrome(int i, int j, String s){
        while(i < j){
            if(s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }

}
