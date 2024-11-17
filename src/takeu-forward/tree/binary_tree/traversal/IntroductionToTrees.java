package tree.binary_tree.traversal;

import java.util.ArrayList;

public class IntroductionToTrees {

    /*
    Problem Statement:

        https://www.geeksforgeeks.org/problems/introduction-to-trees/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=introduction-to-trees

        Consider a complete binary tree and Given an integer i (representing level in a binary tree)
        Provide number of nodes available at that level.
     */
    /*
        Approach:
            Since is a complete binary tree, we can say theere will be 2^(i-1) nodes in at a given level i
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
    public static int countNodes(int i) {
        // code here
        return (int)Math.pow(2, i-1);
    }

    /*
    Problem statement:
        https://takeuforward.org/binary-tree/binary-tree-representation-in-java/
        https://www.geeksforgeeks.org/problems/binary-tree-representation/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=binary-tree-representation

        create tree by adding nodes.
     */
    /*
    Approach:
        Create a node with structure, data, left and right pointers
        insert data and link it's left and right child to them

        Time Complexity: O(1)
            n nodes to insert
        Space Compexity: O(1)
     */
    public static void createTree(Node root0, ArrayList<Integer> v ){
        // Code here
        root0.left = new Node(v.get(1));
        root0.right = new Node(v.get(2));

        root0.left.left = new Node(v.get(3));
        root0.left.right = new Node(v.get(4));

        root0.right.left = new Node(v.get(5));
        root0.right.right = new Node(v.get(6));
    }
}

class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
    }
}
