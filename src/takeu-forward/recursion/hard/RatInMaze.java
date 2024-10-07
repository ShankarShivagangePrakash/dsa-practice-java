package recursion.hard;

import java.util.List;
import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/rat-in-a-maze/
    https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
 */
public class RatInMaze {

    /*
    Approach:
        Explained in detail in notes, check

        Time Complexity: O( 4^(m*n))
            you are trying all four direction for each cell
            so each cell will have four possibilities

        Space Complexity: O( 2 * (m * n))
            visited matrix of size m * n
            recursion stack space maximum length can be close to (m*n)
                when the path to reach end of matrix is so long

        In case of optimal approach
        we will not imporve time or space complexity
        rather we will have one for loop to traverse in all four directions instead of 4 if conditions

     */
    public ArrayList<String> findPathBruteForce(int[][] matrix){

        int n = matrix.length;
        ArrayList<String> result = new ArrayList<>();
        int[][] visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = 0;
            }
        }

        // If first cell of input matrix is 0, then you cannot enter to first cell also
        // means there is no way to reach end of the matrix, so return empty array list
        if(matrix[0][0] == 0)
            return result;

        findPathBruteForceRecursive(0, 0, n, matrix, visited, "", result);
        return result;
    }

    public void findPathBruteForceRecursive(int i, int j, int n, int[][] matrix, int[][] visited, String s, ArrayList<String> result){

        if(i == n-1 && j == n-1){
            result.add(s);
            return;
        }

        //downwards
        // if there is a valid next cell and that cell is not visited and path exist in that direction then move
        if(i+1 < n && visited[i+1][j] != 1 && matrix[i+1][j] == 1){
            // if you are moving to next cell, mark current cell as visited in visted matrix.
            visited[i][j] = 1;
            findPathBruteForceRecursive(i+1, j, n, matrix, visited, s+"D", result);

            // backtracking step, roll back changes.
            visited[i][j] = 0;
        }

        // left
        if(j-1 >= 0 && visited[i][j-1] != 1 && matrix[i][j-1] == 1){
            visited[i][j] = 1;
            findPathBruteForceRecursive(i, j-1, n, matrix, visited, s+"L", result);
            visited[i][j] = 0;
        }

        // right
        if(j+1 < n && visited[i][j+1] != 1 && matrix[i][j+1] == 1){
            visited[i][j] = 1;
            findPathBruteForceRecursive(i, j+1, n, matrix, visited, s+"R", result);
            visited[i][j] = 0;
        }

        //upwards
        if(i-1 >= 0 && visited[i-1][j] != 1 && matrix[i-1][j] == 1){
            visited[i][j] = 1;
            findPathBruteForceRecursive(i-1, j, n, matrix, visited, s+"U", result);
            visited[i][j] = 0;
        }
    }

    public ArrayList<String> findPathOptimal(int[][] matrix){
        int n = matrix.length;
        ArrayList<String> result = new ArrayList<>();
        int[][] visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = 0;
            }
        }

        /* If first cell of input matrix is 0, then you cannot enter to first cell also
         means there is no way to reach end of the matrix, so return empty array list*/
        if(matrix[0][0] == 0)
            return result;

        /* di, dj arrays holds the value to be added to move in a particular direction
         downwards, left, right, Up*/
        int[] di = {1, 0, 0, -1};
        int[] dj = {0, -1, 1, 0};

        // pass di, dj lists also
        findPathOptimalRecursive(0, 0, n, matrix, visited, "", result, di, dj);
        return result;
    }

    public void findPathOptimalRecursive(int i, int j, int n, int[][] matrix, int[][] visited, String s, ArrayList<String> result,
                                         int[] di, int[] dj){

        if(i == n-1 && j == n-1){
            result.add(s);
            return;
        }

        /*
        instead of having four if loops to move in four directions we achive it using a single for loop which runs 4 times
        each time it refers to different direction - in order Down, left, right, Up

        newI and newJ values will hold values of new cell after moving in a certain direction from any current cell
         */
        String temp = "DLRU";
        for(int k = 0; k < 4; k++){

            // new cell indices after moving in a particular direction from current cell.
            int newI = di[k] + i;
            int newJ = di[k] + j;

            /* if newI and newJ indices are valid
             and not visited previously and
             has path matrix[newI][newJ] = 1
             means, we can move in this direction*/
            if(newI >= 0 && newI < n
                && newJ >= 0 && newJ < n
                && visited[newI][newJ] != 1 && matrix[newI][newJ] == 1){

                // since you are moving to next cell, mark current cell as visited.
                visited[i][j] = 1;

                /* add temp.chatAt(k) to s
                 k represents the direction
                 0 - down, 1 - left, 2 - right, 3 - Up
                 temp.charAt(k) gives corresponding direction string value*/
                findPathOptimalRecursive(newI, newJ, n, matrix, visited, s + temp.charAt(k), result, di, dj);
                visited[i][j] = 0;
            }
        }
    }
}
