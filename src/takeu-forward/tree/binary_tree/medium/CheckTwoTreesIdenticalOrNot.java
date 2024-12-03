package tree.binary_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/data-structure/check-if-two-trees-are-identical/
    https://leetcode.com/problems/same-tree/description/
 */
public class CheckTwoTreesIdenticalOrNot {

    /*
    Approach:
        Explained in notes,
        We use preorder traversal to traverse both the trees simulatenously
        we compare current nodes,
        then we move to its left and right subtree

        Base case: first two if conditions

        Time Complexity: O(n)
            pre order traversal

        Space Complexity: O(n)
            stack space
     */
    public boolean isSameTree(TreeNode p, TreeNode q){

        // If both nodes are NULL, they are identical
        if(p == null && q == null)
            return true;

        // If only one of the nodes is NULL, they are not identical
        if(p == null || q == null)
            return false;

        // Check if the current nodes have the same data value
        // and recursively check their left and right subtrees
        return (p.val == q.val)
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}
