package basicmaths;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/print-all-divisors-of-a-given-number/
    https://www.geeksforgeeks.org/problems/sum-of-all-divisors-from-1-to-n4738/1
 */
public class PrintAllDivisors {

    /*
    Approach:
        A brute force approach would be to iterate from 1 to n checking each value if it divides n without leaving a remainder.
        For each divisor found, store it in an array list
        return the array list after loop.

        Time Complexity: O(n)
        Space complexity: O(n)
     */
    public static List<Integer> sumOfAllDivisorsBruteForce(int n) {
        List<Integer> divisors = new ArrayList<>();

        for(int i = 1; i <= n; i++){
            if(n % i == 0){
                divisors.add(i);
            }
        }
        return divisors;
    }

    /*
    Approach:
        We can optimise the previous approach by using the property that for any non-negative integer n, if d is a divisor of n then n/d is also a divisor of n.
        This property is symmetric about the square root of n by traversing just the first half we can avoid redundant iteration
        and computations improving the efficiency of the algorithm.

        It means, consider the below example, you will understand else goto https://takeuforward.org/data-structure/print-all-divisors-of-a-given-number/
        consider number 36, its divisors are [1, 2, 3, 4, 6, 9, 12, 18, 36]

        we can compute 36, using above divisors
        1 * 36 = 36
        2 * 18 = 36
        3 * 12 = 36
        4 * 09 = 36
        till now consider the left-hand side 1, 2,3,4 - these are the divisors and remaining 36, 18, 12, 09 are numbers which helps to yield result 36
        Square root of 36 is 6

        Now let's compute 36 using divisors greater than 36's square root.
        9 * 04 = 36
        12 * 3 = 36
        18 * 2 = 36
        36 * 1 = 36

        If you observe left-hand side now, divisors are 9, 12, 18, 36 and numbers 1,2,3,4 helps to yield result 36

        you can clearly notice there is a symmetry
        left-hand side two operands just got exchanged in second half after square root
        So we can run our loop till the square root
        add both first and second operand to result list.
        return the result list

        Time complexity: O(sqrt(n))
        Space complexity: O(sqrt(n)) - precisely O( 2 * sqrt(n)) - only those many numbers will be present in the divisor list.


     */
    public static List<Integer> sumOfAllDivisorsOptimal(int n) {
        // Here I'm maintaining two array list because, if i push everything to same array list then elements will not be in ascending or one order.
        // divisor array list will have divisors till the square root.
        List<Integer> divisors = new ArrayList<>();
        // counterPartDivisors array list will store divisors greater than the square root of n.
        List<Integer> counterPartDivisors = new ArrayList<>();

        int k = (int) Math.sqrt(n);
        for(int i = 1; i <= k; i++){

            if( n % i == 0){
                divisors.add(i);

                // as per the symmetry property explained above, if `i` is divisor then n/i will also be divisor
                // avoid adding duplicate divisors that's why check i != n/i
                if( i != n/i){
                    counterPartDivisors.add(n / i);
                }
            }
        }
        // Now counterPartDivisors will have divisors greater than square root of n is descending order
        // iterate from last and add those elements to divisors list and finally return it.
        for (int i = counterPartDivisors.size() -1; i >= 0; i--) {
            divisors.add(counterPartDivisors.get(i));
        }
        return  divisors;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number");
        int n = in.nextInt();

        System.out.println("All Divisors of n: " + sumOfAllDivisorsBruteForce(n));
        System.out.println("All Divisors of n: " + sumOfAllDivisorsOptimal(n));
    }
}
