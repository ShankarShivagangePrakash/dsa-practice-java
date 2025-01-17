package dynamic_programming.one_dimensional_dp;

import java.util.ArrayList;
import java.util.List;

/*
Problem:
    https://takeuforward.org/data-structure/dynamic-programming-house-robber-dp-6/
    https://leetcode.com/problems/house-robber-ii/description/
 */
public class HouseRobber2 {

    /*
    Approach:
        This problem is almost same as `MaximumSumOfSubSequenceWithoutConsideringAdjacentEleemnts.java`
        except that we cannot choose arr[n-1] and arr[0] in the same subsequence

        For that we will form two arrays
            one containing all elements of the original array except arr[n-1]
            another array containing all elements except arr[0]

        Ti
     */
    public int rob(int[] arr) {
        // Edge case: if the array has only one element, only subsequence is possible return it
        if(arr.length == 1)
            return arr[0];

        // contains element from index 1 to n-1 (excluding first element)
        List<Integer> temp1 = new ArrayList<>();
        // contains element from index 0 to n-2 (excluding last element)
        List<Integer> temp2 = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            if(i != 0)
                temp1.add(arr[i]);
            if(i != arr.length - 1)
                temp2.add(arr[i]);
        }

        int ans1 = subSequenceWithMaxSumWithoutAdjacentElementsSpaceOptimization(temp1);
        int ans2 = subSequenceWithMaxSumWithoutAdjacentElementsSpaceOptimization(temp2);

        return Math.max(ans1, ans2);
    }

    public int subSequenceWithMaxSumWithoutAdjacentElementsSpaceOptimization(List<Integer> list){
        int size = list.size();

        int n = size -1;

        int prev = list.get(0);
        int prev2 = 0;

        for(int i = 1; i <= n; i++){
            int take = list.get(i) + prev2;
            int notTake = 0 + prev;

            int cur = Math.max(take, notTake);
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }
}
