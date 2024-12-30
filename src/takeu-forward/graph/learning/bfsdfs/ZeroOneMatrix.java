package graph.learning.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/graph/distance-of-nearest-cell-having-1/
    https://leetcode.com/problems/01-matrix/description/
 */
public class ZeroOneMatrix {

    /*

    Problem Statement:
        return the distance of the nearest 0 for each cell.
    Approach:
        we try to solve this problem using BFS

        first we will iterate through every cell, if they are zero
        we will add it to queue with distance 0 - nearest zero for this cell is this cell itself, so distance 0
        we will mark these cells as visited

        then, we run the while loop till queue is not empty

            pop the element from the queue, mark the popped element cell distance from the same element

            we move in all four directions,
                if they are not visited and valid cell
                    then we will add that cell to queue with incremented distance
                    mark it as visited
        when we complete while loop, distance matrix will be calculated return it

        Time Complexity:
            O(NxM + NxMx4) ~ O(N x M)
            For the worst case, the BFS function will be called for (N x M) nodes, and for every node, we are traversing for 4 neighbors, so it will take O(N x M x 4) time.

        Space Complexity:
            O(N x M) + O(N x M) + O(N x M) ~ O(N x M)
            O(N x M) for the visited array, distance matrix, and queue space takes up N x M locations at max.
     */
    public int[][] updateMatrix(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        Queue<PairZeroOneMatrix> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        int[][] distanceMatrix = new int[n][m];

        int[] deltaRow = {0, 1, 0, -1};
        int[] deltaCol = {1, 0, -1, 0};

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                // here they are asking to find the nearest zero, that's why adding zero's to queue
                // if they have asked nearest 1, add 1 to queue.
                if(mat[i][j] == 0){
                    queue.offer(new PairZeroOneMatrix(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        while(!queue.isEmpty()){
            PairZeroOneMatrix temp = queue.poll();

            int row = temp.row;
            int col = temp.col;
            int distance = temp.distance;

            distanceMatrix[row][col] = distance;

            for(int i = 0; i < 4; i++){
                int nRow = row + deltaRow[i];
                int nCol = col + deltaCol[i];

                // valid index and cell not visited, visit it
                if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m &&
                visited[nRow][nCol] == false){
                    queue.offer(new PairZeroOneMatrix(nRow, nCol, distance + 1));
                    visited[nRow][nCol] = true;
                }
            }
        }

        return distanceMatrix;
    }
}

class PairZeroOneMatrix{
    int row;
    int col;
    int distance;

    PairZeroOneMatrix(int row, int col, int distance){
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}
