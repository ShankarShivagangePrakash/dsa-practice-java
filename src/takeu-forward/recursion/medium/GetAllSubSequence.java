package recursion.medium;

import java.util.List;
import java.util.ArrayList;

/*
Problem:
    https://leetcode.com/problems/subsets/description/
 */
public class GetAllSubSequence {

    /*
    Approach:
        It has been explained in detail in notes, check
        Time Complexity: O(2^n)
            Check notes
        Space Complexity: O(n)
            Check notes
     */
    public List<List<Integer>> subsets(int[] arr){
        List<List<Integer>> result = new ArrayList<>();
        // we are passing the result list,so that the generated sub sequences can be added to this list
        getAllSubSequences(0, new ArrayList<Integer>(), arr, result);
        return result;
    }

    public void getAllSubSequences(int ind, List<Integer> temp, int[] arr, List<List<Integer>> result){
        if(ind >= arr.length){
            /* we are explictly creating new array list and adding it to the result
             this is because, if we simply do result.add(temp) - then it will add temp reference to result list
             but when recursion call completes temp will be deleted and reference present in result will not be valid
             so i'm creating a new arraylist with temp data and adding to the result.*/
            result.add(new ArrayList<>(temp));
            return;
        }
        temp.add(arr[ind]);
        getAllSubSequences(ind+1, temp, arr, result);

        temp.remove(temp.size()-1);
        getAllSubSequences(ind+1, temp, arr, result);
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 3};
        GetAllSubSequence getAllSubSequence = new GetAllSubSequence();
        List<List<Integer>> result  =  getAllSubSequence.subsets(arr);
        System.out.println(result);
    }
}
