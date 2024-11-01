package sliding_window_two_pointer;

/*
Problem:
    https://leetcode.com/problems/count-number-of-nice-subarrays/description/
 */
public class CountNumberOfNiceSubArrays {

    /*
    Approach:
        Same as `BinarySubarrayWithSumK.java`

        only difference is % 2 division

        Time Complexity:
            Same as `BinarySubarrayWithSumK.java`

        Space Complexity:
            Same as `BinarySubarrayWithSumK.java`
     */
    public static int countNumberOfNiceSubArraysOptimal(int[] arr, int k){
        return countNumberOfSubArraysWithSumLesserThanEqualToK(arr, k) - countNumberOfSubArraysWithSumLesserThanEqualToK(arr, k-1);
    }

    public static int countNumberOfSubArraysWithSumLesserThanEqualToK(int[] arr, int k){
        int left = 0, right = 0, sum = 0, count = 0;

        if(k < 0)
            return 0;

        while(right < arr.length){
            sum  += arr[right] % 2;

            while(sum > k){
                sum = sum - arr[left] % 2;
                left++;
            }

            if(sum <= k)
                count = count + (right - left + 1);

            right++;
        }
        return count;
    }
}
