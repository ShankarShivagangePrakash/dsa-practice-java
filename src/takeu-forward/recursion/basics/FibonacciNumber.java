package recursion.basics;

/*
Problem:
    https://leetcode.com/problems/fibonacci-number/description/
 */
public class FibonacciNumber {

    /*
    Approach:
        fibannoci(n)  = fib(n-1) + fib(n-2)

        solved using functional recursion

        Time Complexity: O(2^n)
            To calculate fib(n), the recursive calls create a tree-like structure, and the number of nodes in the recursion tree grows exponentially.
            In fact, the number of calls to the fib function is approximately proportional to the Fibonacci number itself, which grows exponentially as O(2^n)
        Space Complexity: O(n)
            due to the maximum depth of recursion.
     */
    public int fib(int n){
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        return fib(n-1) + fib(n-2);
    }
}
