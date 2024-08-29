package arrays.binarysearch;
/*
Problem:
    https://takeuforward.org/data-structure/minimum-in-rotated-sorted-array/
    https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMinimumIRotatedSortedArray {

    /*
    Approach:
        The steps are as follows:

        We will declare the ‘res’ variable and initialize it with the largest value possible.
        With that, as usual, we will declare 2 pointers i.e. low and high.

        Place the 2 pointers i.e. low and high:
        Initially, we will place the pointers like this: low will point to the first index and high will point to the last index.

        Calculate the ‘mid’: Now, inside a loop, we will calculate the value of ‘mid’ using the following formula:
        mid = (low+high) // 2 ( ‘//’ refers to integer division)

        Identify the sorted half, and after picking the leftmost element, eliminate that half.

        If arr[low] <= arr[mid]: This condition ensures that the left part is sorted.
        So, we will pick the leftmost element i.e. arr[low].
        Now, we will compare it with 'res' and update 'res' with the smaller value (i.e., min(res, arr[low])).
        Now, we will eliminate this left half(i.e. low = mid+1).

        Otherwise, if the right half is sorted:
        This condition ensures that the right half is sorted.
        So, we will pick the leftmost element i.e. arr[mid].
        Now, we will compare it with 'res' and update 'res' with the smaller value (i.e., min(res, arr[mid])).
        Now, we will eliminate this right half(i.e. high = mid-1).

        This process will be inside a loop and the loop will continue until low crosses high.

        Finally, we will return the ‘res’ variable that stores the minimum element.

        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
    public static int findMinOptimal1(int[] arr){
        int low = 0, high = arr.length - 1;

        // Initialize res variable to some maximum value.
        int res = Integer.MAX_VALUE;

        while(low <= high){
            int mid = (low + high)/2;

            // check is left sub array sorted.
            if(arr[low] <= arr[mid]){
                // if left sub array is sorted, then arr[low] will be the lowest in this sub array
                // so set res = min(res, arr[low])
                res = Math.min(res, arr[low]);

                // now, no element in this range can be smaller than arr[low], so move to right sub array and check
                low = mid + 1;
            }
            // else means right sub array is sorted
            else{
                // in sorted right sub array, arr[mid] must be the smallest,
                // so set res as res = min(res, arr[mid])
                res = Math.min(res, arr[mid]);

                // now, no element in this range can be smaller than arr[mid], so move to left sub array and check
                high = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};
        System.out.printf("The minimum element using optimal approach1 is: %d\n", findMinOptimal1(arr));
    }
}
