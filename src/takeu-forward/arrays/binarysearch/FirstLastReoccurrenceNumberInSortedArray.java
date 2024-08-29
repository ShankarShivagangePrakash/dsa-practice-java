package arrays.binarysearch;

/*
Problem:
    https://takeuforward.org/data-structure/last-occurrence-in-a-sorted-array/
    https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 */
public class FirstLastReoccurrenceNumberInSortedArray {

    /*
    Problem statement:
        Given a sorted array and a target element,
            If that element is present in the array, we have to return the first index and last index at which it is present.
        If not present, return [-1. -1]

    Approach:
        we slightly modify floor and Ceil, which is again based on LowerBound.java

        We need to find the first index which is equal to target - floor
        we need to find the last index which is equal to target - ceil

        we initialize low, high as we do in binary search
        we initialize floor to arr.length, because we need to find the minimum index at which target is present
        similarly, we will initialize ceil to -1, because we need to find the maximum index at which target is present

        first while loop, to find the ceil
            same logic of Lower Bound, slight alteration
            if the array[mid] == target
                that may be the ceil value
                so ceil = max(ceil, mid)

                in this case, the same element might be present on the right side of the array, so we cannot stop,
                and we need to move to right sub array
                so low = mid + 1

            else if target < arr[mid]
                move to left sub array, high = mid - 1;
            else
                this else means, target > arr[mid] - move right; low = mid + 1;

         when you come out of the loop,
            if target is present in the array, ceil variable will have the index of last occurrence of it.

        Now, again reinitialize low and high
        now second while loop to find the floor
            same logic of Lower Bound, slight alteration
            if the array[mid] == target
                that may be the floor value
                so floor = min(floor, mid)

                in this case, the same element might be present on the left side of the array, so we cannot stop,
                and we need to move to left sub array
                so high = mid - 1;

            else if target < arr[mid]
                move to left sub array, high = mid - 1;
            else
                this else means, target > arr[mid] - move right; low = mid + 1;

        same logic,
        when you come out of the loop, if element is present
            then floor will have the index of first occurance of it
        If the target is not present, then floor will not be updated and it will still have value as arr.length

        but as per problem statement, if it is not present, we have to return -1
        so add a condition at the end,
        if floor == arr.length, update it to -1

        finally create array from these two variables and return

        Time Complexity: O( log n)
        Space Complexity: O(1)
     */
    public static int[] findFirstAndLastOccurrenceOfGivenNumber(int[] arr, int target){
        int floor = arr.length;
        int ceil = -1;

        int low = 0, high = arr.length - 1;

        // find ceil
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] == target){
                ceil = Math.max(ceil, mid);
                low = mid+1;
            } else if(target < arr[mid]){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        low = 0; high = arr.length-1;
        // find floor
        while(low <= high){
            int mid = (low + high)/2;

            if(arr[mid] == target){
                floor = Math.min(floor, mid);
                high = mid - 1;
            } else if(target < arr[mid]){
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }
        // If target is not present, then we have to reset floor to -1
        if(floor == arr.length){
            floor = -1;
        }

        return new int[]{floor, ceil};
    }

    public static void main(String args[]) {
        int target = 13;
        int[] arr = {3,4,13,13,13,20,40};

        int[] res = findFirstAndLastOccurrenceOfGivenNumber(arr,target);

        System.out.printf("Floor = %d and Ceil = %d for target %d\n", res[0], res[1], target);
    }
}
