package graph.minmum_spanning_tree_disjoint_set;

import java.util.HashSet;

/*
Problem:
    https://takeuforward.org/data-structure/making-a-large-island-dsu-g-52/
    https://leetcode.com/problems/making-a-large-island/description/
 */
public class MakeLargeIsland {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N^2)+O(N^2) ~ O(N^2)
            where N = total number of rows of the grid.
            Inside those nested loops, all the operations are taking apparently constant time.
            So, O(N^2) for the nested loop only, is the time complexity.

        Space Complexity: O(2*N^2)
            where N = the total number of rows of the grid.
            This is for the two arrays i.e.
            parent array and size array of size N^2 inside the Disjoint set.
     */
    public int largestIsland(int[][] grid) {
        // since it is given as grid will be of n * n size, no need of m = number of columns
        int n = grid.length;

        DisjoinSetByUnionSet disjoinSetByUnionSet = new DisjoinSetByUnionSet(n * n);

        // helps to move to adjacent 4 cells
        int[] deltaRow = {0, 1, 0, -1};
        int[] deltaCol = {1, 0, -1, 0};

        // Step 1: Form components
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // current cell is having value as 1, means it is island
                // now check, is it having islands in any adjacent cells and if so, merge islands
                if(grid[i][j] == 1){
                    for(int k = 0; k < 4; k++){
                        int nRow = i + deltaRow[k];
                        int nCol = j + deltaCol[k];

                        if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < n
                            && grid[nRow][nCol] == 1) {
                            int nodeNo = i * n + j;
                            int adjNodeNo = nRow * n + nCol;
                            disjoinSetByUnionSet.unionBySet(nodeNo, adjNodeNo);
                        }
                    }
                }
            }
        }

        // Step 2: make 0's as 1 and check size of larger island possible
        int maxConnections = 0;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // if the cell value is 1, just skip iteration
                if(grid[i][j] == 1)
                    continue;

                // hash set stores the adjacent components ultimate parent
                HashSet<Integer> componentsSet = new HashSet<>();
                for(int k = 0; k < 4; k++){
                    int nRow = i + deltaRow[k];
                    int nCol = j + deltaCol[k];

                    /* if the adjacent cell is valid and has the value as 1,
                     we can connect current cell with adjacent cell component
                     so we will add the adjacent cell component's ultimate parent to set*/
                    if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < n && grid[nRow][nCol] == 1)
                        componentsSet.add(disjoinSetByUnionSet.findUltimateParent(nRow * n + nCol));
                }

                /* now get the size of each component stored in the set and
                 to that total add + 1, since we are converting a zero to one
                 and compute max Connections possible*/
                int sizeTotal = 0;
                for(int it : componentsSet)
                    sizeTotal += disjoinSetByUnionSet.size.get(it);

                maxConnections = Math.max(maxConnections, sizeTotal + 1);
            }
        }

        /* edge case: Assume the whole matrix has only 1's
         in that case step 2 will not execute
         so, we will just check any components size is greater than the maxConnections computed so far,
         if it is larger, update*/
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int ultimateParent = disjoinSetByUnionSet.findUltimateParent(i * n + j);
                maxConnections = Math.max(maxConnections,disjoinSetByUnionSet.size.get(ultimateParent));
            }
        }

        return maxConnections;
    }
}
