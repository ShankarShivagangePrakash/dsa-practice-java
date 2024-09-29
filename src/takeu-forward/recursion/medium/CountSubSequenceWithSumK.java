package recursion.medium;

/*
Problem:
    https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=perfect-sum-problem

Problem Statement:
    count sub sequences with sum `k`
 */
public class CountSubSequenceWithSumK {

    /*
    Approach:
        Explained in detail in notes, check

        Time Complexity: O(2^n)
            Check notes of `GetAllSubSequence.java`
        Space Complexity: O(n)
            Check notes of `GetAllSubSequence.java`
     */
    public static int countSubSequencesWithSumK(int[] arr, int k){
        return countSubSequencesWithSumKRecursive(0, arr, 0, k);
    }

    /* Note: Since we are just checking whether a sub sequence with a partcular sum `k` exists or not
          we don't want to actually return that sub sequence we don't need to maintain the sub sequence
          It's enough, if we just maintain the sum of that sub sequence.
          So you don't see a parameter List<Integer> temp.
     */
    public static int countSubSequencesWithSumKRecursive(int ind, int[] arr, int currentSum, int targetSum){

        if(ind >= arr.length){
            if(currentSum == targetSum)
                return 1;
            return 0;
        }

        currentSum += arr[ind];
        int l = countSubSequencesWithSumKRecursive(ind+1, arr, currentSum, targetSum);

        currentSum -= arr[ind];
        int r = countSubSequencesWithSumKRecursive(ind+1, arr, currentSum, targetSum);

        return l + r;
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 1};
        int k = 2;

        System.out.printf("Number of sub sequences with sum %d are: %d\n", k, countSubSequencesWithSumK(arr, k));
    }
}
