package graph.topological_sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/course-schedule-i-and-ii-pre-requisite-tasks-topological-sort-g-24/
    https://leetcode.com/problems/course-schedule/description/
 */
public class CourseSchedule2 {

    /*
    Problem statement:
        return the order if possible, else return empty array, rest everything explained in notes

        Approach:
            Explained in notes, check

            Time Complexity: O(V+E),
                where V = no. of courses and E = no. of edges. This is a simple BFS algorithm.

            Space Complexity: O(N) + O(N) ~ O(2N),
                O(N) for the indegree array, and O(N) for the queue data structure used in BFS(where N = no.of nodes).
                Extra O(N) for storing the topological sorting. Total ~ O(3N).
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // number of vertices in the graph is equal to number of courses given
        int v = numCourses;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < v; i++)
            adj.add(new ArrayList<>());

        // construct adjacency list
        // here, we know u -> v means, for u to complete, before that v has to be completed
        // to represent it in a graph for topological sort, we can reverse it as v -> u
        // so that it will be represented as after completing v, u will be executed.
        // so that, we can use topological sorting logic without alteration
        int m = prerequisites.length;
        for (int i = 0; i < m; i++) {
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
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

        int topologicalSort[] = new int[v];
        int i = 0;
        while(!queue.isEmpty()){
            int node = queue.poll();

            topologicalSort[i++] = node;

            for(int neighbour : adj.get(node)){
                inDegree[neighbour]--;
                if(inDegree[neighbour] == 0)
                    queue.add(neighbour);
            }
        }

        // if the size of the topological sort is equal to `number of courses`
        // we can return the topological sorting.
        if(i == v)
            return topologicalSort;
        // else, we return empty array.
        return new int[0];
    }
}
