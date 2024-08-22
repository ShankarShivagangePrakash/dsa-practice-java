package arrays.medium;

import java.util.ArrayList;
import java.util.List;

/*
Problem:
    https://takeuforward.org/data-structure/spiral-traversal-of-matrix/
    https://leetcode.com/problems/spiral-matrix/description/
 */
public class MatrixSpiralTraversal {

    public static List<Integer> printSpiral(int[][] matrix){
        List<Integer> ans = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[0].length;

        // top - first row
        // left - first column
        // right - last column
        // bottom - last row
        int top = 0, left = 0, right = m-1, bottom = n-1;

        // Loop until all elements are not traversed.
        while(top <= bottom  && left <=right){

            // For moving left to right
            for(int i = left; i<= right; i++){
                ans.add(matrix[top][i]);
            }
            top++;

            // For moving top to bottom.
            for(int i = top; i <= bottom; i++){
                ans.add(matrix[i][right]);
            }
            right--;

            // For moving right to left.
            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    ans.add(matrix[bottom][i]);

                bottom--;
            }

            // For moving bottom to top.
            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    ans.add(matrix[i][left]);

                left++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //Matrix initialization.
        int[][] mat = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        List<Integer> ans = printSpiral(mat);

        for(int i = 0;i<ans.size();i++){
            System.out.print(ans.get(i) + " ");
        }

        System.out.println();
    }
}
