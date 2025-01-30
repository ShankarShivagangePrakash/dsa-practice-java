package dynamic_programming.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Problem:
    https://takeuforward.org/data-structure/longest-divisible-subset-dp-44/
    https://leetcode.com/problems/largest-divisible-subset/description/
 */
public class LargestDivisibleSubset {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * log n) + O(n^2) + O(n) + O(n)
            O(n * log n) - sort the array
            O(n^2) - two for loops
            O(n) - find the largest subsequence length and index
            O(n) - get the subsequence by backtracking

        Space Complexity: O(n) + O(n) + O(n)
            O(n) - DP array
            O(n) - hash array
            O(n) - divisible subsequence
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;

        // First step: Sort the input array
        Arrays.sort(nums);

        int[] dp = new int[n];
        int[] hash = new int[n];

        Arrays.fill(dp, 1);
        for(int i = 0; i < n; i++)
            hash[i] = i;

        for(int i = 0; i < n; i++){
            for(int prevIndex = 0; prevIndex < i; prevIndex++){
                // divisible subsequence
                if(nums[i] % nums[prevIndex] == 0 && (1 + dp[prevIndex] > dp[i])) {
                    dp[i] = 1 + dp[prevIndex];
                    hash[i] = prevIndex;
                }
            }
        }

        // find the longest divisible subsequence and that index at which it occurs
        int indexAtWhichLongestSubsequenceOccurs = -1;
        int longestSubsequenceLength = -1;
        for(int i = 0; i < n; i++){
            if(longestSubsequenceLength < dp[i]){
                longestSubsequenceLength = dp[i];
                indexAtWhichLongestSubsequenceOccurs = i;
            }
        }

        int currentIndex = indexAtWhichLongestSubsequenceOccurs;
        ArrayList<Integer> result = new ArrayList<>();

        // backtrack longest subsequence elements
        result.add(nums[currentIndex]);
        while(hash[currentIndex] != currentIndex){
            currentIndex = hash[currentIndex];
            result.add(nums[currentIndex]);

        }

        /* longest subsequence elements are found,
         but it is in reverse order
         so reverse it to get the correct order*/
        Collections.reverse(result);

        return result;
    }
}
