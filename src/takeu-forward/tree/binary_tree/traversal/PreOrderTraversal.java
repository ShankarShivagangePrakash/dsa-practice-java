package tree.binary_tree.traversal;

import java.util.List;
import java.util.ArrayList;

/*
Problem:
    https://leetcode.com/problems/binary-tree-preorder-traversal/description/
    https://takeuforward.org/data-structure/preorder-traversal-of-binary-tree/
 */
public class PreOrderTraversal {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            we will visit every node, so O(n)
        Space Complexity: O(n)
            If the tree is skewed completely towards one side, then in stack maximum of n nodes will be waiting for execution so O(n)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        preorderTraversalRecursive(root, result);
        return result;
    }

    public void preorderTraversalRecursive(TreeNode root, ArrayList<Integer> result){

        if(root == null)
            return;

        result.add(root.val);
        preorderTraversalRecursive(root.left, result);
        preorderTraversalRecursive(root.right, result);
    }
}

