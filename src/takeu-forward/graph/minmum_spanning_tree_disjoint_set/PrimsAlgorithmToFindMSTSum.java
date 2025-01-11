package graph.minmum_spanning_tree_disjoint_set;


import java.util.*;

/*
Problem:
    https://takeuforward.org/data-structure/prims-algorithm-minimum-spanning-tree-c-and-java-g-45/
    https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
 */
public class PrimsAlgorithmToFindMSTSum {

    /*
    Approach:
        Almost same as how explained in the notes.
        But only few changes
        Creating adjacency list from 2D matrix each row represents a edge

        Tuple used to compute Minimum Spanning Tree

        Time Complexity: O(E * log E)
            Explained in program, check

        Space Complexity: O( V + E)
            visited array of size V
            priority Queue can have at max E edges, so E
            adjacency list you can ignore, as it should be given in problem statement
     */
    static int spanningTree(int V, int E, List<List<int[]>> adj) {

        ArrayList<ArrayList<PairPrimsAlgorithm<Integer, Integer>>> adjacencyList = new ArrayList<>();

        // tuple format will be (weight or cost of the edge, node (destination node), parent (source node)
        PriorityQueue<PairPrimsAlgorithm<Integer, Integer>> priorityQueue = new PriorityQueue<PairPrimsAlgorithm<Integer, Integer>>((x, y) -> x.first - y.first);

        int[] visited = new int[V];

        /* we will start traversing from node 0
         since it won't have any previous edge, we consider parent as -1
         destination is 0 (second parameter)
         weight is also 0, since there is no edge to this 0 (first parameter)*/
        priorityQueue.add(new PairPrimsAlgorithm<>(0, 0));

        int mstSum = 0;

        // Priority Queue can have at max all edges of the tree, so E
        // multiply it by pop operation log E
        // so E * log E
        // Total = E * log E + E * log E
        // summarising = E * log E
        while(!priorityQueue.isEmpty()){
            // pop operation from queue takes log(size of PQ)
            PairPrimsAlgorithm pair = priorityQueue.poll();

            int weight = (int) pair.first;
            int node = (int) pair.second;

            if(visited[node] == 1)
                continue;

            visited[node] = 1;
            mstSum += weight;

            // this for loop at max, for the while program, it might touch all edges, so E
            // inside it we are pushing element to PQ, so log E
            // so E * log E
            // for whole program execution
            for(int[] edge : adj.get(node)){
                int adjNode = edge[0];
                int edgeWeight = edge[1];

                // if the adjacent node is not visited,
                // add it to priority Queue with parent
                if(visited[adjNode] == 0)
                    priorityQueue.add(new PairPrimsAlgorithm<>(edgeWeight, adjNode));
            }
        }

        // print mstSum
        return mstSum;
    }
}