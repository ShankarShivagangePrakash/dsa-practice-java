package tree.binary_tree.medium;

import tree.binary_tree.traversal.TreeNode;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/boundary-traversal-of-a-binary-tree/
    https://leetcode.com/problems/boundary-of-binary-tree/description/
 */
public class BoundayTraversalOfBinaryTree {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(height of the tree) + O(height of the tree) + O(n)
            O(height of the tree) - for calculating left boundary
            O(height of the tree) - for calculating right boundary
            O(n) - for Inorder traversals to get leaf nodes

        Space Complexity: O(n)
            stack space
     */
    public List<Integer> boundaryTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;

        // add the root node to result
        if(!isLeaf(root))
            result.add(root.val);

        addLeftBoundary(root, result);
        addLeaves(root, result);
        addRightBoundary(root, result);

        return result;
    }

    // utitlity method, if both left and right child are null, then it is leaf node
    boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    public void addLeftBoundary(TreeNode root, List<Integer> result){
        TreeNode current = root.left;

        // we keep moving towards left child if available, if left child is not available, we move towards right child
        while(current != null){
            if(!isLeaf(current))
                result.add(current.val);

            if(current.left != null)
                current = current.left;
            else
                current = current.right;
        }
    }

    // we use inOrder traversal, you can use preOrder also
    // the moment, we know that current node is leaf node, add it to the result
    public void addLeaves(TreeNode root, List<Integer> result){

        if(isLeaf(root))
            result.add(root.val);

        if(root.left != null)
            addLeaves(root.left, result);

        if(root.right != null)
            addLeaves(root.right, result);
    }

    // add right boundary, but it has to be in reverse order
    // so we make use of stack.
    public void addRightBoundary(TreeNode root, List<Integer> result){
        TreeNode current = root.right;

        Stack<Integer> stack = new Stack<>();
        while(current != null){
            if(!isLeaf(current))
                stack.push(current.val);

            if(current.right != null)
                current = current.right;
            else
                current = current.left;
        }

        while(!stack.isEmpty())
            result.add(stack.pop());
    }
}
