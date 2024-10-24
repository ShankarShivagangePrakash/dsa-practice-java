package stack_n_queue.monotonic;

import java.util.Stack;

/*
Problem:
    https://leetcode.com/problems/remove-k-digits/description/
 */
public class RemoveKDigits {

    public static String removeKDigits(String num, int k){

        Stack<Character> stack = new Stack<>();

        // Traverse the string `num` character by character.
        for (int i = 0; i < num.length(); i++) {
            char currentChar = num.charAt(i);

            // Remove larger digits from the stack to make the number smaller
            while (!stack.isEmpty() && k > 0 && stack.peek() > currentChar) {
                stack.pop();
                k--;
            }

            stack.push(currentChar);
        }

        // if we have not removed k digits after completing number traversal
        // remove k digits from the top of the stack.
        while (k > 0) {
            stack.pop();
            k--;
        }

        // Build the final number from the stack
        StringBuilder sb = new StringBuilder();

        // transfer stack contents to String builder.
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        // Reverse the string since we built it backwards
        sb.reverse();

        // Remove trailing zeros
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        // If the result is empty, return "0"
        if (sb.length() == 0) {
            return "0";
        }

        return sb.toString();
    }
}
