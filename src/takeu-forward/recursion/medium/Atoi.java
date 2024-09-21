package recursion.medium;

/*
Problem:
    https://leetcode.com/problems/string-to-integer-atoi/description/
Problem Statement:
    convert String to number
 */
public class Atoi {

    /*
    The method atoi aims to convert a string representation of an integer (ASCII to integer) into an integer value, similar to how the C function atoi works.
    The method handles potential spaces, optional '+' or '-' signs, and ensures that only valid numeric characters are processed.
    If the number exceeds the boundaries of a 32-bit signed integer, the method returns Integer.MIN_VALUE or Integer.MAX_VALUE.

    Step-by-Step Approach:
        Trim Input:
        The string s is trimmed of leading and trailing whitespace characters using trim(). If the string is empty after trimming, return 0.

        Handle Signs:
        Check for an optional leading '-' or '+' character to determine the sign of the number.
        The variable sign is set to -1 for negative numbers and 1 for positive numbers.
        The start variable is adjusted to skip the sign character during number parsing.

        Recursive Parsing:
        The method recursiveAtoi is called to recursively parse the string character by character starting from the index after the optional sign(- or +).
        It builds the integer by converting each character to its numeric value, accumulating the result, and checking for overflow at each step.

        Handle Integer Overflow:
        If adding another digit causes the current number to exceed the bounds of a 32-bit signed integer, return Integer.MIN_VALUE or Integer.MAX_VALUE.

    Time Complexity: O(n)
         where n is the length of the string:
        The algorithm processes each character of the string exactly once (ignoring whitespace and signs),
        performing constant-time operations (character comparisons and integer multiplication/addition) for each character.

    Space Complexity: O(n)
        The recursive function uses stack space proportional to the number of digits in the string, making the space complexity O(n).
        where n is the number of digits in the input string.
        However, if the recursion were implemented iteratively, the space complexity could be reduced to O(1).
     */
    public static int atoi(String s){
        if(s.isEmpty())
            return 0;
        s = s.trim();
        if(s.length() == 0)
            return 0;

        int start = 0, sign = 1;
        boolean isNegative = false;
        if(s.charAt(0) == '-'){
            start = 1;
            isNegative = true;
            sign = -1;
        }
        if(s.charAt(0) == '+')
            start = 1;

        return sign * recursiveAtoi(s, start, 0, isNegative);
        // uncomment below line to try iterative approach
        // return sign * iterativeAtoi(s, start, 0, isNegative);

    }

    public static int recursiveAtoi(String s, int index, int currentNumber, boolean isNegaive){
        if(index >= s.length() || !Character.isDigit(s.charAt(index)))
            return currentNumber;

        int digit = s.charAt(index) - '0';

        if(currentNumber > (Integer.MAX_VALUE - digit) / 10)
            return (isNegaive) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        currentNumber = currentNumber * 10 + digit;

        return recursiveAtoi(s, index+1, currentNumber, isNegaive);
    }

    // iterative approach doesn't uses any stack space do space complexity reduces to O(1)
    public static int iterativeAtoi(String s, int index, int currentNumber, boolean isNegative){
        while(index < s.length()){
            if(!Character.isDigit(s.charAt(index)))
                return currentNumber;

            int digit = s.charAt(index) - '0';

            if(currentNumber > (Integer.MAX_VALUE - digit)/10)
                return (isNegative) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            currentNumber = currentNumber * 10 + digit;
        }
        return currentNumber;
    }

    public static void main(String[] args){
        String s1 = " -042";
        System.out.printf("Integer value of %s is %d\n", s1, atoi(s1));
    }
}
