package graph.learning.bfsdfs;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/depth-first-search-dfs/
    https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
 */
public class GraphDFS {

    /*
    Approach:
        Explained in notes, check

        Time Complexity:
            Using the recursive funtion, we will visit every node in the graph - O(n)
            for each node, we will visit its adjacency list which is nothing but indegree

            so in total, we will visit every node in the graph and total degree of the graph

            So, = O(n) + O(2 * E)

        Space Complexity:
            visited array - O(n)
            Array list to store the result - O(n)
            Recursive stack space - O(n) - worst case if the graph given is linked list
                else recursive stack space will be maximum height of the graph

            Total = O(3n) = O(n)
     */
    public ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        int v = adj.size();

        boolean[] visited = new boolean[v];
        ArrayList<Integer> dfs = new ArrayList<>();
        // we start with first node and we assume as 0
        dfsRecursive(0, adj, visited, dfs);
        return dfs;
    }

    public void dfsRecursive(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> dfs){

        // we mark current node as visited and add to the result list
        dfs.add(node);
        visited[node] = true;

        // for each node in the adjacency list of the current node
        // if it is not visited, then visit it by calling dfsRecursive()
        for(int i : adj.get(node)){
            if(visited[i] == false)
                dfsRecursive(i, adj, visited, dfs);
        }
    }
}
