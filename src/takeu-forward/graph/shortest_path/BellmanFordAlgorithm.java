package graph.shortest_path;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/bellman-ford-algorithm-g-41/
    https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1
 */
public class BellmanFordAlgorithm {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(V*E)
            we perform V-1 iterations relaxing all edges
            means V * E
            where V = no. of vertices and E = no. of Edges.

        Space Complexity: O(V)
            for the distance array which stores the minimized distances.
     */
    static int[] bellmanFord(int V, int[][] edges, int src) {
        int[] distance = new int[V];

        Arrays.fill(distance, (int)1e8);
        distance[src] = 0;

        // Perform V-1 iterations, relaxing edges
        for(int i = 0; i < V-1; i++){
            for(int[] it : edges){
                int u = it[0];
                int v = it[1];
                int edgeWeight = it[2];

                if(distance[u] != (int)1e8 && distance[u] + edgeWeight < distance[v])
                    distance[v] = distance[u] + edgeWeight;
            }
        }

        // check, if there are any negative cycles
        // if there are negative cycles, on Nth/Vth complete iteration
        // distance gets updated. So in that case return -1 as problem statement demands
        for(int[] it : edges){
            int u = it[0];
            int v = it[1];
            int edgeWeight = it[2];

            if(distance[u] != (int)1e8 && distance[u] + edgeWeight < distance[v])
                return new int[]{-1};
        }

        return distance;
    }
}
