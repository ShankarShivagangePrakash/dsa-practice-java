package dynamic_programming.partition_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/partition-array-for-maximum-sum-front-partition-dp-54/
    https://leetcode.com/problems/partition-array-for-maximum-sum/description/
 */
public class PartitionArrayForMaximumSum {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential

        Space Complexity: O(n)
            stack space
     */
    public int maxSumAfterPartitioningRecursiveMain(int[] arr, int k) {
        int n = arr.length;

        return maxSumAfterPartitioningRecursive(0, n, arr, k);
    }

    public int maxSumAfterPartitioningRecursive(int i, int n, int[] arr, int k){

        if(i == n)
            return 0;

        int partitionLen = 0;
        int partitionMax = Integer.MIN_VALUE;

        int answer = 0;
        for(int j = i; j < Math.min(n, i+k); j++){
            partitionLen++;
            partitionMax = Math.max(partitionMax, arr[j]);

            int partitionSum = (partitionLen * partitionMax)
                            + maxSumAfterPartitioningRecursive(j+1, n, arr, k);

            answer = Math.max(answer, partitionSum);
        }
        return answer;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*k)
            There are a total of N states and for each state, we are running a loop from 0 to k.

        Space Complexity: O(N) + Auxiliary stack space O(N)
            First O(N) for the dp array we are using.
     */
    public int maxSumAfterPartitioningMemoizationMain(int[] arr, int k) {
        int n = arr.length;

        // Only one changing parameter
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        return maxSumAfterPartitioningMemoization(0, n, arr, k, dp);
    }

    public int maxSumAfterPartitioningMemoization(int i, int n, int[] arr, int k, int[] dp){

        if(i == n)
            return 0;

        if(dp[i] != -1)
            return dp[i];

        int partitionLen = 0;
        int partitionMax = Integer.MIN_VALUE;

        int answer = 0;
        for(int j = i; j < Math.min(n, i+k); j++){
            partitionLen++;
            partitionMax = Math.max(partitionMax, arr[j]);

            int partitionSum = (partitionLen * partitionMax)
                    + maxSumAfterPartitioningMemoization(j+1, n, arr, k, dp);

            answer = Math.max(answer, partitionSum);
        }
        return dp[i] = answer;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*k)
            There are a total of N states and for each state, we are running a loop from 0 to k.

        Space Complexity: O(N)
            First O(N) for the dp array we are using.
     */
    public int maxSumAfterPartitioningTabulation(int[] arr, int k) {
        int n = arr.length;

        // Only one changing parameter
        int[] dp = new int[n+1];

        //Base case:
        dp[n] = 0;

        for(int i = n-1; i >= 0; i--){

            int partitionLen = 0;
            int partitionMax = Integer.MIN_VALUE;

            int answer = 0;
            for(int j = i; j < Math.min(n, i+k); j++){
                partitionLen++;
                partitionMax = Math.max(partitionMax, arr[j]);

                int partitionSum = (partitionLen * partitionMax) + dp[j+1];

                answer = Math.max(answer, partitionSum);
            }
            dp[i] = answer;
        }

        return dp[0];
    }
}
