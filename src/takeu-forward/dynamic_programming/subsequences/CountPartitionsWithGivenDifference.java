package dynamic_programming.subsequences;

import sun.security.util.ArrayUtil;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/count-partitions-with-given-difference-dp-18/
    https://www.geeksforgeeks.org/problems/partitions-with-given-difference/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=partitions-with-given-difference
 */
public class CountPartitionsWithGivenDifference {

    static int mod =(int)(Math.pow(10,9)+7);

    /*
    Approach:
        we will explore all paths, we will continue till we reach the leaf
        even though before it we have got target

        Time Complexity: O(2^n)
            O(2^n) for recursion

        Space Complexity: O(n)
            stack space
     */
    int countPartitionsRecursiveMain(int[] arr, int d) {
        int n = arr.length;
        int totalSum = 0;

        for(int i = 0; i < n; i++)
            totalSum += arr[i];


        // if total sum - d is less than 0 then we cannot form subsequences whose diff is d
        if(totalSum - d < 0)
            return 0;
        // if the total sum is odd, then we cannot form two subsequences
        /*if(totalSum % 2 == 1)
            return 0;*/

        int s2 = (totalSum - d)/2;

        return countPartitionsRecursive(n-1, s2, arr);
    }

    public int countPartitionsRecursive(int ind, int target, int[] arr){

        if(ind == 0){
            if(target == 0 && arr[ind] == 0)
                return 2;
            else if(target == 0 || target ==  arr[ind])
                return 1;
            else
                return 0;
        }


        int notTake = countPartitionsRecursive(ind-1, target, arr);

        int take = 0;
        if(arr[ind] <= target)
            take = countPartitionsRecursive(ind-1, target - arr[ind], arr);

        return take + notTake;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*K)
            There are N*K states therefore at max ‘N*K’ new problems will be solved.

        Space Complexity: O(N*K) + O(N)
            We are using a recursion stack space(O(N)) and a 2D array ( O(N*K)).
     */
    int countPartitionsMemoizationMain(int[] arr, int d) {
        int n = arr.length;
        int totalSum = 0;

        for(int i = 0; i < n; i++)
            totalSum += arr[i];


        // if total sum - d is less than 0 then we cannot form subsequences whose diff is d
        if(totalSum - d < 0)
            return 0;
        // if the total sum is odd, then we cannot form two subsequences
        if(totalSum % 2 == 1)
            return 0;

        int s2 = (totalSum - d)/2;

        int[][] dp = new int[n][s2+1];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return countPartitionsMemoization(n-1, s2, arr, dp);
    }

    public int countPartitionsMemoization(int ind, int target, int[] nums, int[][] dp){
        if(ind == 0){
            if(target == 0 && nums[ind] == 0)
                return 2;
            else if(target == 0 || target ==  nums[ind])
                return 1;
            else
                return 0;
        }

        if(dp[ind][target] != -1)
            return dp[ind][target];


        int notTake = countPartitionsMemoization(ind-1, target, nums, dp);

        int take = 0;
        if(nums[ind] <= target)
            take = countPartitionsMemoization(ind-1, target - nums[ind], nums, dp);

        return dp[ind][target] = take + notTake;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*K)
            There are two nested loops

        Space Complexity: O(N*K)
            We are using an external array of size ‘N*K’. Stack Space is eliminated.
     */
    public int countPartitions(int d,int[] arr){
        int n = arr.length;
        int totSum = 0;
        for(int i=0; i<n;i++){
            totSum += arr[i];
        }

        //Checking for edge cases
        if(totSum-d <0 || (totSum-d)%2==1 ) return 0;

        int s2 = (totSum - d) / 2;
        int dp[][] = new int[n][s2+1];

        if(arr[0] == 0) dp[0][0] =2;  // 2 cases -pick and not pick
        else dp[0][0] = 1;  // 1 case - not pick

        if(arr[0]!=0 && arr[0]<=s2) dp[0][arr[0]] = 1;  // 1 case -pick

        for(int ind = 1; ind<n; ind++){
            for(int target= 0; target<=s2; target++){

                int notTaken = dp[ind-1][target];

                int taken = 0;
                if(arr[ind]<=target)
                    taken = dp[ind-1][target-arr[ind]];

                dp[ind][target]= (notTaken + taken)%mod;
            }
        }
        return dp[n-1][s2];
    }

    /*
     Approach:
        Explained in notes, check

        Time Complexity: O(N*K)
            There are three nested loops

        Space Complexity: O(K)
            We are using an external array of size ‘K+1’ to store only one row.
     */
    public int countPartitions(int n, int d,int[] arr){
        int totSum = 0;
        for(int i=0; i<n;i++){
            totSum += arr[i];
        }

        //Checking for edge cases
        if(totSum-d <0 || (totSum-d)%2 ==1) return 0;

        int s2 = (totSum-d)/2;

        int prev[] = new int[s2+1];

        if(arr[0] == 0) prev[0] =2;  // 2 cases -pick and not pick
        else prev[0] = 1;  // 1 case - not pick

        if(arr[0]!=0 && arr[0]<=s2) prev[arr[0]] = 1;  // 1 case -pick

        for(int ind = 1; ind<n; ind++){
            int cur[]=new int[s2+1];
            for(int target= 0; target<=s2; target++){
                int notTaken = prev[target];

                int taken = 0;
                if(arr[ind]<=target)
                    taken = prev[target-arr[ind]];

                cur[target]= (notTaken + taken)%mod;
            }
            prev = cur;
        }
        return prev[s2];
    }
}
