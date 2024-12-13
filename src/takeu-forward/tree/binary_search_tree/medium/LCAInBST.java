package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 */
public class LCAInBST {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(Height of the tree)
            DFS traversal

        Space Complexity: O(Height of the tree)
            stack space
     */
    public TreeNode LCABST(TreeNode root, TreeNode p, TreeNode q){
        return LCABSTRecursive(root, p, q);
    }

    public TreeNode LCABSTRecursive(TreeNode node, TreeNode p, TreeNode q){
        if(node == null)
            return null;

        // current node value
        int currentVal = node.val;

        // both p and q values are lesser than current node, both can be found in left subtree, so move left
        if(p.val < currentVal && q.val < currentVal)
            return LCABSTRecursive(node.left, p, q);

        // both p and q values are greater than current node, both can be found in right subtree, so move right
        if(p.val > currentVal && q.val > currentVal)
            return LCABSTRecursive(node.right, p, q);

        // both p and q cannot be found in one direction, node is the common point for both of them, return this as LCA
        return node;
    }
}
