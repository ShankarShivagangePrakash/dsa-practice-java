package arrays.medium;

import java.util.Arrays;
import java.util.HashSet;

/*
Problem:
    https://takeuforward.org/data-structure/longest-consecutive-sequence-in-an-array/
    https://leetcode.com/problems/longest-consecutive-sequence/editorial/
 */
public class LongestConsecutiveSequence {

    /*
    Approach:
        Brute force approach
        we create a variable to store the longestSubSequence

        we iterate through each element of the array.
        we keep a temporary variable count - initialize it to 1, since at least minimum sequence length will be 1.
        For array element, arr[i] -
            we declare x = arr[i]
            if we can find x + 1 (1 value greater) anywhere in the array - then increment count and increment x
            we keep doing till x+1 is available in the array, means consecutive sequence is found.

            if linear search of x +1 is not found
                then we do set longestSequence to max(longestSequence, count)

        finally return longestSequence

        Time Complexity: O(N2), N = size of the given array.
            Reason: We are using nested loops each running for approximately N times.

        Space Complexity: O(1), as we are not using any extra space to solve this problem.
     */
    public static int longestCommonSequenceBruteForce(int[] arr){
        int longestSequence = 1;
        for(int i = 0; i < arr.length; i++){
            int x = arr[i];
            int cnt = 1;
            while(linearSearch(arr, x+1) == true){
                cnt++;
                x++;
            }
            longestSequence = Math.max(longestSequence, cnt);
        }
        return longestSequence;
    }

