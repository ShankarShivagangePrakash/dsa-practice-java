package tree.binary_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem Statement:
    https://takeuforward.org/data-structure/calculate-the-diameter-of-a-binary-tree/
    https://leetcode.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            we will visit each node once,
        Space Complexity:O(height of the tree)
            stack space
     */
    public int diameterOfBinaryTree(TreeNode root){
        // we are creating array here, because,
        // in java, primitive variables are pass by value, so we will not get the result back
        // so i'm creating an array, so that it acts as pass by reference and we will get the value.
        int[] diameter = new int[1];
        maxDepth(root, diameter);
        return diameter[0];
    }

    public int maxDepth(TreeNode root, int[] diameter){
        if(root == null)
            return 0;

        int leftHeight = maxDepth(root.left, diameter);
        int rightHeight = maxDepth(root.right, diameter);

        // leftHeight and rightHeight combining will form a path, we need to check will it be larger than current Diagmeter?
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
