package graph.minmum_spanning_tree_disjoint_set;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/disjoint-set-union-by-rank-union-by-size-path-compression-g-46/
    https://www.geeksforgeeks.org/problems/disjoint-set-union-find/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=disjoint-set-union-find
 */

/*
Approach:
    Explain in notes and program, check

    Time Complexity for all methods:
        O(4 * alpha), which is constant
    Space Complexity for all methods:
        O(4 * alpha), which is constant

    You don't need to know about, how we arrive at time and space complexities
        no one will ask about them in interviews
 */
public class DisjoinSetByUnionRank {

    // rank refers to maximum distance between the root and the leaf of that tree which contains element at index i
    ArrayList<Integer> rank = new ArrayList<>();
    // parent stores the parent (immediate upper node) of i in the tree
    ArrayList<Integer> parent = new ArrayList<>();

    public DisjoinSetByUnionRank(int n){
        /* graph can be 0 based or 1 based, we have to handle both the scenarios
         so creating arrays of size n+1
         initially set rank[i] = 0, since each node will be a separate tree of its own
         and parent[i] = i, since no one will be under anyone.*/
        for(int i = 0; i < n+1; i++ ){
            rank.add(0);
            parent.add(i);
        }
    }

    /* we will find the ultimate parent of the given node
     and also, we will perform path compression*/
    public int findUltimateParent(int node){
        /* Base case: if parent[node] = node means,
         it is the ultimate parent, so stop recursion and return ultimate parent*/
        if(parent.get(node) == node)
            return node;

        // set this ultimate parent to every node that path
        int ultimateParent = findUltimateParent(parent.get(node));
        parent.set(node, ultimateParent);

        // return ultimate parent
        return ultimateParent;
    }

    public void unionByRank(int u, int v){
        // find the ultimate parent of u and v
        int ultp_u = findUltimateParent(u);
        int ultp_v = findUltimateParent(v);

        // if ultimate parent of both u and v are same, it means, they both are in same tree
        // no need to do any restructuring of trees
        if(ultp_u == ultp_v)
            return;

        /* if ultimate parents of u and v are not equal
         we will link smaller tree to bigger tree
         we use rank to find bigger tree*/

        // if, ultp_u is smaller, set its parent as ultp_v
        if(rank.get(ultp_u) < rank.get(ultp_v))
            parent.set(ultp_u, ultp_v);
        else if(rank.get(ultp_v) < rank.get(ultp_u))
            parent.set(ultp_v, ultp_u);
        else{
            /* else means both are same, in this case we have link any tree to any
             but we will link v under u
             and in this case, we have to increment the rank of u*/
            parent.set(ultp_v, ultp_u);
            int rankU = rank.get(ultp_u);
            rank.set(ultp_u, rankU + 1);
        }
    }
}
