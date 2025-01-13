package graph.minmum_spanning_tree_disjoint_set;

/*
Problem:
    https://takeuforward.org/data-structure/disjoint-set-union-by-rank-union-by-size-path-compression-g-46/
    https://www.geeksforgeeks.org/problems/disjoint-set-union-find/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=disjoint-set-union-find
 */

import java.util.ArrayList;

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

/*
Approach:
    Explain in notes and program, check
    Same as `DisjoinSetByUnionRank.java` but we use size arrayList instead of rank

    Time Complexity for all methods:
        O(4 * alpha), which is constant
    Space Complexity for all methods:
        O(4 * alpha), which is constant

    You don't need to know about, how we arrive at time and space complexities
        no one will ask about them in interviews
 */
public class DisjoinSetByUnionSet {

    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();

    public DisjoinSetByUnionSet(int n){
        for(int i = 0; i < n+1; i++){
            size.add(1);
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

    public void unionBySet(int u, int v){
        int ultp_u = findUltimateParent(u);
        int ultp_v = findUltimateParent(v);

        if(ultp_u == ultp_v)
            return;

        if(size.get(ultp_u) < size.get(ultp_v)){
            parent.set(ultp_u, ultp_v);
            size.set(ultp_v, size.get(ultp_v) + size.get(ultp_u));
        }
        else{
            // else means, u is greater than or equal to v
            // in this case, we add v under u
            parent.set(ultp_v, ultp_u);
            size.set(ultp_u, size.get(ultp_u) + size.get(ultp_v));
        }
    }
}
