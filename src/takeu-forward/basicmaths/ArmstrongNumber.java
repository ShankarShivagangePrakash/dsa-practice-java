package basicmaths;


import java.util.Scanner;

/*
Problem:
    Problem Statement: Given an integer N, return true it is an Armstrong number otherwise return false.
    An Armstrong number is a number that is equal to the sum of its own digits each raised to the power of the number of digits.

    https://takeuforward.org/maths/check-if-a-number-is-armstrong-number-or-not/
    https://www.naukri.com/code360/problems/check-armstrong_589?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
 */
public class ArmstrongNumber {

    /*
    Approach:
    This is the optimal solution
    Step 1:Calculate the number of digits in the input number and store it in k. Read more about this Approach here: Count Digits

    Step 2: Initialise a variable sum to 0. This variable will store the sum of each digit raised to the power of number of digits in number.

    Make a copy of the original number to store it in a temporary variable.
    Step 3: Run a while loop with the condition n>0 and at each iteration:

    Get the last digit of n by using the modulus operator % with 10 and store it in a temporary variable ld.
    Add the digit ld raised to the power of k of the sum.
    Update n by integer division with 10 effectively removing the last digit.

    Step 4: After the loop, check if the original input number is equal to the sum of the digits raised to the power of the number of digits in the number.

    If they are equal, return true indicating the number is an Armstrong number.
    If they are not equal, return false indicating that the number is not an Armstrong number.

    Time complexity: O(log n)
     */
    public static boolean checkArmstrong(int n){
        int numberOfDigits = (int) Math.log10(n) + 1;
        int temp = n;

        int sum = 0;

        while( temp > 0){
            int digit = temp % 10;
            sum = sum + (int) Math.pow(digit, numberOfDigits);
             temp = temp / 10;
        }
        return  sum == n;

    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter number");
        int n = input.nextInt();

        System.out.printf("Brute force approach: Number of digits in the number % d is %b\n", n, checkArmstrong(n));

    }
}
