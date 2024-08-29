package arrays.binarysearch;

/*
Problem:
    https://takeuforward.org/arrays/search-insert-position/
    https://leetcode.com/problems/search-insert-position/description/
 */
public class SearchInsertPosition {

    /*
    Approach:
        We will solve this problem using the lower-bound algorithm which is basically a modified version of the classic Binary Search algorithm.

        Now, if the element is not present, we have to find the nearest greater number of the target number.
        So, basically, we are trying to find an element arr[ind] >= x and hence the lower bound of the target number i.e. x.

        The lower bound algorithm returns the first occurrence of the target number if the number is present and otherwise,
        it returns the nearest greater element of the target number.

        // same lower bound algorithm, refer to LowerBound.java

        Time Complexity: O(log n)
        Apace complexity: O(1)
     */
    public static int searchInsertPosition(int[] arr, int target){
        int low = 0, high = arr.length-1;
        int ans = arr.length;

        while(low <= high){
            int mid = (low + high)/2;

            // target is greater than mid, move to right and search
            if(arr[mid] < target){
                low = mid + 1;
            }
            // it can be equal or greater than target, you have to find minimum of all elements in array
            // such that, it is greater than or equal to target - lower bound algorithm.
            // so you have to move to left sub array, if you have any doubts, refer LowerBound.java
            else{
                ans = Math.min(ans, mid);
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 7};
        int target = 6;

        System.out.printf("The position at %d can be inserted is %d\n", target, searchInsertPosition(arr, target));
    }
}
