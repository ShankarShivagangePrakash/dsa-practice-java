package sliding_window_two_pointer;

/*
Problem:
    https://leetcode.com/problems/max-consecutive-ones-iii/description/
 */
public class MaxConsecutiveOnes3 {

    /*
    Approach:
        We form sub arrays containing at max k zeroes

        we compare their length with `maxLen` and update it

        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    public static int maxConsecutiveOnesBruteforce(int[] arr, int k){

        int maxLen = 0;

        for(int i = 0; i < arr.length; i++){
            int zeroes = 0;
            // we are forming sub array so j has to start from i
            for(int j = i; j < arr.length; j++){
                if(arr[j] == 0)
                    zeroes++;

                if(zeroes <= k)
                    maxLen = Math.max(maxLen, j-i+1);

                if(zeroes > k)
                    break;
            }
        }
        return maxLen;
    }

    /*
    Approach:
        Explained in notes, Check

        Time Complexity: O(2n)
            O(n) for while loop outer
            O(n) for inner while loop
        Space Compelxity: O(1)
     */
    public static int maxConsecutiveOnesBetterApproach(int[] arr, int k){
        int left = 0, right = 0, zeroes = 0;
        int maxLen = 0;

        while( right < arr.length){
            if(arr[right] == 0)
                zeroes++;

            while( zeroes > k){
                if(arr[left] == 0)
                    zeroes--;
                left++;
            }

            if(zeroes <= k)
                maxLen = Math.max(maxLen, right - left + 1);

            right++;
        }
        return maxLen;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static int maxConsecutiveOnesOptimal(int[] arr, int k){
        int left = 0, right = 0, zeroes = 0;
        int maxLen = 0;

        while(right < arr.length){
            if(arr[right] == 0)
                zeroes++;

            if(zeroes > k){
                if(arr[left] == 0)
                    zeroes--;
                left++;
            }

            if(zeroes <= k)
                maxLen = Math.max(maxLen, right - left + 1);

            right++;
        }

        return maxLen;
    }
}
