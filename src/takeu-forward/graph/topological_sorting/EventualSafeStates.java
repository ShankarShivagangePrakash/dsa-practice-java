package graph.topological_sorting;

import java.util.*;

/*
Problem:
    https://takeuforward.org/data-structure/find-eventual-safe-states-bfs-topological-sort-g-25/
    https://leetcode.com/problems/find-eventual-safe-states/description/
 */
public class EventualSafeStates {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(V+E)+O(N*logN),
            where V = no. of nodes and E = no. of edges.
            This is a simple BFS algorithm.
            Extra O(N*logN) for sorting the safeNodes array, where N is the number of safe nodes.

        Space Complexity: O(N) + O(N) + O(N) ~ O(3N),
            O(N) for the indegree array,
            O(N) for the queue data structure used in BFS(where N = no.of nodes),
            and extra O(N) for the adjacency list to store the graph in a reversed order.
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int v = graph.length;

        ArrayList<ArrayList<Integer>> revAdj = new ArrayList<>();

        for(int i = 0 ; i < v; i++)
            revAdj.add(new ArrayList<>());

        for(int i = 0; i < v; i++){
            // reversing edge and representing it in reverese adjacency list
            for(int it : graph[i])
                revAdj.get(it).add(i);
        }

        return topologicalSortBFS(v, revAdj);

    }

    public List<Integer> topologicalSortBFS(int v, ArrayList<ArrayList<Integer>> revAdj){
        int[] inDegree = new int[v];

        for(int i = 0; i < v; i++){
            for(int neighbour : revAdj.get(i))
                inDegree[neighbour]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < v; i++){
            if(inDegree[i] == 0)
                queue.add(i);
        }

        List<Integer> topologicalSort = new ArrayList<>();
        while(!queue.isEmpty()){
            int node = queue.poll();

            topologicalSort.add(node);

            for(int neigbour : revAdj.get(node)){
                inDegree[neigbour]--;
                if(inDegree[neigbour] == 0)
                    queue.add(neigbour);
            }
        }
        Collections.sort(topologicalSort);
        return topologicalSort;
    }
}
