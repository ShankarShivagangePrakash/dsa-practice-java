package arrays.binarysearch.onanswers;

/*
Problem:
    https://takeuforward.org/arrays/find-the-smallest-divisor-given-a-threshold/
    https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/
 */
public class SmallestDivisorWithThreshold {

    /*
    Problem Statement:
        You are given an array of integers 'arr' and an integer i.e. a threshold value. Your task is to find the smallest positive integer,
        such that upon dividing all the elements of the given array by it, the sum of the division's result is less than or equal to the given threshold value.
        note, when you divide array element from the positive integer - you have to take ceil of the division result and then sum.

        such total sum should be less than threshold, in order to say it is a divisor
        find minimum divisor of such kind.

    Approach:

        Negative use case:
            the divisor we need to find has to divide each element in the array
            (doesn't mean it has to divide without leaving reminder - but we need to perform division on every element)

            so, best case we find a divisor which divides every element in the array with 1 unit - so sum will be `n`
            n - means number of elements in the array.

            so if threshold is less than n, we cannot do anything return -1

       Positive scenario:
            divisor can range from 1 to maximum element in the array.

            so we do binary search from 1 to max (array)
                we calculate mid
                for mid = we will find sumOfDivisions
                    if the value yield by this method for divisor chosen `mid` is less than or equal to threshold
                        we update res = mid;
                        but there can be other smaller divisors too, which satisfies the condition,
                        so we move to left sub array
                    else
                        we move to right sub array.

      Time Complexity: O(n * log(max element in the array))
      Space Complexity: O(1)
     */
    public static int smallestDivisor(int[] arr, int threshold){
        int res = Integer.MAX_VALUE;

        /* edge case, if threshold is less than the array length, we cannot do anything return -1
         because to divide every number with any divisor we need at least 1 unit, to divide all numbers in the array by divisor
         we need at least n units. if not return -1.*/
        if(threshold < arr.length)
            return -1;

        int low = 1, high = max(arr);

        while(low <= high){
            int mid = (low + high)/2;

            if(sumOfDivisions(arr, mid) <= threshold){
                res = Math.min(res, mid);
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }
        return res;
    }

    public static int max(int[] arr){
        int max = -1;
        for(int i : arr){
            if(i > max)
                max = i;
        }
        return max;
    }

    // O(n)
    public static int sumOfDivisions(int[] arr, int divisor){
        double sum = 0;

        for(int i = 0; i < arr.length; i++){
            sum = sum + Math.ceil((double)arr[i] / (double)divisor);
        }
        return (int)sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int threshold = 8;
        System.out.printf("The minimum divisor for array with threshold %d is: %d\n", threshold, smallestDivisor(arr, threshold));
    }
}
