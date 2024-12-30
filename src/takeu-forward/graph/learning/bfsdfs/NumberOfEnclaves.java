package graph.learning.bfsdfs;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/graph/number-of-enclaves/
    https://leetcode.com/problems/number-of-enclaves/description/
 */
public class NumberOfEnclaves {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(NxMx4) ~ O(N x M)
            For the worst case, assuming all the pieces as land, the BFS function will be called for (N x M) nodes and for every node,
            we are traversing for 4 neighbors, so it will take O(N x M x 4) time.

        Space Complexity: O(N x M),
            O(N x M) for the visited array, and queue space takes up N x M locations at max.
     */
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        int[] deltaRow = {0, 1, 0, -1};
        int[] deltaCol = {1, 0, -1, 0};

        // first row, last row
        for(int i = 0; i < m; i++){
            if(grid[0][i] == 1 && visited[0][i] == false) {
                queue.offer(new Pair<>(0, i));
                visited[0][i] = true;
            }
            if(grid[n-1][i] == 1 && visited[n-1][i] == false){
                queue.offer(new Pair<>(n-1, i));
                visited[n-1][i] = true;
            }
        }

        // first column, last column
        for(int i = 0; i < n; i++){
            if(grid[i][0] == 1 && visited[i][0] == false){
                queue.offer(new Pair<>(i, 0));
                visited[i][0] = true;
            }
            if(grid[i][m-1] == 1 && visited[i][m-1] == false){
                queue.offer(new Pair<>(i, m-1));
                visited[i][m-1] = true;
            }
        }

        while (!queue.isEmpty()){
            Pair temp = queue.poll();
            int row = (int) temp.getKey();
            int col = (int) temp.getValue();

            for(int i = 0; i < 4; i++){
                int nRow = row + deltaRow[i];
                int nCol = col + deltaCol[i];

                if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m
                        && visited[nRow][nCol] == false && grid[nRow][nCol] == 1){
                    queue.offer(new Pair<>(nRow, nCol));
                    visited[nRow][nCol] = true;
                }
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1 && visited[i][j] == false)
                    count++;
            }
        }
        return count;
    }
}
