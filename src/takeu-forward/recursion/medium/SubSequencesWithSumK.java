package recursion.medium;

import java.util.List;
import java.util.ArrayList;

/*
Problem Statement:
    Print all sub sequences of a given array with sum k
 */
public class SubSequencesWithSumK {

    /*
    Approach:
        Almost same logic as `GetAllSubSequence.java`
        but to the recursive function
            pass the currentSum - intially 0
            target sum - k

        Inside recursive function,
            whenever you add any element to the temp - which is current sub sequnence, then add that value to `currentSum`
            whenever you delete any element from temp - delete that value from `currentSum`

            Inside base case:
                If we have reached base case means, we have generated a sub sequence
                Now if the sum of the sub sequence is equal to `k` then we have to add that to result list

        Time Complexity: O(2^n)
            Check notes of `GetAllSubSequence.java`
        Space Complexity: O(n)
            Check notes of `GetAllSubSequence.java`
     */
    public static List<List<Integer>> subSequencesWithSumK(int[] arr, int k){
        List<List<Integer>> result = new ArrayList<>();
        getSubSequencesWithSumK(0, new ArrayList<>(), arr, result,0,  k);
        return result;
    }

    public static void getSubSequencesWithSumK(int ind, List<Integer> temp, int[] arr, List<List<Integer>> result, int currentSum, int targetSum){

        if(ind >= arr.length){
            if(currentSum == targetSum)
                result.add(new ArrayList<>(temp));
            return;
        }
        temp.add(arr[ind]);
        currentSum += arr[ind];
        getSubSequencesWithSumK(ind+1, temp, arr, result, currentSum, targetSum);

        temp.remove(temp.size()-1);
        currentSum -= arr[ind];
        getSubSequencesWithSumK(ind+1, temp, arr, result, currentSum, targetSum);
    }

    public static void main(String[] args){
        int[] arr1 = {1, 2, 1};
        int k = 2;
        List<List<Integer>> result = subSequencesWithSumK(arr1, k);

        System.out.println("sub sequences with sum " + k + " are : " + result);
    }
}
