package graph.learning.representation.undirected;

/*
Problem:
    https://www.geeksforgeeks.org/adjacency-list-meaning-definition-in-dsa/
 */

import java.util.ArrayList;
import java.util.List;

/*
Title:
    Undirected, not weighted Graph Representation using Adjacency List

Approach:
    We create a Array list with size as n
        where n is the number of nodes

    each array list index has a list inside it which represents list of nodes having edge from that index

    Space Complexity: O(2 * E)
 */
public class UndirectedUnWeightedGraphUsingAdjacencyList {

    public static void addEdge(List<List<Integer>> adj, int i, int j) {
        // There is an edge between i and j
        // so both i and j array list have reference to other node in their list
        adj.get(i).add(j);
        adj.get(j).add(i); // Since the graph is undirected
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
