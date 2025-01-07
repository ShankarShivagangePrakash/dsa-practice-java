package graph.shortest_path;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

/*
Problem:
    https://takeuforward.org/data-structure/dijkstras-algorithm-using-priority-queue-g-32/
    https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
 */
public class DijkstraAlgorithmUsingSet {

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

        // create asorted set which sorts based on the distance which is `second` variable in the Pair (adjacency list pair)
        TreeSet<iPair> sortedSet = new TreeSet<>((x, y)-> x.second - y.second);

        // distance from `src` to `src` is 0, also add it to the set
        distance[src] = 0;
        sortedSet.add(new iPair(0, src));

        while(!sortedSet.isEmpty()){
            // while storing pair in set we are following (distance, node)
            // because we have to sort based on the distance.
            iPair pair = sortedSet.pollFirst();
            int dist = pair.first;
            int node = pair.second;

            for(iPair p : adj.get(node)){
                // in the given adjacency node, it will be of the form (node, edgeWeight)
                int adjNode = p.first;
                int edgeWeight = p.second;

                if(dist + edgeWeight < distance[adjNode]){

                    /* assume there set contents looks like below
                     (10, 5) and (8, 5)
                     to reach the same node 5, we have two paths with distance 10 and 8
                     since we have the shortest path of 8, why to even consider 10 path
                     in priority queue, we will execute that iteration, but we don't update distance[5]
                     in set, we will skip that iteration, but removing that more cost path from the set*/
                    if(distance[adjNode] != (int)1e9)
                        sortedSet.remove(new iPair(distance[adjNode], adjNode));

                    distance[adjNode] = dist + edgeWeight;
                    sortedSet.add(new iPair(distance[adjNode], adjNode));
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
