package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://leetcode.com/problems/validate-binary-search-tree/description/
 */
public class ValidBSTorNot {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            DFS traversal, we are visiting every node

        Space Complexity: O(Height of the tree)
            stack space
     */
    public boolean isValidBST(TreeNode root){
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBSTHelper(TreeNode node, long minVal, long maxVal){
        // if node is null, return true,
        // it could be entire tree is empty or you have reached null
        if(node == null)
            return true;

        // current node is voilating its range, return false
        if(node.val >= maxVal || node.val <= minVal)
            return false;

        // check whether both left and right subtres are in their range
        // check notes for the boundary being passed to each
        return isValidBSTHelper(node.left, minVal, node.val)
                && isValidBSTHelper(node.right, node.val, maxVal);
    }
}
