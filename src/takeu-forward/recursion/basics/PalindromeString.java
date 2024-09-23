package recursion.basics;

/*
Problem:
    https://leetcode.com/problems/valid-palindrome/description/

    problem statement:
        Given a sentence, you should ignore special characters in it like , + - space
        and compare whether is it palindrome or not
 */
public class PalindromeString {

    /*
    Approach:
        Used Two pointer approach

        Time Complexity: O(n/2)
        Space Complexity: O(1)
     */
    public static boolean isPalindromIterative(String s){
        if(s.isEmpty())
            return true;

        int i = 0, j = s.length() -1;

        while( i < j){
            char firstCharacter = s.charAt(i);
            char seondCharacter = s.charAt(j);

            // if the current character is not letter or digit increment i
            if(!Character.isLetterOrDigit(firstCharacter))
                i++;
            // if the current character is not letter or digit decrement j
            else if(!Character.isLetterOrDigit(seondCharacter))
                j--;
            else{
                // if corresponding characters are not same, then it cannot be palindrome return false
                if(Character.toLowerCase(firstCharacter) != Character.toLowerCase(seondCharacter))
                    return false;

                i++;
                j--;
            }
        }
        return true;
    }

    /*
    Approach:
        Using parameterized recursion to solve the problem

        iterative logic but using recursion to solve.

        Time Complexity:
            Worst case: O(n)
                if the string is of type "-+, a"
                then we keep incrementing `i` pointer till we reach the end of the array, so in this case O(n)
            best case: O(n/2)
                when it is valid palindrome, we move both i and j pointers
                so n/2
        Space Complexity:
            Worst case: O(n)
            Best case: O(n/2)

            same logic as of time complexity, recursive stack space
     */
    public static boolean isPalindromeRecursive(String s){
        if(s.isEmpty())
            return true;

        int i = 0, j = s.length() - 1;
        return isPalindromeParameterizedRecursive(s, i, j);
    }

    public static boolean isPalindromeParameterizedRecursive(String s, int i, int j){
        // while loop stopping condition became parameterized recursion base case.
        if(i > j)
            return true;

        char firstCharacter = s.charAt(i);
        char seondCharacter = s.charAt(j);

        // if the current character is not letter or digit increment i
        if(!Character.isLetterOrDigit(firstCharacter))
            i++;
            // if the current character is not letter or digit decrement j
        else if(!Character.isLetterOrDigit(seondCharacter))
            j--;
        else{
            // if corresponding characters are not same, then it cannot be palindrome return false
            if(Character.toLowerCase(firstCharacter) != Character.toLowerCase(seondCharacter))
                return false;

            i++;
            j--;
        }
        return isPalindromeParameterizedRecursive(s, i, j);
    }
}
