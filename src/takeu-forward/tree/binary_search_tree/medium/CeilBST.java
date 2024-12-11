package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/binary-search-tree/ceil-in-a-binary-search-tree/
    https://www.geeksforgeeks.org/problems/implementing-ceil-in-bst/1
 */
public class CeilBST {

    /*
    Approach:
        Explained in notes, check

        Time Compleixty: O(Heigh of the BST)
            maximum we would visit height of binary search tree nodes to find ceil
        Space Complexity: O(Height of the BST)
            stack space

     */
    public int findCeil(TreeNode root, int key){
        TreeNode cur = root;

        // Initialize the variable to store the ceiling value
        int ceil = -1;

        // Traverse the BST until reaching the end or finding the key
        while(cur != null){
            // If the key is found, assign it as the ceiling and return
            if(cur.val == key)
                return key;

            // If the key is greater, move to the right subtree
            if(key > cur.val)
                cur = cur.right;
            else{
                // If the key is smaller, update ceil
                ceil = cur.val;
                // and move to the left subtree
                cur  = cur.left;
            }
        }
        return ceil;
    }
}
