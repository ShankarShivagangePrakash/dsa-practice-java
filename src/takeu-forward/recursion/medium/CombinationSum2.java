package recursion.medium;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/*
Problem:
    https://takeuforward.org/data-structure/combination-sum-ii-find-all-unique-combinations/
    https://leetcode.com/problems/combination-sum-ii/

Problem Statement:
    Give unique combinations with given sum k
    but each combination should be sorted by itself.
 */
public class CombinationSum2 {

    /*
    Approach:
        First we sort the input array
        Then we find all sub sequences with sum k
        but we store those sub sequences in a hash set, so duplicate sub sequences will not be stored.

        Time Complexity: O(2^n * k * x)
            There will be 2^n sub sequences we generate all those sub sequences
            each sub sequences are of length k, k can range from null to length of the array
            to generate those length k sub sequence we might need k units of time

            but we are storing them in a hash set, while returning we have to convert this set to list, this again takes x units of time
            because there are x sub sequences these have to be converted to list.

        Space Complexity: O(K * x)
            There are x sub sequences of k as average length.
     */
    public static List<List<Integer>> combinations2BruteForce(int[] arr, int k){

        Set<List<Integer>> tempResult = new HashSet<>();
        Arrays.sort(arr);
        findCombinationsBruteForceRecursive(0, new ArrayList<>(), k, arr, tempResult);
        return new ArrayList<>(tempResult);
    }

    public static void findCombinationsBruteForceRecursive(int ind, List<Integer> temp, int target, int[] arr, Set<List<Integer>> result){

        if(ind >= arr.length){
            if(target == 0)
                result.add(new ArrayList<>(temp));
            return;
        }

        // take an element, refer `CombinationSum1.java`
        if(arr[ind] <= target){
            temp.add(arr[ind]);
            findCombinationsBruteForceRecursive(ind+1, temp, target-arr[ind], arr, result);
            temp.remove(temp.size()-1);
        }

        // not take an element
        findCombinationsBruteForceRecursive(ind+1, temp, target, arr, result);
    }

    /*
    Approach:
        Neatly explained in notes, check

        Time Complexity: O(2^n * k)
        Space Complexity: O(k * x)
            x combinations each of size k
     */
    public static List<List<Integer>> combinatations2Optimal(int[] arr, int k){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        findCombinationsOptimalRecursive(0, new ArrayList<Integer>(), k, arr, result);
        return result;
    }

    public static void findCombinationsOptimalRecursive(int ind, List<Integer> temp, int target, int[] arr,List<List<Integer>> result){

        if(target == 0){
            result.add(new ArrayList<>(temp));
            return;
        }

        for(int i = ind; i < arr.length; i++){
            if(i > ind && arr[i] == arr[i-1])
                continue;

            if(arr[i] > target)
                break;

            temp.add(arr[i]);
            findCombinationsOptimalRecursive(i+1, temp, target-arr[i], arr, result);
            temp.remove(temp.size()-1);
        }
    }

    public static void main(String[] args){
        int[] arr = {10,1,2,7,6,1,5};
        int k = 8;

        List<List<Integer>> resultBruteForce = combinations2BruteForce(arr, k);
        List<List<Integer>> resultOptimal = combinatations2Optimal(arr, k);
        System.out.println("List of unique combinations each sorted with sum " + k + " using brute force approach are " + resultBruteForce);
        System.out.println("List of unique combinations each sorted with sum " + k + " using optimal approach are " + resultOptimal);
    }
}
