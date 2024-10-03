package recursion.hard;

/*
Problem:
    https://takeuforward.org/data-structure/sudoku-solver/
    https://leetcode.com/problems/sudoku-solver/description/
 */
public class SudokuSolver {

    /*
    Approach:
        Explained in detail in notes, check

        Time Complexity: O(9^(n ^ 2)), in the worst case, for each cell in the n2 board, we have 9 possible numbers.
            we try with all 9 possible numbers for each empty cell
            worst case, the entire matrix can be empty
            in that case we need to fill entire matrix
            traversing through matrix takes n^2
            each of those cells, you have try with 1-9
            so O(9^(n^2))

        Space Complexity: O(1), since we are refilling the given board itself, there is no extra space required, so constant space complexity.
     */
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    public boolean solve(char[][] board){

        // iterate through entire board and check for empty cells
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                // we have found an empty cell, try to fill.
                if(board[i][j] == '.'){

                    for(char c = '1'; c <= '9'; c++){
                        if(isValid(c, i, j, board)) {
                            board[i][j] = c;
                            /* we have filled current empty cell,
                             fill next empty cell,
                             we will return true if we have filled all empty cells,
                             in that case, we shouldn't back track, rollback changes we did
                             that's why below check*/
                            if (solve(board) == true) {
                                return true;
                            }
                            // else means we were not able to fill next empty cell, so remove value you added to current empty cell - backtrack rollback changes
                            else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    /* we found the empty cell, but if the control has reached this place
                     after finishing for loop means, we couldn't fill it
                     so we have to return false saying we were not able to fill cell.*/
                    return false;
                }
            }
        }
        // if you have reached the end of the board, means there are no more empty cells, so return true
        return true;
    }

    //O(n)
    public boolean isValid(char c, int row, int col, char[][] board){
        for(int i = 0; i < 9; i++){
            // check is the number present anywhere in the current row.
            if(board[row][i] == c)
                return false;

            // check is number present in anywhere in current column
            if(board[i][col] == c)
                return false;

            // check is number present anywhere in the current sub matrix.
            if( board[3*(row/3 + i/3)][3*(col/3) + i%3] == c)
                return false;
        }
        return true;
    }
}
