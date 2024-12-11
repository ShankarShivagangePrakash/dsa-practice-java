package tree.binary_tree.hard;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/data-structure/flatten-binary-tree-to-linked-list/
    https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
 */
public class FlattenBinaryTreeToLinkedList {

    // Initialize a global variable 'prev' to keep track of the previously processed node.
    TreeNode prev = null;

    /*
    Approach:
        Explained in notes,
            we solve right subtree first
            then left subtree

        Time Complexity: O(n)
            we will visit every node once.

        Space Complexity: O(Height of the binary tree)
            stack space
     */
    public void flattenBinaryTreeToLinkedList(TreeNode root){
        flattenBinaryTreeToLinkedListRecursive(root);
    }

    public void flattenBinaryTreeToLinkedListRecursive(TreeNode node){
        // Base case: If the current node is null, return.
        if(node == null)
            return;

        // Recursive call to flatten the right subtree
        flattenBinaryTreeToLinkedListRecursive(node.right);
        // Recursive call to flatten the left subtree
        // - order matters here, first solve right subtree then left subtree
        flattenBinaryTreeToLinkedListRecursive(node.left);

        /* At this point, both left and right subtrees are flattened,
         and 'prev' is pointing to the recent node in the flattened right subtree.
         Set the right child of the current node to 'prev'.*/
        node.right = prev;
        // since it is linked list, only one link should be present
        // since we are setting link using right pointer, reset left to null
        node.left = null;
        // Update 'prev' to the current node for the next iteration.
        prev = node;
    }
}
