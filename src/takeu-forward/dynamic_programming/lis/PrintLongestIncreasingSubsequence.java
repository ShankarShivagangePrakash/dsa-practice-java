package dynamic_programming.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Problem:
    https://takeuforward.org/data-structure/printing-longest-increasing-subsequence-dp-42/
    https://www.geeksforgeeks.org/problems/printing-longest-increasing-subsequence/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=printing-longest-increasing-subsequence
 */
public class PrintLongestIncreasingSubsequence {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n^2)
            two loops

        Space Complexity: O(n)
            DP array
     */
    public int longestIncreasingSubsequenceTabulationAlgorithm(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];

        /* at each index, even if we don't consider any other element
         if we consider only element at that index, we can form
         subsequence of length 1
         so we fill dp array with value 1*/
        Arrays.fill(dp, 1);

        int longestIncreasingSubSequenceLength = 1;
        for(int i = 0; i < n; i++){
            /* if the current element is greater than the
             element at the previous index
             and if we are going to form a new subsequence with that previous subsequence
             it is nothing but adding current element to that already existing subsequence
             so it will be 1 + length of subsequence at previous index
             we check is this greater than subsequence which is possible from current index
             if so, we update
             while each updation, we keep track of maximum length subsequence using
             `longestIncreasingSubSequenceLength`*/
            for(int prevIndex = 0; prevIndex < i; prevIndex++){
                if(arr[i] > arr[prevIndex] && 1 + dp[prevIndex] > dp[i]){
                    dp[i] = 1 + dp[prevIndex];
                    longestIncreasingSubSequenceLength = Math.max(longestIncreasingSubSequenceLength, dp[i]);
                }
            }
        }
        return longestIncreasingSubSequenceLength;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n^2) + O(n) + O(n) + O(n * log n)
            two loops - O(n^2)
            find longest subsequence length and index - O(n)
            reverse longest subsequence - O(n * log n)

        Space Complexity: O(n) +O(n) + O(n)
            DP array and hash array and longest increasing subsequence
     */
    public ArrayList<Integer> printlongestIncreasingSubsequence(int n, int arr[]) {
        int[] dp = new int[n];
        /* while forming dp[i]
         we use existing subsequence we add current element to it
         and we form larger subsequences
         but if we need to keep track of which is that previous subsequence
         from which subsequence we got that
         hash array will keep that information*/
        int[] hash = new int[n];

        /* at each index, even if we don't consider any other element
         if we consider only element at that index, we can form
         subsequence of length 1
         so we fill dp array with value 1*/
        Arrays.fill(dp, 1);

        /* we know, initially each dp[i] has that arr[i] themselves
         so the index from which subsequence was formed was i
         we fill this details*/
        for(int i = 0; i < n; i++)
            hash[i] = i;

        for(int i = 0; i < n; i++){
            /* if the current element is greater than the
             element at the previous index
             and if we are going to form a new subsequence with that previous subsequence
             it is nothing but adding current element to that already existing subsequence
             so it will be 1 + length of subsequence at previous index
             we check is this greater than subsequence which is possible from current index
             if so, we update
             while each updation, we keep track of maximum length subsequence using
             `longestIncreasingSubSequenceLength`*/
            for(int prevIndex = 0; prevIndex < i; prevIndex++){
                if(arr[i] > arr[prevIndex] && 1 + dp[prevIndex] > dp[i]){
                    dp[i] = 1 + dp[prevIndex];
                    /* we are using prevIndex subsequence to form current subsequence
                     so current subsequence should be linked to previous index*/
                    hash[i] = prevIndex;
                }
            }
        }

        // find the longest increasing subsequence and that index at which it occurs
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
        result.add(arr[currentIndex]);
        while(hash[currentIndex] != currentIndex){
            currentIndex = hash[currentIndex];
            result.add(arr[currentIndex]);

        }

        /* longest subsequence elements are found,
         but it is in reverse order
         so reverse it to get the correct order*/
        Collections.reverse(result);

        return result;
    }
}
