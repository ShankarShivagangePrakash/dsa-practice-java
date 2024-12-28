package graph.learning.bfsdfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/detect-cycle-in-an-undirected-graph-using-bfs/
    https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=detect-cycle-in-an-undirected-graph
 */
public class DetectCycleUndirectedGraphUsingBFS {

    /*
    Approach:
        Explained in notes, check

        Time Complexity:

            = O(n + 2E) + O(n)
            same as BFS traversal + for loop inCycle()

        Space Complexity:
            = O(n) + O(n) - visited array and queue
            = O(n)
     */
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        int v = adj.size();

        boolean[] visited = new boolean[v];

        // graph can have multiple components, in those components there can be a cycle
        // so perform BFS traversal on all those components, we are running a loop V times
        for(int i = 0; i < v; i++){
            // current node would have been marked as visited in some component BFS traversal
            // in that case, performing BFS on it, is not required
            if(visited[i] == false) {
                // this method will check is there a cycle in the current component
                if (isCycleInUndirectedGraphUsingBFS(i, adj, visited, v))
                    return true;
            }
        }
        return false;
    }

    public boolean isCycleInUndirectedGraphUsingBFS(int startingNodeOfCurrentComponent, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int v){

        // we will treat the node with which we start the component traversal as the starting node
        // and we will mark it as visited
        visited[startingNodeOfCurrentComponent] = true;
        Queue<PairToDetectCycleBFS> queue = new LinkedList<>();

        /* also we will add that node to queue
         queue will represent below data {node and its parent}
         since this is the initial node of the current component, we will mark parent as -1*/
        queue.offer(new PairToDetectCycleBFS(startingNodeOfCurrentComponent, -1));

        while(!queue.isEmpty()){
            // we pop the element from the queue
            PairToDetectCycleBFS temp = queue.poll();
            int currentNode = temp.node;
            int parentNode = temp.parent;

            // we iterate through each node in the adjacency list of the current node
            for(int it : adj.get(currentNode)){
                // if this node is not visited, then we mark it as visited
                // and we add it to the queue, with parent as current node
                if(visited[it] == false){
                    visited[it] = true;
                    queue.offer(new PairToDetectCycleBFS(it, currentNode));
                }
                /* else case means, this node was already visited
                 but check, is this the parent of the current node
                 if so, ok
                 else means, we have already visited node previously and we are visiting the same node now
                 using a different path
                 means there exists a cycle
                 so return true*/
                else if(it != parentNode)
                    return true;
            }
        }
        // if you have completed while loop means, you didn't find cycle in current component so return false
        return false;
    }
}

class PairToDetectCycleBFS {
    int node;
    int parent;

    PairToDetectCycleBFS(int node, int parent){
        this.node = node;
        this.parent = parent;
    }
}
