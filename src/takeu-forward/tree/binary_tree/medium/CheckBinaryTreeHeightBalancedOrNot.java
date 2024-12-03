package tree.binary_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/data-structure/check-if-the-binary-tree-is-balanced-binary-tree/
    https://leetcode.com/problems/balanced-binary-tree/description/
 */
public class CheckBinaryTreeHeightBalancedOrNot {

    /*
    Approach:
        We calculate the height of left subtree and right subtree for each node
        if the absolute difference between them is greater than 1, we return false

        else we will move and check its left, right subtree is balanced or not?
        if not, we return false

        else, the whole subtree is balanced, we return true.

        Time Complexity: O(n^2)
            O(n) for traversal and O(n) for finding height of each node

        Space Complexity: O(height of the tree)
            stack space.
     */
    public boolean isBalancedBruteForce(TreeNode node){
        int leftSubTreeHeight = maxDepth(node.left);
        int rightSubTreeHeight = maxDepth(node.right);

        if(Math.abs(leftSubTreeHeight - rightSubTreeHeight) > 1)
            return false;

        boolean isLeftBalanced = isBalancedBruteForce(node.left);
        boolean isRightBalanced = isBalancedBruteForce(node.right);

        if(!isLeftBalanced || !isRightBalanced)
            return false;

        return true;
    }

    public int maxDepth(TreeNode node){
        if(node == null)
            return 0;

        int leftHeight = maxDepth(node.left);
        int rightHeight = maxDepth(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /*
    Approach:
        we will reuse the maxDepth method, which will calculate the height of the tree

        But we want to return true or false,
        but maxDepthOptimal returns number
        so we make a small tweek
            if the tree is balanced, we return maxDepth
            else we return -1

        to say, it is not balanced, we check, the difference between left height and right height at any level is greater than 1
            if so we return -1

            but in further recursive calls pending for that in stack space, should also be not evaluated
            so we have an additional check
                if(leftHeight == -1 || rightHeight == -1)
                    return -1


        Time Complexity: O(n)
            we just calculate the height of the binary tree once
        Space Complexity: O(height of the binary tree)
            stack space.
     */
    public boolean isbalanced(TreeNode node){
        return maxDepthOptimal(node) != -1;
    }

    public int maxDepthOptimal(TreeNode node){
        if(node == null)
            return 0;

        int leftHeight = maxDepthOptimal(node.left);
        int rightHeight = maxDepthOptimal(node.right);

        // if the height difference between left and right subtree is greater than 1, return -1
        if(Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        // from previous recursive calls if you have recieved value as -1, don't evaluate further return -1
        if(leftHeight == -1 || rightHeight == -1)
            return -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
