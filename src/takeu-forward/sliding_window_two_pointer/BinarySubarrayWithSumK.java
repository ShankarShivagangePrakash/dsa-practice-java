package sliding_window_two_pointer;

/*
Problem:
    https://leetcode.com/problems/binary-subarrays-with-sum/description/
 */
public class BinarySubarrayWithSumK {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n^2)
            Two for loops

        Space Complexity: O(1)
     */
    public static int binarySubArrayWithSumKCountBruteforce(int[] arr, int goal){
        int result = 0;

        for(int i = 0; i < arr.length; i++){

            int sum = 0;
            for(int j = i; j < arr.length; j++){
                sum = sum + arr[j];
                if(sum == goal)
                    result++;
                if(sum > goal)
                    break;
            }
        }
        return result;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2n) * 2
            countSubArrayWithSumLesserThanOrEqualToK() is called two times which takes O(2n)

        Space Complexity: O(1)
     */
    public static int binarySubArrayWithSumKCountOptimal(int[] arr, int goal){
        return countSubArrayWithSumLesserThanOrEqualToK(arr, goal) - countSubArrayWithSumLesserThanOrEqualToK(arr, goal - 1);
    }

    /* Time Complexity: O(2n)
        O(n) for outer while loop
        O(n) for inner while loop

        space Complexity: O(1)
    */
    public static int countSubArrayWithSumLesserThanOrEqualToK(int[] arr, int goal){
        int left = 0, right = 0, sum = 0, count = 0;

        // edge case, if the main goal itself is 0, then this method would accept value as -1
        if(goal < 0)
            return 0;

        while(right < arr.length){
            sum = sum + arr[right];

            while(sum > goal){
                sum = sum - arr[left];
                left++;
            }

            // above while loop ensures that sum will be <= goal, we can update count
            count = count + (right - left + 1);

            right++;
        }
        return count;
    }
}
