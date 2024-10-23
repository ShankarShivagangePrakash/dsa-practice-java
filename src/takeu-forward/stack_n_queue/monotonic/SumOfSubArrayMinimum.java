package stack_n_queue.monotonic;

import java.util.Stack;

/*
Problem:
    https://leetcode.com/problems/sum-of-subarray-minimums/description/
 */
public class SumOfSubArrayMinimum {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    public static int sumOfSubArrayMinimumBruteforce(int[] arr){
        int total = 0;
        int mod = (int)(1e9 + 7);

        for(int i = 0; i < arr.length; i++){
            int min = arr[i];
            for(int j = i; j < arr.length; j++){
                min = Math.min(min, arr[j]);

                // after each index, it means we have found new sub array.
                // in that new sub array `min` will store minimum of that sub array
                // so we add `min` to total
                // to avoid result crossing the range, we perform modulo as suggested by the problem statement.
                total = (total + min) %mod;
            }
        }
        return total;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(5n)
            O(2n) for findPSEE
            O(2n) for findNSE

            O(n) for other for loop in this method.

        Space Complexity: O(4n)
            O(2n) for findPSEE
            O(2n) for findNSE
     */
    public static int sumOfSubArrayMinimumOptimal(int[] arr){
        int total = 0;
        int mod = (int)(1e9 + 7);

        int[] psee = new int[arr.length];
        int[] nse= new int[arr.length];
        findPSEE(arr, psee);
        findNSE(arr, nse);

        for(int i = 0; i < arr.length; i++){
            int noOfElementOnLeft = i - psee[i];
            int noOfElementOnRight = nse[i] - i;

            total = (total + (noOfElementOnLeft * noOfElementOnRight * arr[i]) % mod ) % mod;
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
    public static void findNSE(int[] arr, int[] nse){
        Stack<Integer> stack = new Stack<>();

        for(int i = arr.length-1; i >= 0; i--){

            // we store indices in the stack
            // we have to find the element which is smaller than the arr[i] towards its right
            // so we have to pop all greater and equal elements from the stack.
            while(!stack.isEmpty() && arr[stack.peek()] >=  arr[i])
                stack.pop();

            // if we don't find any smaller element on right side we set it as arr.length else we set the stack top which stores index
            nse[i] = (stack.isEmpty()) ? arr.length : stack.peek();

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
    public static void findPSEE(int[] arr, int[] psee){
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < arr.length; i++){

            // we store indices in the stack
            // we have to find the element which is smaller or equal to arr[i] on left side of it

            while(!stack.isEmpty() && arr[stack.peek()] > arr[i])
                stack.pop();

            psee[i] = (stack.isEmpty()) ? -1 : stack.peek();

            stack.push(i);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2,4};

        int resultBruteforce = sumOfSubArrayMinimumBruteforce(arr);
        int resultOptimal = sumOfSubArrayMinimumOptimal(arr);
        System.out.println();
    }
}
