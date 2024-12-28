package graph.learning.bfsdfs;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/detect-cycle-in-an-undirected-graph-using-dfs/
    https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=detect-cycle-in-an-undirected-graph
 */
public class DetectCycleUndirectedGraphUsingDFS {

    /*
    Approach:
        Almost similar to `DetectCycleUndirectedGraphUsingBFS.java`
        but since we are following DFS, we are using recursive approach

        Time Complexity:

            DFS traversal = O(N + 2E)
            for loop in isCycle() method - O(n) - but it won't be O(n), because of visited[i] == false condition

            Total = O(n) + O(N + 2E)

        Space Complexity:
            O(n) - recursive stack space
            O(n) - visited array

            Total = O(n)
     */
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        int v = adj.size();
        boolean[] visited = new boolean[v];

        // graph can have multiple components, in those components there can be a cycle
        // so perform BFS traversal on all those components, we are running a loop V times
        for(int i = 0; i < v; i++){
            // current node would have been marked as visited in some component BFS traversal
            // in that case, performing BFS on it, is not required
            if(visited[i] == false){
                // this method will check is there a cycle in the current component
                if(isCycleInUndirectedGraphUsingDFS(i, -1, adj, visited))
                    return true;
            }
        }
        return false;
    }

    public boolean isCycleInUndirectedGraphUsingDFS(int currentNode, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited){

        // mark the current node as visited
        visited[currentNode] = true;

        // we iterate through each node in the adjacency list of the current node
        for(int i : adj.get(currentNode)){
            // if this node is not visited, then we mark it as visited
            // and we will call isCycleInUndirectedGraphUsingDFS() with parent as current node
            if(visited[i] == false){
                // say that call yeiled a value as true, means it detected cycle
                // so there is no reason to further continue or perform new recursive calls
                // so in that case, simply return true
                // this will avoid new recursive calls
                if(isCycleInUndirectedGraphUsingDFS(i, currentNode, adj, visited))
                    return true;
            }
            /* else case means, this node was already visited
                 but check, is this the parent of the current node
                 if so, ok
                 else means, we have already visited node previously and we are visiting the same node now
                 using a different path
                 means there exists a cycle
                 so return true*/
            else if(i != parent)
                return true;
        }
        // if you have completed while loop means, you didn't find cycle in current component so return false
        return false;
    }
}
