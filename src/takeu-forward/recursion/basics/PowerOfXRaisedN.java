package recursion.basics;

import java.util.stream.LongStream;

/*
Problem:
    https://takeuforward.org/data-structure/implement-powxn-x-raised-to-the-power-n/
    https://leetcode.com/problems/powx-n/description/
 */
public class PowerOfXRaisedN {

    /*
    Approach:
        Simple straight forward
        multiply `x` n times

        Time Complexity: O(n)
        Space Complexity: O(1)

        Note: It might not pass all test cases
     */
    public static double powerBruteForceIterative(double x, int n){
        long nn = Math.abs(n);
        double ans = 1;

        for(int i = 1; i < nn; i++){
            ans = ans * x;
        }
        if(n < 0)
            ans = 1/ans;
        return ans;
    }

    /*
        time complexity: O(n)
        space complexity: O(1)
     */
    public static double powerBruteForceLambda(double x, int n){
        long nn = Math.abs(n);

        /* get a range of numbers from 1 to n
         since you have consider long data type stream but the return type you are expecting form reduce() is double
         so reduce() will expect both the parameters to be double, res is the initial value it will be set as double
         but i second parameter which is long but that has to be explicitly converted to double
         we are using mapToDouble() for it*/
        double ans = LongStream.range(1, nn+1)
                .mapToDouble(j -> x)
                .reduce(1, (res, i) -> res * i);

        if(n < 0)
            return 1/ans;
        return ans;
    }

    /*
    Approach:
        In optimal approach, we try to reduce the time complexity to logorithmic

        Read notes
        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
    public static double powerOptimalIterative(double x, int n){
        long nn = n;
        if(nn < 0)
            nn = -1 * nn;

        double ans = 1;
        while(nn > 0){
            if(nn % 2 == 0){
                x = x * x;
                nn = nn/2;
            }
            else{
                ans = ans * x;
                nn = nn -1;
            }
        }
        if(n < 0)
            ans = (double)1.0 / ans;

        return ans;
    }

    /*
    Approach:
        Iterative optimal approach converted to recursive

        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
    public static double powerOptimalRecursive(double x, int n){
        long nn = Math.abs((long) n);

        double ans = myPowerRecursive(x, nn);
        if(n < 0)
            ans =  1/ans;
        return ans;
    }

    public static double myPowerRecursive(double x, long nn){
        // base case
        if(nn == 0)
            return 1;

        if(nn % 2 == 0)
            return myPowerRecursive(x*x, nn/2);
        else
            return x * myPowerRecursive(x, nn-1);
    }


    public static void main(String[] args) {
        double x = 2.00;
        int n= -2;
        System.out.printf("Power of %f raised by %d is %.5f\n", x, n, powerOptimalIterative(x, n));
    }
}
