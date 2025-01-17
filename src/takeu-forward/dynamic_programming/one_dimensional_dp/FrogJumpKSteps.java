package dynamic_programming.one_dimensional_dp;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/dynamic-programming-frog-jump-with-k-distances-dp-4/
 */
public class FrogJumpKSteps {

    /*
   Approach:
       We have to find the cost to reach last index of the heights array starting from 0th index

       Time Complexity: O(k^n)
           At each level, we have k possibilities

       Space Complexity: O(n)
           stack space
    */
    int minCostRecursiveMain(int[] height, int k) {
        return minCostRecursive(height.length - 1, height, k);
    }

    int minCostRecursive(int n, int[] height, int k) {
        if(n == 0)
            return 0;

        /* Try out all possible range of steps from 1 to k
         among them which step yields minimum value to reach 0 from current index
         consider that*/
        int minSteps = Integer.MAX_VALUE;
        for(int i = 1; i <= k; i++){
            if(n-i >= 0){
                int currentStepEffort = minCostRecursive(n-i, height, k) + Math.abs(height[n] - height[n-i]);
                minSteps = Math.min(minSteps, currentStepEffort);
            }
        }

        return minSteps;
    }

    /*
    Approach:
        We have to find the cost to reach last index of the heights array starting from 0th index

       Time Complexity: O(N * k)
            The overlapping subproblems will return the answer in constant time O(1).
            Therefore the total number of new subproblems we solve is ‘n’.
            Hence total time complexity is O(N).
            To solve each subproblem, we have to try all k possible steps
            So, it will become O(n * k)

        Space Complexity: O(N) + O(N)
            Reason: We are using a recursion stack space(O(N)) and an array (again O(N)).
            Therefore total space complexity will be O(N) + O(N) ≈ O(N)
     */
    int minCostMemoizationMain(int[] height, int k) {
        int n = height.length - 1;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return minCostRecursive(height.length - 1, height, k);
    }

    int minCostMemoization(int n, int[] height, int[] dp, int k) {
        if(n == 0)
            return 0;

        if(dp[n] != -1)
            return dp[n];

        int minSteps = Integer.MAX_VALUE;
        for(int i = 1; i <= k; i++){
            // Ensure that we do not jump beyond the beginning of the array
            if(n-i >= 0){
                int currentStepEffort = minCostRecursive(n-i, height, k) + Math.abs(height[n] - height[n-i]);
                minSteps = Math.min(minSteps, currentStepEffort);
            }
        }

        return dp[n] = minSteps;
    }

    /*
    Approach:
        We know the base case
        Cost to reach 0th index starting from 0th index is 0
        then compute the cost to next indexes

        Time Complexity: O(N * k)
            We are running a simple iterative loop

        Space Complexity: O(N)
            We are using an external array of size ‘n+1’.
     */
    int minCostTabulation(int[] height, int k) {
        int n = height.length - 1;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        dp[0] = 0;

        for(int i = 1; i <= n; i++){

            int minSteps = Integer.MAX_VALUE;
            for(int j = 1; j <= k; j++){

                if(i-j >= 0){
                    int currentStepEffort = minCostRecursive(n-j, height, k) + Math.abs(height[n] - height[n-j]);
                    minSteps = Math.min(minSteps, currentStepEffort);
                }
            }

            dp[i] = minSteps;
        }

        return dp[n];
    }

    /*
    Approach:
        Not possible
        As, in each cell we can take a jump ranging fro 1 to k
        we have to create array to store these values
        and in each iteration, all values of this array has to updated

        it increases complexity and the space complexity cannot be decreased
        So, we won't try space optimization technique for this problem
     */
    int minCostSpaceOptimization(int[] height) {
        return 0;
    }
}
