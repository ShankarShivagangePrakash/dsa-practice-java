package graph.shortest_path;

import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/g-36-shortest-distance-in-a-binary-maze/
    https://leetcode.com/problems/shortest-path-in-binary-matrix/description/
 */
public class ShortestPathInBinaryMaze {

    /*
    Approach:

     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // If the starting or ending cell is not clear, return -1
        if (grid[0][0] != 0 || grid[n - 1][m - 1] != 0) {
            return -1;
        }

        int[] source = {0, 0};
        int[] destination = {n - 1, m - 1};

        // Source and destination both are the same, return 1 (as we need to count the starting cell)
        if (source[0] == destination[0] && source[1] == destination[1]) {
            return 1;
        }

        // Create distance matrix and initialize with a large value
        int[][] distance = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        distance[source[0]][source[1]] = 1;

        Queue<Tuple> queue = new LinkedList<>();
        // tuple first refers to distance
        // second and third refers to row and column of the cell
        queue.add(new Tuple(1, source[0], source[1]));

        int[] deltaRow = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] deltaCol = {0, 1, 1, 1, 0, -1, -1, -1};

        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();

            int dist = tuple.first;
            int row = tuple.second;
            int col = tuple.third;

            for (int i = 0; i < 8; i++) {
                int nRow = row + deltaRow[i];
                int nCol = col + deltaCol[i];

                // If the new cell is within bounds, is a 0, and offers a shorter path
                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m
                        && grid[nRow][nCol] == 0 && dist + 1 < distance[nRow][nCol]) {

                    distance[nRow][nCol] = dist + 1;

                    if (nRow == destination[0] && nCol == destination[1]) {
                        return dist + 1;
                    }

                    queue.add(new Tuple(distance[nRow][nCol], nRow, nCol));
                }
            }
        }
        return -1;
    }
}

class Tuple{
    int first;
    int second;
    int third;

    public Tuple(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
