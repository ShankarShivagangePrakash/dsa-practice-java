package graph.learning.bfsdfs;

import java.util.AbstractMap.SimpleEntry;

import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/number-of-islands/
    https://leetcode.com/problems/number-of-distinct-islands-ii/description/
 */
public class NumberOfDistinctIslands {

    /*
    Approach:
        Explained in the notes, check

        we call BFS for cell value with `1`
            it will perform BFS for all neigbours connected to it with value 1
            so we can treat that as 1 island

        if we again find a cell with value `1` and not visited,
            that will be a new island
            so we perform BFS on this and increment counter

        after traversing entire matrix, we return `counter`

        Time Complexity: O(n * m * 8)
            we are traversing the input matrix, - O(n * m)
            each cell will look for neigbours in all 8 directions so multiply by 8
            but we won't call BFS for every node, above one is theoritical interpretation
            but time complexity will be smaller than this.

        Space Complexity: O(n * m * 2) ~ O(n * m)
            O(n * m) for visited matrix
            O(n * m) for queue at worst case, but it wont happen
     */
    public int numberOfIslands(char[][] grid){
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;
        boolean[][] visited = new boolean[n][m];

        int[] deltaRow = {-1, 0, 1, 1, 1, 0, -1, -1 };
        int[] deltaCol = {1, 1, 1, 0, -1, -1, -1, 0 };

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j] == false && grid[i][j] == '1'){
                    count++;
                    bfs(i, j, visited, grid, deltaRow, deltaCol);
                }
            }
        }
        return count;
    }

    public void bfs(int i, int j, boolean[][] visited, char[][] grid, int[] deltaRow, int[] deltaCol){
        int n = grid.length;
        int m = grid[0].length;

        visited[i][j] = true;

        Queue<SimpleEntry<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new SimpleEntry<>(i, j));

        while(!queue.isEmpty()){
            SimpleEntry temp = queue.poll();
            int row = (int) temp.getKey();
            int col = (int) temp.getValue();

            for(int k = 0; k < 8; k++){
                int nRow = row + deltaRow[k];
                int nCol = col + deltaCol[k];

                if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m
                    && visited[nRow][nCol] == false && grid[nRow][nCol] == '1'){
                    visited[nRow][nCol] = true;
                    queue.offer(new SimpleEntry<>(nRow, nCol));
                }
            }
        }
    }
}
