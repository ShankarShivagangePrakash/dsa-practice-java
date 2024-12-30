package graph.learning.bfsdfs;

/*
Problem:
    https://takeuforward.org/graph/surrounded-regions-replace-os-with-xs/
    https://leetcode.com/problems/surrounded-regions/
 */
public class SurroundedRegions {

    /*
    Problem Statement:
        we have to find `O` in the given matrix such that it has to be covered by `X` in all four directions
        say in some direction it was covered by another `O`, then we have to move further in that direction till we find `X`
        if we don't find `X` till the boundary in that direction(column/row) then we cannot consider this `O`

        we have to find `O` following above criteria and replace them with `X`
    Approach:
        Intuition:
            Basic case is `O` on the boundary of the matrix cannot be replaced with `X` - since it is not covered by `X` in all 4 directions
            any other `O` connected to these boundary `O` cannot be replaced with `X`
            so, if we can find these kind `O`
            then we can replace remaining `O` with `X`

         Algorithm:
            we will have a visited matrix
            we will visit each boundary (last row, last column, first row, first column)
            and we check for `O` in that row/boundary
                if so, we will mark it as visited and we perform DFS from there in all 4 directions
                    provided the new cell is valid and having `O`

            once, we complete these boundaries, we will have all `O` marked as true in visited matrix which cannot be replaced with `X`
            Now, iterate the matrix, if it is `O` and not visited, then we can replace it with `X`

        Note: This problem can be solved using both BFS/DFS since in previous problem we used BFS, we are trying to solve similar problem using DFS

        Time Complexity:
            O(N) + O(M) + O(NxMx4) ~ O(N x M)
                For the worst case, every element will be marked as ‘O’ in the matrix,
                and the DFS function will be called for (N x M) nodes and for every node, we are traversing for 4 neighbors,
                so it will take O(N x M x 4) time.
                Also, we are running loops for boundary elements so it will take O(N) + O(M).

        Space Complexity:
            O(N x M)
                O(N x M) for the visited array, and auxiliary stack space takes up N x M locations at max.
     */
    public void solve(char[][] board) {

        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];

        int[] deltaRow = {0, 1, 0, -1};
        int[] deltaCol = {1, 0, -1, 0};

        // first row, last row
        for(int i = 0; i < m; i++){
            if(board[0][i] == 'O' && visited[0][i] == false)
                dfsRecursive(0, i, board, visited, deltaRow, deltaCol);
            if(board[n-1][i] == 'O' && visited[n-1][i] == false)
                dfsRecursive(n-1, i, board, visited, deltaRow, deltaCol);
        }

        // first column, last column
        for(int i = 0; i < n; i++){
            if(board[i][0] == 'O' && visited[i][0] == false)
                dfsRecursive(i, 0, board, visited, deltaRow, deltaCol);
            if(board[i][m-1] == 'O' && visited[i][m-1] == false)
                dfsRecursive(i, m-1, board, visited, deltaRow, deltaCol);
        }

        // non visited `O` can be replaced with `X` since they are surrounded by `X` only.
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 'O' && visited[i][j] == false)
                    board[i][j] = 'X';
            }
        }
    }

    public void dfsRecursive(int row, int col, char[][] board, boolean[][] visited, int[] deltaRow, int[] deltaCol){

        visited[row][col] = true;

        int n = board.length;
        int m= board[0].length;

        for(int i = 0; i < 4; i++){
            int nRow = row + deltaRow[i];
            int nCol = col + deltaCol[i];

            if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m
                && visited[nRow][nCol] == false && board[nRow][nCol] == 'O')
                dfsRecursive(nRow, nCol, board, visited, deltaRow, deltaCol);
        }
    }
}
