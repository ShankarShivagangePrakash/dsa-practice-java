package dynamic_programming.subsequences;

import java.util.AbstractMap.SimpleEntry;

import java.util.HashMap;

/*
Problem:
    https://takeuforward.org/data-structure/partition-set-into-2-subsets-with-min-absolute-sum-diff-dp-16/
    https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/description/
 */
public class PartitionSetIntoTwoSubsetsWithMinAbsoluteSumDiff {

    /*
    Approach:
        This program can be easily solved using Tabulation method
        First read `PartitionEqualSubsetSum.java`
        in that program, we are creating a 2D DP array
        what it represents?
            to be precise, what last row represents
            last row will have k+1 columns
            ranging from 0 to k (target)

            each cell value can be either true or false
            if true means, we can form a subset of sum `i` with n elements of the array
            `i` ranges from 0 to k

        Now, we have this DP array
            if we have a subset of sum x
            and the total sum of the sub array is `totalSum`
                then remaining subset sum will be `totalSum` - x

            we can take the absolute difference of this and store in a variable

        we repeat this operation for each sum subset possible, by checking every cell of the last row of DP

        Time Complexity: O(N*totSum) +O(N) +O(N)
            There are two nested loops that account for O(N*totSum),
             at starting we are running a for loop to calculate totSum,
             and at last a for loop to traverse the last row.

        Space Complexity: O(N*totSum)
            We are using an external array of size ‘N * totSum’. Stack Space is eliminated.
     */
    /* Note: this logic works for only positive inputs
     For handling negative numbers refer to
     https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/solutions/2786039/%20DP%20%7C%7C%20Handling-case-when-sum-is-negative/*/
    public int minimumDifferenceTabulation(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        for(int i = 0; i < n; i++)
            totalSum += nums[i];

        int target = totalSum;
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

                dp[ind][k] = take || notTake;
            }
        }

        int minDiff = Integer.MAX_VALUE;
        for(int k = 0; k < target + 1; k++){
            int s1 = 0;
            if(dp[n-1][k])
                s1 = k;

            int s2 = totalSum - s1;

            minDiff = Math.min(minDiff, Math.abs(s1 - s2));
        }

        return minDiff;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*totSum) +O(N) +O(N)
            There are two nested loops that account for O(N*totSum),
            at starting we are running a for loop to calculate totSum
            and at last a for loop to traverse the last row.

        Space Complexity: O(totalSum)
            We are using an external array of size ‘totSum+1’ to store only one row.
     */
    public int minimumDifferenceSpaceOptimization(int[] nums){
        int n = nums.length;
        int totalSum = 0;

        for(int i = 0; i < n; i++)
            totalSum += nums[i];

        int target = totalSum;
        boolean[] prev = new boolean[target+1];

        prev[0] = true;


        for(int ind = 1; ind < n; ind++){
            boolean[] cur = new boolean[target+1];
            for(int k = 1; k < target + 1; k++){

                boolean notTake = prev[k];

                boolean take = false;
                if(nums[ind] <= k)
                    take = prev[k - nums[ind]];

                cur[k] = take || notTake;
            }
            prev = cur;
        }

        int minDiff = Integer.MAX_VALUE;
        for(int k = 0; k < target + 1; k++){
            int s1 = 0;
            if(prev[k])
                s1 = k;

            int s2 = totalSum - s1;

            minDiff = Math.min(minDiff, Math.abs(s1 - s2));
        }

        return minDiff;
    }

    /*
    Approach:
        instead of using 2D DP array
        we are using hashamp with key as (ind, k)
            k refers to sum/target

        so that if k value is negative, we can still handle it

        Time Complexity: O(N*totSum) +O(N) +O(N)
            There are two nested loops that account for O(N*totSum),
             at starting we are running a for loop to calculate totSum,
             and at last a for loop to traverse the last row.

        Space Complexity: O(N*totSum)
            We are using an external array of size ‘N * totSum’. Stack Space is eliminated.
     */
    public int minimumDifferenceTabulationToHandleNegativeInputs(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        for(int i = 0; i < n; i++)
            totalSum += nums[i];

        int target = totalSum;

        int maxPossibleSum  = 0;
        int minPossibleSum = 0;

        if(totalSum < 0){
            maxPossibleSum = -totalSum;
            minPossibleSum = totalSum;
        }
        else{
            maxPossibleSum = totalSum;
            minPossibleSum = -totalSum;
        }
        /* if the sum is negative or array has negative element
         then the range of target will be (-ve to +ve)
         array cannot handle it, so we are creating hashmap
         key being (ind, sum) and value being boolean*/
        HashMap<SimpleEntry<Integer, Integer>, Boolean> dp = new HashMap();

        for(int i = 0; i < n; i++)
            dp.put(new SimpleEntry<>(i, 0), true);

        if(nums[0] <= maxPossibleSum)
            dp.put(new SimpleEntry<>(0, nums[0]), true);

        for(int ind = 1; ind < n; ind++){
            for(int k = minPossibleSum; k < maxPossibleSum + 1; k++){

                boolean notTake = dp.containsKey(new SimpleEntry(ind-1, k)) ? dp.get(new SimpleEntry(ind-1, k)) : false;

                boolean take = false;
                if(nums[ind] <= k) {
                    take = dp.containsKey(new SimpleEntry(ind-1, k - nums[ind]))
                            ? dp.get(new SimpleEntry(ind-1, k - nums[ind])) : false;
                }

                dp.put(new SimpleEntry(ind, k), take || notTake);
            }
        }

        int minDiff = Integer.MAX_VALUE;
        for(int k = minPossibleSum; k <= maxPossibleSum; k++){
            int s1 = 0;
            if(dp.get(new SimpleEntry(n-1, k)))
                s1 = k;

            int s2 = totalSum - s1;

            minDiff = Math.min(minDiff, Math.abs(s1 - s2));
        }

        return minDiff;
    }
}
