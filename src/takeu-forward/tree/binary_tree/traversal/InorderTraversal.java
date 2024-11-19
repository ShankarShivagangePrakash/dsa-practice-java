package tree.binary_tree.traversal;

import tree.binary_tree.traversal.TreeNode;

import java.util.List;
import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/inorder-traversal-of-binary-tree/
    https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class InorderTraversal {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            we will visit every node, so O(n)
        Space Complexity: O(n)
            If the tree is skewed completely towards one side, then in stack maximum of n nodes will be waiting for execution so O(n)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        inorderTraversalRecursive(root, result);
        return result;
    }

    public void inorderTraversalRecursive(TreeNode node, ArrayList<Integer> result){
        if(node == null)
            return;

        inorderTraversalRecursive(node.left, result);
        result.add(node.val);
        inorderTraversalRecursive(node.right, result);
    }
}
