package stack_n_queue.easy;

import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/check-for-balanced-parentheses/
    https://leetcode.com/problems/valid-parentheses/description/
 */
public class BalancedParanthesis {

    /*
    Approach:
        Whenever we get the opening bracket we will push it into the stack. I.e ‘{‘, ’[’, ’(‘.
        Whenever we get the closing bracket we will check if the stack is non-empty or not.
        If the stack is empty we will return false, else if it is nonempty then we will check if the topmost element of the stack is the opposite pair of the closing bracket or not.
        If it is not the opposite pair of the closing bracket then return false, else move ahead.
        After we move out of the string the stack has to be empty if it is non-empty then return it as invalid else it is a valid string.

        Time Complexity: O(n)
            we traverse entire string.
        Space Complexity: O(n)
            worst case scenario, if there are no closing brackets in the input string, we store entire string in stack.
     */
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                st.add(s.charAt(i));
            else{
                if(st.isEmpty())
                    return false;

                char ele = st.pop();
                if((s.charAt(i) == ']' && ele == '[') ||
                        (s.charAt(i) == ')' && ele == '(') ||
                        (s.charAt(i) == '}' && ele == '{'))
                    continue;
                else
                    return false;
            }
        }
        return st.isEmpty();
    }
}
