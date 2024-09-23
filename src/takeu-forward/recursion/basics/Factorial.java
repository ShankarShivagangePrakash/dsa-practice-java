package recursion.basics;

/*
Problem:
    https://www.geeksforgeeks.org/problems/factorial5739/1
 */
public class Factorial {

    /*
    Approach:
        We cannot solve for factorial(n), till we know factorial(n-1)
        similarly, we cannot find factorial(n-1) till we know factorial(n-2)
        ....
        so we have to keep braking the problem to smallest set, then find the result for smallest set
        then recursively solve bigger sets

        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    public static long factorial(int N){
        if(N <= 1)
            return 1;
        return N * factorial(N-1);
    }
}
