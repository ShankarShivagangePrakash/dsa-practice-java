package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://www.geeksforgeeks.org/problems/largest-bst/1
 */
public class LargestBSTInBinaryTree {

    /*
    Approach:
        Explained in the program

        Time Complexity: O(n)
            post order traversal

        Space Complexity: O(n) * 3
            for each node we are maintaing some details
     */
    static int largestBst(TreeNode root)
    {
        return largestBSTSubTreeHelper(root).maxSize;
    }

    static public NodeValue largestBSTSubTreeHelper(TreeNode node){

        if(node == null)
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        NodeValue left = largestBSTSubTreeHelper(node.left);
        NodeValue right = largestBSTSubTreeHelper(node.right);

        // both left and right subtree follows the property of BST with current node
        // so consider whole as BST
        if(left.maxNodeValue < node.val && right.minNodeValue > node.val)
            return new NodeValue(
                    Math.max(node.val, right.maxNodeValue),
                    Math.min(node.val, left.minNodeValue),
                    left.maxSize + right.maxSize + 1
            );

        // either left or right subtree is not valid, so reset max and min range
        return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, Math.max(left.maxSize, right.maxSize));
    }
}

class NodeValue{
    int maxNodeValue;
    int minNodeValue;
    int maxSize;

    public NodeValue(int maxNodeValue, int minNodeValue, int maxSize) {
        this.maxNodeValue = maxNodeValue;
        this.minNodeValue = minNodeValue;
        this.maxSize = maxSize;
    }
}
