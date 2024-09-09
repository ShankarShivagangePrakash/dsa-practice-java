package arrays.binarysearch.two_dimensional_arrays;

/*
Problem:
    https://takeuforward.org/data-structure/search-in-a-sorted-2d-matrix/
    https://leetcode.com/problems/search-a-2d-matrix/
 */
public class SearchInTwoDimensionalSortedArray {

    /*
    Approach:
        The extremely naive approach is to get the answer by checking all the elements of the given matrix.
        So, we will traverse the matrix and check every element if it is equal to the given ‘target’.

        Steps are as follows:

        We will use a loop(say i) to select a particular row at a time.
        Next, for every row, we will use another loop(say j) to traverse each column.
        Inside the loops, we will check if the element i.e. matrix[i][j] is equal to the ‘target’. If we find any matching element, we will return true.
        Otherwise, after completing the traversal, we will return false.

        Time Complexity: O(n*m)
        Space Complexity: O(1)
     */
    public static boolean searchInTwoDimensionalSortedArrayBruteForce(int[][] arr, int target){

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(arr[i][j] == target)
                    return true;
            }
        }
        return false;
    }

    /*
    Approach:
        We will use a loop(say i) to select a particular row at a time.
        Next, for every row, i, we will check if it contains the target.
        If matrix[i][0] <= target && target <= matrix[i][m-1]:
            If this condition is met, we can conclude that row i has the possibility of containing the target.
            So, we will apply binary search on row i, and check if the ‘target’ is present.
        If it is present, we will return true from this step. Otherwise, we will return false.
        Otherwise, after completing the traversal, we will return false.

        Time Complexity: O(n) + O(log m)
               Reason: We are traversing all rows, and it takes O(N) time complexity.
               But for all rows, we are not applying binary search rather we are only applying it once for a particular row.
               That is why the time complexity is O(N + logM) instead of O(N*logM).
        Space Complexity: O(1)
     */
    public static boolean searchInTwoDimensionalSortedArrayBetter(int[][] arr, int target){
        for(int i = 0; i < arr.length; i++){
            int col = arr[i].length;
            if(arr[i][0] <= target && target <= arr[i][col-1])
                return binarySearch(arr[i], target);
        }
        return false;
    }

    public static boolean binarySearch(int[] arr, int target){
        int low = 0, high = arr.length - 1;

        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] == target)
                return true;
            else if(arr[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return false;
    }

    /*
    Approach:
        Place the 2 pointers i.e. low and high: Initially, we will place the pointers. The pointer low will point to 0 and the high will point to (NxM)-1.

        Calculate the ‘mid’: Now, inside the loop, we will calculate the value of ‘mid’ using the following formula:
        mid = (low+high) // 2 ( ‘//’ refers to integer division)

        Eliminate the halves based on the element at index mid: To get the element,
        we will convert index ‘mid’ to the corresponding cell using the above formula. Here no. of columns of the matrix = M.
        row = mid / M, col = mid % M.

        If matrix[row][col] == target: We should return true here, as we have found the ‘target’.
        If matrix[row][col] < target: In this case, we need bigger elements. So, we will eliminate the left half and consider the right half (low = mid+1).
        If matrix[row][col] > target: In this case, we need smaller elements. So, we will eliminate the right half and consider the left half (high = mid-1).

        (i.e. low > high). If we are out of the loop, we can say the target does not exist in the matrix. So, we will return false.

        Time Complexity: O( log( m *n)
            We are applying binary search on the imaginary 1D array of size NxM.

        Space Complexity: O(1)
     */
    public static boolean searchInTwoDimensionalSortedArrayOptimal(int[][] arr, int target){
        int noOfRows = arr.length;
        int noOfCols = arr[0].length;
        int low = 0, high = (noOfRows * noOfCols) - 1;

        while(low <= high){
            int mid = (low + high)/2;

            int row = mid/noOfCols;
            int col = mid % noOfCols;

            if(arr[row][col] == target)
                return true;
            else if(arr[row][col] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr =
                {
                        {1,3,5,7},
                        {10,11,16,20},
                        {23,30,34,60}
                };
        int target = 16;

        System.out.printf("Is element %d present? - brute force approach %b\n", target, searchInTwoDimensionalSortedArrayBruteForce(arr, target));
        System.out.printf("Is element %d present? - better approach %b\n", target, searchInTwoDimensionalSortedArrayBetter(arr, target));
        System.out.printf("Is element %d present? - optimal approach %b\n", target, searchInTwoDimensionalSortedArrayOptimal(arr, target));
    }
}
