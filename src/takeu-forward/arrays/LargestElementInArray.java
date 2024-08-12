package arrays;

/*
Problem:
    https://takeuforward.org/data-structure/find-the-largest-element-in-an-array/
    https://www.geeksforgeeks.org/problems/largest-element-in-array4009/0?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=largest-element-in-array
 */
public class LargestElementInArray {

    /* Approach:
    keep a variable and initialize it with arr[0] as the largest.
    Iterate through every element and if it is greater than largest, then update.

    Time complexity: O(n)
    Space complexity: O(1)
     */
    public static int findLargestElement(int[] arr){
        int largest = arr[0];
        for(int i = 1; i < arr.length; i++){
            if( arr[i] > largest)
                largest = arr[i];
        }
        return largest;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 5, 4, 3};

        System.out.printf("Largest element in the array is %d\n", findLargestElement(arr));
    }
}
