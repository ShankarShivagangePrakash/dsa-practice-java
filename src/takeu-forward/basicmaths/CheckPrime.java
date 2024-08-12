package basicmaths;

import java.util.Scanner;

/*
Problem:
    https://takeuforward.org/data-structure/check-if-a-number-is-prime-or-not/
    https://www.geeksforgeeks.org/problems/minimum-number-of-jumps-1587115620/1
 */
public class CheckPrime {

    /*
    Approach:
        Refer to Print all divisors program, same logic can be applied here.
        Algorithm

        Step 1: Initialise a counter variable cnt to count the number of factors to 0.

        Step 2: Begin a loop from 1 to the square root of n. This loop iterates through possible factors of n. For each value of i within the loop:

        Check if n is divisible by i without any remainder.
        If n is divisible by i, it means i is a factor of n, so increment the counter variable cnt by 1.
        Check if the reciprocal factor of i.e. n/i is not equal to `i`. If they are not equal, it means there is a distinct factor so increment cnt by 1 again.
        Step 3: After the loop, cnt will contain the total numbers of factors of n.

        Step 4: Check if the value of cnt is exactly 2, it means that n has exactly two distinct factors (1 and itself), indicating that it is a prime number.

        If the number of factors is greater than 2 then it is a composite number, return false.

        Time complexity: O(log n)
        Space complexity: O(1)
     */
    public static boolean isPrime(int n) {

        int count = 0;
        for(int i = 1; i <= (int) Math.sqrt(n); i++ ){
            if(n % i == 0){
                count++;

                if( i!= n/i)
                    count++;
            }
        }
        // for prime numbers there will be exactly two factors, 1 and the number itself. If exactly two factors means prime else not.
        return count == 2;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number");
        int n = in.nextInt();

        System.out.printf("Is %d prime? %b\n", n, isPrime(n));
    }
}
