package stack_n_queue.monotonic;

import java.util.Stack;

/*
Problem:
    https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 */
public class LargestRectangleInHistogram {

    /*
    Approach:
        Explained in detail in notes, check

        Time Complexity: O(5n)
            O(2n) for findNSE()
            O(2n) for findPSE()
            O(n) to for loop in this method

        Space Complexity: O(4n)
            O(2n) for findNSE()
            O(2n) for findPSE()
     */
    public static int largestRectangleInHistogramBruteforce(int[] arr){

        int n = arr.length;
        int[] nse = new int[n];
        int[] pse = new int[n];
        int maxArea = 0;

        findNSE(arr, nse);
        findPSE(arr, pse);

        for(int i = 0; i < arr.length; i++){
            int nseIndex = nse[i];
            int pseIndex = pse[i];

            int currentRectangleArea = arr[i] * (nseIndex - pseIndex -1);
            maxArea = Math.max(maxArea, currentRectangleArea);
        }
        return maxArea;
    }

    // finds next smallere element and stores it's index in nse[]
    public static void findNSE(int[] arr, int[] nse){
        Stack<Integer> stack = new Stack<>();

        for(int i = arr.length-1; i >= 0; i--){

            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i])
                stack.pop();

            // if no next smaller element are found, set it as `n`
            nse[i] = stack.isEmpty() ? arr.length : stack.peek();

            stack.push(i);
        }
    }

    // finds previous smaller element and stores i's index in pse[]
    public static void findPSE(int[] arr, int[] pse){
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < arr.length; i++){
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i])
                stack.pop();

            // no previous smaller element are found then set as -1
            pse[i] = stack.isEmpty() ? -1: stack.peek();

            stack.push(i);
        }
    }

    /*
    Approach:
        Explained in detail in notes, check

        Time Complexity: O(2n)
            O(n) to for loop
            O(n) for two while loops combining
                because stack can have maximum of `n` elements to process all of them, while() requires O(n)
        Space Complexity: O(n)
            stack space.
     */
    public static int largestRectangleInHistogramOptimal(int[] arr){
        int maxArea = 0;

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < arr.length; i++){

            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                // `element` - element to which rectangle area is computed
                int element = stack.pop();
                int nseIndex = i;
                int pseIndex = stack.isEmpty() ? -1 : stack.peek();

                int currentRectangleArea = arr[element] * (nseIndex - pseIndex - 1);

                maxArea = Math.max(maxArea, currentRectangleArea);
            }
            stack.push(i);

        }

        /* if stack is not empty after completing array traversal
         we have not found nse for them, use `n` as nse for them and compute rectangle area*/
        while(!stack.isEmpty()){
            int element = stack.pop();
            int nseIndex = arr.length;
            int pseIndex = stack.isEmpty() ? -1 : stack.peek();

            int currentRectangleArea = arr[element] * (nseIndex - pseIndex - 1);

            maxArea = Math.max(maxArea, currentRectangleArea);
        }

        return maxArea;
    }
}
