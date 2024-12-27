package graph.learning.bfsdfs;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/number-of-provinces/
    https://leetcode.com/problems/number-of-provinces/description/
 */
public class NumberOfProvinces {

    /*
    Approach:
        if we find number of distinct disconnected components in the graph, we are done

        To do that, we can use either DFS or BFS, we will use DFS

        we have a visited array.
        we perform DFS for every unvisited index, and increment counter
        By the time, all visited indices are marked, counter will have value as number of distinct components in the graph
        That is equivalent to number of provinces
        return it

        Note: In the problem, they have given, adjacency matrix
              we create an adjacency list from it and then perform DFS

        Time Complexity:

            O(n) + O(V + 2E)
                O(V+ + 2E) - is for DFS

            but if you check, we will call DFS for n times for all nodes available in the graph

        Space Complexity:
            we are using visited array - O(n)
            Recursion stack space in worst case scenario - O(n)

            Total = O(2n)
     */
    public int findCircleNum(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < isConnected.length; i++)
            adjacencyList.add(new ArrayList<>());

        for(int i = 0; i < isConnected.length; i++){
            for(int j = 0; j < isConnected[i].length; j++){
                // i != j condition is to avoid self loops
                if(isConnected[i][j] == 1 && i != j){
                    adjacencyList.get(i).add(j);
                    adjacencyList.get(j).add(i);
                }
            }
        }

        int v = isConnected.length;
        boolean[] visited = new boolean[v];
        int counter = 0;

        for(int i = 0; i < v; i++){
            if(visited[i] == false){
                counter++;
                dfsRecursive(i, adjacencyList, visited);
            }
        }

        return counter;
    }

    public void dfsRecursive(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited){

        visited[node] = true;

        for(int i : adj.get(node)){
            if(visited[i] == false){
                visited[i] = true;
                dfsRecursive(i, adj, visited);
            }
        }
    }
}
