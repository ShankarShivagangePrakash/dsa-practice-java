package graph.shortest_path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
Problem:
    https://takeuforward.org/data-structure/dijkstras-algorithm-using-priority-queue-g-32/
    https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
 */
public class DijkstraAlgorithmUsingPriorityQueue {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: E * log (v)

        Space Complexity: O( |E| + |V| )
     */
    ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {

        // array and list to store the minimum distance from the given `src`
        // based on what is the return type of the method, use array list or array.
        int[] distance = new int[adj.size()];
        ArrayList<Integer> result = new ArrayList<>();

        // initalize all cell values to infinity
        for(int i = 0; i < distance.length; i++)
            distance[i] = (int)1e9;

        // create a priority Queue (min heap) which heapify based on the distance which is `second` variable in the Pair
        PriorityQueue<iPair> priorityQueue = new PriorityQueue<iPair>((x,y)-> x.second - y.second);

        // distance from `src` to `src` is 0, also add it to the priority Queue
        distance[src] = 0;
        priorityQueue.add(new iPair(0, src));

        while(!priorityQueue.isEmpty()){
            // while storing pair in Priority Queue we are following (distance, node)
            // because we have to sort/ heapify based on the distance.
            iPair pair = priorityQueue.poll();
            int dist = pair.first;
            int node = pair.second;

            for(iPair p : adj.get(node)){
                // in the given adjacency node, it will be of the form (node, edgeWeight)
                int adjNode = p.first;
                int edgeWeight = p.second;

                if(dist + edgeWeight < distance[adjNode]){
                    distance[adjNode] = dist + edgeWeight;
                    priorityQueue.add(new iPair(distance[adjNode], adjNode));
                }
            }
        }

        // move arrays content to array list, depends on problem
        for(int i = 0; i < distance.length; i++){
            if(distance[i] == (int)1e9)
                result.add(-1);
            else
                result.add(distance[i]);
        }

        return result;
    }
}

class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
