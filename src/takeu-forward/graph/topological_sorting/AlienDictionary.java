package graph.topological_sorting;

import java.util.*;

/*
Problem:
    https://takeuforward.org/data-structure/alien-dictionary-topological-sort-g-26/
    https://leetcode.com/problems/alien-dictionary/editorial/
 */
public class AlienDictionary {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*len)+O(K+E),
            where N is the number of words in the dictionary,
            ‘len’ is the length up to the index where the first inequality occurs,
            K = no. of nodes, and E = no. of edges.

        Space Complexity: O(K) + O(K)+O(K)+O(K) ~ O(4K),
            O(K) for the indegree array, and O(K) for the queue data structure used in BFS(where K = no.of nodes),
            O(K) for the answer array and O(K) for the adjacency list used in the algorithm.
     */
    public String findOrder(String [] dict, int N, int K) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < K; i++)
            adj.add(new ArrayList<>());

        for(int i = 0; i < N-1; i++){
            String s1 = dict[i];
            String s2 = dict[i+1];

            int len = Math.min(s1.length(), s2.length());

            for(int j = 0; j < len; j++){
                if(s1.charAt(j) != s2.charAt(j)){
                    adj.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    break;
                }
            }
        }

        List<Integer> topologicalSort = topologicalSortBFS(K, adj);

        String ans = "";
        for(int i = 0; i < topologicalSort.size(); i++)
            ans = ans + (char)(topologicalSort.get(i) + (int)'a');

        return ans;
    }

    public List<Integer> topologicalSortBFS(int v, List<List<Integer>> adj){
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

        List<Integer> topologicalSort = new ArrayList<>();
        while(!queue.isEmpty()){
            int node = queue.poll();

            topologicalSort.add(node);

            for(int neigbour : adj.get(node)){
                inDegree[neigbour]--;
                if(inDegree[neigbour] == 0)
                    queue.add(neigbour);
            }
        }
        return topologicalSort;
    }
}
