package arrays.binarysearch.two_dimensional_arrays;

/*
Problem:
    https://leetcode.com/problems/find-a-peak-element-ii/
 */
public class PeakElementMatrix {

    /*
    Approach:
        Approach has been discussed in notes,

        Time Complexity: O(n * m * 4)
        Space complexity: O(1)
     */
    public static int[] peakElementBruteForce(int[][] arr){
        return new int[0];
    }

    /*
    Approach:
        Have discussed in notes, check

        Time Complexity: O(n * log m)
            We are performing binary search on matrix columns so log (m)
            To find maximum in each column, we have to do linear search which takes O(n)
            Multiply both, O(n * log m)

        Space Complexity: O(1)
     */
    public static int[] peakElementOptimal(int[][] arr){

        int low = 0, high = arr[0].length-1;

        while(low <= high){
            int mid = (low + high)/2;

            // it is the row index at which maximum element of the current column is found.
            int row = maximum(arr, mid);

            // get left and right element adjacent to the row index we have found.
            int leftElement = mid - 1 >= 0 ? arr[row][mid-1] : -1;
            int rightElement = mid + 1 < arr[0].length ? arr[row][mid+1] : -1;

            // maximum in current column means, it is already greater than top and bottom
            // we need to check whether is it greater than its left and right  - if so it is the peak element - return its coordinates.
            if(leftElement < arr[row][mid] && rightElement < arr[row][mid])
                return new int[]{row, mid};
            // if left element is greater than current element, peak can be on left side move left.
            else if(leftElement > arr[row][mid])
                high = mid - 1;
            // peak could be on right side, move right
            else
                low = mid + 1;
        }
        // peak not found return (-1, -1)
        return new int[]{-1, -1};
    }

    /* gets the index of maximum element in the specified column. - which is actually row
     O(n) - we need to traverse all elements in the current column - means we need to visit every row.*/
    public static int maximum(int[][] arr, int column){
        int index = -1, maximum = -1;

        for(int i = 0; i < arr.length; i++){
            if(arr[i][column] > maximum){
                maximum = arr[i][column];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[][] arr =
                {
                        {41,8,2,48,18},
                        {16,15,9,7,44},
                        {48,35,6,38,28},
                        {3,2,14,15,33},
                        {39,36,13,46,42}

                };

        int[] result = peakElementOptimal(arr);
        System.out.printf("Peak element is found at the index using optimal approach %d %d\n", result[0], result[1]);
    }
}
