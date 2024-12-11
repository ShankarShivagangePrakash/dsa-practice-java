package tree.binary_search_tree.easy;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://www.geeksforgeeks.org/problems/minimum-element-in-bst/1
 */
public class MinMaxBST {

    /*
    Approach:
        Minimum can be found in left most node in left subtree

        Time Complexity: O(Height of the BST)
        Space Complexity: O(1)
     */
    int minValue(TreeNode root) {
        TreeNode cur = root;
        while(cur.left != null)
            cur = cur.left;

        return cur.val;
    }

    /*
    Approach:
        Maximum can be found in right most node in right subtree

        Time Complexity: O(Height of the BST)
        Space Complexity: O(1)
     */
    int maxValue(TreeNode root){
        TreeNode cur = root;
        while(cur.right != null)
            cur = cur.right;
        return cur.val;
    }
}
