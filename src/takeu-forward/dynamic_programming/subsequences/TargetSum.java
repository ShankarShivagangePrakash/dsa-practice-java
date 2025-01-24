package dynamic_programming.subsequences;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;

/*
Problem:
    https://takeuforward.org/data-structure/target-sum-dp-21/
    https://leetcode.com/problems/target-sum/description/
 */
public class TargetSum {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2^n)
            At every index, we have two possibilites
            add plus or negative sign

        Space Complexity: O(n)
            stack space
     */
    public int targetSumRecursiveMain(int[] nums, int target) {
        int n = nums.length;

        return  targetSumRecursive(n-1, target, nums);
    }

    public int targetSumRecursive(int ind, int target, int[] nums){

        if (ind < 0) {
            return target == 0 ? 1 : 0;
        }

        /* Recursive case: consider both '+' and '-' for the current number
         when you include +/- general form is
         target - (number included)
         when positive is included
              target - ( +ve number)
              target - number
         when negative is included
              target - (-ve number)
              target + number*/
        int includePlus = targetSumRecursive(ind - 1, target - nums[ind], nums);
        int includeMinus = targetSumRecursive(ind - 1, target + nums[ind], nums);

        return includePlus + includeMinus;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*T)
            There are N*T states therefore at max ‘N*T’ new problems will be solved.

        Space Complexity: O(N*T) + O(N)
            We are using a recursion stack space(O(N)) and a 2D array ( O(N*T)).
     */
    public int targetSumMemoizationMain(int[] nums, int target) {
        int n = nums.length;
        HashMap<Pair<Integer, Integer>, Integer> map = new HashMap();

        return targetSumMemoization(n-1, target, nums, map);
    }

    public int targetSumMemoization(int ind, int target, int[] nums, HashMap<Pair<Integer, Integer>, Integer> map){

        if (ind < 0) {
            return target == 0 ? 1 : 0;
        }

        if(map.containsKey(new Pair<>(ind, target)))
            return map.get(new Pair<>(ind, target));

        /* Recursive case: consider both '+' and '-' for the current number
         when you include +/- general form is
         target - (number included)
         when positive is included
              target - ( +ve number)
              target - number
         when negative is included
              target - (-ve number)
              target + number*/
        int includePlus = targetSumMemoization(ind - 1, target - nums[ind], nums, map);
        int includeMinus = targetSumMemoization(ind - 1, target + nums[ind], nums, map);

        int val = includePlus + includeMinus;
        map.put(new Pair<>(ind, target), val);
        return val;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*T)
            There are two nested loops

        Space Complexity: O(N*T)
            We are using an external array of size ‘N*T’. Stack Space is eliminated.
     */
    public int targetSumTabulation(int[] nums, int target) {
        int n = nums.length;

        /* we know that, target can be negative or positive
         i.e is the reason, we chose hashmap for memoization
         but we can't use hashmap for memoization
         can we use 2D array?
         yes, we can, but how to represent negative targets columns in 2D array
         we will check the summation of array
         then maximum possible values from the array will be in [-sumation, sumation]
         so we will create 2 * sumation
         index 0 to sumation will serve for negative target values
         index sumation + 1 to 2 * target will serve for positive values*/

        int sum = Arrays.stream(nums).sum();

        if(Math.abs(target) > sum)
            return 0;

        int[][] dp = new int[n][2 * sum + 1];

        int offSet = sum;

        dp[0][nums[0] + offSet] = 1;
        dp[0][-nums[0] + offSet] = 1;

        for(int ind = 1; ind < n; ind++){
            for(int t = -sum; t <= sum; t++){

                // Include '+' case
                int includePlus = (t - nums[ind] + offSet >= 0 && t - nums[ind] + offSet < 2 * sum + 1)
                        ? dp[ind - 1][t - nums[ind] + offSet]
                        : 0;

                // Include '-' case
                int includeMinus = (t + nums[ind] + offSet >= 0 && t + nums[ind] + offSet < 2 * sum + 1)
                        ? dp[ind - 1][t + nums[ind] + offSet]
                        : 0;

                // Sum up both cases
                dp[ind][t + offSet] = includePlus + includeMinus;
            }
        }
        return dp[n-1][target+offSet];
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*T)
            There are two nested loops.

        Space Complexity: O(T)
            We are using two external arrays of size ‘T+1’.
     */
    public int targetSumSpaceOptimization(int[] nums, int target) {
        int n = nums.length;

        /* we know that, target can be negative or positive
         i.e is the reason, we chose hashmap for memoization
         but we can't use hashmap for memoization
         can we use 2D array?
         yes, we can, but how to represent negative targets columns in 2D array
         we will check the summation of array
         then maximum possible values from the array will be in [-sumation, sumation]
         so we will create 2 * sumation
         index 0 to sumation will serve for negative target values
         index sumation + 1 to 2 * target will serve for positive values*/

        int sum = Arrays.stream(nums).sum();

        if(Math.abs(target) > sum)
            return 0;

        int[] prev = new int[2 * sum + 1];

        int offSet = sum;

        prev[nums[0] + offSet] = 1;
        prev[-nums[0] + offSet] = 1;

        for(int ind = 1; ind < n; ind++){
            int[] cur = new int[2 * sum + 1];
            for(int t = -sum; t <= sum; t++){

                // Include '+' case
                int includePlus = (t - nums[ind] + offSet >= 0 && t - nums[ind] + offSet < 2 * sum + 1)
                        ? prev[t - nums[ind] + offSet]
                        : 0;

                // Include '-' case
                int includeMinus = (t + nums[ind] + offSet >= 0 && t + nums[ind] + offSet < 2 * sum + 1)
                        ? prev[t + nums[ind] + offSet]
                        : 0;

                // Sum up both cases
                cur[t + offSet] = includePlus + includeMinus;
            }
            prev = cur;
        }
        return prev[target+offSet];
    }


}
