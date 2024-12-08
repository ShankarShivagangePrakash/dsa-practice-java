package tree.binary_tree.hard;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/data-structure/lowest-common-ancestor-for-two-given-nodes/
    https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 */
public class LCAInBInaryTree {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            we are using BFS
        Space Complexity: O(Height of the binary tree)
            stack space
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        return lowestCommonAncestorRecursive(root, p, q);
    }

    public TreeNode lowestCommonAncestorRecursive(TreeNode node, TreeNode p, TreeNode q){
        //base case: If the current node is null or equal to either p or q, return node.
        if(node == null || node == p || node == q)
            return node;

        // try to find the path for either p or q in left and right direction from current node.
         TreeNode leftLCA = lowestCommonAncestorRecursive(node.left, p, q);
         TreeNode rightLCA = lowestCommonAncestorRecursive(node.right, p, q);

         // if you haven't found path in left subtree, you might find path in right subtree, so return right
         if(leftLCA == null)
             return rightLCA;
         // if you haven't found path in right subtree, but previous if states that left path was not null, so return left
         else if(rightLCA == null)
             return leftLCA;
         // in both the above if and else if, you were able to find LCA for only one node, eith p or q
         // else means you have found LCA for both p and q, i.e. current node `node`
         // so return it
         else
             return node;
    }
}
