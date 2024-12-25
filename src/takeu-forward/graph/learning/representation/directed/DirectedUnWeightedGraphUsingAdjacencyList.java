package graph.learning.representation.directed;

/*
Problem:
    https://www.geeksforgeeks.org/adjacency-list-meaning-definition-in-dsa/
 */

import java.util.ArrayList;
import java.util.List;

/*
Title:
    Directed, not weighted Graph Representation using Adjacency List

Approach:
    We create a Array list with size as n
        where n is the number of nodes

    each array list index has a list inside it which represents list of nodes having edge from that index

    Space Complexity: O(E)
 */
public class DirectedUnWeightedGraphUsingAdjacencyList {

    public static void addEdge(List<List<Integer>> adj, int i, int j) {
        // There is an edge between i and j
        // since it is a directed graph we have to add j to i list only
        adj.get(i).add(j);
    }

    public static void displayMatrix(List<List<Integer>> adj) {
        for (int i = 0; i < adj.size(); i++) {
            System.out.print(i + " ---> ");
            for (int j : adj.get(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // Create a graph with 4 vertices and no edges
        // Note that all values are initialized as 0
        int n = 4;
        List<List<Integer>> adj = new ArrayList<>();

        // for every node, we need to create a arraylist of its own, that's what this for loop is doing
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Now add edges one by one
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);

        System.out.println("Adjacency List Representation");
        displayMatrix(adj);
    }
}
