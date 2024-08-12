package basicmaths;

import java.util.Scanner;

/*
Problem:
    https://takeuforward.org/maths/reverse-digits-of-a-number
    https://www.naukri.com/code360/problems/reverse-bits_2181102?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
 */
public class ReverseNumber {

    /*
    Algorithm
    Step 1:Initialise an integer revNum to 0. This variable will store the reversed number.

    Step 2: Using a while loop we iterate while n is greater than 0 and at each iteration:

    Calculate the last digit of the number using the modulus operator (N%10) and store it in a variable last digit.
    Update the reversed number by multiplying it with 10 and adding the last digit. This effectively appends the last digit to the end of the reversed number.
    Remove the last digit of the number by dividing it by 10.

    Time complexity: log(n) to base 10.
     */
    public static long reverseNumber(int n) {

        int reversedNumber = 0;
        while(n > 0){
            // Extract the last digit of 'n'
            int digit =  n % 10;
            // Multiply the current reverse number by 10 and add the last digit.
            reversedNumber = (reversedNumber * 10  + digit);
            // Remove the last digit from 'n'.
            n /= 10;
        }
        return reversedNumber;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number");
        // try with inputs 2, 15, 7789, 00401, 100
        int n = in.nextInt();

        System.out.println("reversed number: " + reverseNumber(n));

    }
}
