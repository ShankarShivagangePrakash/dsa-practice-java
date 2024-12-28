package graph.learning.bfsdfs;

/*
Problem:
    https://takeuforward.org/graph/flood-fill-algorithm-graphs/
    https://leetcode.com/problems/flood-fill/description/
 */
public class FloodFill {

    /*
    Approach:

        Explained in notes, check

        We can solve this problem using both BFS and DFS
        if we had to solve this problem in minimum time then BFS was the most suitable approach

        But we want to explore and learn how to solve using other methods, so using DFS

        Time Complexity:

            dfsRecursive() would be called for every cell having value same as initialColor
            so, if all the cells are having value same as initial color, then
            we will call this method O(n * m) times
                each time it will again call 4 times (not necessarily dfsRecursive() will be called) but condition checked

                so O(n * m * 4)

            also, we need to consider recursive stack space, worst case it will be O(n * m)
                if all cells are having same initialColor

            Total = O(n * m) + O(n * m * 4)
                  = O(n * m)
        Space Complexity:
            O(n * m)
            we are creating a new 2D matrix, since we don't want to alter input

            also, we need to consider recursive stack space, worst case it will be O(n * m)
                if all cells are having same initialColor

            Total = O(n * m) + O(n * m * 4)
                  = O(n * m)
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int[][] ans = image;
        int initialColor = image[sr][sc];

        int[] deltaRow = {0, 1, 0, -1};
        int[] deltaCol = {1, 0, -1, 0};
        dfsRecursive(sr, sc, image, ans, initialColor, newColor, deltaRow, deltaCol);
        return ans;
    }

    public void dfsRecursive(int row, int col, int[][] image, int[][] ans, int initialColor,
                             int newColor, int[] deltaRow, int[] deltaCol){

        ans[row][col] = newColor;

        for(int i = 0; i < 4; i++){
            int nRow = row + deltaRow[i];
            int nCol = col + deltaCol[i];

            // check the new cell value is valid in the image dimension given
            // the cell we want to color is having same as starting pixel initial color (image[nRow][nCol] == initialColor)
            // and it has not been colored, to do that, we can simply check that cell value in ans[][] is not same as newColor
            if(nRow >= 0 && nRow < image.length && nCol >= 0 && nCol< image[0].length
            && image[nRow][nCol] == initialColor && ans[nRow][nCol] != newColor)
                dfsRecursive(nRow, nCol, image, ans, initialColor, newColor, deltaRow, deltaCol);
        }
    }
}
