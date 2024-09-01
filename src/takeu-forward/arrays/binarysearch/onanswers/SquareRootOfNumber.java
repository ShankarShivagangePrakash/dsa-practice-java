package arrays.binarysearch.onanswers;

/*
Problem:
    https://takeuforward.org/binary-search/finding-sqrt-of-a-number-using-binary-search/
    https://www.geeksforgeeks.org/problems/square-root/0?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=square-root
 */
public class SquareRootOfNumber {

    /*
    Approach:
        Brute Force:
            we can iterate from 1 to n (improvised 1 to n/2) to find i * i = n
            if so return that number as square root.
            But this takes O(n)

        Optimal Approach:
            Use Binary search:
                we know that the square root of a number exists from 1 to n
                how we search for an element in sorted array using binary search, the same approach can be used here as well.

            We initialize low = 1, high = n
                we calculate mid, then we perform temp = mid* mid
                    - if temp = n, return mid; // n is perfect square we found the square root.
                    - else if temp < n // we store this in `ans` variable as
                        ans = max(ans, mid); // because if n is not perfect square,
                        then we have to return floor of the square root of n. So this logic helps to handle not perfect squares.
                        and, move further in the number space and check is there any other number which can be the square root of n
                        low = mid + 1;
                    - else,
                           else case means mid * mid > n
                           in this case we have to reduce `mid` value, so we do high = mid - 1;

           Time Complexity: O(log n)
           Space Complexity: O(1)
     */
    public static long squareRoot(long n){
        long ans = Integer.MIN_VALUE;

        long low = 1, high = n;

        while(low <= high){
            long mid = (low + high)/2;
            long temp = mid * mid;

            if(temp == n)
                return mid;
            else if(temp < n) {
                ans = Math.max(ans, mid);
                low = mid + 1;
            }
            else
                high = mid - 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        long n = 25;
        System.out.printf("Square root of number %d is %d\n", n, squareRoot(n));
    }
}
