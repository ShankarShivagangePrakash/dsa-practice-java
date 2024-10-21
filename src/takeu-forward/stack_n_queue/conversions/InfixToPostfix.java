package stack_n_queue.conversions;

import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/infix-to-postfix/
    https://www.geeksforgeeks.org/problems/infix-to-postfix-1587115620/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=infix-to-postfix
 */
public class InfixToPostfix {

    /*
    Approach:
        Explained in detail, in notes

        Time Complexity: O(2n)
            outer for loop will take O(n)
            inner two for loops (popping elements from stack) might take O(n) in worst case

        Space Complexity: O(n)
            we are pushing operators to stack, which might take O(n) in worst case scenario.
     */
    public static String infixToPostfix(String s){

        int i = 0;
        int n = s.length();
        String ans = new String();
        Stack<Character> stack = new Stack<>();

        while(i < n){
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                ans = ans + ch;
            }
            else if(ch == '(')
                stack.push(ch);
            else if(ch == ')'){
                while(stack.peek() != '(')
                    ans = ans + stack.pop();

                // finally pop '(' from stack
                stack.pop();
            }
            else{
                while (!stack.isEmpty() && priority(ch) <= priority(stack.peek()))
                    ans = ans + stack.pop();
                stack.push(ch);
            }
            i++;
        }

        while (!stack.isEmpty())
            ans = ans + stack.pop();

        return ans;
    }

    // O(1)
    public static int priority(char ch){
        switch (ch){
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    public static void main(String[] args){
        String s = "(A+B)*(C-D)";
        System.out.println("Infix to postfix conversion: " + infixToPostfix(s)) ;

    }
}
