package graph.learning.bfsdfs;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/graph/bipartite-graph-dfs-implementation/
    https://leetcode.com/problems/is-graph-bipartite/description/
 */
public class BipartiteGraph {

    /*
    Approach:
        Explained in notes, check

        Time Complexity:
             O(V + 2E), Where V = Vertices, 2E is for total degrees as we traverse all adjacent nodes.

        Space Complexity:
            O(3V) ~ O(V), Space for DFS stack space, colour array and an adjacency list.
     */
    public boolean isBipartite(int[][] graph) {
        int v = graph.length;

        // we have been given adjacency matrix, create adjacency list
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for(int i = 0; i < v; i++)
            adjacencyList.add(new ArrayList<>());

        for(int i = 0; i < graph.length; i++){
            for (int j : graph[i]) { // Graph is given as adjacency list, no need to check matrix indices.
                adjacencyList.get(i).add(j);
                adjacencyList.get(j).add(i); // Assuming undirected graph
            }
        }

        // instead of visited array, create coloured array which will have default value of -1 before colouring
        // and we will colour it with either 0/1
        int[] colour = new int[v];
        for(int i = 0; i < v; i++)
            colour[i] = -1;

        // there can be many components in the graph, so perform DFS for each component
        for(int i = 0; i < v; i++){
            if(colour[i] == -1){
                // if the DFS traversal returns false, then it is not a Bipartite graph so return false
                if(dfsRecursive(i, 0, adjacencyList, colour) == false)
                    return false;
            }
        }
        return true;
    }

    public boolean dfsRecursive(int node, int currentColour, ArrayList<ArrayList<Integer>> adjacencyList, int[] colour){

        colour[node] = currentColour;

        for(int i : adjacencyList.get(node)){
            // if any adjacent node is not visited, colour it with opposite color
            if(colour[i] == -1){
                // from previous recursion, if you get false as the output,
                // don't invoke new recursion just return this value to previous recursion
                if(dfsRecursive(i,  1 - currentColour, adjacencyList, colour) == false)
                    return false;
            }
            // adjacent node has been already visited and having same color, so return false
            else if(colour[i] == currentColour)
                return false;
        }

        return true;
    }
}
