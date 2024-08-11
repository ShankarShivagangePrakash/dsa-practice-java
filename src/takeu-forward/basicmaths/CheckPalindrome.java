package basicmaths;

import java.util.Scanner;

/*
Problem:
    https://takeuforward.org/data-structure/check-if-a-number-is-palindrome-or-not/
    https://www.naukri.com/code360/problems/palindrome-number_624662?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
 */
public class CheckPalindrome {

    /*
    Approach: reverse the given number - refer to reverseNumber problem - O(log n)
    then check is given number and reverse number same, if so return true else false

    Time complexity - O(log n)
     */
    public static boolean checkPalindrome(int n){
        int duplicateN = n;

        int reversedNumber = 0;

        while(duplicateN > 0){
            int rem = duplicateN % 10;
            reversedNumber = reversedNumber * 10 + rem;
            duplicateN /= 10;
        }
        return  reversedNumber == n;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number");
        int n = input.nextInt();

        System.out.printf("Check palindrome of %d is %b\n", n, checkPalindrome(n));
    }
}
