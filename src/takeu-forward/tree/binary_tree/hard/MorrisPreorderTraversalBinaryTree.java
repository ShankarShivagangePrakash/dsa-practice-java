package tree.binary_tree.hard;

import tree.binary_tree.traversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
Problem:
    https://takeuforward.org/data-structure/morris-inorder-traversal-of-a-binary-tree/
    https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 */
public class MorrisPreorderTraversalBinaryTree {

    /*
    Approach: Explained in detail, in notes, check

        Time Complexity: O(2n)
            The time complexity is linear,
                as each node is visited at most twice (once for establishing the temporary link and once for reverting it).
                In each step, we perform constant-time operations, such as moving to the left or right child and updating pointers.

        Space Complexity: O(1)
             no additional space used.
     */
    public List<Integer> inOrderTraversal(TreeNode root){
        // List to store the inorder traversal result
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null)
            return result;

        // Pointer to the current node, starting from the root. This will be used for traversal
        TreeNode cur = root;

        while(cur != null){
            // Case 1: If the current node's left child is NULL
            if(cur.left == null){
                // since there is no left child, we can add current node to inorder list and move right
                result.add(cur.val);
                cur = cur.right;
            }
            else{
                /* case-2:
                 There is a left subtree, and the right-most child of this left subtree is pointing to null.
                 Set the right-most child of the left subtree to point to the current node.
                 Move to the left child of the current node.*/
                TreeNode prev = cur.left;
                while(prev.right != null && prev.right != cur){
                    prev = prev.right;
                }
                // In pre order, case - 2 if block itself, we will add current node to result
                if(prev.right == null){
                    prev.right = cur;
                    result.add(cur.val);
                    cur = cur.left;
                }
                /* Case 3:
                There is a left subtree, and the right-most child of this left subtree is already pointing to the current node.
				 Revert the temporary link (set it back to null).
                 Move to the right child of the current node.
                 else means case 3*/
                else{
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return result;
    }
}
