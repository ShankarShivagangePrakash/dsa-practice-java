package arrays.binarysearch;

/*
Problem:
    https://takeuforward.org/arrays/implement-upper-bound/
 */
public class UpperBound {

    /*
    What is Upper Bound?
        The upper bound algorithm finds the first or the smallest index in a sorted array where the value at that index is greater than the given key i.e. x.
        The upper bound is the smallest index, ind, where arr[ind] > x.

        But if any such index is not found, the upper bound algorithm returns n i.e. size of the given array.
        The main difference between the lower and upper bound is in the condition.
        For the lower bound the condition was arr[ind] >= x and here, in the case of the upper bound, it is arr[ind] > x.

        we slightly tweak the binary search algorithm to solve.
        In binary search, we check for three conditions
            arr[mid] == target - return mid
            target < arr[mid] - search in right half of the array
            target > arr[mid] - search in left half of the array

        But in this problem, we need not check for all these three conditions.
        we want to find the element, which is greater than target
        but that has to be the smallest among all elements which is greater than target, we have return such index

        so, we can initialize a variable `ans` = arr.length;

        now, we keep searching for target using binary search.
            if we find an element which is greater than target
                we calculate the minimum of `ans` and mid,
                and we move the high to mid-1
                means search on left sub array

            if the arr[mid] <= target
                then we need to move further in the array and search
                so set low = mid+1
                don't do anything for `ans`

        Time Complexity: O(log n)
        Apace complexity: O(1)
     */
    public static int upperBound(int[] arr, int n, int target){
        int low = 0, high = n-1;
        int ans = n;

        while(low <= high){
            int mid = (low + high) / 2;

            // if the current element is lesser than or equal to target, move to right
            if(arr[mid] <= target){
                low = mid + 1;
            }
            // else means we have found an element which is greater than target
            // so ans = min(ans, mid)
            // but there can be other element which is greater than target but lesser than current element
            // to find whether such element exists, we have to move left.
            else{
                ans = Math.min(ans, mid);
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 9, 15, 19};;
        int  n = 5, target = 9;

        System.out.printf("Upper bound of %d is %d\n", target, upperBound(arr, n, target));
    }
}
