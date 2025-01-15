package graph.minmum_spanning_tree_disjoint_set;

import java.util.ArrayList;
import java.util.Stack;

/*
Problem:
    https://takeuforward.org/graph/strongly-connected-components-kosarajus-algorithm-g-54/
    https://www.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=strongly-connected-components-kosarajus-algo
 */
public class KosaRajuAlgorithm {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(V+E) + O(V+E) + O(V+E) ~ O(V+E)
            where V = no. of vertices, E = no. of edges.
            The first step is a simple DFS, so the first term is O(V+E).
            The second step of reversing the graph and the third step, containing DFS again, will take O(V+E) each.

        Space Complexity: O(V)+O(V)+O(V+E)
            where V = no. of vertices, E = no. of edges.
            Two O(V) for the visited array and the stack we have used.
            O(V+E) space for the reversed adjacent list.

            If we are storing SCC components, it might take another O(V)
                since each node will be part of SCC
     */
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();

        int[] visited = new int[V];

        // step 1: create a stack which will stores nodes in the time of their completion
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < visited.length; i++){
            if(visited[i] == 0)
                dfsRecursive1(i, visited, adj, stack);
        }

        // step 2: reverse all the edges,
        // to do that we will have a new adjacency list, which will store all reversed edges
        ArrayList<ArrayList<Integer>> reverseAdj = new ArrayList<>();

        for(int i = 0; i < V; i++)
            reverseAdj.add(new ArrayList<>());

        for(int i = 0; i < V; i++){
            visited[i] = 0;
            for(int it : adj.get(i)){
                reverseAdj.get(it).add(i);
            }
        }

        // step 3: DFS traversal on stack
        int numberOfSCC = 0;
        // scc will store SCC components
        ArrayList<ArrayList<Integer>> scc = new ArrayList<>();
        while(!stack.isEmpty()){
            int node = stack.pop();
            if(visited[node] == 0){
                numberOfSCC++;
                dfsRecursive2(node, visited, reverseAdj, scc);
            }
        }

        // printing SCC components
        for(int i = 0; i < scc.size(); i++){
            for(int j = 0; j < scc.get(i).size(); j++){
                System.out.print(scc.get(i).get(j) + " ");
            }
            System.out.println("");
        }

        return numberOfSCC;
    }

    public void dfsRecursive1(int node, int[] visited, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack){

        visited[node] = 1;

        for(int it : adj.get(node)){
            if(visited[it] == 0)
                dfsRecursive1(it, visited, adj, stack);
        }
        stack.push(node);
    }

    public void dfsRecursive2(int node, int[] visited, ArrayList<ArrayList<Integer>> adj, ArrayList<ArrayList<Integer>> scc){

        visited[node] = 1;

        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(node);
        for(int it : adj.get(node)){
            if(visited[it] == 0) {
                dfsRecursive2(it, visited, adj, scc);
            }
        }
    }
}
