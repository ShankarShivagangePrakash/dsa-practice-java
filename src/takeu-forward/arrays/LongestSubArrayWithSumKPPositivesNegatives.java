package arrays;

import java.util.HashMap;
/*
Problem:
    https://takeuforward.org/arrays/longest-subarray-with-sum-k-postives-and-negatives/
    https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=longest-sub-array-with-sum-k
 */
public class LongestSubArrayWithSumKPPositivesNegatives {

    /*
    check program `LongestSubArrayWithSumK.java` there is a detailed explanation.
    Time complexity: O(n^2)
    Space complexity: O(1)
     */
    public static int longestSubArrayWithSumKBruteForce(int[] arr, long k){
        int maxLen = 0;
        for(int i = 0; i < arr.length; i++){
            long sum = 0;
            for(int j = i; j < arr.length; j++){
                sum += arr[j];

                if(sum == k){
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    /*
     check program `LongestSubArrayWithSumK.java` there is a detailed explanation.
     Prefix sum approach
     Time complexity: O(n)
     Space complexity: O(n)
     */
    public static int longestSubArrayWithSumKOptimal(int[] arr, long k){
        int maxLen = 0;
        long sum = 0;
        HashMap<Long, Integer> prefixSum = new HashMap<>();

        for(int i = 0; i < arr.length; i++){
            sum += arr[i];

            if(sum == k){
                maxLen = Math.max(maxLen, i + 1);
            }

            long rem = sum - k;
            if (prefixSum.containsKey(rem)) {
                int len = i - prefixSum.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            if (!prefixSum.containsKey(sum)) {
                prefixSum.put(sum, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] a = { -1, 1, 1};
        int k = 1;
        int len = longestSubArrayWithSumKBruteForce(a, k);
        int len2 = longestSubArrayWithSumKOptimal(a, k);
        System.out.println("The length of the longest subarray using brute force approach: " + len);
        System.out.println("The length of the longest subarray using optimal approach (Prefix sum): " + len2);
    }
}
