package graph.shortest_path;

import java.util.PriorityQueue;

/*
Problem:
    https://takeuforward.org/data-structure/g-37-path-with-minimum-effort/
    https://leetcode.com/problems/path-with-minimum-effort/description/
 */
public class PathWithMinimumEffort {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O( 4*N*M * log( N*M) )
            N*M are the total cells, for each of which we also check 4 adjacent nodes for the minimum effort
            and additional log(N*M) for insertion-deletion operations in a priority queue

             Where, N = No. of rows and M = No. of columns

        Space Complexity: O( N*M )
            Distance matrix containing N*M cells
            priority queue in the worst case containing all the nodes ( N*M)
     */
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        // difference matrix is similar to distance matrix
        // but here, we store the maximum difference between any two cell values in the current path
        int[][] diffMatrix = new int[n][m];

        // priority with Tuple of the form (maxDifference in the current path, current cell row, current cell column)
        PriorityQueue<TuplePathWithMinimumEffort> priorityQueue =
                new PriorityQueue<TuplePathWithMinimumEffort>((x, y) -> x.diff - y.diff);

        // initalize all cells with max value
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                diffMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

        // difference for (0, 0) is 0 since it is the source cell
        diffMatrix[0][0] = 0;
        // insert (0, 0) to PQ with diff 0
        priorityQueue.add(new TuplePathWithMinimumEffort(0, 0, 0));

        // below two arrays to move in all 4 directions from any given cell
        int[] deltaRow = {0, 1, 0, -1};
        int[] deltaCol = {1, 0, -1, 0};

        // This is a Dijkstra Algoritm which takes E * log V
        // what is E and V
        // E = number of edges = there are n * m vertices each having 4 edges ( we move in 4 directions)
        // E = n * m * 4
        // V = n * m
        // Time Complexity : (n * m * 4) * log(n * m)
        while(!priorityQueue.isEmpty()){
            // poll the top element from the PQ
            TuplePathWithMinimumEffort tuple = priorityQueue.poll();
            int diff = tuple.diff;
            int row = tuple.row;
            int col = tuple.col;

            /* if the index popped is matching with (last row, last column)
             means we have reached the destination and since we are using PQ
             and heapifying based on diff
             no other remaining paths in PQ will yield shorter diff than this
             so return diff of this element*/
            if(row == n-1 && col == m-1)
                return diff;

            // move in all 4 directions
            for(int i = 0; i < 4; i++){
                int nRow = row + deltaRow[i];
                int nCol = col + deltaCol[i];

                // if the new location is valid
                if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m){
                    // get the new effort
                    // it will be maximum of current difference and the absolute difference between the value of two cells
                    int newEffort = Math.max(
                            Math.abs(heights[nRow][nCol] - heights[row][col]),
                            diff
                    );

                    // if this new effort is less than the current effort of the new cell
                    // modify effort and push it into the queue
                    if(newEffort < diffMatrix[nRow][nCol]){
                        diffMatrix[nRow][nCol] = newEffort;
                        priorityQueue.add(new TuplePathWithMinimumEffort(diffMatrix[nRow][nCol], nRow, nCol));
                    }
                }
            }
        }
        // if we have completed above while loop means,
        // we were not able to reach destination, so we return 0 indicating not able to reach destination
        return 0;
    }
}

class TuplePathWithMinimumEffort {
    int diff;
    int row;
    int col;

    public TuplePathWithMinimumEffort(int diff, int row, int col) {
        this.diff = diff;
        this.row = row;
        this.col = col;
    }
}
