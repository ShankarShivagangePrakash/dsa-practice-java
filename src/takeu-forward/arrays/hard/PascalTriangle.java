package arrays.hard;

import java.util.List;
import java.util.ArrayList;
/*
Problem:
    https://takeuforward.org/data-structure/program-to-generate-pascals-triangle/
    https://leetcode.com/problems/pascals-triangle/description/

   Variation 1: Given row number r and column number c. Print the element at position (r, c) in Pascal’s triangle.
   Variation 2: Given the row number n. Print the n-th row of Pascal’s triangle.
   Variation 3: Given the number of rows n. Print the first n rows of Pascal’s triangle.
 */
public class PascalTriangle {

    /*
    Approach:
        Brute force approach is to generate the entire pascal triangle and then get the specified element we want
        But generating entire pascal triangle is not easy and time complexity and space complexity will be more.
     */
    public static int variation1GetElementInPascalTriangleBruteForce(int r, int c){
        return 1;
    }

    /*
    Approach:
        We have an easier formula to find out the element i.e. r-1Cc-1.
        Let’s try to analyse the formula to find the value of the combination assuming r-1 as n and c-1 as r:
        nCr = n! / (r! * (n-r)!)

        So we calculate the combination using above formula.

        Calculating nCr

        We can separately calculate n!, r!, (n-r)! and using their values we can calculate nCr.
        This is an extremely naive way to calculate. The time complexity will be O(n)+O(r)+O(n-r).
        This is again not completely good.
     */
    public static long variation1GetElementInPascalTriangleBetterApproach(int row, int column){

        // row is equivalent to n
        // column is equivalent to r.
        long numerator = 1;
        long d1 = 1, d2 =1 ;
        for(int i = 1; i < row; i++){
            numerator *= i;
        }
        for( int i = 1; i < column; i++){
            d1 *= i;
        }
        for(int i = 1; i <= (row - column); i++){
            d2 *= i;
        }

        return (numerator) / (d1 * d2);
    }

    /*
    Approach:
        It is an improvisation of the better approach
        while calculating nCr, instead of calculating everything means n!, r!, (n-r)!
        we can calculate, it as
            Numerator we will consider only r terms starting from n, means say r = 3 and n = 10
            then numerator will be 10 * 9 * 8

            denominator will be r terms starting from 1, which is nothing but r!
            also, you divide every nth and rth term you calculate in every iteration of the loop
            means, when 10 is computed, r will be 1, do 10/1
            then that res * 9 = 10 * 9 = 90 and denominator will be 2 this time do 90/2 = 45
            then when r = 3, n will 8, numerator will be 45 * 8/3 = = 120

            this will be the answer

        The steps are as follows:

        First, we will consider r-1 as n and c-1 as r.
        After that, we will simply calculate the value of the combination using a loop.
        The loop will run from 0 to r. And in each iteration, we will multiply (n-i) with the result and divide the result by (i+1).
        Finally, the calculated value of the combination will be our answer.

        Time Complexity: O(r)
        Space Complexity: O(1)

     */
    public static int variation1GetElementInPascalTriangleOptimalApproach(int row, int column){
        // row is equivalent to n
        // column is equivalent to r.

        return nCr(row - 1, column - 1);
    }

    public static int nCr(int n, int r){

        int res = 1;
        for(int i = 0; i < r; i++){
            res = res * (n-i);
            res = res / (i+1);
        }
        return res;
    }

    /*
    Approach:
        we know that n row will have n elements
        we run a loop from 1 to n
        in each iteration we apply nCr formula, what we have applied in variation1GetElementInPascalTriangleOptimalApproach()
        we add the result to the list.
        return the list

        Time Complexity: O(n * r)
        Space Complexity: O(1)
            the list we have created is to return the result, it's not used to solve the problem.
     */
    public static List<Integer> variation2GeneratePascalRowBruteForce(int n){
        List<Integer> ans = new ArrayList<>();
        // in pascal triangle i should start from 1 because, we invoke nCr()
        // with n = n-1 and r = r-1. So we have start r with 1. `i` corresponds to r.
        for(int i = 1; i <= n; i++){
            ans.add(nCr(n - 1, i - 1));
        }
        return ans;
    }

    /*
    Approach:
        Check notes.

        The steps are as follows:

        First, we will print the 1st element i.e. 1 manually.
        After that, we will use a loop(say i) that runs from 1 to n-1. It will print the rest of the elements.
        Inside the loop, we will use the above-said formula to print the element. We will multiply the previous answer by (n-i) and then divide it by i itself.
        Thus, the entire row will be printed.

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static List<Integer> variation2GeneratePascalRowOptimal(int n){

        List<Integer> result = new ArrayList<>();

        int ans = 1;
        result.add(ans);

        for(int i = 1; i < n; i++){
            // (n - i) will give the only element which needs to be multiplied to numerator
            ans = ans * (n - i);
            // `i` is the only element which needs to be multiplied with denominator.
            ans = ans / i;

            result.add(ans);
        }
        return result;
    }

    /*
    Approach:
        reuse variation2GeneratePascalRowOptimal() which will generate pascal rows efficiently.
        invoke that method for every row.
        That's it

        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    public static List<List<Integer>> variation3GeneratePascalTriangle(int n){

        List<List<Integer>> result = new ArrayList<>();

        for(int i = 1; i <= n; i++){
            // invoke variation2GeneratePascalRowOptimal(i) which will generate pascal row.
            result.add(variation2GeneratePascalRowOptimal(i));
        }
        return result;
    }

    public static void main(String[] args){

        // Variation 1:
        int row = 5,  column = 3;
        long element = variation1GetElementInPascalTriangleBetterApproach(row, column);
        int element2 = variation1GetElementInPascalTriangleOptimalApproach(row, column);
        System.out.println("Pascal Triangle Element using Brute Force Approach: " + element);
        System.out.println("Pascal Triangle Element using optimal Approach: " + element2);

        // Variation 2:
        int n = 6;
        List<Integer> pascalRow  = variation2GeneratePascalRowBruteForce(n);
        List<Integer> pascalRow2  = variation2GeneratePascalRowOptimal(n);
        System.out.println("Pascal row using Brute force approach");
        System.out.println(pascalRow);
        System.out.println("Pascal row using optimal approach");
        System.out.println(pascalRow2);

        // Variation 3:
        List<List<Integer>> pascalTriangle = variation3GeneratePascalTriangle(n);
        System.out.println("Pascal Triangle using optimal approach");
        System.out.println(pascalTriangle);



    }
}
