package stack_n_queue.hard;

import java.util.Deque;
import java.util.ArrayDeque;

/*
Problem:
    https://leetcode.com/problems/sliding-window-maximum/description/
 */
public class SlidingWindowMaximum {

    /*
    Approach:
        Keep forming k sized windows and find maximum in them and add it to the result

        Time Complexity: O((n-k) * k))
            (n-k) for outer for loop
            (k) for inner for loop
        Space Complexity: O(n-k)
            This is to store the result and it cannot be optimized
     */
    public static int[] slidingWindowMaximumBruteforce(int[] arr, int k){

        int n = arr.length;
        // there can be (n-k)+ 1 windows, so those many maximums
        int[] result = new int[n-k + 1];


        for(int i = 0; i <= n-k; i++){
            int maxVal = arr[i];
            for(int j = i; j < i+k; j++)
                maxVal = Math.max(maxVal, arr[j]);
            result[i] = maxVal;
        }
        return result;
    }

    /*
    Approach:
        Explained in detail in notes, check

        Time Complexity: O(3n)
            O(n) for outer for loop
            inside for loop, we are using while loops and other logic to add and remove elements from deque
            at max deque can have n elements and we can remove all these n elements
            so it might take O(2n) for adding and removal of all `n` elements.
        Space Complexity: O(k) + O(n-k)
            in deque we store last k elements which represents the window size
            (n-k) such windows and maximums of all those windows.
     */
    public static int[] slidingWindowMaximumOptimal(int[] arr, int k){

        int n = arr.length;
        int[] result = new int[n-k+1];
        int j = 0;

        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0; i < n; i++){

            // Remove elements from the front if they are out of the current window
            if(!dq.isEmpty() && dq.peekFirst() < i - k + 1)
                dq.pollFirst();

            // Remove elements from the back while they are smaller than the current element
            while(!dq.isEmpty() && arr[dq.peekLast()] <= arr[i])
                dq.pollLast();

            // Add the current element's index to the back of the deque
            dq.offerLast(i);

            // Start adding results to the list once the first window is fully traversed
            if (i >= k - 1) {
                result[j++] = arr[dq.peekFirst()];
            }
        }
        return result;
    }
}
