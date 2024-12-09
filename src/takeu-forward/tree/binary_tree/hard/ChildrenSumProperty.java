package tree.binary_tree.hard;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/data-structure/check-for-children-sum-property-in-a-binary-tree/
    https://www.geeksforgeeks.org/problems/children-sum-parent/1
 */
public class ChildrenSumProperty {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            It is a variation of DFS, we are visiting each node once.

        Space Complexity: O(Height of the binary tree)
            stack space.
     */
    public static void changeTreeToHoldChildrenSumProperty(TreeNode root){
        changeTreeToHoldChildrenSumPropertyRecursive(root);
    }

    public static void changeTreeToHoldChildrenSumPropertyRecursive(TreeNode node){
        // Base case: If the current node is null, return and do nothing.
        if(node == null)
            return;

        // Calculate the sum of the values of the left and right children, if they exist.
        int child = 0;
        if(node.left != null)
            child += node.left.val;
        if(node.right != null)
            child += node.right.val;

        // Compare the sum of children with the current node's value and update
        // if the sum of children is greater than current node, assign the sum to current node.
        // else assign each child the value same as current node.
        if(child >= node.val)
            node.val = child;
        else{
            if(node.left != null)
                node.left.val = node.val;
            if(node.right != null)
                node.right.val = node.val;
        }

        //  Recursively call the function on the left and right children.
        changeTreeToHoldChildrenSumPropertyRecursive(node.left);
        changeTreeToHoldChildrenSumPropertyRecursive(node.right);

        // Calculate the total sum of the values of the left and right children, if they exist.
        int total = 0;
        if(node.left != null)
            total += node.left.val;
        if(node.right != null)
            total += node.right.val;

        // if the current node is not leaf node, then only update it's value got from its children
        if(node.left != null || node.right != null)
            node.val = total;
    }
}
