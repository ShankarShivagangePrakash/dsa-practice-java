package dynamic_programming.one_dimensional_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/maximum-sum-of-non-adjacent-elements-dp-5/
    https://leetcode.com/problems/house-robber/description/
 */
public class MaximumSumOfSubSequenceWithoutConsideringAdjacentEleemnts {

    /*
    Approach:
        Explained in the notes, check

        Time Complexity: O(2^n)
            at every node, we have two possibilities

        Space Complexity: O(n)
            stack space
     */
    public int maximumSumOfSubSequenceWithoutConsideringAdjacentElementsRecursiveMain(int[] arr){
        int n = arr.length;
        /* since we need to find maxium sum of sequence possible from index 0 to n-1 (last index of the array)
         we will send n-1 as parameter to method*/
        return maximumSumOfSubSequenceWithoutConsideringAdjacentElementsRecursive(n-1, arr);
    }

    public int maximumSumOfSubSequenceWithoutConsideringAdjacentElementsRecursive(int ind, int[] arr){
        /* if you have reached 0th index means, you haven't chosen 1st index
         since we cannot choose adjacent elements in our subsequence
         and this will be the last element in the subsequence (since we start forming subsequence from nth index to 0th)
         so just return the arr[0] as there won't be any other possible subsequences before this index*/
        if(ind == 0)
            return arr[ind];

        /* ind < 0 means, previously you were at index 1
         you picked that element, so you have to move 2 locations behind which becomes -1
         since this location is invalid, return -1*/
        if(ind < 0)
            return 0;

        // you chose current element, so you cannot pick adjacent element so ind -2
        int take = arr[ind] + maximumSumOfSubSequenceWithoutConsideringAdjacentElementsRecursive(ind-2, arr);

        // you didn't choose current element, so you can pick adjacent element so ind-1
        int notTake = 0 + maximumSumOfSubSequenceWithoutConsideringAdjacentElementsRecursive(ind-1, arr);

        return Math.max(take, notTake);
    }

    /*
    Approach:
        Explained in the notes, check

        Time Complexity: O(N)
            The overlapping subproblems will return the answer in constant time O(1).
            Therefore the total number of new subproblems we solve is ‘n’.
            Hence total time complexity is O(N).

        Space Complexity: O(N) + O(n)
            We are using a recursion stack space(O(N)) and an array (again O(N)).
            Therefore total space complexity will be O(N) + O(N) ≈ O(N)
     */
    public int maximumSumOfSubSequenceWithoutConsideringAdjacentElementsMemoizationMain(int[] arr){
        int size = arr.length;
        /* since we need to find maxium sum of sequence possible from index 0 to n-1 (last index of the array)
         we will send n-1 as parameter to method*/
        int n = size - 1;

        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return maximumSumOfSubSequenceWithoutConsideringAdjacentElementsMemoization(n, arr, dp);
    }

    public int maximumSumOfSubSequenceWithoutConsideringAdjacentElementsMemoization(int ind, int[] arr, int[] dp){
        /* if you have reached 0th index means, you haven't chosen 1st index
         since we cannot choose adjacent elements in our subsequence
         and this will be the last element in the subsequence (since we start forming subsequence from nth index to 0th)
         so just return the arr[0] as there won't be any other possible subsequences before this index*/
        if(ind == 0)
            return arr[ind];

        /* ind < 0 means, previously you were at index 1
         you picked that element, so you have to move 2 locations behind which becomes -1
         since this location is invalid, return -1*/
        if(ind < 0)
            return 0;

        if(dp[ind] != -1)
            return dp[ind];

        // you chose current element, so you cannot pick adjacent element so ind -2
        int take = arr[ind] + maximumSumOfSubSequenceWithoutConsideringAdjacentElementsMemoization(ind-2, arr, dp);

        // you didn't choose current element, so you can pick adjacent element so ind-1
        int notTake = 0 + maximumSumOfSubSequenceWithoutConsideringAdjacentElementsMemoization(ind-1, arr, dp);

        return dp[ind] = Math.max(take, notTake);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N)
            We are running a simple iterative loop

        Space Complexity: O(N)
            We are using an external array of size ‘n+1’.
     */
    public int maximumSumOfSubSequenceWithoutConsideringAdjacentElementsTabulation(int[] arr){
        int size = arr.length;
        /* since we need to find maxium sum of sequence possible from index 0 to n-1 (last index of the array)
         we will send n-1 as parameter to method*/
        int n = size - 1;

        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        // base case:
        dp[0] = arr[0];

        for(int i = 1; i <= n; i++){
            int take = arr[i] ;
            // if index is 1 and we move two steps back index will be -1 which will be invalid
            // so except that in all other cases consider 2nd previous element in sub sequence
            if(i > 1)
                take += + dp[i-2];

            int notTake = 0 + dp[i-1];

            dp[i] = Math.max(take, notTake);
        }
        return dp[n];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N)
            We are running a simple iterative loop

        Space Complexity: O(1)
            We are not using any extra space.
     */
    public int maximumSumOfSubSequenceWithoutConsideringAdjacentElementsSpaceOptimization(int[] arr){
        int size = arr.length;
        /* since we need to find maxium sum of sequence possible from index 0 to n-1 (last index of the array)
         we will send n-1 as parameter to method*/
        int n = size - 1;

        // base case:
        int prev = arr[0];
        int prev2 = 0;

        for(int i = 1; i <= n; i++){
            int take = arr[i] + prev2;
            int notTake = 0 + prev;

            int cur = Math.max(take, notTake);
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }
}
