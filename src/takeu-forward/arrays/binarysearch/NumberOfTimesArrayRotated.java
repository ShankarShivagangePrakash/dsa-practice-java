package arrays.binarysearch;

/*
Problem:
    https://takeuforward.org/arrays/find-out-how-many-times-the-array-has-been-rotated/
    https://www.geeksforgeeks.org/problems/rotation4723/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=rotation
 */
public class NumberOfTimesArrayRotated {

    /*
    Approach:
        the number of rotations in a sorted rotated array is equal to the index(0-based index) of its minimum element.
        So, in order to solve this problem, we have to find the index of the minimum element.

        To find the minimum element check `FindMinimumIRotatedSortedArray.java`

        same logic, just store its position in a separate variable
        return index

        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
    public static int findNumberOfRotations(int[] arr){
        int low = 0, high = arr.length -1;
        int smallest = Integer.MAX_VALUE, lowestValueIndex = -1;

        while(low <= high){
            int mid = (low + high)/2;
            // if left sub array is sorted, then compare arr[low] with `smallest` - and update
            if(arr[low] <= arr[mid]){
                if(arr[low] < smallest){
                    smallest = arr[low];
                    lowestValueIndex = low;
                }
                // once you have found the lowest in this left sub array,
                // you have to move to right sub array to check is there any other element smaller than current `smallest`
                low = mid + 1;
            }
            // else means right sub array is sorted
            else{
                // compare the lowest value in right sub array, i.e. arr[mid] with `smallest`
                if(arr[mid] < smallest){
                    smallest = arr[mid];
                    lowestValueIndex = mid;
                }
                // once you have found the lowest in the sorted right sub array
                // you have to move to left sub array to check is there any other element which is smaller than `smallest`
                high = mid - 1;
            }
        }

        // The position of the smallest element in the array is equal to number of rotation that has been made in clockwise direction.
        return lowestValueIndex;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};
        System.out.printf("The array is rotated %d times in clockwise direction\n", findNumberOfRotations(arr));
    }

}
