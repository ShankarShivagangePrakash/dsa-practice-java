package tree.binary_tree.hard;

import tree.binary_tree.traversal.TreeNode;

import java.util.HashMap;

/*
Problem:
    https://takeuforward.org/data-structure/construct-a-binary-tree-from-inorder-and-preorder-traversal/
    https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 */
public class constructBinaryTreeFromInOrderAndPreOrder {

    /*
    Approach:
        Explained in notes, check
        Suggestion: write inOrder, preOrder for a sample example on paper
        and then you can form parameters, boundaries of subtrees easily.

        Time Complexity: O(n)
            almost similar to DFS

        Space Complexity: O(Height of the binary tree)
            stack space
     */
    public TreeNode buildTree(int[] preOrder, int[] inOrder){
        // Create a map to store indices of elements in the inorder traversal
        HashMap<Integer, Integer> inOrderMap = new HashMap<>();

        // Populate the map with indices of elements in the inorder traversal
        for(int i = 0; i < inOrder.length; i++)
            inOrderMap.put(inOrder[i], i);

        // Call the private helper function to recursively build the tree
        TreeNode root = buildTree(preOrder, 0, preOrder.length -1,
                                  inOrder, 0, inOrder.length -1, inOrderMap);

        return root;
    }

    // Recursive helper function to build the tree
    public TreeNode buildTree(int[] preOrder, int preStart, int preEnd,
                         int[] inOrder, int inStart, int inEnd, HashMap<Integer, Integer> inOrdermap){
        // Base case: If the start indices exceed the end indices, return null
        if(preStart > preEnd || inStart > inEnd)
            return null;

        // Create a new TreeNode with value at the current preorder index
        TreeNode root = new TreeNode(preOrder[preStart]);

        // Find the index of the current root value in the inorder traversal
        int indexOfCurrentElementinInOrder = inOrdermap.get(root.val);

        // Calculate the number of elements in the left subtree
        int numsLeft = indexOfCurrentElementinInOrder - inStart;

        // Recursively build the left subtree
        root.left = buildTree(preOrder, preStart + 1, preStart + numsLeft,
                inOrder, inStart, indexOfCurrentElementinInOrder - 1, inOrdermap);

        // Recursively build the right subtree
        root.right = buildTree(preOrder, preStart + numsLeft + 1, preEnd,
                inOrder, indexOfCurrentElementinInOrder + 1, inEnd, inOrdermap);

        return root;
    }
}
