package sliding_window_two_pointer.hard;

import java.util.HashMap;

/*
Problem:
    https://leetcode.com/problems/subarrays-with-k-different-integers/description/
 */
public class CountSubarrayWithKDifferentIntegers {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n^2)
        Space Complexity: O(k)
            hash map size
     */
    public static int countSubArrayWithKDifferentIntegersBruteforce(int[] arr, int k){
        int count = 0;

        for(int i = 0; i < arr.length; i++){
            HashMap<Integer, Integer> map = new HashMap<>();

            for(int j = i; j < arr.length; j++){
                map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);

                if(map.size() == k)
                    count++;
                else if(map.size() > k)
                    break;
            }
        }
        return count;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2 * 2n)
            countSubArrayWithLesserThanOrEqualToKDifferentIntegers() invoked two times.

        Space Complexity: O(2 * k)
            countSubArrayWithLesserThanOrEqualToKDifferentIntegers() invoked two times.
     */
    public static int countSubArrayWithKDifferentIntegersOptimal(int[] arr, int k){
        return countSubArrayWithLesserThanOrEqualToKDifferentIntegers(arr, k) - countSubArrayWithLesserThanOrEqualToKDifferentIntegers(arr, k-1);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2n)
            O(n) for outer while loop
            O(n) for inner while loop

        Space Complexity: O(k)
            hash map can have at maximum k elements
     */
    public static int countSubArrayWithLesserThanOrEqualToKDifferentIntegers(int[] arr, int k){
        int left = 0, right = 0, count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        while(right < arr.length){
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            while(map.size() > k){
                map.put(arr[left], map.get(arr[left]) - 1);

                if(map.get(arr[left]) == 0)
                    map.remove(arr[left]);

                left++;
            }

            if(map.size() <= k)
                count = count + (right - left + 1);

            right++;
        }
        return count;
    }
}
