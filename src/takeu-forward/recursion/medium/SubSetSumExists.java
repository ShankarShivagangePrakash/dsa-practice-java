package recursion.medium;

/*
Problem:
    https://www.naukri.com/code360/problems/subset-sum_630213?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
Problem statement:
    We have to check, whether a sub sequence with specified sum exists or not.
 */
public class SubSetSumExists {

    /*
    Approach:
        Approach has been explained in notes, check

        Time Complexity: O(2^n)
            Check notes of `GetAllSubSequence.java`
        Space Complexity: O(n)
            Check notes of `GetAllSubSequence.java`
     */
    public static boolean subSetSumExists(int[] arr, int k){

        return subSetSumExistsRecursive(0, arr, 0, k);
    }

    /*
    Note: Since we are just checking whether a sub sequence with a partcular sum `k` exists or not
          we don't want to actually return that sub sequence we don't need to maintain the sub sequence
          It's enough, if we just maintain the sum of that sub sequence.
          So you don't see a parameter List<Integer> temp.
     */
    public static boolean subSetSumExistsRecursive(int ind, int[] arr, int currentSum, int targetSum){

        if(ind >= arr.length){
            if(currentSum == targetSum)
                return true;
            return false;
        }

        // take an element
        currentSum += arr[ind];
        if(subSetSumExistsRecursive(ind+1, arr, currentSum, targetSum) == true)
            return true;

        // not take element.
        currentSum -= arr[ind];
        if(subSetSumExistsRecursive(ind+1, arr, currentSum, targetSum) == true)
            return true;

        return false;
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 1};
        int k = 2;

        System.out.printf("Sub sequence with sum %d exists? %b\n", k, subSetSumExists(arr, k));
    }
}
