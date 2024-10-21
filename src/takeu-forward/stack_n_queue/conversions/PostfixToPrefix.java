package stack_n_queue.conversions;

import java.util.Stack;

/*
Problem:
    https://www.geeksforgeeks.org/problems/postfix-to-prefix-conversion/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=postfix-to-prefix-conversion
 */
public class PostfixToPrefix {

    public static String postfixToPrefix(String s){
        int i = 0;
        int n = s.length();
        Stack<String> stack = new Stack<>();

        while(i < n){
            char ch = s.charAt(i);
            if(Character.isLetterOrDigit(ch))
                stack.push(String.valueOf(ch));
            else{
                String op2 = stack.pop();
                String op1 = stack.pop();

                String temp = ch + op1 + op2;
                stack.push(temp);
            }
            i++;
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        String s = "AB+CD-*";
        System.out.println("Postfix to Prefix conversion: " + postfixToPrefix(s));
    }
}
