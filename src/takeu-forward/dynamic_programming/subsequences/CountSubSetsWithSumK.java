package dynamic_programming.subsequences;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/count-subsets-with-sum-k-dp-17/
    https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=perfect-sum-problem
 */
public class CountSubSetsWithSumK {

    /*
    Approach:
        Explained in notes,
        But one addition input can have zeroes as well
        even those things should be considered while forming subsets

            for example: consider the input is [0, 0, 1] target = 1
                since our recursion starts from index (n-1) we will start with index 2
                so, at index 2 we can either take that element or not take
                    assume we take, then
                        recursion becomes (0, 0)
                            first 0 index we moved to
                            second 0 represents the sum now

                        since sum == target
                            we return 1
                        but, there are other combinations which yields 1
                        {1} {0, 1} {0, 1} {0, 0, 1}

                So, the summary is, if zeroes are at the begining of the array
                    they are not considered to form subsequence

                we can use power set concept to solve this
                    use power set, we can identify how many subsequences can be formed from `k` integers
                    it is 2^k
                we will sort the arrray in ascending order
                    so all 0's will come at the begining and they will not be considered for subsequence formation

                now, we will invoke recursion, which will yield number of subsequences possible without 0's

                now we will multiply * 2^numberofzeroes in array to it
                and return

        Time Complexity: O(2^n) + O(n * log n) + O(n)
            O(n * logn n) for sorting the array
            O(2^n) for recursion
            O(n) for counting zeroes

        Space Complexity: O(n)
            stack space
     */
    public int perfectSumRecursiveMainWithPowerSet(int[] nums, int target) {
        int n = nums.length;

        Arrays.sort(nums);

        int zeoresCount = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] == 0)
                zeoresCount++;
        }

        return (int)Math.pow(2, zeoresCount) * perfectSumRecursive(n-1, target, nums);
    }

    public int perfectSumRecursive(int ind, int target, int[] nums){

        if(target == 0)
            return 1;
        if(ind == 0)
            return (nums[0] == target) ? 1 : 0;

        int notTake = perfectSumRecursive(ind-1, target, nums);

        int take = 0;
        if(nums[ind] <= target)
            take = perfectSumRecursive(ind-1, target - nums[ind], nums);

        return take + notTake;
    }

    public int perfectSumRecursiveMain(int[] nums, int target) {
        int n = nums.length;

        return perfectSumRecursiveHandlesZero(n-1, target, nums);
    }

    /*
    Approach:
        we will explore all paths, we will continue till we reach the leaf
        even though before it we have got target

        Time Complexity: O(2^n)
            O(2^n) for recursion

        Space Complexity: O(n)
            stack space
     */
    public int perfectSumRecursiveHandlesZero(int ind, int target, int[] nums){

        if(ind == 0){
            if(target == 0 && nums[ind] == 0)
                return 2;
            else if(target == 0 || target ==  nums[ind])
                return 1;
            else
                return 0;
        }


        int notTake = perfectSumRecursiveHandlesZero(ind-1, target, nums);

        int take = 0;
        if(nums[ind] <= target)
            take = perfectSumRecursiveHandlesZero(ind-1, target - nums[ind], nums);

        return take + notTake;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * target)
            we have to explore all possible states

        Space complexity: O(n * target) + O(n)
            DP array and stack space
     */
    public int perfectSumMemoizationMain(int[] nums, int target) {
        int n = nums.length;

        int[][] dp = new int[n][target+1];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return perfectSumMemoization(n-1, target, nums, dp);
    }

    public int perfectSumMemoization(int ind, int target, int[] nums, int[][] dp){
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


        int notTake = perfectSumMemoization(ind-1, target, nums, dp);

        int take = 0;
        if(nums[ind] <= target)
            take = perfectSumMemoization(ind-1, target - nums[ind], nums, dp);

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
    public int perfectSumTabulation(int[] nums, int target) {
        int n = nums.length;

        int[][] dp = new int[n][target+1];

        // Base case: we can always form a sub array of sum 0 without taking any element
        for(int i = 0; i < n; i++){
            dp[i][0] = 1;
        }

        // If the first element is less than or equal to target, set the sum as possible.
        if(nums[0] <= target)
            dp[0][nums[0]] = 1;

        if(nums[0] == 0)
            dp[0][0] = 2;

        for(int ind = 1; ind < n; ind++){
            for(int t = 0; t <= target; t++){
                int notTake = dp[ind-1][t];

                int take = 0;
                if(nums[ind] <= t)
                    take = dp[ind-1][t- nums[ind]];

                dp[ind][t] = take + notTake;
            }
        }
        return dp[n-1][target];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*K)
            There are two nested loops

        Space Complexity: O(K)
            We are using an external array of size ‘K+1’ to store only one row.
     */
    public int perfectSumSpaceOptimization(int[] nums, int target) {
        int n = nums.length;

        int[] prev = new int[target+1];

        // Base case: we can always form a sub array of sum 0 without taking any element
        prev[0] = 1;

        // If the first element is less than or equal to target, set the sum as possible.
        if(nums[0] <= target)
            prev[nums[0]] = 1;

        if(nums[0] == 0)
            prev[0] = 2;

        for(int ind = 1; ind < n; ind++){
            int[] cur = new int[target+1];
            cur[0] = 1;
            if(nums[0] == 0) cur[0] = 2;

            for(int t = 0; t <= target; t++){
                int notTake = prev[t];

                int take = 0;
                if(nums[ind] <= t)
                    take = prev[t- nums[ind]];

                cur[t] = take + notTake;
            }
            prev = cur;
        }
        return prev[target];
    }
}
