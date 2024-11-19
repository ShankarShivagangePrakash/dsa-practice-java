package tree.binary_tree.traversal;

import java.util.ArrayList;
import java.util.List;

/*
Problem:
    https://takeuforward.org/data-structure/post-order-traversal-of-binary-tree/
    https://leetcode.com/problems/binary-tree-postorder-traversal/
 */
public class PostOrderTraversal {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            we will visit every node, so O(n)
        Space Complexity: O(n)
            If the tree is skewed completely towards one side, then in stack maximum of n nodes will be waiting for execution so O(n)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        postorderTraversalRecursive(root, result);
        return result;
    }

    public void postorderTraversalRecursive(TreeNode node, ArrayList<Integer> result){
        if(node == null)
            return;

        postorderTraversalRecursive(node.left, result);
        postorderTraversalRecursive(node.right, result);
        result.add(node.val);
    }
}
