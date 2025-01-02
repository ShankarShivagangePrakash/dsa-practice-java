package graph.topological_sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/course-schedule-i-and-ii-pre-requisite-tasks-topological-sort-g-24/
    https://leetcode.com/problems/course-schedule/description/
 */
public class CourseSchedule1 {

    /*
    Problem statement:
        Return true or false, rest everything explained in notes

        Approach:
            Explained in notes, check

            Time Complexity: O(V+E),
                where V = no. of courses and E = no. of edges. This is a simple BFS algorithm.

            Space Complexity: O(N) + O(N) ~ O(2N),
                O(N) for the indegree array, and O(N) for the queue data structure used in BFS(where N = no.of nodes).
                Extra O(N) for storing the topological sorting. Total ~ O(3N).
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // number of vertices in the graph is equal to number of courses given
        int v = numCourses;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < v; i++)
            adj.add(new ArrayList<>());

        // construct adjacency list
        int m = prerequisites.length;
        for (int i = 0; i < m; i++) {
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

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

        // topologicalSort.size() == v, then it is DAG we can complete, return true
        // topologicalSort.size() != v, there is a cycle, we cannot complete, return false
        return topologicalSort.size() == v;
    }
}
