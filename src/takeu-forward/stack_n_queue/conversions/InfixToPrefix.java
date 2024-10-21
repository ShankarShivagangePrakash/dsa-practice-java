package stack_n_queue.conversions;

import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/infix-to-prefix/
    https://www.geeksforgeeks.org/problems/infix-to-postfix-1587115620/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=infix-to-postfix
 */
public class InfixToPrefix {

    /*
    Approach:
        Explained in detail, in notes

        high level notes:

            convert the string to char array
            we will operate with char array

            First step is to reverse this char Array
            execute modified infix to postfix conversion method
            reverse the result obtained from conversion step
            convert this back to string and return.

        Time Complexity: O(4n)
            O(n/2) - to reverse the char array initially
            O(n/2) - to reverse the char array while returning the result at the end

            O(n) - to reverse '(' and ')' symbols using for loop

            O(2n) - for modified infix to postfix conversion method.

        Space Complexity: O(3n)
            for stack we use O(n)
            for two character arrays we are creating takes O(n) each.
     */
    public static String infixToPrefix(String s){
        char[] temp = s.toCharArray();
        int n = temp.length;
        //O(n/2)
        reverse(temp, 0, n-1);

        //O(n)
        for(int i = 0; i < n; i++){
            if(temp[i] == '(')
                temp[i] = ')';
            else if(temp[i] == ')')
                temp[i] = '(';
        }

        //O(2n)
        String ans = infixToPostFixModified(temp);

        /* when you do charArray on a string, it will create new character array from string.
         in this problem, you are reversing that char array,
         so you need to have a reference for that char array, so we are creating and assigning char array to ansCharArray variable.*/
        char[] ansCharArray = ans.toCharArray();
        //O(n/2)
        reverse(ansCharArray, 0, ansCharArray.length-1);

        /* we have the result of infix to prefix conversion, but it is in char[] form
         we need to send String. so we are forming String from Char[]*/
        return String.valueOf(ansCharArray);
    }

    public static void reverse(char[] temp, int start, int end){
        while(start < end){
            char ch = temp[start];
            temp[start] = temp[end];
            temp[end] = ch;

            start++;
            end--;
        }
    }

    /* only change from normal infix to postfix is
     in processing operators
     when the incoming operator is '^', we keep popping elements from stack till incoming operator priority is lesser or equal to stack top
     when the incoming operator is other than '^' then we keep popping elements from stack, till till incoming operator priority is lesser than stack top*/
    public static String infixToPostFixModified(char[] s){

        int i = 0;
        int n = s.length;
        String ans = new String("");
        Stack<Character> stack = new Stack<>();

        while(i < n){
            if(Character.isLetterOrDigit(s[i]))
                ans = ans + s[i];
            else if(s[i] == '('){
                stack.push(s[i]);
            }
            else if(s[i] == ')'){
                while(!stack.isEmpty() && stack.peek() != '(')
                    ans = ans + stack.pop();

                stack.pop(); // to pop '(' from stack
            }
            else{

                if(s[i] == '^'){
                    while(!stack.isEmpty() && priority(s[i]) < priority(stack.peek()))
                        ans = ans + stack.pop();
                }
                else{
                    while(!stack.isEmpty() && priority(s[i]) < priority(stack.peek()))
                        ans = ans + stack.pop();
                }
                stack.push(s[i]);
            }
            i++;
        }

        while(!stack.isEmpty())
            ans += stack.pop();

        return ans;
    }

    public static int priority(char ch){
        switch(ch){
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
        System.out.println("Infix to prefix conversion: " + infixToPrefix(s)) ;

    }
}
