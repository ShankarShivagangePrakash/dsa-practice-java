package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://leetcode.com/problems/recover-binary-search-tree/description/
 */
public class RecoverBST {

    private TreeNode first;   // First swapped node
    private TreeNode middle;  // Middle node for adjacent swap
    private TreeNode last;    // Second swapped node (non-adjacent case)
    private TreeNode prev;    // Keeps track of the previous node during in-order traversal

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            in order traversal

        Space Complexity: O(1)
     */
    public void recoverTree(TreeNode root) {
        // Initialize nodes
        first = middle = last = null;
        prev = new TreeNode(Integer.MIN_VALUE); // Assign minimum value for comparison

        // Perform in-order traversal to find swapped nodes
        inOrder(root);

        // Correct the tree by swapping values
        if (first != null && last != null) {
            // Case 1: Nodes are non-adjacent
            swap(first, last);
        } else if (first != null && middle != null) {
            // Case 2: Nodes are adjacent
            swap(first, middle);
        }
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return; // Base case
        }

        // Traverse the left subtree
        inOrder(node.left);

        // Compare current node's value with the previous node's value
        if (prev != null && node.val < prev.val) {
            if (first == null) {
                // First violation detected
                first = prev;   // Store the first incorrect node
                middle = node;  // Store the adjacent node (potential second incorrect)
            } else {
                // Second violation detected
                last = node; // Store the last incorrect node
            }
        }

        // Update prev to current node for next comparison
        prev = node;

        // Traverse the right subtree
        inOrder(node.right);
    }

    // Helper method to swap two nodes' values
    private void swap(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
}
