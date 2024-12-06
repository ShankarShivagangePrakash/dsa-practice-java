package tree.binary_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/data-structure/check-for-symmetrical-binary-tree/
    https://leetcode.com/problems/symmetric-tree/description/
 */
public class SymetricBinaryTree {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            variation of DFS

        Space Complexity: O(Height of the tree)
            Same as of DFS, this is the stack space
     */
    public boolean isSymmetric(TreeNode root){
        if(root == null)
            return true;

        return isSymmetricUtil(root.left, root.right);
    }

    public boolean isSymmetricUtil(TreeNode p, TreeNode q){
        if (p == null && q == null)
            return true;

        if(p == null || q == null)
            return false;

        return (p.val == q.val)
                && isSymmetricUtil(p.left, q.right)
                && isSymmetricUtil(p.right, q.left);
    }
}
