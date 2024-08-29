package arrays.binarysearch;

/*
Problem:
    https://takeuforward.org/data-structure/search-element-in-a-rotated-sorted-array/
    https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 */
public class SearchInRotateSortedArray1 {

    /*

    Note: sorted array will not have duplicates

    Approach:
        Key Observation: Though the array is rotated, we can clearly notice that for every index, one of the 2 halves will always be sorted.

        So, to efficiently search for a target value using this observation, we will follow a simple two-step process.

        First, we identify the sorted half of the array.
        Once found, we determine if the target is located within this sorted half.
        If not, we eliminate that half from further consideration.
        Conversely, if the target does exist in the sorted half, we eliminate the other half.

        The steps are as follows:

        Place the 2 pointers i.e. low and high: Initially, we will place the pointers like this: low will point to the first index, and high will point to the last index.

        Calculate the ‘mid’: Now, inside a loop, we will calculate the value of ‘mid’ using the following formula:
        mid = (low+high) // 2 ( ‘//’ refers to integer division)

        Check if arr[mid] == target: If it is, return the index mid.

        Identify the sorted half, check where the target is located, and then eliminate one half accordingly:

        If arr[low] <= arr[mid]: This condition ensures that the left part is sorted.
            If arr[low] <= target && target <= arr[mid]: It signifies that the target is in this sorted half. So, we will eliminate the right half (high = mid-1).
        Otherwise, the target does not exist in the sorted half. So, we will eliminate this left half by doing low = mid+1.

        Otherwise, if the right half is sorted:
            If arr[mid] <= target && target <= arr[high]: It signifies that the target is in this sorted right half. So, we will eliminate the left half (low = mid+1).
        Otherwise, the target does not exist in this sorted half. So, we will eliminate this right half by doing high = mid-1.

        Once, the ‘mid’ points to the target, the index will be returned.

        This process will be inside a loop and the loop will continue until low crosses high. If no index is found, we will return -1.

        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
    public static int search(int[] arr, int n, int target){
        int low = 0, high = n-1;

        while(low <= high){
            int mid = (low + high)/2;

            if(arr[mid] == target){
                return mid;
            }
            // else if left array is sorted
            else if(arr[low] <= arr[mid]){
                // now check is the target present in this range
                // if so then search in this left sub array, so move left
                if(arr[low] <= target && target < arr[mid]){
                    high = mid -1;
                }
                // target not present in left sub array, so move right.
                else{
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
                // target not present in right sub array, move left.
                else{
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] arr = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        int n = 9, target = 1;
        System.out.printf("Target element %d is present at %d\n", target, search(arr, n, target));
    }
}
