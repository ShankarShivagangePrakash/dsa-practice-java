package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://leetcode.com/problems/insert-into-a-binary-search-tree/description/
 */
public class InsertNodeBST {

    /*
    Approach:
        Our idea is to insert new node as a leaf always
        so we find the position in the last level,
        such that BST properties holds good even after insertion

        Time Complexity: O(Height of the BST)

        Space Complexity: O(1)
     */
    public TreeNode insertNodeBST(TreeNode root, int val){
        if(root == null)
            return new TreeNode(val);

        TreeNode cur = root;

        while(true){
            // if the node val what we need to insert is greater than the current node value
            // we have to move right
            // if block suggests that we have to move right
            if(cur.val <= val){
                // if case means, we have right child
                // so move in right subtree to find the position where we can insert new node
                if(cur.right != null)
                    cur = cur.right;
                else {
                    // else the right node is null,
                    // then we can insert new node as right child of current node
                    // and we break out from the while loop
                    cur.right = new TreeNode(val);
                    break;
                }
            }
            // else means the node val what we need to insert is lesser than the current node value
            // we have to move left
            else{
                // since our aim is to insert new node as left
                // if current node has left child, we further move left
                if(cur.left != null)
                    cur = cur.left;
                else{
                    // we found a place where we can insert, insert and break
                    cur.left = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}
