package greedy.easy;

/*
Problem:
    https://leetcode.com/problems/valid-parenthesis-string/description/
 */
public class ValidParanthesisChecker {

    /*
    Approach:
        Explained in notes,

        Time Complexity: O(3^n)
            there can be three branches at each node, so 3^n
        Space Complexity: O(n)
            stack space can go upto n level
     */
    public static boolean validParanthesisCheckerBruteforce(String s){
        return validParanthesisCheckerRecursive(s, 0, 0);
    }

    public static boolean validParanthesisCheckerRecursive(String s, int ind, int count){

        // anytime count goes below zero, it is not a valid paranthesis, don't go further in this path return false
        if(count < 0)
            return false;

        // after completing the traversal,
        // check is count == 0, if so it is a valid paranthesis return true, else return false
        if(ind == s.length())
            return (count == 0);

        // you have opening bracket move to next character by incrementing count
        if(s.charAt(ind) == '(')
            return validParanthesisCheckerRecursive(s, ind+1, count+1);
        // you have closing bracket, move to next character by decrementing count
        else if(s.charAt(ind) == ')')
            return validParanthesisCheckerRecursive(s, ind+1, count-1);
        // else means, * you have to try with all options, if you get atleast one true it means it is a valid paranthesis, so use || between all three options.
        else
            return validParanthesisCheckerRecursive(s, ind+1, count+1) ||
                    validParanthesisCheckerRecursive(s, ind+1, count-1) ||
                    validParanthesisCheckerRecursive(s, ind+1, count);
    }

    /*
    Approach:
        Explained in detail, in notes

        Time Complexity: O(n)
            we are traversing the array only once.
        Space Complexity: O(1)
     */
    public static boolean validParanthesisCheckerOptimal(String s){

        // maintain min and max variables to maintain the range.
        int min = 0, max = 0;

        for(int i = 0; i < s.length(); i++){

            // get the current character
            char ch = s.charAt(i);

            // if the character is opening bracket, increment value of both `min` and `max
            if(ch == '('){
                min++;
                max++;
            }
            // if the character is closing bracket, decrement the value of both `min` and `max`
            else if(ch == ')'){
                min--;
                max--;
            }
            // else means, *
            // min need to have minimum value so subtract it by -1
            // max needs to have maximum value, so add 1 to it
            else{
                min = min -1;
                max = max + 1;
            }

            // we cannot let min to go below 0, if it is less than 0, reset it
            if(min < 0)
                min = 0;

            // if max is < 0 means, whatever we do we cannot make it valid return false there itself
            if(max < 0)
                return false;
        }
        // check if min is equal to 0, means it is valid return true, else return false
        return (min == 0);
    }
}
