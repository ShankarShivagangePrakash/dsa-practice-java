package graph.minmum_spanning_tree_disjoint_set;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Problem:
https://takeuforward.org/data-structure/kruskals-algorithm-minimum-spanning-tree-g-47/
    https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
 */
public class KruskalAlgorithm {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N+E) + O(E logE) + O(E * 4 alpha *2)
            where N = no. of nodes and E = no. of edges.
            O(N+E) for extracting edge information from the adjacency list.
            O(E logE) for sorting the array consists of the edge tuples.
            Finally, we are using the disjoint set operations inside a loop.
            The loop will continue to E times. Inside that loop,
            there are two disjoint set operations like findUPar() and UnionBySize()
            each taking 4 * alpha and so it will result in 4 alpha * 2.
            That is why the last term O(E*4 alpha*2) is added.

        Space Complexity: O(N) + O(N) + O(E)
            where E = no. of edges and N = no. of nodes.
            O(E) space is taken by the array that we are using to store the edge information.
            And in the disjoint set data structure, we are using two N-sized arrays i.e.
            a parent and a size array (as we are using unionBySize() function otherwise,
            a rank array of the same size if unionByRank() is used) which result in the first two terms O(N).
     */
    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        List<Edge> edges = new ArrayList<>();

        // edges will be of the form, for every node, there will be pair but that pair is int[]
        // using that created edges
        for(int i = 0; i < V; i++){
            for(int j = 0; j < adj.get(i).size(); j++){
                int adjNode = adj.get(i).get(j)[0];
                int wt = adj.get(i).get(j)[1];
                int node = i;

                edges.add(new Edge(node, adjNode, wt));
            }
        }
        //sort the edges based on edge weight
        Collections.sort(edges);
        int mstSum = 0;

        // create disjoing set object
        DisjoinSetByUnionSet disjoinSetByUnionSet = new DisjoinSetByUnionSet(V);

        for(int i = 0; i < edges.size(); i++){
            int wt = edges.get(i).weight;
            int u = edges.get(i).src;
            int v = edges.get(i).dest;

            // if the Ultimate parent of u and v are different, they are not connected components
            // so you can create an edge with weight wt, so add wt to mstSum
            // and peform unionBySet which draws the edge
            if(disjoinSetByUnionSet.findUltimateParent(u) != disjoinSetByUnionSet.findUltimateParent(v)){
                mstSum  += wt;
                disjoinSetByUnionSet.unionBySet(u, v);
            }
        }

        return mstSum;
    }
}

class Edge implements Comparable<Edge> {
    int src, dest, weight;
    Edge(int _src, int _dest, int _wt) {
        this.src = _src; this.dest = _dest; this.weight = _wt;
    }

    // Comparator function used for sorting edgesbased on their weight
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
};
