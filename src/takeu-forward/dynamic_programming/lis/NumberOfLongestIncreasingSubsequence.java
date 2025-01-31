package dynamic_programming.lis;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/number-of-longest-increasing-subsequences-dp-47/
    https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/
 */
public class NumberOfLongestIncreasingSubsequence {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*N)
            There are two nested loops that are run twice.

        Space Complexity: O(N)
            We are only using two rows of size n.
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n]; // Stores LIS length ending at index i
        int[] ct = new int[n]; // Stores count of LIS ending at index i

        Arrays.fill(dp, 1);
        Arrays.fill(ct, 1);

        int maxLen = 1; // Track max LIS length

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[i] > nums[prev]) {
                    // Found a longer LIS ending at i
                    if (dp[prev] + 1 > dp[i]) {
                        dp[i] = dp[prev] + 1;
                        // Copy count from prev
                        ct[i] = ct[prev];
                    }
                    // Same length LIS found
                    else if (dp[prev] + 1 == dp[i]) {
                        ct[i] += ct[prev];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        // Count total number of subsequences with maxLen
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                count += ct[i];
            }
        }
        return count;
    }
}
