package arrays.binarysearch;

/*
Problem:
    https://takeuforward.org/data-structure/binary-search-explained/
    https://leetcode.com/problems/binary-search/description/
 */
public class BinarySearchOnArray {

    /*
    Approach:
        Initialize two variables
            low = 0, high = arr.length-1

        Step 1: Divide the search space into 2 halves:
        In order to divide the search space, we need to find the middle point of it. So, we will take a ‘mid’ pointer and do the following:
        mid = (low+high) // 2 ( ‘//’ refers to integer division)

        Step 2: Compare the middle element with the target:

        In this step, we can observe 3 different cases:

        If arr[mid] == target: We have found the target. From this step, we can return the index of the target possibly.
        If target > arr[mid]: This case signifies our target is located on the right half of the array. So, the next search space will be the right half.
        If target < arr[mid]: This case signifies our target is located on the left half of the array. So, the next search space will be the left half.

        Step 3: Trim down the search space:
        Based on the probable location of the target we will trim down the search space.
        If the target occurs on the left, we should set the high pointer to mid-1.
        Thus, the left half will be the next search space.
        Similarly, if the target occurs on the right, we should set the low pointer to mid+1.
        Thus, the right half will be the next search space.

        Time Complexity: O(n log n)
        Space Complexity: O(1)
     */
    public static int binarySearchIterativeApproach(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while(low <= high){
            int mid = (low + high)/2;

            if( target == arr[mid])
                return mid;
            else if(target < arr[mid]){
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }
        return -1;
    }

    /*
    Approach:
        The steps are as follows:

        pass low = 0 and high as arr.length-1 to the recursive function initially.

        Step 1: Divide the search space into 2 halves:

        In order to divide the search space, we need to find the middle point of it. So, we will take a ‘mid’ pointer and do the following:
        mid = (low+high) // 2 ( ‘//’ refers to integer division)

        Step 2: Compare the middle element with the target and trim down the search space:

        In this step, we can observe 3 different cases:

        If arr[mid] == target: We have found the target. From this step, we can return the index of the target, and the recursion will end.
        If target > arr[mid]: This case signifies our target is located on the right half of the array. So, the next recursion call will be binarySearch(nums, mid+1, high).
        If target < arr[mid]: This case signifies our target is located on the left half of the array. So, the next recursion call will be binarySearch(nums, low, mid-1).

        Base case: The base case of the recursion will be low > high. If (low > high), the search space becomes invalid which means the target is not present in the array.

        Time Complexity: O(n log n)
        Space Complexity: O(1)

        Note: in space complexity, even recursion stack space will be considered, but we don't count that for space complexity while explaining.
     */
    public static int binarySearchRecursive(int[] arr, int low, int high, int target){
        // base case
        if(low > high)
            return -1;

        int mid = (low + high)/2;

        if(arr[mid] == target)
            return mid;
        else if(target < arr[mid])
            return binarySearchRecursive(arr, low, mid-1, target);
        else
            return binarySearchRecursive(arr, mid+1, high, target);
    }

    public static int binarySearch(int[] arr, int target){
        return binarySearchRecursive(arr, 0, arr.length-1, target);
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 6, 7, 9, 12, 16, 17};
        int target = 6;

        int ind = binarySearchIterativeApproach(arr, target);
        System.out.printf("Using Binary Search Iterative approach, element %d found at position %d\n", target, ind);

        int ind2 = binarySearch(arr, target);
        System.out.printf("Using Binary Search recursive approach, element %d found at position %d\n", target, ind2);
    }
}
