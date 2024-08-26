package arrays.hard;

/*
Problem:
    https://takeuforward.org/data-structure/maximum-product-subarray-in-an-array/
    https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubArray {

    /*
    Approach:
        Following are the steps for the approach:

        Run a loop to find the start of the sub arrays.
        Run another nested loop
        Multiply each element and store the maximum value of all the subarray.

        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    public static int maximumProductSubArrayBruteForce(int[] arr){
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++){
            int prod = 1;
            for(int j = i; j < arr.length; j++){
                prod *= arr[j];
                max = Math.max(max, prod);
            }
        }
        return max;
    }

    /*
    Approach:
        We will first declare 2 variables i.e. ‘prefix’(stores the product of the prefix subarray - starts from o to n-1)
        and ‘suffix’(stores the product of the suffix subarray starts from n-1 to 0).
        They both will be initialized with 1(as we want to store the product).

        Now, we will use a loop(say i) that will run from 0 to n-1.
        We have to check 2 cases to handle the presence of 0:
        If prefix = 0:
            This means the previous element was 0. So, we will consider the current element as a part of the new subarray. So, we will set ‘prefix’ to 1.
        If suffix = 0:
         This means the previous element was 0 in the suffix. So, we will consider the current element as a part of the new suffix subarray. So, we will set ‘suffix’ to 1.

        Next, we will multiply the elements from the starting index with ‘prefix’ and the elements from the end with ‘suffix’.
        To incorporate both cases inside a single loop, we will do the following:
        We will multiply arr[i] with ‘pre’ i.e. prefix *= arr[i].
        We will multiply arr[n-i-1] with ‘suffix’ i.e. suffix *= arr[n-i-1].

        After each iteration, we will consider the maximum among the previous 'answer', ‘prefix’ and ‘suffix’ i.e. max(previous_answer, prefix, suffix).
        Finally, we will return the maximum product.

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static int maximumProductSubArrayOptimal(int[] arr){
        int max = Integer.MIN_VALUE;
        int prefix = 1, suffix =1;

        for(int i = 0; i < arr.length; i++){
            if(prefix == 0)
                prefix = 1;
            if(suffix == 0)
                suffix = 1;

            prefix *= arr[i];
            suffix *= arr[arr.length-1-i];

            max = Math.max(max, Math.max(prefix, suffix));
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,-3,0,-4,-5};
        int answer = maximumProductSubArrayBruteForce(arr);
        int answer2 = maximumProductSubArrayOptimal(arr);

        System.out.printf("Maximum product sub array using brute force approach: %d\n", answer);
        System.out.printf("Maximum product sub array using optimal approach: %d\n", answer2);

    }
}
