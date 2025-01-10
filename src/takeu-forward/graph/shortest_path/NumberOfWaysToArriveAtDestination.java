package graph.shortest_path;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
Problem:
    https://takeuforward.org/data-structure/g-40-number-of-ways-to-arrive-at-destination/
    https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/
 */
public class NumberOfWaysToArriveAtDestination {

    /*
    Approach:
        Explained in the notes, check

        Time Complexity: O( E* log(V))
            As we are using simple Dijkstra's algorithm here, the time complexity will be or the order E * log(V)
            Where E = Number of edges and V = No. of vertices.

        Space Complexity :  O(N)
            for dist array + ways array + approximate complexity for priority queue
            Where, N = Number of nodes.
     */
    public int countPaths(int n, int[][] roads) {

        // it is given in problem statement that, arithmetic operations has to undergo module of below value
        int mod = (int)(1e9 + 7);

        // creating adjaceny list from the 2D matrix
        ArrayList<ArrayList<Pair<Integer, Integer>>> adj = new ArrayList<ArrayList<Pair<Integer, Integer>>>();
        for(int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for(int i = 0; i < roads.length; i++){
            adj.get(roads[i][0]).add(new Pair<>(roads[i][1], roads[i][2]));
            adj.get(roads[i][1]).add(new Pair<>(roads[i][0], roads[i][2]));
        }

        // create distance and weight array and initalise
        int[] distance = new int[n];
        int[] ways = new int[n];

        for(int i = 0; i < n; i++){
            ways[i] = 0;
            distance[i] = Integer.MAX_VALUE;
        }

        // distance which we need to traver to src node 0 is 0
        // and number of ways to reach it is only 1
        distance[0] = 0;
        ways[0] = 1;

        // Priority Queue will store entries in the format (distance, node)
        PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<Pair<Integer, Integer>>((x, y) -> (int)x.getKey() - (int)y.getKey());

        priorityQueue.add(new Pair<>(0, 0));

        while(!priorityQueue.isEmpty()){
            Pair pair = priorityQueue.poll();
            int dist = (int) pair.getKey();
            int node = (int) pair.getValue();

            for(Pair p : adj.get(node)){
                int adjNode = (int) p.getKey();
                int edgeWeight = (int) p.getValue();

                // if the new cost is less than the current cost, then update
                // number of ways to reach that new node will be same as number of ways to reach current node
                if(dist + edgeWeight < distance[adjNode]){
                    distance[adjNode] = dist + edgeWeight;
                    ways[adjNode] = ways[node];
                    priorityQueue.add(new Pair<>(distance[adjNode], adjNode));
                }
                // if cost is equal means, current path is a new way to reach new node
                // so we have consider all paths which are previously existing and explored now
                else if(dist + edgeWeight == distance[adjNode])
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
            }
        }
        return (ways[n-1]) % mod;
    }
}
