package recursion.hard;

/*
Problem:
    https://takeuforward.org/data-structure/m-coloring-problem/
    https://www.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1
 */
public class MColoringProblem {

    /*
    Parameters in the graph:
        boolean[][] represents the graph in adjacent matrix form
            a cell can have value true or false, true represents the presence of edge from i to j

        m - represents the number of colours
        n - represents the number of vertices in the graph

     Approach:
        Explained in detail in notes, check

        Time Complexity: O(N^m)
            We are trying every colour on every vertices.
        Space Complexity: O(n)
     */
    public boolean graphColouring(boolean[][] graph, int m, int n){
        // Below array will store the colour we have applied for each vertices
        int[] colour = new int[n];

        return colourGraph(0, graph, m, n, colour);
    }

    public boolean colourGraph(int index, boolean[][] graph, int m, int n, int[] colour){

        // if we have reached index equal to n means, we have coloured all vertices so return true
        if(index == n)
            return true;

        // try out all colours on current vertex
        for(int i = 1; i <= m; i++){

            // check, if we can colour currentColour on current vertex.
            if(isItSaferToColour(index, graph, n, colour, i)){
                // make colour[i] = i, means current vertix is coloured with colour `i`.
                colour[i] = i;

                // colour next index. If we have coloured next index in recursion means at the end it will reach base case
                // it keeps returning true indicating entire graph has been coloured so we return true.
                if(colourGraph(index + 1, graph, m, n, colour) == true)
                    return true;

                // if we cannot colour next vertex, then we have to backtrack
                // rollback changes did for next vertex.
                colour[i] = 0;
            }
        }
        return false;
    }

    public boolean isItSaferToColour(int index, boolean[][] graph, int n, int[] colour, int currentColour){

        for(int i = 0; i < n; i++){
            if(graph[index][i] == true && colour[i] == currentColour)
                return false;
        }
        return true;
    }
}
