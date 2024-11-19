package tree.binary_tree.traversal;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/iterative-preorder-traversal-of-binary-tree
    https://leetcode.com/problems/binary-tree-preorder-traversal/
 */
public class PreOrderTraversalIterative {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            Every node of the binary tree is visited exactly once, and for each node.

        Space Complexity: O(n)
            This is because the stack can potentially hold all nodes in the tree when dealing with a skewed tree
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // Initialize list to store the preorder traversal result
        ArrayList<Integer> preOrder = new ArrayList<>();

        // If the root is null, return an empty traversal result
        if(root == null)
            return preOrder;

        // // Create a stack to store nodes during traversal and add root node to it.
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();

            preOrder.add(node.val);

            // push right and left child if exists
            // Note: since you are using stack and you have to pick left child before right, so insert right first then left, so left will be on top
            if(node.right != null)
                stack.add(node.right);
            if(node.left != null)
                stack.add(node.left);
        }
        return preOrder;
    }
}
