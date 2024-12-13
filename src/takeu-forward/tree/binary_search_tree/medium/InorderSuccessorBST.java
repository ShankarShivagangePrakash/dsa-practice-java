package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://leetcode.com/problems/inorder-successor-in-bst/description/
 */
public class InorderSuccessorBST {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(Height of the tree)
            DFS

        Space Complexity: O(1)
     */
    public TreeNode inorderSuccessorBST(TreeNode root, TreeNode p){
        TreeNode successor = null;

        TreeNode cur = root;

        while (cur != null){
            /* if the p node for which we are trying to find successor
             if that `p.val` is < cur.val
             it means cur can be our successor
             so assign cur as our successor, but it need not be immediate successor
             so move left*/
            if(p.val < cur.val){
                successor = cur;
                cur = cur.left;
            }
            // p value is greater than cur, so move right
            else
                cur = cur.right;
        }
        return successor;
    }
}
