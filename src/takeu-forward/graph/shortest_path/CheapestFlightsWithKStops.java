package graph.shortest_path;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/g-38-cheapest-flights-within-k-stops/
    https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
 */
public class CheapestFlightsWithKStops {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O( N )
            Additional log(N) of time eliminated here because we’re using a simple queue rather than a priority queue
            which is usually used in Dijkstra’s Algorithm

            Where N = Number of flights / Number of edges.

        Space Complexity:  O( |E| + |V| )
            for the adjacency list, queue, and the dist array

            Where E = Number of edges (flights.size()) and V = Number of Airports.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        ArrayList<ArrayList<PairCheapestFlight>> adj = new ArrayList<>();

        for(int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for(int i = 0; i < flights.length; i++){
            // flights[i][0] source vertex,
            // flights[i][1] other vertex
            // flights[i][2] edge weight
            adj.get(flights[i][0]).add(new PairCheapestFlight(flights[i][1], flights[i][2]));
        }

        // we have n nodes(cities), we have find distace for those nodes from `src` vertex
        int[] distance = new int[n];
        for(int i = 0; i < n; i++)
            distance[i] = Integer.MAX_VALUE;


        // Explained, why Queue over Priority Queue in notes,
        // Tuple will store (number of stops already consumed, current node, distance taken)
        Queue<Tuple> queue = new LinkedList<>();

        distance[src] = 0;
        queue.add(new Tuple(0, src, 0));

        // Even though, it is Dijkstra Algorithm
        // we are not using Priority Queue, so log(V) in the time complexity component will not be applicable
        // so, Time complexity becomes = E = number of edges
        // Time Complexity: O(n)
        while(!queue.isEmpty()){
            Tuple tuple = queue.poll();

            int stops = tuple.first;
            int node = tuple.second;
            int dist = tuple.third;

            // if the number of stops taken currently has exceeded k means, don't move further in that path
            // as it will lead to wrong answers or it could be the case that we have reached destination node
            // but there could be other efficient paths, continue loop to explore them as well
            // since we are sorting based on stops not distance
            if(stops > k)
                continue;

            for(PairCheapestFlight p : adj.get(node)){
                int adjNode = p.node;
                int edgeWeight = p.weight;

                // if we are able to reach adjacent node with better distance
                // and number of stops taken is equal to k means, we can still allow this path
                if(dist + edgeWeight < distance[adjNode] && stops <= k){
                    distance[adjNode] = dist + edgeWeight;
                    queue.offer(new Tuple(stops + 1, adjNode, distance[adjNode]));
                }
            }
        }

        // if distance[destination] is still integer max value means
        // we were not able to reach destination, so return -1
        if(distance[dst] == Integer.MAX_VALUE)
            return -1;

        // we reached destination, return the value
        return distance[dst];
    }
}

class PairCheapestFlight {

    int node;
    int weight;

    public PairCheapestFlight(int edge, int weight){
        this.node = edge;
        this.weight = weight;
    }
}
