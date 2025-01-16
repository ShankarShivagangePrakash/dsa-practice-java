package dynamic_programming.introduction_to_dp.memoization;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/dynamic-programming-introduction/
    https://www.geeksforgeeks.org/problems/introduction-to-dp/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=introduction-to-dp
 */
public class FibanocciNumber {

    /*
    Approach:
        Explained in notes, check

        Summary:
            you know how to solve problems using recursion
            write the same logic, but
            create a DP array which will store the results of smaller problems
            during recursion, if we encounter that we have to solve same problem again
            don't solve it again, just get it from DP array

        Time Complexity: O(N)
            The overlapping subproblems will return the answer in constant time O(1).
            Therefore the total number of new subproblems we solve is ‘n’.
            Hence total time complexity is O(N).

        Space Complexity: O(N)
            We are using a recursion stack space(O(N)) and an array (again O(N)).
            Therefore total space complexity will be O(N) + O(N) ≈ O(N)
     */
    static long topDown(int n) {
       int[] dp = new int[n+1];
       Arrays.fill(dp, -1);

       return fibanocciRecursiveMemoization(n, dp);
    }

    public static int fibanocciRecursiveMemoization(int n, int[] dp){
        if(n <= 1)
            return n;

        if(dp[n] != -1)
            return dp[n];

        dp[n] = fibanocciRecursiveMemoization(n-1, dp) + fibanocciRecursiveMemoization(n-2, dp);
        return dp[n];
    }
}
