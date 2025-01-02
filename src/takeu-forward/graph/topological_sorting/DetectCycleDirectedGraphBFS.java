package graph.topological_sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
 */
public class DetectCycleDirectedGraphBFS {

    /*
    Approach:
        We make use of Kahn's Algorithm to solve this problem

        We know, topological sort can be created only for DAG
        if, we try to create topological sort for Directed graph with cycle
            we get incomplete topological sort,
            means the size of the topological sort we get will be not equal to number of vertices in the graph

        so, we can compare the size of the topological sort and number of vertices in the graph
            to detect cycle

        Instead of creating toplogical sort, we just need size of the topological sort
            so, we will use `counter`

        Time Complexity: O(V+E),
            where V = no. of nodes and E = no. of edges. This is a simple BFS algorithm.

        Space Complexity: O(N) + O(N) ~ O(2N),
            O(N) for the indegree array, and O(N) for the queue data structure used in BFS(where N = no.of nodes).
     */
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {

        int v = adj.size();

        int[] inDegree = new int[v];
        for(int i = 0; i < v; i++){
            for(int neighbour : adj.get(i))
                inDegree[neighbour]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < v; i++){
            if(inDegree[i] == 0)
                queue.add(i);
        }

        int count = 0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            count++;

            for(int neigbour : adj.get(node)){
                inDegree[neigbour]--;
                if(inDegree[neigbour] == 0)
                    queue.add(neigbour);
            }
        }

        // count == v - no cycle, return false
        // count != v - cycle, return true
        return !(count == v);
    }
}
