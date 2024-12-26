package graph.learning.bfsdfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/graph/breadth-first-search-bfs-level-order-traversal/
    https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
 */
public class GraphBFS {

    /*
    Approach:
        Explained in notes, check

        Time Complexity:
            we have a while loop
            inside while loop, we have a for loop
            inside, while loop we pop the element
            and for every element, we popped, we will go to its adjacency list
            and visit every element in that adjacency list

            so we can say total time complexity will be
                - we will visit every node in the graph using outer while loop - O(n)
                - for each popped node, we will visit its adjacency list (outdegree of that node)

                we know that total outdegree of a undirected graph is 2 * E

            so we can say

            O(n) + O(2 * E)

        Space Complexity:
            we are having three arrays
                - one to store the BFS result list
                - visited array
                - queue

            including all these there can be maximum of 3 * n space complexity

            So, we can say O(3n) = O(n)
     */
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {

        // Array list to store the BFS traversal result of the Graph
        ArrayList<Integer> bfs = new ArrayList<>();

        // we are assuming that the graph is zero based indexing,
        // that's why we are creating visited array of size V
        // if it was 1-based indexing, visited array size should have been V + 1
        boolean[] visited = new boolean[V];

        // create a queue to keep track of list of nodes to be visited
        Queue<Integer> queue = new LinkedList<>();

        // next two lines specifies, with which node, we start the traversal
        // here, we are starting with node 0, but we can start with any node
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()){
            // take the queue element and add to the list
            Integer node = queue.poll();
            bfs.add(node);

            // get the adjacency list of the popped element,
            // if any node in the adjacency list is not visited,
            // then mark it as visited and addd it to the queue
            for(int i : adj.get(node)){
                if(visited[i] == false){
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        return bfs;
    }
}
