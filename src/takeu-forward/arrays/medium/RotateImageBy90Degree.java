package arrays.medium;

/*
Problem:
    https://takeuforward.org/data-structure/rotate-image-by-90-degree/
    https://leetcode.com/problems/rotate-image/
 */
public class RotateImageBy90Degree {

    /*
    Approach:
        when we rotate the matrix by 90 degree, it is same as column put into row
       it means, take the first column (it will be vertical) just make it horizontal
       so the bottom element will be on the left side of horizontal array, top element will be at right side of the array.
       do this for every column, matrix is rotated by 90 degree.

       Time Complexity: O(2*(n*n))
       Space Complexity: O(n*n)

     */
    public static void rotateImageBy90DegreeBruteForce(int[][] matrix){
        int n = matrix.length;
        int[][] ans = new int[n][n];
        int k = 0;
        for(int j = 0; j < n; j++) {
            int l = 0;
            for (int i = n-1; i >= 0; i--) {
                ans[k][l] = matrix[i][j];
                l++;
            }
            k++;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = ans[i][j];
            }
        }
    }

    /*
    Approach:
        Intuition: By observation, we see that the first column of the original matrix is the reverse of the first row of the rotated matrix,
        so that’s why we transpose the matrix and then reverse each row, and since we are making changes in the matrix itself space complexity gets reduced to O(1).

        Approach:

        Step 1: Transpose the matrix. (transposing means changing columns to rows and rows to columns)

        Step 2: Reverse each row of the matrix.

        Time Complexity: O(2*(n*n))
        Space Complexity: O(1)
     */
    public static void rotateImageBy90DegreeOptimal(int[][] matrix){
        int n = matrix.length;

        // transpose of the matrix
        // here j is initialized with i because the already transposed elements should not be picked for transpose again.
        // so in first iteration when i = 0, say (0,1) swapped with (1,0) so these elements are transposed
        // so when i becomes 1, (1,0) index should not be picked, means j should not be 0
        // if j is initialized with the value of i, then we can bypass (1,0)
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // now reverse each row.
        for(int i = 0; i < n; i++){
            int start = 0, end = n -1;
            while(start < end){
                int temp = matrix[i][start];
                matrix[i][start] = matrix[i][end];
                matrix[i][end] = temp;
                start++;
                end--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        rotateImageBy90DegreeBruteForce(matrix);

        System.out.println("Rotate Image by 90 degree using Brute Force approach");
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                System.out.printf(matrix[i][j] + " ");
            }
            System.out.println();
        }

        int[][] matrix2 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        rotateImageBy90DegreeOptimal(matrix2);

        System.out.println("Rotate Image by 90 degree using optimal approach");
        for(int i = 0; i < matrix2.length; i++){
            for(int j = 0; j < matrix2.length; j++){
                System.out.printf(matrix2[i][j] + " ");
            }
            System.out.println();
        }

    }
}
