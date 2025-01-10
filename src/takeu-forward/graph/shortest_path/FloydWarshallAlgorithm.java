package graph.shortest_path;

/*
Problem:
    https://takeuforward.org/data-structure/floyd-warshall-algorithm-g-42/
    https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
 */
public class FloydWarshallAlgorithm {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(V^3)
            we have three nested loops each running for V times, where V = no. of vertices.

        Space Complexity: O(V^2)
            where V = no. of vertices.
            This space complexity is due to storing the adjacency matrix of the given graph.
            Even though we are solving inplace, it is used to solve the problem, so we have consider it
     */
    public void shortestDistance(int[][] mat) {
        int v = mat.length;

        /* pre configuration,
         mat[i][j] = -1 means, there is no edge from i to j
         we update the value for such cells to ie9
         and we update mat[i][i] = 0*/
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                if(mat[i][j] == -1)
                    mat[i][j] = (int)1e9;
                if(i == j)
                    mat[i][j] = 0;
            }
        }

        for(int k = 0; k < v; k++) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    mat[i][j] = Math.min(mat[i][j],
                            mat[i][k] + mat[k][j]);
                }
            }
        }

        /* since the problem requirement to solve it inplace, so you have to
         rollback pre configurations you have made
         if any cell value is 1e9 means, you haven't found any path for it, reset to -1*/
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                if(mat[i][j] == 1e9)
                    mat[i][j] = -1;
            }
        }
    }
}
