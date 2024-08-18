package arrays.medium;

/*
Problem:
    https://takeuforward.org/data-structure/kadanes-algorithm-maximum-subarray-sum-in-an-array/
    https://leetcode.com/problems/maximum-subarray/description/
 */
public class KadaneMaximumSubArray {

    /*
    Approach:
        Check `LongestSubArrayWithSumK.java` brute force approach.
         If we carefully observe, we can notice that to get the sum of the current subarray we just need to add the current element(i.e. arr[j])
         to the sum of the previous subarray i.e. arr[i….j-1].

        Assume previous subarray = arr[i……j-1]
        current subarray = arr[i…..j]
        Sum of arr[i….j] = (sum of arr[i….j-1]) + arr[j]

        This is how we can remove the third loop and while moving j pointer, we can calculate the sum.

        The steps are as follows:

        First, we will run a loop(say i) that will select every possible starting index of the subarray.
        The possible starting indices can vary from index 0 to index n-1(n = array size).
        Inside the loop, we will run another loop(say j) that will signify the ending index as well as the current element of the subarray.
        For every subarray starting from index i, the possible ending index can vary from index i to n-1(n = size of the array).
        Inside loop j, we will add the current element to the sum of the previous subarray i.e. sum = sum + arr[j].
        Among all the sums the maximum one will be the answer.

        Time Complexity: O(n^2),
        Space Complexity: O(1)
     */
    public static int maxSubarraySumBruteForce(int[] arr){
        int maxSubarrayValue = 0;
        for(int i = 0; i < arr.length; i++){
            int sum = 0;
            for(int j = i; j < arr.length; j++){
                sum += arr[j];
                maxSubarrayValue = Math.max(maxSubarrayValue, sum);
            }
        }
        return maxSubarrayValue;
    }

    /*
    Approach:
        The intuition of the algorithm is not to consider the subarray as a part of the answer if its sum is less than 0.
        A subarray with a sum less than 0 will always reduce our answer and so this type of subarray cannot be a part of the subarray with maximum sum.

        Here, we will iterate the given array with a single loop and while iterating we will add the elements in a sum variable.
        Now, if at any point the sum becomes less than 0, we will set the sum as 0 as we are not going to consider any subarray with a negative sum.
        Among all the sums calculated, we will consider the maximum one.
        Thus, we can solve this problem with a single loop.

        The steps are as follows:

        We will run a loop(say i) to iterate the given array.
        Now, while iterating we will add the elements to the sum variable and consider the maximum one.
        If at any point the sum becomes negative we will set the sum to 0 as we are not going to consider it as a part of our answer.

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static int maxSubarraySumOptimal(int[] arr){
        int maxSubarrayValue = 0;
        int sum = 0;

        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            if(sum > maxSubarrayValue){
                maxSubarrayValue = sum;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        return maxSubarrayValue;
    }

    /*
    Question:
        Along with returning maximum of sub array
        print the sub array.
    Approach:
        we know that when sum goes below zero, we reset it to zero.
        means we are ignoring that particular index and we start considering new sub array from next location
        which means when sum becomes zero, we can consider next index as starting index of the sub array
        same when maxSubArrayValue is overwritten, it means we can update the end index.

        Finally, iterate through all elements between those indices and print.
     */
    public static int maxSubArraySumOptimalFollowUpQuestion(int[] arr){
        int maxSubarrayValue = 0;
        int sum = 0;

        // to store start and end index of the sub array
        int start = 0;
        int ansStart = -1, ansEnd = -1;

        for(int i = 0; i < arr.length; i++){

            if(sum == 0)
                start = i;

            sum += arr[i];
            if(sum > maxSubarrayValue){
                maxSubarrayValue = sum;

                ansStart = start;
                ansEnd = i;
            }
            if(sum < 0){
                sum = 0;
            }
        }

        System.out.print("Actual Sub array is: [ ");
        for(int i = ansStart; i <= ansEnd; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.print("]\n");
        return maxSubarrayValue;
    }

    public static void main(String args[]) {
        int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4};
        long maxSum = maxSubarraySumBruteForce(arr);
        long maxSum2 = maxSubarraySumOptimal(arr);
        System.out.println("The maximum subarray sum using brute force approach: " + maxSum);
        System.out.println("The maximum subarray sum using optimal approach: " + maxSum2);

        int[] arr2 = {-1, -4, -5};
        long maxSum3 = maxSubarraySumBruteForce(arr2);
        long maxSum4 = maxSubarraySumOptimal(arr2);
        System.out.println("The maximum subarray sum for negative array using brute force approach: " + maxSum3);
        System.out.println("The maximum subarray sum for negative array using optimal approach: " + maxSum4);

        int[] arr3 = new int[0];
        long maxSum5 = maxSubarraySumBruteForce(arr3);
        long maxSum6 = maxSubarraySumOptimal(arr3);
        System.out.println("The maximum subarray sum for empty array using brute force approach: " + maxSum5);
        System.out.println("The maximum subarray sum for empty array using optimal approach: " + maxSum6);

        // print maximum sub array as well. Follow up Question
        maxSubArraySumOptimalFollowUpQuestion(arr);

    }
}
