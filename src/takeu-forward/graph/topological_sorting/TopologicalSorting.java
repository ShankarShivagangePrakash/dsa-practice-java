package graph.topological_sorting;

import java.util.ArrayList;
import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/topological-sort-algorithm-dfs-g-21/
    https://www.geeksforgeeks.org/problems/topological-sort/1
 */
public class TopologicalSorting {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(V+E)+O(V),
            where V = no. of nodes and E = no. of edges.
            There can be at most V components. So, another O(V) time complexity.

        Space Complexity: O(2N) + O(N) ~ O(2N)
            O(n) for the visited array and the stack carried during DFS calls and O(N) for recursive stack space,
            where N = no. of nodes.
     */
    static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        int v = adj.size();
        boolean[] visited = new boolean[v];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < v; i++){
            if(visited[i] == false)
                dfsRecursive(i, adj, visited, stack);
        }

        // move stack contents to array list, i.e is the topological order of the given DAG
        ArrayList<Integer> topologicalOrder = new ArrayList<>();
        while(!stack.isEmpty())
            topologicalOrder.add(stack.pop());

        return topologicalOrder;
    }

    public static void dfsRecursive(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack){
        visited[node] = true;

        for(int neighbour : adj.get(node)){
            if(visited[neighbour] == false)
                dfsRecursive(neighbour, adj, visited, stack);
        }

        // current node traversal is completed, so add it to the stack
        stack.add(node);
    }
}
