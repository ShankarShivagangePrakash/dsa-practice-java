package graph.learning.bfsdfs;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/detect-cycle-in-a-directed-graph-using-dfs-g-19/
 */
public class DetectCycleDirectedGraph {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(V+E)+O(V)
            where V = no. of nodes and E = no. of edges. There can be at most V components. So, another O(V) time complexity.
            DFS time complexity for V components

        Space Complexity: O(2N) + O(N) ~ O(2N):
            O(2N) for two visited arrays and O(N) for recursive stack space.
     */
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];

        // perform DFS for each component
        for(int i = 0; i < V; i++){
            if(visited[i] == false){
                if(dfsRecursive(i, adj, visited, pathVisited) == true)
                    return true;
            }
        }
        return false;
    }

    public boolean dfsRecursive(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] pathVisited){
        visited[node] = true;
        // to keep track of current path traversing
        pathVisited[node] = true;

        for(int neighbour : adj.get(node)){
            // if the adjacent node has not been visited, visit it
            if(visited[neighbour] == false){
                // if previous DFS call returns true, then don't invoke new recusion, return true to previous recursion
                if(dfsRecursive(neighbour, adj, visited, pathVisited))
                    return true;
            }
            // in the current path, there is some one which we have already visited,
            // it means, there is a cycle, return true
            else if(pathVisited[neighbour] == true)
                return true;
        }

        // we are going back from the current path, so remove current node from the current path
        pathVisited[node] = false;
        // no cycle in the current path, so return false
        return false;
    }
}
