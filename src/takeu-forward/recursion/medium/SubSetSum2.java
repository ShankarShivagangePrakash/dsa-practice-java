package recursion.medium;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/subset-ii-print-all-the-unique-subsets/
    https://leetcode.com/problems/subsets-ii/description/
 */
public class SubSetSum2 {

    /*
    Approach:
        Check notes

        Time Complexity: O(2^n * k) + O(n log n )
            O( n log n ) - is to sort the input array initally
        Space Complexity: O(k * x)
            x combinations each of size k
     */
    public static List<List<Integer>> subSetSum2(int[] arr){

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        subSetSum2Recursive(0, new ArrayList<Integer>(), arr, result);
        return result;
    }

    public static void subSetSum2Recursive(int ind, List<Integer> temp, int[] arr, List<List<Integer>> result){

        // since we need to generate all subsets, no condition just keep adding subsets generated.
        result.add(new ArrayList<>(temp));

        // but we should not add duplicate sub sets,
        // so avoid picking same element for same position more than once at the same level, refer Combinations2 problem
        for(int i = ind; i < arr.length; i++){
            if(i > ind && arr[i] == arr[i-1])
                continue;

            temp.add(arr[i]);
            subSetSum2Recursive(i+1, temp, arr, result);
            temp.remove(temp.size()-1);
        }
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 2};

        List<List<Integer>> result = subSetSum2(arr);

        System.out.println("Sub set sum 2 result: " + result);
    }
}
