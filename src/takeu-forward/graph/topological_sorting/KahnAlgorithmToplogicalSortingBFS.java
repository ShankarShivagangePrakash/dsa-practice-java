package graph.topological_sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/kahns-algorithm-topological-sort-algorithm-bfs-g-22/
    https://www.geeksforgeeks.org/problems/topological-sort/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=topological-sort
 */
public class KahnAlgorithmToplogicalSortingBFS {

    /*
    Approach:
        Explained in the notes, check

        Time Complexity: O(V+E),
            where V = no. of nodes and E = no. of edges. This is a simple BFS algorithm.

        Space Complexity: O(N) + O(N) ~ O(2N),
            O(N) for the indegree array, and O(N) for the queue data structure used in BFS(where N = no.of nodes).
     */
    static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        int v = adj.size();

        int[] inDegree = new int[v];

        for(int i = 0; i < v; i++){
            for(int neighbour : adj.get(i))
                inDegree[neighbour]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < v; i++){
            if(inDegree[i] == 0)
                queue.add(i);
        }

        ArrayList<Integer> topologicalSort = new ArrayList<>();

        while(!queue.isEmpty()){
            int node = queue.poll();
            topologicalSort.add(node);

            for(int neighbour : adj.get(node)){
                inDegree[neighbour]--;
                if(inDegree[neighbour] == 0)
                    queue.add(neighbour);
            }
        }
        return topologicalSort;
    }
}
