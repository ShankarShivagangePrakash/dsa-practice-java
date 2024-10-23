package stack_n_queue.monotonic;

import java.util.Stack;

/*
Problem:
    https://leetcode.com/problems/sum-of-subarray-ranges/
 */
public class SumOfSubArrayRange {

    /*
    Approach: Explained in notes, check

        Time Complexity: O(n^2)
            Two for loops
        Space Complexity: O(1)
     */
    public static int sumOfSubArrayRangeBruteforce(int[] arr){
        int total = 0;

        for(int i = 0; i < arr.length; i++){
            int smallest = arr[i], largest = arr[i];
            for(int j = i; j < arr.length; j++){
                smallest = Math.min(smallest, arr[j]);
                largest = Math.max(largest, arr[j]);

                total = total + (largest - smallest);
            }
        }
        return total;
    }

    /*
    Approach:
        Explained in notes, check

        We try to find the total of largest in all sub arrays
        We try to find the total of smallest in all sub arrays

        we return it's difference

        Time Complexity: O(10n)
            O(5n) for largestSubArraySum
            O(5n) for smallestSubArraySum

        Space Complexity: O(8n)
            O(4n) for largestSubArraySum
            O(4n) for smallestSubArraySum
     */
    public long sumOfSubArrayRangeOptimal(int[] nums) {
        return largestSubArraySum(nums) - smallestSubArraySum(nums);
    }

    /*
    Approach:
        Similar to `SumOfSubArrayMinimum.jva`

        we try to find out which is the next larger element and previous larger or equal element
        in this range current element will be the maximum
        for this range multiply the arr[i] - that will be the sum of larger elements in sub array where larger element in arr[i]

        do the same thing for every element in given array and form total of it.

        Time Complexity: O(5n)
            O(2n) for findNGE
            O(2n) for findPGEE
            O(n) to for loop in this method

        Space Complexity: O(4n)
            O(2n) for findNGE
            O(2n) for findPGEE
     */
    public long largestSubArraySum(int[] arr){
        long total = 0;

        int[] nge = new int[arr.length];
        int[] pgee = new int[arr.length];

        findNGE(arr, nge);
        findPGEE(arr, pgee);

        for(int i = 0; i < arr.length; i++){
            int noOfElementOnLeft = i - pgee[i];
            int noOfElementOnRight = nge[i] - i;

            total += (long) (noOfElementOnLeft * noOfElementOnRight * arr[i]);
        }

        return total;
    }

    /*
    Approach:
        Explained in `SumOfSubArrayMinimum.java read it

        Time Complexity: O(5n)
            O(2n) for findPSEE
            O(2n) for findNSE

            O(n) for other for loop in this method.

        Space Complexity: O(4n)
            O(2n) for findPSEE
            O(2n) for findNSE
     */
    public long smallestSubArraySum(int[] arr){
        int[] nse = new int[arr.length];
        int[] psee = new int[arr.length];
        int total = 0;

        findNSE(arr, nse);
        findPSEE(arr,  psee);

        for(int i = 0; i < arr.length; i++){

            int noOfElementOnLeft = i - psee[i];
            int noOfElementOnRight = nse[i] - i;

            total += (long) (noOfElementOnLeft * noOfElementOnRight * arr[i]);
        }

        return total;
    }

    /*
    Approach:
        we try to find smaller element on the right half

        Time Complexity: O(2n)
            O(n) for outer for loop
            while loop will pop elements from stack,
                at max stack can can have n elements so it might take O(n)
            combining both O(2n)

        Space Complexity: O(2n)
            O(n) for stack
            O(n) for next smaller element array
     */
    public void findNSE(int[] arr, int[] nse){
        Stack<Integer> stack = new Stack<>();

        for(int i = arr.length-1; i >= 0; i--){

            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i])
                stack.pop();

            nse[i] = stack.isEmpty() ? arr.length : stack.peek();

            stack.push(i);
        }
    }

    /*
    Approach:
        we try to find smaller or equal element on the left half

        Time Complexity: O(2n)
            O(n) for outer for loop
            while loop will pop elements from stack,
                at max stack can can have n elements so it might take O(n)
            combining both O(2n)

        Space Complexity: O(2n)
            O(n) for stack
            O(n) for previous smaller or equal element array
     */
    public void findPSEE(int[] arr, int[] psee){
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < arr.length; i++){

            // we store the indices in the stack.
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i])
                stack.pop();

            psee[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }
    }

    /*
    Approach:
        we try to find larger element on the right half

        Time Complexity: O(2n)
            O(n) for outer for loop
            while loop will pop elements from stack,
                at max stack can can have n elements so it might take O(n)
            combining both O(2n)

        Space Complexity: O(2n)
            O(n) for stack
            O(n) for next larger element array
     */
    public void findNGE(int[] arr, int[] nge){
        Stack<Integer> stack = new Stack<>();

        for(int i = arr.length-1; i >=0; i--){

            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i])
                stack.pop();

            nge[i] = stack.isEmpty() ? arr.length : stack.peek();

            stack.push(i);
        }
    }

    /*
    Approach:
        we try to find larger or equal element on the left half

        Time Complexity: O(2n)
            O(n) for outer for loop
            while loop will pop elements from stack,
                at max stack can can have n elements so it might take O(n)
            combining both O(2n)

        Space Complexity: O(2n)
            O(n) for stack
            O(n) for previous larger or equal element array
     */
    public void findPGEE(int[] arr, int[] pgee){
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < arr.length; i++){

            while(!stack.isEmpty() && arr[stack.peek()] < arr[i])
                stack.pop();

            pgee[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }
    }
}
