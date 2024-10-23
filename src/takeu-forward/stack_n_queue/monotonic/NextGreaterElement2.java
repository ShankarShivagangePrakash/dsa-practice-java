package stack_n_queue.monotonic;

import java.util.Stack;

/*
Problem:
    https://leetcode.com/problems/next-greater-element-ii/description/
 */
public class NextGreaterElement2 {

    /*
    Approach:
        Explained in notes.

        Time Complexity: O(n^2)
        Space Complexity: O(n)
            Used to return the result.
     */
    public static int[] nextGreaterElementBruteForce(int[] arr){
        int[] result = new int[arr.length];

        for(int i = 0; i < arr.length; i++){
            boolean foundNextGreater = false;

            for(int j = i+1; j < arr.length && !foundNextGreater; j++){
                if(arr[j] > arr[i]){
                    result[i] = arr[j];
                    foundNextGreater = true;
                }
            }

            for(int j = 0; j < i && !foundNextGreater; j++){
                if(arr[j] > arr[i]){
                    result[i] = arr[j];
                    foundNextGreater = true;
                }
            }

            if(!foundNextGreater)
                result[i] = -1;
        }
        return result;
    }

    /*
    Approach:
        Explained in notes

        Time Complexity: O(n^2)
            The inner for loop also iterate through n elements. So n^2
        Space Complexity: O(n)
            Used to return the result.
     */
    public static int[] nextGreaterElementBetterApproach(int[] arr){
        int[] result = new int[arr.length];

        for(int i = 0; i < arr.length; i++){
            boolean foundGreater = false;
            for(int j = i+1; j < i + (arr.length-1) && !foundGreater; j++){
                if(arr[j] > arr[i]){
                    foundGreater = true;
                    result[i] = arr[j];
                }
            }
            if(!foundGreater)
                result[i] = -1;
        }
        return result;
    }

    /*
    Approach:
        Explained in detail.

        Time Complexity: O(4n)
            O(2n) for the for loop we are running a loop from (2n-1) to 0
            Another O(2n) for while loop
                Stack can have maximum of 2n elements, so while loop can pop maximum of 2n elements
                So O(2n) for this while loop

            Total O(4n)

        Space Complexity: O(n) + O(2n)
            O(n) for storing results
            O(2n) stack space at worst case.
     */
    public static int[] nextGreaterElementOptimal(int[] arr){
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i = 2*n-1; i >= 0; i--){

            while(!stack.isEmpty() && stack.peek() <= arr[i%n])
                stack.pop();

            if(i < n) {
                if (!stack.isEmpty())
                    result[i] = stack.peek();
                else
                    result[i] = -1;
            }

            stack.push(arr[i%n]);
        }
        return result;
    }
}
