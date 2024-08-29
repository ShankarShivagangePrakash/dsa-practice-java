package arrays.binarysearch;

/*
Approach:
    https://takeuforward.org/arrays/search-element-in-rotated-sorted-array-ii/
    https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
 */
public class SearchInRotatedSortedArray2 {

    /*
    Approach:
        Exactly same as SearchInRotateSortedArray1.java

        But we need to handle one edge case, since duplicates are allowed.
        This check was effective in the previous problem, where there were no duplicate numbers. However, in the current issue,
        the array may contain duplicates. Consequently, the previous approach will not work when arr[low] = arr[mid] = arr[high].

        How to handle the edge case arr[low] = arr[mid] = arr[high]:
            Check if arr[low] = arr[mid] = arr[high]:
            If this condition is satisfied, we will just increment the low pointer and decrement the high pointer by one step.
            We will not perform in the latter steps until this condition is no longer satisfied. So, we will continue to the next iteration from this step.

        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
    public static boolean search(int[] arr, int n, int target){
        int low = 0, high = n-1;

        while(low <= high){
            int mid = (low + high)/2;

            if(arr[mid] == target)
                return true;

            //handle edge case, if arr[low] = arr[mid] = arr[high]
            if(arr[low] == arr[mid] && arr[mid] == arr[high]){
                low++;
                high--;
            }
            // left array is sorted
            else if(arr[low] <= arr[mid]){
                // now check is the target present in this range
                // if so then search in this left sub array, so move left
                if(arr[low] <= target && target < arr[mid]){
                    high = mid -1;
                }
                else{
                    // target not present in left sub array, so move right.
                    low = mid + 1;
                }
            }
            // right sub array is sorted
            else{
                // check is target available in right sub array range
                // if so move to right sub array.
                if(arr[mid] < target && target <= arr[high]){
                    low = mid + 1;
                }
                else{
                    // target not present in right sub array, move left.
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1,0,1,1,1};
        int n = 9, target = 1;
        System.out.printf("Target element %d is present? %b\n", target, search(arr, n, target));
    }
}
