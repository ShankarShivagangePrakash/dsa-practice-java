package recursion.medium;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
Problem:
    https://takeuforward.org/data-structure/subset-sum-sum-of-all-subsets/
    https://www.geeksforgeeks.org/problems/subset-sums2234/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=subset-sums
 */
public class SubSetSum1 {

    /*
    Approach:
        Check notes

        Time Complexity: O(2^n * k)
        Space Complexity: (k * x)
            Read sub sets problem in notes
     */
    public static List<Integer> subsetSum1(int[] arr){

        List<Integer> result = new ArrayList<>();
        calculateSubSetSums1Recursive(0, arr, 0, result);
        Collections.sort(result);
        return result;
    }

    public static void calculateSubSetSums1Recursive(int ind, int[] arr, int sum, List<Integer> result){

        if(ind >= arr.length){
            result.add(sum);
            return;
        }

        // take an element, so we are adding current element arr[ind] to sum
        calculateSubSetSums1Recursive(ind+1, arr, sum+arr[ind], result);

        // not take, so we are not adding current element to sum
        calculateSubSetSums1Recursive(ind+1, arr, sum, result);
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 1};

        List<Integer> result = subsetSum1(arr);
        System.out.println("Sub set sum is " + result);
    }
}
