package graph.minmum_spanning_tree_disjoint_set;


import java.util.*;

/*
Problem:
    https://takeuforward.org/data-structure/prims-algorithm-minimum-spanning-tree-c-and-java-g-45/
    https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
 */
public class PrimsAlgorithmToFindMST {

    /*
    Approach:
        Almost same as how explained in the notes.
        But only few changes
        Creating adjacency list from 2D matrix each row represents a edge

        Tuple used to compute Minimum Spanning Tree

        Time Complexity: O(E * log E)
            Explained in program, check

        Space Complexity: O( V + E)
            visited array of size V
            priority Queue can have at max E edges, so E
            adjacency list you can ignore, as it should be given in problem statement
     */
    static int spanningTree(int V, int E, List<List<int[]>> adj) {

        ArrayList<ArrayList<PairPrimsAlgorithm<Integer, Integer>>> adjacencyList = new ArrayList<>();

        // This part is necessary only for this program, because of the kind of input
        /*for(int i = 0; i < adj.size(); i++)
            adj.add(new ArrayList<>());

        create adjacency list, since we have been given 2D matrix
         each row represents and edge with weight
         but the representation is slightly different
        for (int i = 0; i < adj.size(); i++) {
            for (int[] edge : adj.get(i)) {
                int destination = edge[0];
                int weight = edge[1];
                adjacencyList.get(i).add(new PairPrimsAlgorithm<>(destination, weight));
                adjacencyList.get(destination).add(new PairPrimsAlgorithm<>(i, weight)); // Undirected graph
            }
        }*/

        // tuple format will be (weight or cost of the edge, node (destination node), parent (source node)
        PriorityQueue<TupleMST> priorityQueue = new PriorityQueue<TupleMST>((x, y) -> x.first - y.first);

        int[] visited = new int[V];

        /* we will start traversing from node 0
         since it won't have any previous edge, we consider parent as -1
         destination is 0 (second parameter)
         weight is also 0, since there is no edge to this 0 (first parameter)*/
        priorityQueue.add(new TupleMST(0, 0, -1));

        Queue<PairPrimsAlgorithm<Integer, Integer>> minimumSpanningTree = new LinkedList<>();
        int mstSum = 0;

        // Priority Queue can have at max all edges of the tree, so E
        // multiply it by pop operation log E
        // so E * log E
        // Total = E * log E + E * log E
        // summarising = E * log E
        while(!priorityQueue.isEmpty()){
            // pop operation from queue takes log(size of PQ)
            TupleMST tupleMST = priorityQueue.poll();

            int weight = tupleMST.first;
            int node = tupleMST.second;
            int parent = tupleMST.third;

            if(visited[node] == 1)
                continue;

            visited[node] = 1;
            mstSum += weight;

            // parent = -1 means, source node, so this edge should not be added to minimum spanning tree
            if(parent == -1)
                minimumSpanningTree.add(new PairPrimsAlgorithm<>(parent, node));

            // this for loop at max, for the while program, it might touch all edges, so E
            // inside it we are pushing element to PQ, so log E
            // so E * log E
            // for whole program execution
           for(int[] edge : adj.get(node)){
               int adjNode = edge[0];
               int edgeWeight = edge[1];

               // if the adjacent node is not visited,
               // add it to priority Queue with parent
               if(visited[adjNode] == 0)
                   priorityQueue.add(new TupleMST(edgeWeight, adjNode, node));
           }
        }

        //  print minimum Spanning Tree
        System.out.println("Minimum Spanning Tree:");
        while(!minimumSpanningTree.isEmpty()){
            System.out.println(minimumSpanningTree.poll().toString());
        }

        // print mstSum
        return mstSum;
    }
}

class TupleMST{
    int first;
    int second;
    int third;

    public TupleMST (int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

class PairPrimsAlgorithm<K, V>{
    K first;
    V second;

    public PairPrimsAlgorithm(K first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "PairPrimsAlgorithm{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
