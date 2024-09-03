package arrays.binarysearch.onanswers.minmax;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/arrays/split-array-largest-sum/
    https://leetcode.com/problems/split-array-largest-sum/description/?source=submission-ac
 */
public class SplitArrayLargestSum {

    /*
    Approach:
        read notes

        Time Complexity: O(N * (sum(arr[])-max(arr[])+1))
        Space Complexity: O(1)
     */
    public static int largestSubarraySumMinimizedBruteForce(int[] arr, int k){
        if(k > arr.length)
            return -1;

        int low = Arrays.stream(arr).reduce((a, b) -> (a > b) ? a : b).orElseThrow(()->new RuntimeException("Error while finding maximum element"));
        int high = Arrays.stream(arr).reduce(0, (sum, i) -> sum + i);

        for(int i = low; i <= high; i++){

            // if for `i`, we can create k sub arrays
            // then `k` is the answer

            int noOfSubArrays = formSubArraysWithSum(arr, i);
            if(noOfSubArrays == k)
                return i;
        }
        return high;
    }

    /*
    Approach:
        same as BookAllocation.java
        just variables name changed

        Time Complexity: O(N * log(sum(arr[])-max(arr[])+1))
        Space Complexity: O(1)
     */
    public static int largestSubarraySumMinimizedOptimal(int[] arr, int k){
        if(k > arr.length)
            return -1;

        int low = Arrays.stream(arr).reduce((a, b) -> (a > b) ? a : b).orElseThrow(()-> new RuntimeException("Error while finding maximum in the array"));
        int high = Arrays.stream(arr).reduce(0, (sum, i) -> sum + i);

        int ans = -1;

        while(low <= high){
            int mid = (low + high)/2;

            int noOfSubArrays = formSubArraysWithSum(arr, mid);
            // we have formed specified number of sub arrays.
            // but our aim is to form sub arrays, such that maximum among the sub array is minimum possible
            // so if we consider the answer range, it will start from log(maximum in the original array) to summation of the original array
            // we have to find minimum answer, so we will check, can we still form k sub arrays and with minimum sum,
            // so we move to left.
            if(noOfSubArrays == k){
                // this can be our answer, but we still move left to find out is there any other better answer
                ans = mid;
                high = mid - 1;
            }
            // you are on the right side of the answer, move left
            else if(noOfSubArrays < k){
                high = mid - 1;
            }
            // you are on the left side of answer, move right.
            else{
                low = mid + 1;
            }
        }

        // instead of returning `ans`
        // print both high and low
        // whichever matches with the actual answer, return that
        // this is the trick.
        System.out.println("high: " + high);
        System.out.println("low: " + low);
        return low;
    }

    public static int formSubArraysWithSum(int[] arr, int subArraySumLimit){
        int subArrayCount = 1, currentSubArraySum = 0;

        for(int i = 0; i < arr.length; i++){
            if(currentSubArraySum + arr[i] <= subArraySumLimit){
                currentSubArraySum += arr[i];
            } else{
                subArrayCount++;
                currentSubArraySum = arr[i];
            }
        }
        return subArrayCount;
    }

    public static void main(String[] args) {
        int[] a = {10, 20, 30, 40};
        int k = 2;
        System.out.printf("The minimum of maximum possible sub array is: %d\n", largestSubarraySumMinimizedBruteForce(a, k));
    }
}
