package graph.learning.representation;

import java.util.ArrayList;
import java.util.List;

/*
Problem:
    https://www.geeksforgeeks.org/problems/print-adjacency-list-1587115620/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=print-adjacency-list
 */
public class PrintAdjacencyList {

    public List<List<Integer>> printGraph(int V, int edges[][]) {

        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        for(int i = 0; i < edges.length; i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        return adj;
    }
}