    public static boolean linearSearch(int[] arr, int x){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == x){
                return true;
            }
        }
        return false;
    }

    /*
    Approach:
        We sort the array first
        We will consider 3 variables,
        ‘lastSmaller’ →(to store the last included element of the current sequence),
        ‘cnt’ → (to store the length of the current sequence),
        ‘longest’ → (to store the maximum length).
        Initialize ‘lastSmaller’ with ‘INT_MIN’, ‘cnt’ with 0, and ‘longest’ with 1(as the minimum length of the sequence is 1).
        The steps are as follows:

        First, we will sort the entire array.
        To begin, we will utilize a loop(say i) to iterate through each element one by one.
        For every element, we will check if this can be a part of the current sequence like the following:
        If arr[i]-1 == lastSmaller: The last element in our sequence is labeled as 'lastSmaller' and the current element 'arr[i]' is exactly 'lastSmaller'+1.
        It indicates that 'arr[i]' is the next consecutive element.
        To incorporate it into the sequence, we update 'lastSmaller' with the value of 'arr[i]' and increment the length of the current sequence, denoted as 'cnt', by 1.

        If arr[i] == lastSmaller: If the current element, arr[i], matches the last element of the sequence (represented by 'lastSmaller'),
        we can skip it since we have already included it before.

        Otherwise, if lastSmaller != arr[i]: On satisfying this condition, we can conclude that the current element, arr[i] > lastSmaller+1.
        It indicates that arr[i] cannot be a part of the current sequence.
        So, we will start a new sequence from arr[i] by updating ‘lastSmaller’ with the value of arr[i]. And we will set the length of the current sequence(cnt) to 1.
        Every time, inside the loop, we will compare ‘cnt’ and ‘longest’ and update ‘longest’ with the maximum value. longest = max(longest, cnt)
        Finally, once the iteration is complete, we will have the answer stored in the variable ‘longest’.

        Note: Here, we are distorting the given array by sorting it.

        Time Complexity: O(NlogN) + O(N), N = size of the given array.
        Reason: O(NlogN) for sorting the array. To find the longest sequence, we are using a loop that results in O(N).

        Space Complexity: O(1), as we are not using any extra space to solve this problem.
     */
    public static int longestCommonSequenceBetterSolution(int[] arr){
        if(arr.length == 0)
            return 0;
        Arrays.sort(arr);
        int longestSequence = 1;
        int lastSmaller = Integer.MIN_VALUE;
        int count = 0;
        for(int i = 0; i < arr.length; i++){
           if(arr[i] -1 == lastSmaller){
               count++;
               lastSmaller = arr[i];
           } else if(arr[i] != lastSmaller){
               count = 1;
               lastSmaller = arr[i];
           }
            longestSequence = Math.max(longestSequence, count);
        }
        return longestSequence;
    }

    /*
    Approach:
        We will adopt a similar approach to the brute-force method but with optimizations in the search process.
        Instead of searching sequences for every array element as in the brute-force approach,
        we will focus solely on finding sequences only for those numbers that can be the starting numbers of the sequences.
        This targeted approach narrows down our search and improves efficiency.

        We will do this with the help of the Set data structure.

        How to identify if a number can be the starting number for a sequence:

        First, we will put all the array elements into the set data structure.

        If a number, num, is a starting number, ideally, num-1 should not exist. So, for every element, x, in the set, we will check if x-1 exists inside the set.
        If x-1 exists: This means x cannot be a starting number, and we will move on to the next element in the set.
        If x-1 does not exist: This means x is a starting number of a sequence. So, for number, x, we will start finding the consecutive elements.

        How to search for consecutive elements for a number, x:

        Instead of using linear search, we will use the set data structure itself to search for the elements x+1, x+2, x+3, and so on.
        Thus, using this method we can narrow down the search and optimize the approach.

        Algorithm:
        We will declare 2 variables,

        ‘cnt’ → (to store the length of the current sequence),
        ‘longestSequence’ → (to store the maximum length).
        First, we will put all the array elements into the set data structure.
        For every element, x, that can be a starting number(i.e. x-1 does not exist in the set) we will do the following:
        We will set the length of the current sequence(cnt) to 1.
        Then, again using the set, we will search for the consecutive elements such as x+1, x+2, and so on,
        and find the maximum possible length of the current sequence. This length will be stored in the variable ‘cnt’.
        After that, we will compare ‘cnt’ and ‘longest’ and update the variable ‘longest’ with the maximum value (i.e. longest = max(longest, cnt)).
        Finally, we will have the answer i.e. ‘longest’.

        Time Complexity: O(N) + O(2*N) = O(3*N), where N = size of the array.
        Reason: O(N) for putting all the elements into the set data structure.
        After that for every starting element, we are finding the consecutive elements.
        Though we are using nested loops, the set will be traversed at most twice in the worst case.
        So, the time complexity is O(2*N) instead of O(N2).

        Space Complexity: O(N), as we are using the set data structure to solve this problem.

        Note: The time complexity is computed under the assumption that we are using hashSet, and it is taking O(1) for the set operations.

        If we consider the worst case the set operations will take O(N) in that case and the total time complexity will be approximately O(N2^) for inserting all elements into set.
        But which is very rare to occur.
     */
    public static int longestCommonSequenceOptimal(int[] arr){
        if(arr.length == 0)
            return 0;
        int longestSequence = 1;
        int count;
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < arr.length; i++){
            set.add(arr[i]);
        }
        for(int i : set){
            if(!set.contains(i -1)) {
                int x = i;
                count = 1;
                while (set.contains(x + 1) == true){
                    count++;
                    x++;
                }
                longestSequence = Math.max(longestSequence, count);
            }
        }

        return longestSequence;
    }

    public static void main(String[] args){
        int[] arr = {100, 200, 1, 3, 2, 4};
        int res = longestCommonSequenceBruteForce(arr);
        int res2 = longestCommonSequenceBetterSolution(arr);
        int res3 = longestCommonSequenceOptimal(arr);
        System.out.println("Longest Common Sequence using Brute force approach: " + res);
        System.out.println("Longest Common Sequence using better approach: " + res2);
        System.out.println("Longest Common Sequence using optimal approach: " + res3);

    }
}
