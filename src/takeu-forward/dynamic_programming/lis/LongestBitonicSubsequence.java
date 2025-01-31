package dynamic_programming.lis;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/longest-bitonic-subsequence-dp-46/
    https://www.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
 */
public class LongestBitonicSubsequence {

    /*
    Approach:
        Explained in program

        Time Complexity: O(N*N)
            There are two nested loops that are run twice.

        Space Complexity: O(N)
            We are only using two rows of size n.
     */
    public static int LongestBitonicSequence(int n, int[] nums) {
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        // Increasing LIS from front (ascending)
        for(int i = 0; i < n; i++){
            for(int prevIndex = 0; prevIndex < i; prevIndex++){
                if(nums[i] > nums[prevIndex] && 1 + dp1[prevIndex] > dp1[i])
                    dp1[i] = 1 + dp1[prevIndex];
            }
        }

        // Increasing LIS from end of the array (descending)
        for(int i = n-1; i >= 0; i--){
            for(int prevIndex = n-1; prevIndex > i; prevIndex--){
                if(nums[i] > nums[prevIndex] && 1 + dp2[prevIndex] > dp2[i])
                    dp2[i] = 1 + dp2[prevIndex];
            }
        }

        int longestBitonicSequenceLength = 0;
        for(int i = 0; i < n; i++){
            /*// condition for GFG: They do not consider only increasing or decreasing sequence as monotonic sequence
            if(dp1[i] == 1 || dp2[i] == 1)
                continue;*/
            longestBitonicSequenceLength = Math.max(
                                                longestBitonicSequenceLength,
                                                dp1[i] + dp2[i] - 1
                                            );
        }

        return longestBitonicSequenceLength;
    }
}
