package dynamic_programming.subsequences;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/partition-equal-subset-sum-dp-15/
    https://leetcode.com/problems/partition-equal-subset-sum/description/
 */
public class PartitionEqualSubsetSum {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2^n) + O(n)
             At every array element, we make 2 choices either take or not take
            recursion will stop, when either target becomes zero or when we visit all array elements
            so O(2^n)
            O(n) for finding the sum of the array elements

        Space Complexity: O(n)
            stack space
     */
    public boolean canPartitionRecursiveMain(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        for(int i = 0; i < n; i++)
            totalSum += nums[i];

        // if the total sum of all the elements in the array is odd, then we cannot form 2 partitions with same sum
        if(totalSum % 2 != 0)
            return false;

        // not odd sum, just check can we create a subset with sum = totalSum/2
        // if so, we can form 2 subsets of equal sum
        return canPartitionRecursive(n-1, totalSum/2, nums);
    }

    public boolean canPartitionRecursive(int ind, int target, int[] arr){
        if(target == 0)
            return true;
        if(ind == 0)
            return arr[ind] == target;

        boolean notTake = canPartitionRecursive(ind-1, target, arr);

        boolean take = false;
        if(arr[ind] <= target)
            take = canPartitionRecursive(ind-1, target-arr[ind], arr);

        return take || notTake;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*K) + O(n)
            There are N*K states therefore at max ‘N*K’ new problems will be solved.

        Space Complexity: O(N*K) + O(N)
            We are using a recursion stack space(O(N)) and a 2D array ( O(N*K)).
     */
    public boolean canPartitionMemoizationMain(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        for(int i = 0; i < n; i++)
            totalSum += nums[i];

        // if the total sum of all the elements in the array is odd, then we cannot form 2 partitions with same sum
        if(totalSum % 2 != 0)
            return false;

        int target = totalSum / 2;

        int[][] dp = new int[n][target+1];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        return canPartitionMemoization(n-1, target, nums, dp);
    }

    public boolean canPartitionMemoization(int ind, int target, int[] arr, int[][] dp){
        if(target == 0)
            return true;
        if(ind == 0)
            return arr[ind] == target;

        if(dp[ind][target] != -1)
            return dp[ind][target] == 1;

        boolean notTake = canPartitionMemoization(ind-1, target, arr, dp);

        boolean take = false;
        if(arr[ind] <= target)
            take = canPartitionMemoization(ind-1, target-arr[ind], arr, dp);

        dp[ind][target] =  take || notTake ? 1 : 0;
        return take || notTake;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*K) +O(N)
            There are two nested loops that account for O(N*K) and at starting we are running a for loop to calculate totSum.

        Space Complexity: O(N*K)
            We are using an external array of size ‘N*K’. Stack Space is eliminated.
     */
    public boolean canPartitionTabulation(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        for(int i = 0; i < n; i++)
            totalSum += nums[i];

        // if the total sum of all the elements in the array is odd, then we cannot form 2 partitions with same sum
        if(totalSum % 2 != 0)
            return false;

        int target = totalSum / 2;

        boolean[][] dp = new boolean[n][target+1];

        for(int i = 0; i < n; i++)
            dp[i][0] = true;

        if(nums[0] <= target)
            dp[0][nums[0]] = true;

        for(int ind = 1; ind < n; ind++){
            for(int k = 1; k < target + 1; k++){
                boolean notTake = dp[ind-1][k];

                boolean take = false;
                if(nums[ind] <= k)
                    take = dp[ind-1][k - nums[ind]];

                dp[ind][k] =  take || notTake;
            }
        }

        return dp[n-1][target];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*K) +O(N)
            There are two nested loops that account for O(N*K) and at starting we are running a for loop to calculate totSum.

        Space Complexity: O(K)
            We are using an external array of size ‘K+1’ to store only one row.
     */
    public boolean canPartitionSpaceOptimization(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        for(int i = 0; i < n; i++)
            totalSum += nums[i];

        // if the total sum of all the elements in the array is odd, then we cannot form 2 partitions with same sum
        if(totalSum % 2 != 0)
            return false;

        int target = totalSum / 2;

        boolean[] prev = new boolean[target+1];

        prev[0] = true;

        if(nums[0] <= target)
            prev[nums[0]] = true;

        for(int ind = 1; ind < n; ind++){
            boolean[] cur = new boolean[target+1];
            cur[0] = true;

            for(int k = 1; k < target + 1; k++){
                boolean notTake = prev[k];

                boolean take = false;
                if(nums[ind] <= k)
                    take = prev[k - nums[ind]];

                cur[k] =  take || notTake;
            }
            prev = cur;
        }

        return prev[target];
    }

}
