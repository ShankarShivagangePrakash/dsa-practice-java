package arrays.medium;

/*
Problem:
    https://takeuforward.org/data-structure/set-matrix-zero/
    https://leetcode.com/problems/set-matrix-zeroes/description/
 */
public class SetMatrixZero {

    /*
    Approach:
        The steps are the following:

        First, we will use two loops(nested loops) to traverse all the cells of the matrix.
        If any cell (i,j) contains the value 0, we will mark all cells in row i and column j with -1 except those which contain 0.
        We will perform step 2 for every cell containing 0.
        Finally, we will mark all the cells containing -1 with 0.
        Thus, the given matrix will be modified according to the question.

        Time complexity: O((n*m)(m+n)) + O((n*m)(m+n))
                         where n is the number of rows, m is the number of columns.

                         check every method, have added complexity of each component.

                         Reason: Firstly, we are traversing the matrix to find the cells with the value 0. It takes O(N*M).
                         Now, whenever we find any such cell we mark that row and column with -1. This process takes O(N+M).
                         So, combining this the whole process, finding and marking, takes O((N*M)*(N + M)).
                         Another O(N*M) is taken to mark all the cells with -1 as 0 finally.

         Space Complexity: O(1)
     */
    public static void zeroMatrixBruteForce(int[][] matrix, int n, int m){

        //O((n*m)(m+n))
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                /// if matrix cell value is zero, then mark that entire column and cell as -1.
                if(matrix[i][j] == 0){
                    // we pass matrix, number of rows, number of columns and the row which we need to update with -1.
                    // O(m)
                    markRow(matrix, n, m, i);
                    // we pass matrix, number of rows, number of columns and the column which we need to update with -1.
                    // O(n)
                    markColumn(matrix, n, m, j);
                }
            }
        }

        // traverse all the elements of the matrix, then if the value is -1, update it with zero.
        //O(n*m)
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == -1){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // iterate through every element of the specified row, update it to -1
    // to do this we have to run loop, where we visit each column - so number of columns are m
    // in the row i
    // we will visit every column of the specified row.
    // Time complexity: O(m)
    public static void markRow(int[][] matrix, int n, int m, int i){
        for(int j = 0; j < m; j++){
            if(matrix[i][j] != 0) {
                matrix[i][j] = -1;
            }
        }
    }

    // iterate through every element of the specified column, update it to -1
    // to do this we have to run loop, where we visit each row - so number of rows are n
    // in the column j
    // we visit every row of specified column
    // Time complexity: O(n)
    public static void markColumn(int[][] matrix, int n, int m, int j){
        for(int i = 0; i < n; i++){
            if(matrix[i][j] != 0){
                matrix[i][j] = -1;
            }
        }
    }

    /*
    Approach:
        The steps are as follows:

        First, we will declare two arrays: a row array of size N and a col array of size M and both are initialized with 0.
        Then, we will use two loops(nested loops) to traverse all the cells of the matrix.
        If any cell (i,j) contains the value 0, we will mark ith index of row array i.e. row[i] and jth index of col array col[j] as 1.
        It signifies that all the elements in the ith row and jth column will be 0 in the final matrix.
        Finally, we will again traverse the entire matrix, and we will put 0 into all the cells (i, j) for which either row[i] or col[j] is marked as 1.
        Thus, we will get our final matrix.

        Time complexity: O( 2 *(n*m)(m+n))

        Space Complexity: O(n) + O(m)
            we create two arrays of size m and n.
     */
    public static void zeroMatrixBetterApproach(int[][] matrix, int n, int m){

        int[] rows = new int[n];
        int[] columns = new int[m];

        //O((n*m))
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                // if matrix cell value is zero, then mark the corresponding indexes of row and columns array to 1
                // i.e. ith row to 1 and jth column to 1
                if(matrix[i][j] == 0){
                    rows[i] = 1;
                    columns[j] = 1;
                }
            }
        }

        // traverse all the elements of the matrix, then if the value is -1, update it with zero.
        //O(n*m)
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(rows[i] == 1 || columns[j] == 1){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /*
    Approach:
        The steps are as follows:

        First, we will traverse the matrix and mark the proper cells of 1st row and 1st column with 0 accordingly.
        The marking will be like this: if cell(i, j) contains 0, we will mark the i-th row i.e. matrix[i][0] with 0 and we will mark j-th column i.e. matrix[0][j] with 0.
        If i is 0, we will mark matrix[0][0] with 0 but if j is 0, we will mark the col0 variable with 0 instead of marking matrix[0][0] again.
        After step 1 is completed, we will modify the cells from (1,1) to (n-1, m-1) using the values from the 1st row, 1st column, and col0 variable.
        We will not modify the 1st row and 1st column of the matrix here as the modification of the rest of the matrix(i.e. From (1,1) to (n-1, m-1))
        is dependent on that row and column.
        Finally, we will change the 1st row and column using the values from matrix[0][0] and col0 variable. Here also we will change the row first and then the column.
        If matrix[0][0] = 0, we will change all the elements from the cell (0,1) to (0, m-1), to 0.
        If col0 = 0, we will change all the elements from the cell (0,0) to (n-1, 0), to 0.

        Time Complexity: O(2*(n*m))
            since we are iterating matrix utmost two times.

        Space Complexity: O(1)
     */
    public static void zeroMatrixOptimalApproach(int[][] matrix, int n, int m){

        // first column of any row - matrix[...][0]
        // first row of any column - matrix[0][...]

        int col0 = 1;
        //O((n*m))
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                // if matrix cell value is zero, then mark first column & row to zero.
                // but first row and first column both cannot be accommodated in matrix[0][0]
                // for that reason, we use other variable to represent first column.
                if(matrix[i][j] == 0){
                    // update first cell of the row.
                    matrix[i][0] = 0;
                    // update first cell of the column
                    if(j!=0) {
                        matrix[0][j] = 0;
                    } else{
                        col0 = 0;
                    }
                }
            }
        }

        // Step 2: Mark with 0 from (1,1) to (n-1, m-1):
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(matrix[i][j] != 0) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        //step 3: Finally mark all the elements of the first row
        if(matrix[0][0] == 0){
            for(int j = 0; j < m; j++){
                matrix[0][j] = 0;
            }
        }

        //step 3: Finally mark all the elements of the first column
        if(col0 == 0){
            for(int i = 0; i < n; i++){
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1},
                {1, 0, 1, 0},
                {0, 1, 1, 0}
        };

        // Here n is the number of rows.
        int n = matrix.length;
        // m is the number of columns
        int m = matrix[0].length;

        zeroMatrixBruteForce(matrix, n, m);

        System.out.println("Zero Matrix using Brute force approach");
        for(int[] row : matrix){
            for(int element : row){
                System.out.print(element + " ");
            }
            System.out.println();
        }

        int[][] matrix1 = {
                {1, 1, 1, 1},
                {1, 0, 1, 0},
                {0, 1, 1, 0}
        };

        // Here n is the number of rows.
        int n1 = matrix1.length;
        // m is the number of columns
        int m1 = matrix1[0].length;

        zeroMatrixBetterApproach(matrix1, n1, m1);

        System.out.println("Zero Matrix using better approach");
        for(int[] row : matrix1){
            for(int element : row){
                System.out.print(element + " ");
            }
            System.out.println();
        }

        int[][] matrix2 = {
                {1, 1, 1, 1},
                {1, 0, 1, 0},
                {0, 1, 1, 0}
        };

        // Here n is the number of rows.
        int n2 = matrix2.length;
        // m is the number of columns
        int m2 = matrix2[0].length;

        zeroMatrixOptimalApproach(matrix2, n2, m2);

        System.out.println("Zero Matrix using better approach");
        for(int[] row : matrix2){
            for(int element : row){
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
