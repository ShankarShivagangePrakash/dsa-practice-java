package graph.minmum_spanning_tree_disjoint_set;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/articulation-point-in-graph-g-56/
    https://www.geeksforgeeks.org/problems/articulation-point-1/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=articulation-point
 */
public class ArticulationPoint {

    int timer = 1;

    /*
    Approach:
        Explained in notes, check
        One liner story, a node `i` becomes articulation point
        if any of its adjacent nodes, cannot reach node before `i` without `i`

        Time Complexity: O(V+2E)
            where V = no. of vertices, E = no. of edges.
            It is because the algorithm is just a simple DFS traversal.

        Space Complexity: O(3V)
            where V = no. of vertices.
            O(3V) is for the three arrays i.e. tin, low, and vis, each of size V.
     */
    public ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj)
    {
        int[] visited = new int[V];
        int[] tin = new int[V];
        int[] low = new int[V];
        int[] articulationPointsMark = new int[V];

        // DFS traversal
        for(int i = 0; i < V; i++) {
            if(visited[i] == 0)
            dfsRecursive(i, -1, adj, visited, tin, low, articulationPointsMark);
        }

        ArrayList<Integer> articulationPoints = new ArrayList<>();

        // check for articulation points and add to result list
        for (int i = 0; i < V; i++) {
            if (articulationPointsMark[i] == 1)
                articulationPoints.add(i);
        }

        // if there are no articulation points, then we have to return [-1]
        if(articulationPoints.isEmpty())
            articulationPoints.add(-1);

        return articulationPoints;
    }

    public void dfsRecursive(int node, int parent,
                             ArrayList<ArrayList<Integer>> adj,
                             int[] visited,
                             int[] tin,
                             int[] low,
                             int[] articulationPointsMark){

        visited[node] = 1;
        tin[node] = low[node] = timer;
        timer++;

        int children = 0;
        for(int it : adj.get(node)){
            if(it == parent)
                continue;

            if(visited[it] == 0){
                dfsRecursive(it, node, adj, visited, tin, low, articulationPointsMark);

                low[node] = Math.min(low[node], low[it]);

                /* if, it can reach any node before `node`
                 then `node` cannot be articulation point
                 else it is articulation point, we are checking for this else case
                 and we have to exclude first node `0` from this logic*/
                if(low[it] >= tin[node] && parent != -1)
                    articulationPointsMark[node] = 1;

                // this logic is to count number of childs and this logic is specific to node `0`
                children++;
            }
            /* else case means that adjacent node is already visited
             so comparing with its low[it] is not useful, so compare with its tin[it]*/
            else
                low[node] = Math.min(low[node], tin[it]);
        }

        /* articulation point logic for node `0` first node
         if first node has multiple children, then it is an articulation point*/
        if(children > 1 && parent == -1)
            articulationPointsMark[node] = 1;
    }
}
