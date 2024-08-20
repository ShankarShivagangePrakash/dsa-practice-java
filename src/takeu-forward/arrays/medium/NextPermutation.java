package arrays.medium;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
Problem:
    https://takeuforward.org/data-structure/next_permutation-find-next-lexicographically-greater-permutation/
    https://leetcode.com/problems/next-permutation/description/
 */
public class NextPermutation {

    /*
    Approach:
        In the brute force approach, we have to generate all possible sorted permutations.
        Then we do linear search on that list to find the input permutation
        If we find that permutation from the list and if there is a next index after that - then that array at the next index will be the next permutation
        If there is no array at the next index, then the permutation at the index 0 is the next permutation

        Time Complexity: Minimum  O(n! * n)
            we need to generate n! permutations and each permutation is of length n
            so to generate all those permutations it will take minimum n! * n time
            it is computationally very expensive, even for small values of n ex: n = 15
            time complexity will be greater than 10^12

        So it is not to implement this.
        Note: If we want to generate all permutations we have to use recursion.
     */
    public static void nextGreaterPermutationBruteForce(int[] nums){
    }

    /*
    Approach:
        The steps are the following:

        Find the break-point, i: Break-point means the first index i from the back of the given array where arr[i] becomes smaller than arr[i+1].
        For example, if the given array is {2,1,5,4,3,0,0}, the break-point will be the index 1(0-based indexing).
        Here from the back of the array, index 1 is the first index where arr[1] i.e. 1 is smaller than arr[i+1] i.e. 5.

        If such a break-point does not exist i.e. if the array is sorted in decreasing order,
        the given permutation is the last one in the sorted order of all possible permutations.
        So, the next permutation must be the first i.e. the permutation in increasing order.
        So, in this case, we will reverse the whole array and will return it as our answer.

        If a break-point exists:
        Find the smallest number i.e. > arr[i] and in the right half of index i(i.e. from index i+1 to n-1) and swap it with arr[i].
        Reverse the entire right half(i.e. from index i+1 to n-1) of index i. And finally, return the array.

        Time Complexity: O(3n), where n = size of the given array
            Finding the break-point, finding the next greater element, and reversal at the end takes O(n) for each,
            This sums up to 3*O(n) which is approximately O(3n).

        Space Complexity: O(1)
        Since no extra storage is required. Thus, its space complexity is O(1).
     */
    public static void nextGreaterPermutationOptimal(int[] nums){
        int n = nums.length;
        int index = -1;

        // To find the break-point, using a loop we will traverse the array backward and store the index i where arr[i] is less than the value at index (i+1) i.e. arr[i+1].
        // starting index at n-2 because last element of the array will be n-1
        // if we start at index n-1, then (i+1) will become n, we cannot compare with arr[n] - will get Array out of bounds exception.
        for(int i = n-2; i >= 0; i--){
            if( nums[i] < nums[i+1]){
                index = i;
                break;
            }
        }

        // If breakpoint doesn't exist means it is the last permutation in the list
        // we have to return the first permutation, just reverse the entire array - that will be the first permutation.
        if(index == -1){
            reverse(nums, 0, n-1);
            return;
        }

        // If a break-point exists:
        // Find the smallest number i.e. > arr[i] and in the right half of index i(i.e. from index i+1 to n-1) and swap it with arr[i].
        for(int i = n-1; i > index; i--){
            if(nums[i] > nums[index]){
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
                break;
            }
        }

        // Reverse the entire right half(i.e. from index i+1 to n-1).
        reverse(nums, index + 1, n-1);
    }

    public static void reverse(int[] arr, int start, int end){
        while(start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String args[]) {
        int[] nums = {2, 1, 5, 4, 3, 0, 0};

        nextGreaterPermutationOptimal(nums);
        System.out.print("The next permutation is: [");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println("]");

    }
}
