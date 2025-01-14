package graph.minmum_spanning_tree_disjoint_set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Problem:
    https://takeuforward.org/graph/bridges-in-graph-using-tarjans-algorithm-of-time-in-and-low-time-g-55/
    https://www.geeksforgeeks.org/bridge-in-a-graph/
    https://leetcode.com/problems/critical-connections-in-a-network/description/
 */
public class BridgesInGraph {

    int timer = 1;

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(V+2E)
            where V = no. of vertices, E = no. of edges.
            It is because the algorithm is just a simple DFS traversal.

        Space Complexity: O(V+2E) + O(3V)
            where V = no. of vertices, E = no. of edges.
            O(V+2E) to store the graph in an adjacency list and O(3V) for the three arrays
            i.e. tin, low, and vis, each of size V.
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for(List<Integer> it : connections){
            int u = it.get(0);
            int v = it.get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] visited = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];

        List<List<Integer>> bridges = new ArrayList<>();

        dfsRecursive(0, -1, adj, visited, tin, low, bridges);

        return bridges;
    }

    public void dfsRecursive(int node, int parent,
                             ArrayList<ArrayList<Integer>> adj,
                             int[] visited, int[] tin, int[] low,
                             List<List<Integer>> bridges){

        visited[node] = 1;
        tin[node] = low[node] = timer;
        timer++;

        for(int it : adj.get(node)){
            if(it == parent)
                continue;

            if(visited[it] == 0){
                dfsRecursive(it, node, adj, visited, tin, low, bridges);
                low[node] = Math.min(low[node], low[it]);

                // check the edge between node --it is bridge
                if(low[it] > tin[node])
                    bridges.add(Arrays.asList(it, node));
            }
            else
                low[node] = Math.min(low[node], low[it]);
        }
    }
}
