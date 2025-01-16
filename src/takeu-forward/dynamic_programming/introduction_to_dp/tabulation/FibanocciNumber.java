package dynamic_programming.introduction_to_dp.tabulation;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/dynamic-programming-introduction/
    https://www.geeksforgeeks.org/problems/introduction-to-dp/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=introduction-to-dp
 */
public class FibanocciNumber {

    /*
    Explained in notes, check

        Steps for tabulation:
            Find the base case
            Then, solve slighly bigger problems using base case
            keep solving bigger problem, till you reach solving the actual problem
            Finally, you will get the answer you were searching for

    Time Complexity: O(N)
        We are running a simple iterative loop

    Space Complexity: O(N)
        DP array
     */
    static long bottomUp(int n) {
        if(n <= 1)
            return n;

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

       return dp[n];
    }
}
