package tree.binary_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/data-structure/maximum-sum-path-in-binary-tree/
    https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class MaximumPathSum {

    /*
    Approach:
       We use the variation of HeightOfBinaryTree.java and DiameterOfBinaryTree.java to solve this

       it is almost similar to DiameterOfBinaryTree.java, there we were calculating longest path
       but here, we consider values
       instead of 1 + we have node.val +

       maxDepth(node.left, maxi) is same as diameterOfBinaryTree.java

       but one change is we have enclosed it in a math.max(0, maxDepth(node.left, maxi))
        this is because, if any subtree yields a total less than 0, then don't consider that
        since it won't contribute to maximum path
        so we ignore them by considering max(0, subtree sum)
        this way, we will handle negative values also

       maxi = maximum path value can be found by adding leftPathSum, rightPathSum and node.val

       in the last line we are doing, node.val + max(leftPathSum, rightPathSum)
        this is because, to its upper node, it gives an information
        which path yields more sum

        Time Complexities: O(n)
        Space Complexity: O(n)
            stack space, except that it is O(1)
     */
    public int maxPathSum(TreeNode node){
        int[] maxi = new int[1];
        maxDepth(node, maxi);
        return maxi[0];
    }

    public int maxDepth(TreeNode node, int[] maxi){
        if(node == null)
            return 0;

        int leftPathSum = Math.max(0, maxDepth(node.left, maxi));
        int rightPathSum = Math.max(0, maxDepth(node.right, maxi));

        maxi[0] = Math.max(maxi[0], leftPathSum + rightPathSum + node.val);

        return node.val + Math.max(leftPathSum, rightPathSum);

    }
}
