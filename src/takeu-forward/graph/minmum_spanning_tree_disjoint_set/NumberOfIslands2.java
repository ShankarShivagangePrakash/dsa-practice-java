package graph.minmum_spanning_tree_disjoint_set;

import java.util.ArrayList;
import java.util.List;

/*
Problem:
    https://takeuforward.org/graph/number-of-islands-ii-online-queries-dsu-g-51/
    https://leetcode.com/problems/number-of-islands-ii/description/
 */
public class NumberOfIslands2 {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(Q*4α) ~ O(Q)
            where Q = no. of queries.
            The term 4α is so small that it can be considered constant.

        Space Complexity: O(Q) + O(N*M) + O(N*M)
            where Q = no. of queries, N = total no. of rows, M = total no. of columns.
            The last two terms are for the parent and the size array used inside the Disjoint set data structure.
            The first term is to store the answer.
     */
    public List<Integer> numOfIslands(int n, int m, int[][] operators) {
        // count refers number of islands so far after executing i operators/ queries
        int count = 0;
        // which all cells have been marked as islands from operators / queries
        int[][] visited = new int[n][m];

        // number of islands after each operations/queries
        List<Integer> answer = new ArrayList<>();
        // create disjoint data structure with size as total number of cells in the given 2D matrix
        DisjoinSetByUnionSet disjoinSetByUnionSet = new DisjoinSetByUnionSet(n * m);

        // helps to move to adjacent 4 cells
        int[] deltaRow = {0, 1, 0, -1};
        int[] deltaCol = {1, 0, -1, 0};

        // iterate through each operations/queries
        for(int i = 0; i < operators.length; i++){
            // get the row and col of the cell which we have to mark as island
            int row = operators[i][0];
            int col = operators[i][1];

            /* if this cell has been visited early means,
             island is already available, at this index and we have calculated number of islands
             so number of islands possible will not change.*/
            if(visited[row][col] == 1){
                answer.add(count);
                continue;
            }

            /* this cell was not visited,
             marks it as visited indicating add island at this cell
             also, increment count*/
            visited[row][col] = 1;
            count++;

            for(int ind = 0; ind < 4; ind++){
                // move in all 4 directions
                int nRow = row + deltaRow[i];
                int nCol = col + deltaCol[i];

                // if the new cell is valid
                if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m){
                    // and there is an island at that adjacent cell
                    if(visited[nRow][nCol] == 1){
                        // get the node number of current node and adjacent node
                        int nodeNo = row * m + col;
                        int adjNodeNo = nRow * m + nCol;

                        // if they both doesn't belong to same ultimate parent
                        // then, we have to link it, when we link, number of islands count will reduce.
                        if(disjoinSetByUnionSet.findUltimateParent(nodeNo) != disjoinSetByUnionSet.findUltimateParent(adjNodeNo)){
                            count--;
                            // since we have linked, the islands, we have to call union method so that disjoint will link these two nodes
                            disjoinSetByUnionSet.unionBySet(nodeNo, adjNodeNo);
                        }
                    }
                }
            }
        }
        return answer;
    }
}
