package graph.shortest_path;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/shortest-path-in-undirected-graph-with-unit-distance-g-28/
    https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=shortest-path-in-undirected-graph-having-unit-distance
 */
public class ShortestPathInUndirectedGraphWithUnitWeight {

    /*
    Problem statement:
        Find the shortest distance from given `src` node to every node in the given graph

        Approach:
            we will create a distance array each cell initalized with maximum value possible
            then, we initalize distance[src] = 0, distance from `src` to `src` is 0

            add `src` to queue

            then perform `BFS`
                 for every node popped from the queue might have edge to many other nodes
                 this can be found by the adjacenyc list of the popped node
                 means, there is a path for all those neigbhours from the popped node

                 so, there can be shorter path
                 what we will do is,
                 since we are given undirected graph with unit weight
                    we do, = distance from the src vertex to `node` - currently popped element
                        distance[node] + 1 < distance[neighbour]
                        then we will update
                        dist[neighbour] = dist[node] + 1;
                        also, we will add this neighbour to queue
                        as we have found a shorter path to neigbhour
                        and from that neigbhour, we can find shorter paths to other connected vertices

            Time Complexity:
                = O(E) + + O(N + 2E) + O(N)
                  O(E) { for creating the adjacency list from given list ‘edges’}
                  O(N + 2E) { for the BFS Algorithm}
                  O(N) { for adding the final values of the shortest path in the resultant array} ~ O(N+2E).

                Where N= number of vertices and V= number of edges.

            Space Complexity:   O(n + E)
                O( N) {for the stack storing the BFS}
                O(N) {for the resultant array}
                O(N) {for the dist array storing updated shortest paths}
                O( N+2E) {for the adjacency list} ~ O(N+M)
     */
    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        int v = adj.size();

        int[] dist = new int[v];
        // initalize distance array with maximum value possible
        for(int i = 0; i < v; i++)
            dist[i] = (int)1e9;

        Queue<Integer> queue = new LinkedList<>();
        // add `src` to queue, since we need to find distance for every node in the graph w.r.t `src`
        queue.add(src);
        // distance from `src` to `src` is 0
        dist[src] = 0;


        while(!queue.isEmpty()){
            int node = queue.poll();

            for(int neighbour : adj.get(node)){
                if(dist[node] + 1 < dist[neighbour]) {
                    dist[neighbour] = dist[node] + 1;
                    queue.add(neighbour);
                }
            }
        }

        // if node is unreachable, then we have to set the value as -1
        for(int i = 0; i < v; i++){
            if(dist[i] == (int)1e9)
                dist[i] = -1;
        }
        return dist;
    }
}
