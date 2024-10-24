package stack_n_queue.monotonic;

import java.util.Stack;

/*
Problem:
    https://leetcode.com/problems/maximal-rectangle/description/
 */
public class MaximalRectangle {

    public static int maximalRectangleOptimal(char[][] matrix){

        int n = matrix.length,m = matrix[0].length;
        int[][] prefixSumMatrix = new int[n][m];
        int maxRectangleArea = 0;

        for(int j = 0; j < m; j++){
            for(int i = 0; i < n; i++){
                if (matrix[i][j] == '1') {
                    prefixSumMatrix[i][j] = (i > 0 ? prefixSumMatrix[i - 1][j] : 0) + 1;
                } else {
                    prefixSumMatrix[i][j] = 0;
                }
            }
        }

        for(int i = 0; i < n; i++)
            maxRectangleArea = Math.max(maxRectangleArea, findLargestRectangleHistogram(prefixSumMatrix[i]));

        return maxRectangleArea;
    }

    public static int findLargestRectangleHistogram(int[] arr){
        int maxRectangleArea = 0;
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < arr.length; i++){

            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int nse = i;
                int element = stack.pop();
                int pse = (stack.isEmpty()) ? -1 : stack.peek();

                int currentRectangleArea = arr[element] * (nse - pse -1);

                maxRectangleArea = Math.max(maxRectangleArea, currentRectangleArea);
            }

            stack.push(i);
        }

        while(!stack.isEmpty()){
            int element = stack.pop();
            int nse = arr.length;
            int pse = (stack.isEmpty()) ? -1 : stack.peek();

            int currentRectangleArea = arr[element] * (nse - pse -1);

            maxRectangleArea = Math.max(maxRectangleArea, currentRectangleArea);
        }

        return maxRectangleArea;
    }
}
