package basicmaths;

import java.util.Scanner;

/*
Problem:
    https://takeuforward.org/data-structure/count-digits-in-a-number/
    https://www.naukri.com/code360/problems/count-digits_8416387?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
 */
public class CountDigits {

    // brute force approach
    // time complexity: O(log n) base is 10.
    public static int numberOfDigits(int n){
        int digits = 0;

        while (n > 0){
            digits++;
            n = n/10;
        }
        return digits;
    }

    // Optimal solution
    // time complexity and space complexity: O(1)
    public static int numberOfDigits2(int n){

        // log to the base 10 give the number of digits in a number.
        // But +1 is to handle edge cases when n is power of 10.
        return (int) (Math.log10(n) + 1);
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number");
        int n = input.nextInt();

        System.out.printf("Brute force approach: Number of digits in the number % d is %d\n", n, numberOfDigits(n));
        System.out.printf("Optimal approach: Number of digits in the number % d is %d\n", n, numberOfDigits2(n));


    }
}
