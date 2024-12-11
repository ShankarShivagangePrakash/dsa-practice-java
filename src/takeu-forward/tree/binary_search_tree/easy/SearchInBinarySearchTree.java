package tree.binary_search_tree.easy;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/data-structure/search-in-a-binary-search-tree-2/
    https://leetcode.com/problems/search-in-a-binary-search-tree/description/
 */
public class SearchInBinarySearchTree {

    /*
    Approach:
        Explained in notes,

        Run while loop till cur pointer becomes null or cur node value is not equal to `val`

            inside it, compare `val` with cur.val
                if `val` < cur.val
                    assign cur = cur.left
                    means search in left subtree

                else
                    assign cur = cur.right
                    means search in right subtree

        Time Complexity: O(Height of the tree)
            height of the tree can be log n or n

         Space Complexity: O(1)
     */
    public TreeNode searchBST(TreeNode root, int val){
        TreeNode cur = root;
        while(cur != null && cur.val != val)
            cur = val < cur.val ? cur.left : cur.right;

        return cur;
    }
}
