package arrays.binarysearch.onanswers;

/*
Problem:
    https://takeuforward.org/data-structure/nth-root-of-a-number-using-binary-search/
    https://www.geeksforgeeks.org/problems/find-nth-root-of-m5843/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=find-nth-root-of-m
 */
public class NthRootOfNumber {

    /*
    Problem Statement:
        input will have two numbers n amd m
            where m is the number and n is the root
            For ex: if n = 2, m = 4 means, it is square root of (4) = 2
                    if n = 3, m = 9 means, it is cube root of (9) = 3
                    if n = 4, m = 4096 means, it is 4th root of 4096 = 4
        Our job is to find whether a number exists such that nth of root a number (m) exists, if so return it else return -1

     Approach:
        we want to find out number which is nth root of (m)
        Let's assume such number is `x`
        this can be rewritten as x^n = m
        so we will binary search technique to find that x.

        Initialize low = 1, high = m
        calculate mid,
        find mid^n = temp
            - if temp == n; return mid;// because mid is the nth root of m
            - if temp < n; move further in the array; low = mid+1
            - else means temp > n; move back in the array; high = mid -1;

    Time Complexity: O(log n)
    Space Complexity: O(1)
     */
    public static int nthRoot(int n, int m)
    {
        int res = -1;

        // we have to find nth root of number m

        int low = 1, high = m;

        while(low <= high){
            int mid = (low + high)/2;

            // if interviewer says not to use inbuilt power method, we can compute it manually
            // we invoke findPow method.
            int temp = (int) Math.pow(mid, n);
            //int temp = findPow(mid, n);

            if(temp == m){
                return mid;
            }
            else if(temp < m){
                low = mid+1;
            } else{
                high = mid-1;
            }
        }
        return res;
    }

    public static int findPow(int base, int exponent){
        double res = 1;
        for(int i = 1; i <= exponent; i++){
            res = res * base;
        }
        return (int)res;
    }

    public static void main(String[] args) {
        int n = 6, m = 4096;
        System.out.printf("Square root of number %d is %d\n", n, nthRoot(n, m));
    }
}
