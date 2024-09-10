package arrays.binarysearch.two_dimensional_arrays;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/median-of-row-wise-sorted-matrix/
    https://www.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=median-in-a-row-wise-sorted-matrix
 */
public class medianRowWiseSortedMatrix {

    /*
    Approach:
        The extremely naive approach is to use a linear array to store the elements of the given matrix.
        Now, we will sort the array and return the middle element.

        Algorithm:

            We will use a loop(say i) to select a particular row at a time.
            Next, we will use another loop(say j) for every row to traverse each column.
            Inside the loops, we will store each element, matrix[i][j] to a linear array.
            Finally, we will return the middle element of that linear array.

            since m and n both will be odd, median will be element at index (m * n)/2
            eg: [
                 [1]
                 [2]
                 [3]
                ]
                in this example, n = 3, m = 1;
                when we form linear array it will become = [1, 2, 3]
                (m * n)/2 = (3 * 1)/2 = 1
                arr[1] = 2 is the median

        Time Complexity: O(M * N) + O(M* N(log (M * N)))
            where M = number of row in the given matrix, N = number of columns in the given matrix

        Reason: At first, we are traversing the matrix to copy the elements. This takes O(MXN) time complexity.
        Then we are sorting the linear array of size (MXN), that takes O(MXN(log(MXN))) time complexity

        Space Complexity: O(MXN) as we are using a temporary list to store the elements of the matrix.
     */
    public static int medianRowWiseSortedMatrixBruteForce(int[][] arr){
        int n = arr.length, m = arr[0].length;
        int[] temp = new int[n * m];
        int k = 0;

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                temp[k++] = arr[i][j];
            }
        }

        Arrays.sort(temp);

        return temp[(n * m)/2];
    }

    /*
    Approach:
        Calculate min(matrix) and max(matrix):
            As the given matrix is row-wise sorted the minimum element will be the minimum element in the first column
            and the maximum will be the maximum in the last column.

        Place the 2 pointers low and high: Initially, we will place the pointers.
        The pointer low will point to min(matrix) and the high will point to max(matrix).

        Calculate the ‘mid’: Now, inside a loop,
        we will calculate the value of ‘mid’ using the following formula: mid = (low+high) // 2 ( ‘//’ refers to integer division)

        Use the countSmaller() function to get the number of elements < mid:
        Inside the function, we will use the above-mentioned upper bound formula for each row
        and calculate the total number of elements < mid.
        Then we will return the total number from the function countSmaller().

        If smallEqual <= (M*N) // 2: We can conclude that our median must be a bigger number.
        So, we will eliminate the left i.e. the smaller half (low = mid+1).

        If smallEqual > (M*N) // 2: We can conclude that the element mid might be the median.
        But we have to reach the smallest number to find the actual median.
        So, in this case, we will remove the right half( i.e. high = mid-1).

        Time Complexity: O(log(10^9)) X O(n * log m)
            where n = number of rows in the given matrix, m = number of columns in the given matrix

            Reason: Our search space lies between [0, 10^9] as the min(matrix) can be 0 and the max(matrix) can be 10^9 - depends on input range.
            We are applying binary search in this search space, and it takes O(log(10*9)) time complexity.
            Then we call countSmaller() function for every ‘mid’ and this function takes O(M(logN)) time complexity.

        Space Complexity : O(1) as we are not using any extra space
     */
    public static int medianRowWiseSortedMatrixOptimal(int[][] arr){
        int n = arr.length, m = arr[0].length;

        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        int requiredElements = (n * m)/2;

        for(int i = 0; i < n; i++){
            low = Math.min(low, arr[i][0]);
            high = Math.max(high, arr[i][m-1]);
        }

        // log (10^9) - only this while loop inside logic time complexity not considered.
        while(low <= high){
            int mid = (low + high)/2;

            int countSmallerThan = countSmaller(arr, mid);

            if(countSmallerThan <= requiredElements)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

    // O(n * log m)
    // for every row, count number of elements smaller than target.
    public static int countSmaller(int[][] arr, int target){
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            count += upperBound(arr[i], target);
        }
        return count;
    }

    // index of the element which is larger than target, so that many elements are larger than target - O(log m)
    public static int upperBound(int[] arr, int target){
        int low = 0, high = arr.length-1;
        int ans = arr.length;

        while(low <= high){
            int mid = (low + high)/2;

            if(arr[mid] <= target)
                low = mid + 1;
            else{
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] arr =
                {
                        {1, 3, 5},
                        {2, 6, 9},
                        {3, 6, 9}
                };

        System.out.printf("Median of row wise sorted matrix using brute force approach is %d\n", medianRowWiseSortedMatrixBruteForce(arr));
        System.out.printf("Median of row wise sorted matrix using optimal approach is %d\n", medianRowWiseSortedMatrixOptimal(arr));
    }
}
