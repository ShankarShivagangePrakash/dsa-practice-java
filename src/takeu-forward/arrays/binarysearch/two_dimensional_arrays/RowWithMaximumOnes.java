package arrays.binarysearch.two_dimensional_arrays;

/*
Problem:
    https://takeuforward.org/arrays/find-the-row-with-maximum-number-of-1s/
    https://www.geeksforgeeks.org/problems/row-with-max-1s0023/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=row-with-max-1s
 */
public class RowWithMaximumOnes {

    /*
    Approach:
        The steps are as follows:

        First, we will declare 2 variables i.e. cnt_max(initialized with 0), and index(initialized with -1).

        The first variable will store the maximum number of 1’s we have got and the ‘index’ will store the row number.

        Next, we will start traversing the matrix. We will use a loop(say i) to select each row at a time.
        Now, for each row i, we will use another loop(say j) and count the number of 1’s in that row.

        After that, we will compare it with cnt_max and if the current number of 1’s is greater,
        we will update cnt_max with the current no. of 1’s and ‘index’ with the current row index.

        Finally, we will return the variable ‘index’. It will store the index of the row with the maximum no. of 1’s. And otherwise, it will store -1.

        Time Complexity: O(n * m)
        Space Complexity: O(1)
     */
    public static int rowWithMaxOnesBruteForce(int[][] arr){
        int index = -1;
        /* here, countMax is initialized to 0 to handle an edge case
         assume the entries' matrix is having only zeroes, so we have to return -1
         so if we initialize countMax to 0, then the logic will not update `index`
         so we can return -1*/
        int countMax = 0;

        int low = 0, high = arr.length - 1;

        for(int i = 0; i < arr.length; i++){
            // count number of Ones in current row.
            int count = 0;
            for(int j = 0; j < arr[i].length; j++){
                // we add current element to `count` since it has only 0 and 1
                // summation will give number of 1s in the row.
                count += arr[i][j];
            }
            // if number of One's is more than countMax, then update countMax
            // mark current row as row with maximum number of ones
            if(count > countMax){
                countMax = count;
                index = i;
            }
        }
        return index;
    }

    /*
    Approach:
        We are going to use the Binary Search algorithm to optimize the approach.

        We cannot optimize the row traversal but we can optimize the counting of 1’s for each row.

        Instead of counting the number of 1’s, we can use the following formula to calculate the number of 1’s:
        Number_of_ones = m(number of columns) - first occurrence of 1(0-based index).

        As, each row is sorted, we can find the first occurrence of 1 in each row we use - lowerBound(1) (Check LowerBound.java)

        Steps are as follows:

        First, we will declare 2 variables i.e. cnt_max(initialized with 0), and index(initialized with -1).
        The first variable will store the maximum number of 1’s we have got and ‘index’ will store the row number.

        Next, we will start traversing the rows. We will use a loop(say i) to select each row at a time.
        Now, for each row i, we will use lowerBound() to get the first occurrence of 1. Now, using the following formula we will calculate the number of 1’s:
        Number_of_ones = m(number of columns) - lowerBound(1)(0-based index).

        After that, we will compare it with cnt_max and if the current number of 1’s is greater,
        we will update cnt_max with the current no. of 1’s and ‘index’ with the current row index.

        Finally, we will return the variable ‘index’. It will store the index of the row with the maximum no. of 1’s.
        And if the matrix does not contain any 1, it stores -1.

        Time Complexity: O(n * log(m))
            where n = given row number, m = given column number.
            Reason: We are using a loop running for n times to traverse the rows. Then we are applying binary search on each row with m columns.

        Space Complexity: O(1)
     */
    public static int rowWithMaxOnesOptimal(int[][] arr){
        int index = -1;
        int countMax = 0;
        for(int i = 0; i < arr.length; i++) {
            int count = arr[i].length - lowerBound(arr[i], 1);
            if(count > countMax){
                countMax = count;
                index = i;
            }
        }
        return index;
    }

    // log(m) -> `m` is arr length;
    public static int lowerBound(int[] arr, int target){
        int low = 0, high = arr.length - 1;
        int ans = arr.length;

        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] < target)
                low = mid + 1;
            else{
                ans = Math.min(ans, mid);
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] arr =
                {
                        {0, 0, 0},
                        {0, 0, 1},
                        {1, 1, 1}
                };

        System.out.printf("Maximum number of One's using brute force approach is found at row number %d\n", rowWithMaxOnesBruteForce(arr));
        System.out.printf("Maximum number of One's using optimal approach is found at row number %d\n", rowWithMaxOnesOptimal(arr));

    }
}
