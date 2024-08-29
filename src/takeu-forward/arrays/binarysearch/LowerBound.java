package arrays.binarysearch;

/*
Problem:
    https://takeuforward.org/arrays/implement-lower-bound-bs-2/
 */
public class LowerBound {

    /*
    Approach:
        What is Lower Bound?
        The lower bound algorithm finds the first or the smallest index in a sorted array where the value at that index is greater than or equal to a given key i.e. x.
        The lower bound is the smallest index, ind, where arr[ind] >= x. But if any such index is not found, the lower bound algorithm returns n i.e. size of the given array.

        we slightly tweak the binary search algorithm to solve.
        In binary search, we check for three conditions
            arr[mid] == target - return mid
            target < arr[mid] - search in right half of the array
            target > arr[mid] - search in left half of the array

        But in this problem, we need not check for all these three conditions.
        we want to find the element, which is greater than or equal to target
        but that has to be the smallest among all elements which is greater than or equal to target, we have return such indes

        so, we can initialize a variable `ans` = arr.length;

        now, we keep searching for target using binary search.
            if we find an element which is greater than or equal to target
                we calculate the minimum of `ans` and mid,
                and we move the high to mid-1
                means search on left sub array

            if the arr[mid] < target
                then we need to move further in the array and search
                so set low = mid+1
                don't do anything for `ans`

        Time Complexity: O(log n)
        Apace complexity: O(1)
     */
    public static int lowerBound(int[] arr, int n, int target){
        int low = 0, high = n-1;
        int ans = n;

        // recursion base case
        if(low > high)
            return -1;

        while(low <= high){
            int mid = (low + high)/2;
            // the element you have encountered in the array is smaller than the target, just move to right sub array.
            if(arr[mid] < target) {
                low = mid + 1;
            }
            // else case refers to arr[mid] >= target,
            // whether the current element is greater or equal, set `ans` as minimum (ans, currentIndex)
            // since we have already found an element which is greater or equal to target.
            // another element which is greater or equal to target can be found in its left sub array, so move to left sub array.
            else{
                ans = Math.min(ans, mid);
                high = mid-1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 15, 19};;
        int  n = 5, target = 8;

        System.out.printf("Lower bound of %d is %d\n", target, lowerBound(arr, n, target));
    }
}
