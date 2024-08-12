package basicmaths;

import java.util.Scanner;

/*
Problem:
    https://takeuforward.org/data-structure/find-gcd-of-two-numbers/
 */
public class GCDOrHCF {

    /*
    Approach:
        The GCD of two numbers is the largest number that divides both of them without leaving a remainder.
        We iterate through all numbers from 1 up to the minimum of the two input numbers, checking if each number is a common factor of both input numbers.

        If a number is a common factor, we update our gcd variable to that number.
        This process continues until we have iterated through all possible common factors.
        Finally, we return the gcd variable, which will hold the greatest common divisor of the two input numbers.

        Time complexity: O(n)
        Space complexity; O(1)

     */
    public static int gcdBruteForce(int m, int n){
        int result = 1;
        for(int i = 1; i <= Math.min(m, n); i++){
            if( m % i == 0 && n % i == 0){
                result = i;
            }
        }

        return result;
    }

    /*
    Approach:
        The Euclidean Algorithm is a method for finding the greatest common divisor of two numbers.
        It operates on the principle that the GCD of two numbers remains the same even if the smaller number is subtracted from the larger number.

        To find the GCD of n1 and n2 where n1 > n2:

            Repeatedly subtract the smaller number from the larger number until one of them becomes 0.
            Once one of them becomes 0, the other number is the GCD of the original numbers.


        Now instead of subtraction, if we divide the larger number, the algorithm stops when we find the remainder 0.

        Time complexity: O(n)
     */
    public static int gcdOptimalSolution(int m, int n){
        // Continue to  loop as long as both m and n are greater than 0
        while( m > 0 && n > 0){

            // subtraction and module operation are almost similar, with modulo we can get result with less number of loops.
            if( m > n){
                m = m % n;
            } else{
                n = n % m;
            }
        }
        // If m == 0 then n will the GCD, debug the program with 14 and 21
        if( m == 0)
            return  n;

        // else m will be the GCD
        return m;
            }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the value of m");
        int m = in.nextInt();
        System.out.println("Enter the value of n");
        int n = in.nextInt();

        System.out.printf("GCD or HCF of: %d and %d is %d\n", m, n, gcdBruteForce(m, n) );
        System.out.printf("GCD or HCF of: %d and %d is %d\n", m, n, gcdOptimalSolution(m, n) );
    }
}
