package graph.shortest_path;

import java.util.ArrayList;
import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/shortest-path-in-directed-acyclic-graph-topological-sort-g-27/
    https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=shortest-path-in-undirected-graph
 */
public class ShortestPathInDAG {

    /*
    Approach:
        It is a two step process
            - create topological sort of the DAG using DFS or BFS, we will use DFS in this program
            - pop the element from the topological sort and compare the distance from
                those nodes to its adjacent nodes with actual distance of adjacent nodes
                and update it with shorter distances

        Time Complexity: O(V + E)
            O(V + E) {for the topological sort}
            O(V+ E) for while loop, which computed distance of each node from src

            Where V= number of vertices and V= number of edges.

        Space Complexity:  O(V + E)
            O(V) {for the stack storing the topological sort}
            O(V) {for storing the shortest distance for each node}
            O(V) {for the visited array}
            O(V+E) {for the adjacency list}

            Where V = number of vertices and E = number of edges.
     */
    public int[] shortestPath(int V, int E, int[][] edges) {
        // since it is DAG with edges having weights
        // we need to store pairs in the array list
        ArrayList<ArrayList<PairDAGWithWeight>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++)
            adj.add(new ArrayList<PairDAGWithWeight>());

        for(int i = 0; i < edges.length; i++){
            // each row will have three columns and they represents
            // u -> v and the cost of the edge from u to v
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new PairDAGWithWeight(v, wt));
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for(int i = 0; i < V; i++) {
            if(visited[i] == false)
                topologicalSortDFSRecursive(i, adj, visited, stack);
        }

        // Step 2: computing distance
        int[] distance = new int[V];
        for(int i = 0; i < V; i++)
            distance[i] = (int)1e9;

        // since we are assuming 0 is the src
        // distance from src to src will be 0
        distance[0] = 0;
        // there are V vertices,
        // so, O(V + E)
        while(!stack.isEmpty()){
            int node = stack.pop();

            // in total, this for loop will run for E times
            for(int i = 0; i < adj.get(node).size(); i++){
                int v = adj.get(node).get(i).node;
                int wt = adj.get(node).get(i).weight;

                if(distance[node] + wt < distance[v])
                    distance[v] = distance[node] + wt;
            }
        }
        for(int i = 0; i < V; i++) {
            if(distance[i] == (int)1e9)
                distance[i] = -1;
        }
        return distance;
    }

    public void topologicalSortDFSRecursive(int node, ArrayList<ArrayList<PairDAGWithWeight>> adj , boolean[] visited, Stack<Integer> stack){
        visited[node] = true;

        for(int i = 0; i < adj.get(node).size(); i++){
            // we are getting the adjacency list of node
            // then, in that adjacency list, we are getting the ith pair
            // in that pair, `node` object represents another node `v`
            int v = adj.get(node).get(i).node;
            if(visited[v] == false)
                topologicalSortDFSRecursive(v, adj, visited, stack);
        }
        stack.push(node);
    }
}

class PairDAGWithWeight {
    int node;
    int weight;

    PairDAGWithWeight(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
}
