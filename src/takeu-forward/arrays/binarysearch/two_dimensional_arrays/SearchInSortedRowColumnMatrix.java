package arrays.binarysearch.two_dimensional_arrays;

/*
Problem:
    https://takeuforward.org/arrays/search-in-a-row-and-column-wise-sorted-matrix/
    https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class SearchInSortedRowColumnMatrix {

    /*
    Approach:
        Algorithm:

        We will use a loop(say i) to select a particular row at a time.
        Next, for every row, we will use another loop(say j) to traverse each column.
        Inside the loops, we will check if the element i.e. matrix[i][j] is equal to the ‘target’. If we found any matching element, we will return true.
        Finally, after completing the traversal, if we found no matching element, we will return false.

        Time Complexity: O(N X M)
            where N = given row number, M = given column number.
            Reason: In order to traverse the matrix, we are using nested loops running for n and m times respectively.

        Space Complexity: O(1) as we are not using any extra space.
     */
    public static boolean searchInSortedRowColumnMatrixBruteForce(int[][] arr, int target){

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
        We are going to use the Binary Search algorithm to optimize the approach.

        We will use a loop(say i) to select a particular row at a time.
        Next, for every row, i, we will check if it contains the target using binary search.
            After applying binary search on row, i, if we found any element equal to the target, we will return true. Otherwise, we will move on to the next row.
        Finally, after completing all the row traversals, if we found no matching element, we will return false.

        Time Complexity: O(N*logM), where N = given row number, M = given column number.
            Reason: We are traversing all rows, and it takes O(N) time complexity. And for all rows,
            we are applying binary search. So, the total time complexity is O(N*logM).

        Space Complexity: O(1) as we are not using any extra space.
     */
    public static boolean searchInSortedRowColumnMatrixBetter(int[][] arr, int target){

        for(int i = 0; i < arr.length; i++){
            boolean isTargetFound =  binarySearch(arr[i], target);
            if(isTargetFound)
                return true;
        }
        return false;
    }

    public static boolean binarySearch(int[] arr, int target){

        int low = 0, high = arr.length-1;
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] == target)
                return true;
            else if(arr[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return false;
    }

    /*
    Approach:
        have discussed in detail in notes, check

        As we are starting from the cell (0, m-1), the two variables i.e. ‘row’ and ‘col’ will point to 0 and m-1 respectively.

        We will do the following steps until row < n and col >= 0(i.e. while(row < n && col >= 0)):

            If matrix[row][col] == target: We have found the target, and so we will return true.

            If matrix[row][col] > target: We need the smaller elements to reach the target.
            But the column is in increasing order and so it contains only greater elements.
            So, we will eliminate the column by decreasing the current column value by 1(i.e. col--) and thus we will move row-wise.

            If matrix[row][col] < target: In this case, We need the bigger elements to reach the target.
            But the row is in decreasing order and so it contains only smaller elements.
            So, we will eliminate the row by increasing the current row value by 1(i.e. row++) and thus we will move column-wise.

            If we are outside the loop without getting any matching element, we will return false.

        Time Complexity: O(N+M)
            where N = given row number, M = given column number.
            Reason: We are starting traversal from (0, M-1), and at most, we can end up being in the cell (M-1, 0).
            So, the total distance can be at most (N+M). So, the time complexity is O(N+M).

        Space Complexity: O(1) as we are not using any extra space.
     */
    public static boolean searchInSortedRowColumnMatrixOptimal(int[][] arr, int target){
        int row = 0, column = arr[0].length-1;

        while(row < arr.length && column >= 0){

            if(arr[row][column] == target)
                return true;
            // if current element is smaller than the target means all the elements in that row before it will be smaller eliminate them by moving to next row.
            else if(arr[row][column] < target)
                row++;
            // else means, current element is greater than the target,
            // all the elements in the column will be greater than the target, eliminate them.
            else
                column--;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr =
                {
                        {1,4,7,11,15},
                        {2,5,8,12,19},
                        {3,6,9,16,22},
                        {10,13,14,17,24},
                        {18,21,23,26,30}
                };
        int target = 14;

        System.out.printf("Target element %d is present in the matrix using brute force approach? %b\n", target, searchInSortedRowColumnMatrixBruteForce(arr, target));
        System.out.printf("Target element %d is present in the matrix using better approach? %b\n", target, searchInSortedRowColumnMatrixBetter(arr, target));
        System.out.printf("Target element %d is present in the matrix using optimal approach? %b\n", target, searchInSortedRowColumnMatrixOptimal(arr, target));
    }
}
