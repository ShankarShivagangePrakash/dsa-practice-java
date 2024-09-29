package recursion.medium;

import java.util.List;
import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/combination-sum-1/
    https://leetcode.com/problems/combination-sum/description/

Problem statement:

    We will get an array of elements and a target value
    We can reuse an array element multiple times and form a combination whose sum is equal to given target
    Generate all possible combinations and return it.
 */
public class CombinationSum1 {

    /*
    Approach:
        Explained neatly in notes, check

        Time Complexity: O(2^n * n)
        Space Complexity: O(n)
            Same logic as of `GetAllSubSequence.java`
     */
    public static List<List<Integer>> getCombinationsWithSum(int[] arr, int target){

        List<List<Integer>> result = new ArrayList<>();
        getCombinationsWithSumRecursive(0, new ArrayList<>(), target, arr, result);
        return result;
    }

    public static void getCombinationsWithSumRecursive(int ind, List<Integer> temp, int target, int[] arr, List<List<Integer>> result){

        if(ind >= arr.length){
            // if target is zero, we have formed a combination whose sum is target. So add it to the result list.
            if(target == 0)
                result.add(new ArrayList<>(temp));
            return;
        }

        // pick an element,
        if(arr[ind] <= target){

            // since you are adding the element to the sub sequence list, for not pick case, you have to explicitly remove it from the list
            temp.add(arr[ind]);

             /*since we have considered current element to combination, target will reduce by its value, that is the third parameter.
             we are not doing target -= arr[ind] because in that case for not pick case we have to add this value back to target, so instead we just pass the effective value to
            recursive call instead of actually updating target.*/
            getCombinationsWithSumRecursive(ind, temp, target - arr[ind], arr, result);

            // for not pick scenario, remove added element from the list, preperation step for line 59
            temp.remove(temp.size()-1);
        }

        // not pick element
        getCombinationsWithSumRecursive(ind+1, temp, target, arr, result);
    }

    public static void main(String[] args){
        int[] arr = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = getCombinationsWithSum(arr, target);

        System.out.println("Combinations with sum " + target + " are: " + result);
    }
}
