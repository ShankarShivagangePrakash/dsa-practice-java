package tree.binary_tree.hard;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/binary-tree/count-number-of-nodes-in-a-binary-tree/
    https://leetcode.com/problems/count-complete-tree-nodes/description/
 */
public class CountNodesCompleteBinaryTree {

    /*
    Approach:
        Explained in notes
        We are using DFS, inOrder traversal to visit every node
        when we visit nodes, we increment the counter
        and we finally return count value, after completing traversal

        Time Complexity: O(n)
        Space Complexity: O(Height of the tree)
            since it is complete binary tree - O(log n)
     */
    public int countNodesCompleteBinaryTreeBruteforce(TreeNode root){
        int[] count = new int[1];
        inOrder(root, count);
        return count[0];
    }

    public void inOrder(TreeNode node, int[] count){
        if(node == null)
            return;

        // you have visited current node, so increment count
        count[0]++;
        inOrder(node.left, count);
        inOrder(node.right, count);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(log n)^2
            O(log N * log N) where N is the number of nodes in the Binary Tree.

            The calculation of leftHeight and rightHeight takes O(log N) time.
            In the worst case, when encountering the second case (leftHeight != rightHeight), the recursive calls are made at most log N times (the height of the tree).
            Therefore, the total time complexity is O(log N * log N).

        Space Complexity: O(Height of the binary tree)
            stack space, since it is complete binary tree
            it will be O(log n)
     */
    public int countNodesCompleteBinaryTreeOptimal(TreeNode root){
        return countNodesCompleteBinaryTreeRecursive(root);
    }

    public int countNodesCompleteBinaryTreeRecursive(TreeNode node){
        if(node == null)
            return 0;

        int leftSubTreeHeight = getLeftSubTreeHeight(node);
        int rightSubTreeHeight = getRightSubTreeHeight(node);

        if(leftSubTreeHeight == rightSubTreeHeight)
            return (1 << leftSubTreeHeight) - 1;
        else
            return  1 + countNodesCompleteBinaryTreeRecursive(node.left) + countNodesCompleteBinaryTreeRecursive(node.right);
    }

    public int getLeftSubTreeHeight(TreeNode node){
        int count = 0;
        while(node != null){
            count++;
            node = node.left;
        }
        return count;
    }

    public int getRightSubTreeHeight(TreeNode node){
        int count = 0;
        while(node != null){
            count++;
            node = node.right;
        }
        return count;
    }

}
