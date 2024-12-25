package graph.learning.representation.directed;

/*
Problem:
    https://www.geeksforgeeks.org/adjacency-matrix/
 */

/*
Title:
    Undirected, not weighted Graph Representation using Adjacency Matrix

Approach:
    We create a matrix or 2D with size n * n
        where n is the number of nodes

    For every edge, assign corresponding cell in matrix with value as weight of that edge

    Space Complexity: O(n^2)
 */
public class DirectedWeightedGraphUsingAdjacencyMatrix {

    public static void addEdge(int[][] mat, int i, int j, int weight) {
        mat[i][j] = weight;
    }

    public static void displayMatrix(int[][] mat) {
        for (int[] row : mat) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // Create a graph with 4 vertices and no edges
        // Note that all values are initialized as 0
        int n = 4;
        int[][] mat = new int[n][n];

        // Now add edges one by one
        addEdge(mat, 0, 1, 5);
        addEdge(mat, 0, 2, 4);
        addEdge(mat, 1, 2, 3);
        addEdge(mat, 2, 3, 2);

        System.out.println("Adjacency Matrix Representation");
        displayMatrix(mat);
    }
}
