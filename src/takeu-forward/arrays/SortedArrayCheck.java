package arrays;
/*
Problem:
    https://takeuforward.org/data-structure/check-if-an-array-is-sorted/
    https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/
 */
public class SortedArrayCheck {

    /*
    Approach:
        We know, in sorted array,
        current element will be less than or equal to next element,
        If that condition is broken, then it is not sorted array.

        We start from index 0 and run the loop till last but one element, to avoid indexOutOfBound exception.

        Time complexity: O(n)
        Space complexity: O(1)
     */
    public static boolean isSorted(int n, int[] arr){
        boolean isSorted = true;
        for(int i = 0; i < arr.length -1; i++){
            if(arr[i] > arr[i+1]){
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 5, 4, 3};

        System.out.printf("Is array sorted? %b\n", isSorted(5, arr));
    }
}
