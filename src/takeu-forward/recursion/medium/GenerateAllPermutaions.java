package recursion.medium;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
Problem:
    https://leetcode.com/problems/permutations/description/
 */
public class GenerateAllPermutaions {

    /*
    Approach:
        Neatly explained in notes check

        Time Complexity: O(n! * n)
            Reason: There will be n! factorial permutations,
            to generate n! factorial permutaions we need n! time
            each factorial is of length n, we need to generate permutation of length n by adding elements to temp data structure
            so it takes O(n)

            So total time complexity is O(n! * n)

        Space Complexity: O(n) + O(n) + O(n)
            First O(n) is for temp data structure which will store current permutation
            Second O(n) is for boolean array
            Third O(n) is for recursion stack space, maximum n recursion calls will be waiting for execution = decursion depth.
     */
    public static List<List<Integer>> generateAllPermutationsBruteForce(int[] arr){
        List<List<Integer>> result = new ArrayList<>();
        boolean[] freq = new boolean[arr.length];
        generateAllPermutationsBruteForceRecursive(new ArrayList<>(), arr, freq, result);
        return result;
    }

    public static void generateAllPermutationsBruteForceRecursive(List<Integer> temp, int[] arr, boolean[] freq, List<List<Integer>> result){

        if(temp.size() == arr.length){
            result.add(new ArrayList<>(temp));
            return;
        }

        /* in subsets problem, we were considering all elements to the right of current index
         but in permutation we can consider elements from both left and right side of the current position
         but make sure, they have been not considered already for current permutation.
         so we loop from first index to last index
         and we compare !freq[i] - meaning if it is not picked*/
        for(int i = 0; i < arr.length; i++){
            if( !freq[i]){
                // since the element is not picked, add it and generate permutation
                temp.add(arr[i]);
                freq[i] = true;

                generateAllPermutationsBruteForceRecursive(temp, arr, freq, result);

                /* after generating permutation, different permutation can be generated
                 by replacing different value for current position,
                 to allow it, remove current element from temp data structure and make freq[i] = false
                 in simple terms, roll back*/
                temp.remove(temp.size()-1);
                freq[i] = false;
            }
        }
    }

    /*
    Approach:
        Instead of using additional temp data structure to store current permutation
        and map to store which elements are picked
        we can avoid that using swap method

        We start with index 0, we swap element at that position with every element to its right including itself
        when the pointer(`ind`) has reached end of the array (n)
            it means that we have generated a permutation add it to the result list
        when recursion call is returned
            rollback the swap you have done, this is to ensure that it goes to previous state
            and from that state, we can again perform swap and generate new valid permutation

       Time Complexity: O(n! * n)
            There are N! permutations each of length n
       Space Complexity: O(n)
            to store the result we will have a list of size O(n!)
            but this is to return the result - so don't consider

            except that recursion stack space will be O(n)
            this is the maximum depth of the tree
     */
    public static List<List<Integer>> generateAllPermutaionsOptimal(int[] arr){
        List<List<Integer>> result = new ArrayList<>();
        generateAllPermutationsOptimalRecursive(0, arr, result);
        return result;
    }

    public static void generateAllPermutationsOptimalRecursive(int ind, int[] arr, List<List<Integer>> result){

        // base case, if we have move to end of the array means, we have generated a permutation add it to the list
        if(ind == arr.length){
            // convert array to list and then add to the result list
            result.add(Arrays.stream(arr)
                    .boxed()
                    .collect(Collectors.toList())
            );
            return;
        }

        // we have to swap current element with every elements to its right, so loop from ind to n-1
        for(int i = ind; i < arr.length; i++){
            swap(i, ind, arr);
            // after swap we move to next index - note it is ind+1 not (i+1)
            generateAllPermutationsOptimalRecursive(ind+1, arr, result);
            // after permutation is generated, we have to rollback the swaps to go back to previous state
            // from that state we perform new swaps to generate valid permutations.
            swap(i, ind, arr);
        }
    }

    public static void swap(int index1, int index2, int[] arr){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 3};
        List<List<Integer>> resultBruteForce = generateAllPermutationsBruteForce(arr);
        List<List<Integer>> resultOptimal = generateAllPermutaionsOptimal(arr);

        System.out.println("All permutations using brute force approach: " + resultBruteForce);
        System.out.println("All permutations using optimal approach: " + resultOptimal);
    }
}
