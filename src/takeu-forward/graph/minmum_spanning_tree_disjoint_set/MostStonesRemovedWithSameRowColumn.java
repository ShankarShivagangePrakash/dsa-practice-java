package graph.minmum_spanning_tree_disjoint_set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
Problem:
    https://takeuforward.org/data-structure/most-stones-removed-with-same-row-or-column-dsu-g-53/
    https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/description/
 */
public class MostStonesRemovedWithSameRowColumn {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N)
            where N = total no. of stones.
            Here we have just traversed the given stones array several times.
            And inside those loops, every operation is apparently taking constant time.
            So, the time complexity is only the time of traversal of the array.

        Space Complexity: O(2* (max row index + max column index))
            for the parent and size array inside the Disjoint Set data structure.
     */
    public int removeStones(int[][] stones) {
        int maxRow = -1, maxCol = -1;

        for(int i = 0; i < stones.length; i++){
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }

        DisjoinSetByUnionSet disjoinSetByUnionSet = new DisjoinSetByUnionSet(maxRow + maxCol + 1);

        /* we might be visualizing the problem as 2D matrix, but its not actually
         they have simply given only those locations where stones are present
         so we have keep track of those locations*/
        HashSet<Integer> set = new HashSet<>();

        /* now iterate through all stones location and perform union of row and column
         this way you treat them as nodes and by union we add them to same component*/
        for(int i = 0; i < stones.length; i++){
            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1] + maxRow + 1;

            disjoinSetByUnionSet.unionBySet(nodeRow, nodeCol);

            // add nodeRow and nodeCol to keep track of locations
            set.add(nodeRow);
            set.add(nodeCol);
        }

        // now iterate through set and check number of components
        int numberOfComponents = 0;
        for(int node : set){
            if(disjoinSetByUnionSet.findUltimateParent(node) == node)
                numberOfComponents++;
        }

        // number of nodes(number of rows in the stones[][]) - number of components
        return stones.length - numberOfComponents;
    }
}
