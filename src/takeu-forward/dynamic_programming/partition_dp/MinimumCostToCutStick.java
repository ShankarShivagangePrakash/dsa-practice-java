package dynamic_programming.partition_dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Problem:
    https://takeuforward.org/data-structure/minimum-cost-to-cut-the-stick-dp-50/
    https://leetcode.com/problems/minimum-cost-to-cut-a-stick/description/
 */
public class MinimumCostToCutStick {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential

        Space Complexity: O(n)
            stack space
     */
    public int minCostRecursiveMain(int n, int[] cuts) {
        int c = cuts.length;

       ArrayList<Integer> cutsArrayList = new ArrayList<>();
       for(int ele : cuts)
           cutsArrayList.add(ele);

       cutsArrayList.add(0);
       cutsArrayList.add(n);

        Collections.sort(cutsArrayList);

        /* block range to be resolved is 1 to c
         because we have added 0 at the begining of the array list
         and after that we have c elements from cuts array which needs to be resolved
         so last index is c*/
        return minCostRecursive(1, c, cutsArrayList);
    }

    public int minCostRecursive(int i, int j, ArrayList<Integer> cutsArrayList){

        // Base case:
        if(i > j)
            return 0;

        int minCost = Integer.MAX_VALUE;
        for(int k = i; k <= j; k++){
            /*
            cutsArrayList.get(j+1) - cutsArrayList.get(i-1) - gives length of the current stick
            minCostRecursive(i, k-1, cutsArrayList) - left half of the stick at which partition has been made
            minCostRecursive(k+1, j, cutsArrayList) - right half of the stick at which partition has been made
             */
            int cost = cutsArrayList.get(j+1) - cutsArrayList.get(i-1)
                    + minCostRecursive(i, k-1, cutsArrayList)
                    + minCostRecursive(k+1, j, cutsArrayList);

            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    public int minCostMemoizationMain(int n, int[] cuts) {
        int c = cuts.length;

        ArrayList<Integer> cutsArrayList = new ArrayList<>();
        for(int ele : cuts)
            cutsArrayList.add(ele);

        cutsArrayList.add(0);
        cutsArrayList.add(n);

        Collections.sort(cutsArrayList);

        int[][] dp = new int[c+1][c+1];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        /* block range to be resolved is 1 to c
         because we have added 0 at the begining of the array list
         and after that we have c elements from cuts array which needs to be resolved
         so last index is c*/
        return minCostMemoization(1, c, cutsArrayList, dp);
    }

    /*
   Approach:
       Explained in notes, check

       Time Complexity: O(c * c * c)
           explore all possible states in DP array
           we are running an internal loop as well

       Space Complexity: O(c * c) + O(n)
           DP array and stack space
    */
    public int minCostMemoization(int i, int j, ArrayList<Integer> cutsArrayList, int[][] dp){

        // Base case:
        if(i > j)
            return 0;

        if(dp[i][j] != -1)
            return dp[i][j];

        int minCost = Integer.MAX_VALUE;
        for(int k = i; k <= j; k++){
            /*
            cutsArrayList.get(j+1) - cutsArrayList.get(i-1) - gives length of the current stick
            minCostRecursive(i, k-1, cutsArrayList) - left half of the stick at which partition has been made
            minCostRecursive(k+1, j, cutsArrayList) - right half of the stick at which partition has been made
             */
            int cost = cutsArrayList.get(j+1) - cutsArrayList.get(i-1)
                    + minCostMemoization(i, k-1, cutsArrayList, dp)
                    + minCostMemoization(k+1, j, cutsArrayList, dp);

            minCost = Math.min(minCost, cost);
        }

        return dp[i][j] = minCost;
    }

    /*
   Approach:
       Explained in notes, check

       Time Complexity: O(c * c * c)
           explore all possible states in DP array
           we are running an internal loop as well

       Space Complexity: O(c * c)
           DP array
    */
    public int minCostTabulation(int n, int[] cuts) {
        int c = cuts.length;

        ArrayList<Integer> cutsArrayList = new ArrayList<>();
        for(int ele : cuts)
            cutsArrayList.add(ele);

        cutsArrayList.add(0);
        cutsArrayList.add(n);

        Collections.sort(cutsArrayList);

        /* possible number of states are c+1
         but, we are doing k+1
         so if n is equal to c+1
         for that scenario, c+2*/
        int[][] dp = new int[c+2][c+2];

        // DP range, opposite order
        for(int i = c; i >= 1; i--){
            for(int j = 1; j <= c; j++){
                if(i > j)
                    continue;

                int minCost = Integer.MAX_VALUE;
                for(int k = i; k <= j; k++){
                    int cost = cutsArrayList.get(j+1) - cutsArrayList.get(i-1)
                            + dp[i][k-1]
                            + dp[k+1][j];

                    minCost = Math.min(minCost, cost);
                }

                dp[i][j] = minCost;
            }
        }

        return dp[1][c];
    }
}
