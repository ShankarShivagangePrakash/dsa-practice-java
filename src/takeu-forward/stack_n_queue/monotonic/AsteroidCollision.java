package stack_n_queue.monotonic;

import java.util.Stack;

/*
Problem:
    https://leetcode.com/problems/asteroid-collision/
 */
public class AsteroidCollision {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(3n)
            O(n) for outer for loop
            O(n) for while loop at worst case
            O(n) for forming result

        Space Complexity: O(2n)
            O(n) for stack
            O(n) for forming result array.
     */
    public static int[] asteroidCollision(int[] arr){
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < arr.length; i++){

            if(arr[i] > 0)
                stack.push(arr[i]);
            else{
                /* array element is negative, stack top is positive
                 absolute value of array element is greater than top of the stack
                 positive element will be destroyed by negative element, so remove top element from stack*/
                while(!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(arr[i]))
                    stack.pop();

                /* if stack top and absolute value of current array element, then both will get destroyed
                 so remove stack top.*/
                if(!stack.isEmpty() && Math.abs(arr[i]) == stack.peek())
                    stack.pop();

                /* if stack is empty means, current array element has destroyed all positive elements in the stack
                 same if, stack top is negative, so we can insert negative element into the stack*/
                else if(stack.isEmpty() || stack.peek() < 0)
                    stack.push(arr[i]);
            }
        }

        // now we stack will contain remaining elements of the array in reverse order.
        int[] result = new int[stack.size()];
        for(int i = stack.size()-1; i >= 0; i--){
            result[i] = stack.pop();
        }
        return result;
    }
}
