package graph.minmum_spanning_tree_disjoint_set;

/*
Problem:
    https://takeuforward.org/data-structure/number-of-operations-to-make-network-connected-dsu-g-49/
    https://leetcode.com/problems/number-of-operations-to-make-network-connected/description/
 */
public class NumberOfOperationsToMakeNetworkConnected {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(E*4αlpha)+O(N*4αlpha)
            where E = no. of edges and N = no. of nodes.
            The first term is to calculate the number of extra edges and the second term is to count the number of components.
            4αlpa is for the disjoint set operation we have used and this term is so small that it can be considered constant.

        Space Complexity: O(2N)
            where N = no. of nodes.
            2N for the two arrays(parent and size) of size N we have used inside the disjoint set.
     */
    public int makeConnected(int n, int[][] connections) {

        DisjoinSetByUnionSet disjoinSetByUnionSet = new DisjoinSetByUnionSet(n);

        int extraEdgesCount = 0;
        for(int i = 0; i < connections.length; i++){
            int u = connections[i][0];
            int v = connections[i][1];

            // if the ultimate parent of u and v are same, then there is no need to add this edge again.
            // and we will treat this as extra edge
            if(disjoinSetByUnionSet.findUltimateParent(u) == disjoinSetByUnionSet.findUltimateParent(v))
                extraEdgesCount++;

            // else means, this edge is required to keep this component, so invoke union on u and v
            else
                disjoinSetByUnionSet.unionBySet(u, v);
        }

        /* now, we have number of extra edges,

         we need to compute number of distinct components in the given graph
         to do that, we have to check, how many ultimate parents are there in disjoin set
         to do that, just iterate through parent array and check, is parent[i] == i
         means, it is ultimate parent*/

        int countDistinctComponents = 0;
        for(int i = 0; i < n; i++){
            if(disjoinSetByUnionSet.parent.get(i) == i)
                countDistinctComponents++;
        }

        /* we need atleast `countDistinctComponents` - 1 extra edges to make whole graph connected
         else return -1*/
        int answer = countDistinctComponents - 1;
        if(extraEdgesCount >= answer)
            return answer;

        return -1;
    }
}
