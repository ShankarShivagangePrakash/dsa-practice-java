package graph.shortest_path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
Problem:
    https://takeuforward.org/data-structure/g-35-print-shortest-path-dijkstras-algorithm/
    https://www.geeksforgeeks.org/problems/shortest-path-in-weighted-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=shortest-path-in-weighted-undirected-graph

    Note: it is given that vertices start from 1 to n
    and we need to find the shortes distance path from 1 to n

    So, this 1 based index graph problem
 */
public class ShortestPathInWeightedUndirectedGraph {

    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        // create adjacency list from the edges matrix

        ArrayList<ArrayList<iPair>> adj = new ArrayList<>();

        // since it 1 based graph, we need one more index to accomodate [1 to n]
        for(int i = 0; i < n+1; i++)
            adj.add(new ArrayList<>());

        for(int i = 0; i < edges.length; i++){
            // since it undirected graph, edge will exist from both the directions, so add edge to both adjacency list
            adj.get(edges[i][0]).add(new iPair(edges[i][1], edges[i][2]));
            adj.get(edges[i][1]).add(new iPair(edges[i][0], edges[i][2]));
        }

        // stores the shortest distance for each vertices in the graph
        int[] distance = new int[n+1];
        // stores the parent of the shortest distance path for given node
        // from immediate previous node of that path for the given node.
        int[] parent = new int[n+1];

        for(int i = 0; i < n + 1; i++){
            distance[i] = (int)1e9;
            parent[i] = i;
        }

        distance[1] = 0;

        PriorityQueue<iPair> priorityQueue = new PriorityQueue<iPair>((x, y) -> x.first - y.first);

        priorityQueue.add(new iPair(0, 1));

        while(!priorityQueue.isEmpty()){
            iPair pair = priorityQueue.poll();
            int node = pair.second;
            int dist = pair.first;

            for(iPair p : adj.get(node)){
                int adjNode = p.first;
                int edgeWeight = p.second;

                if(dist + edgeWeight < distance[adjNode]){
                    distance[adjNode] = dist + edgeWeight;
                    parent[adjNode] = node;
                    priorityQueue.add(new iPair(distance[adjNode], adjNode));
                }
            }
        }

        // result list, which holds the path from vertex 1 to n
        List<Integer> path = new ArrayList<>();

        // if there is no path to reach node `n`, then we have to return -1
        if(distance[n] == (int)1e9){
            path.add(-1);
            return path;
        }

        // set node as target node `n`
        int node = n;

        while(parent[node] != node){
            // add current node to the result list
            path.add(node);
            // move back in the path
            node = parent[node];
        }
        // when the above while loop is completed, 1 would not be added to path
        path.add(1);

        // now path is in reverse direction
        Collections.reverse(path);

        return path;
    }
}
