package recursion.hard;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;

/*
Problem:
    https://takeuforward.org/data-structure/n-queen-problem-return-all-distinct-solutions-to-the-n-queens-puzzle/
    https://leetcode.com/problems/n-queens/description/

Problem Statement:
    explained cleanly in notes, check
 */
public class N_Queens {

    /*
    Approach:
        Explained in detail in the notes
        we fill queens column by column satisying the criteria

        Time Complexity: O(n! *n)
        Space Complexity: O(n! * n^2)

        Check notes, explained in detail
     */
    public static List<List<String>> placeNQueensBruteForce(int n){

        // first we need to create a board of size n * n
        char[][] board = new char[n][n];

        // we have fill the cells inside board with dummy value, else during comparision it will fail
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = '.';
            }
        }

        List<List<String>> result = new ArrayList<>();

        placeNQueensBruteForceRecursion(0, board, result);
        return result;
    }

    public static void placeNQueensBruteForceRecursion(int col, char[][] board, List<List<String>> result){

        // we have placed queen in every column, we can add this board to result list
        if(col == board.length){
            /* board is char[] 2d array, we need to convert it into list of string
             one string means char[] list<String> means one board
             list of list of strings means many boards*/
            result.add(
                    Arrays.stream(board)
                            .map(String::new)
                            .collect(Collectors.toList())
                    );
            return;
        }

        // if we want to place a queen in a column, we have to identify its place satisfying all the crieteria's required
        // it can be any place in that column in other words any row of that column
        for(int row = 0; row < board.length; row++){
            // check if we can place queen in curren cell or not?
            if(isItSafePlace(row, col, board)){

                // you have placed queen for current column
                board[row][col]= 'Q';

                // move to next column to place next queen
                placeNQueensBruteForceRecursion(col+1, board, result);

                // backtracking step after recursion rollback the changes you have done.
                board[row][col]= '.';
            }
        }
    }

    /* it is safe to place the queen at position row, col
     if there won't be any attacks from its left, upper left diagonal and lower left diagonal directions this methods check for that
     O(3n)*/
    public static boolean isItSafePlace(int row, int col, char[][] board){
        int dummyRow = row, dummyCol = col;

        // check are there any queen to its left direction
        while(dummyCol >= 0){
            if(board[dummyRow][dummyCol] == 'Q')
                return false;
            dummyCol--;
        }

        dummyCol = col;
        dummyRow = row;
        // check are there any queen in upper left diagonal direction
        while(dummyCol >= 0 && dummyRow >= 0){
            if(board[dummyRow][dummyCol] == 'Q')
                return false;
            dummyRow--;
            dummyCol--;
        }

        dummyCol = col;
        dummyRow = row;
        // check are there any queens in lower left diagonal direction
        while(dummyRow < board.length && dummyCol >= 0){
            if(board[dummyRow][dummyCol] == 'Q')
                return false;
            dummyRow++;
            dummyCol--;
        }
        // no queens in left, upper left diagonal, lower left diagonal direction
        // you can place queen
        return true;
    }

    /*
    Approach:
        Explained in detail in the notes, check

        One line summary, improvement made only in isItSafePlace()

        Time Complexity: O(n!)
        Space Complexity: O(n! * n^2)

        Check notes, explained in detail
     */
    public static List<List<String>> placeNQueensOptimal(int n){

        char[][] board = new char[n][n];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = '.';
            }
        }

        List<List<String>> result = new ArrayList<>();

        // hashmap represents the queens presence in left, left upper diagonal and left lower diagonal direction
        int[] leftRow = new int[n];
        int[] upperLeftDiagonal = new int[2*n-1];
        int[] lowerLeftDiagonal = new int[2*n-1];

        placeNQueensOptimalRecursive(0, board, result, leftRow, upperLeftDiagonal, lowerLeftDiagonal);
        return result;
    }

    public static void placeNQueensOptimalRecursive(int col, char[][] board, List<List<String>> result,
         int[] leftRow, int[] upperLeftDiagonal, int[] lowerLeftDiagonal){

        if(col == board.length){
            result.add(
                    Arrays.stream(board)
                    .map(String::new)
                    .collect(Collectors.toList())
            );
            return;
        }

        for(int row = 0; row < board.length; row++){
            if(isItSafePlaceOptimal(row, col, board, leftRow, upperLeftDiagonal, lowerLeftDiagonal)){

                // after placing the queen,
                // you also have to specify that queen has been placed at a particular position in all the three hashmaps
                board[row][col] = 'Q';
                leftRow[row] = 1;
                upperLeftDiagonal[(board.length - 1) + (col - row) ] = 1;
                lowerLeftDiagonal[row + col] = 1;

                placeNQueensOptimalRecursive(col+1, board, result, leftRow, upperLeftDiagonal, lowerLeftDiagonal);


                // backtracking after recursion completion, rollback the changes you did
                board[row][col] = '.';
                leftRow[row] = 0;
                upperLeftDiagonal[(board.length - 1) + (col - row) ] = 0;
                lowerLeftDiagonal[row + col] = 0;
            }
        }
    }

    // O(1) - explained in detail in the notes, check
    public static boolean isItSafePlaceOptimal(int row, int col, char[][] board, int[] leftRow, int[] upperLeftDiagonal, int[] lowerLeftDiagonal){

        if(leftRow[row] == 0 && upperLeftDiagonal[(board.length - 1) + (col-row)] == 0
                && lowerLeftDiagonal[col + row] == 0)
            return true;
        return false;
    }

    public static void main(String[] args){
        int n = 4;
        List<List<String>> result = placeNQueensBruteForce(n);
        List<List<String>> result2 = placeNQueensOptimal(n);
        System.out.println("Result of placing " + n + " queens using brute force approach\n: " + result);

        System.out.println();
        System.out.println("Result of placing " + n + " queens using optimal approach\n: " + result);


    }
}
