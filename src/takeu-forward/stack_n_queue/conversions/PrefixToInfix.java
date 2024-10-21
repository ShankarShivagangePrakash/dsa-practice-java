package stack_n_queue.conversions;

import java.util.Stack;

/*
Problem:
    https://www.geeksforgeeks.org/problems/prefix-to-infix-conversion/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=prefix-to-infix-conversion
 */
public class PrefixToInfix {

    /*
    Approach:
        We will have a iterator `i` and a stack which will store String values
		In previous cases the stack used to store Characters

		We start iterating from the end of the input string
		When we find operands, we simply push it to stack
		When we encounter operators, we pop last two elements from the stack
		We insert the incoming operator in between them, then we form an expression
		That expression will be inserted into the stack again.

		We repeat this process until, we reach the beginning of the input string.

        Time Complexity: O(2n)
		    O(n) for outer while loop
		    O(n), worst case specific to few programming language, they might take O(n1 +n2) to add two strings.
		    if we keep repeating the same process for all elements in the stack it might take O(n)
		Space Complexity: O(n)
		    stack space
     */
    public static String prefixToInfix(String s){

        int i = s.length()-1;
        Stack<String> stack = new Stack<>();

        while(i >= 0){
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch))
                stack.push(String.valueOf(ch));
            else{
                String op1 = stack.pop();
                String op2 = stack.pop();

                String temp = '(' + op1 + ch + op2 + ')';
                stack.push(temp);
            }
            i--;
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        String s = "*+AB-CD";
        System.out.println("Prefix to infix conversion: " + prefixToInfix(s)) ;
    }
}
