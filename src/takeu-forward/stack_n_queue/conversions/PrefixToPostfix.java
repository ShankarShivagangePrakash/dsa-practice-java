package stack_n_queue.conversions;

import java.util.Stack;

/*
Problem:
    https://www.geeksforgeeks.org/problems/prefix-to-postfix-conversion/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=prefix-to-postfix-conversion
 */
public class PrefixToPostfix {

    public static String prefixToPostfix(String s){
        int i = s.length() -1;
        Stack<String> stack = new Stack<>();

        while(i >= 0){
            char ch = s.charAt(i);
            if(Character.isLetterOrDigit(ch))
                stack.push(String.valueOf(ch));
            else{
                String op1 = stack.pop();
                String op2 = stack.pop();

                String temp = op1 + op2 + ch;
                stack.push(temp);
            }
            i--;
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        String s = "*+AB-CD";
        System.out.println("Prefix to Postfix conversion: " + prefixToPostfix(s)) ;
    }
}
