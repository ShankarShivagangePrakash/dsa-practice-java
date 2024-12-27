package graph.learning.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/rotten-oranges-min-time-to-rot-all-oranges-bfs/
    https://leetcode.com/problems/rotting-oranges/description/
 */
public class RottingOranges {

    /*
    Approach:
        First we will keep track of rotten oranges present in given grid
        we add them to queue,
            Queue will store rotten oranges in the format (row, col, time at which it was rotten)
            also, we will mark the corresponding cell in visited array as true

        we will run the while loop till the queue is not empty
            we poll the element from the queue
            from that, we know the row, col and time at which it was rotten

            we can rot, oranges in all four directions if there are fresh oranges
            if we find such fresh oranges, then we will mark them as visited and add those to queue with currentTime + 1

            we will get the max (currentTime, tm)

        after completing the while loop,
            we will check, the number of fresh oranges before and how many fresh oranges we have rotten

            if they both are same, then we can return `tm` as time required to rot all fresh oranges

            else
                we were not able to rot all fresh oranges, so we will return -1

        Space Complexity:
            we are having a visited 2D array - O(n * m)
            also we have a queue, worst case in the given grid if all the given oranges are rotten
             then it can take O(n * m)

            Total = O(2 * n * m) = O(n * m)

        Time Complexity:
            Initally we are running a for loop to find the rotten oranges in the given grid which takes - O(n * m)
            then we are running a while loop, which makes fresh oranges to rot
                if majority of the oranges given are fresh, then we make all of them rot
                so O(n * m) but for each rotten oranges, we move in all 4 directions
                so it will become O( 4 * n * m)

            Total time complexity = O(n * m) + O( 4 * n * m)
                  almost equivalent to O(n * m)
     */
    public int orangesRotting(int[][] grid) {

        int n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        Queue<PairRottenOranges> queue = new LinkedList<>();

        // count the fresh oranges and rotten oranges in the given grid
        int countFresh = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 2){
                    visited[i][j] = true;
                    // rotten orange found, add it to the queue in the format
                    // {row, col, time - 0}
                    // time is 0 because it is already given as rotten
                    queue.offer(new PairRottenOranges(i, j, 0));
                }
                else
                    visited[i][j] = false;

                // count fresh oranges
                if(grid[i][j] == 1)
                    countFresh++;
            }
        }

        // total time required to make all fresh oranges rot
        int maxTime = 0;
        // count fresh oranges which are made to rot
        int count = 0;
        int[] deltaRow = {0, 1, 0, -1};
        int[] deltaCol = {1, 0, -1, 0};
        while(!queue.isEmpty()){
            // get the rotten orange
            PairRottenOranges temp = queue.poll();
            int row = temp.row;
            int col = temp.col;
            int tm = temp.tm;

            maxTime = Math.max(tm, maxTime);

            // move in all four directions of the rotten orange
            for(int i = 0; i < 4; i++){
                int nRow = row + deltaRow[i];
                int nCol = col + deltaCol[i];

                // new cell is valid, not visited and at that cell in the grid there is a fresh orange
                if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m
                && visited[nRow][nCol] == false && grid[nRow][nCol] == 1){
                    visited[nRow][nCol] = true;
                    // we can make this new fresh orange as rotten and increment the count
                    queue.offer(new PairRottenOranges(nRow, nCol, tm + 1));
                    count++;
                }
            }
        }

        // number of fresh oranges initially is not equal to number of fresh oranges made to rot
        // so we have to return -1
        if(countFresh != count)
            return -1;

        return maxTime;
    }
}

class PairRottenOranges{
    int row;
    int col;
    int tm;

    PairRottenOranges(int row, int col, int tm){
        this.row = row;
        this.col = col;
        this.tm = tm;
    }
}
