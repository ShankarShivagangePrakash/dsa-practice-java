package tree.binary_tree.medium;
import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/data-structure/maximum-depth-of-a-binary-tree/
    https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class HeightOfBinaryTree {

    /*
    Approach:
        We compute maximum depth of a binary tree using formula = 1 + MaxDepth(leftSubTree, rightSubTree)
            base case: if the node upon traversal has reached null, comeback, return height of that null node as 0

        Time Complexity: O(n)
            We will visit every node, it is a variation of Left Order Traversal

         Space Complexity: O(Height Of the tree):
            stack space,
            if the tree is completely skewed, then it will end up taking O(n)
            which is rare.
     */
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;

        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
