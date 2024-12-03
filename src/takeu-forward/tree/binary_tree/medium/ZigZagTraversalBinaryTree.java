package tree.binary_tree.medium;

import tree.binary_tree.traversal.TreeNode;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;


/*
Problem:
    https://takeuforward.org/data-structure/zig-zag-traversal-of-binary-tree/
    https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 */
public class ZigZagTraversalBinaryTree {

    public List<List<Integer>> zigZagTraversal(TreeNode root){
        // Create a list of lists to store levels
        List<List<Integer>> result = new ArrayList<>();

        // If the tree is empty, return an empty list
        if(root == null)
            return result;

        // Create a queue to store nodes for level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();

        // Push the root node to the queue
        queue.add(root);

        // Flag to determine the direction of traversal (left to right or right to left)
        boolean leftToRight = true;

        while(!queue.isEmpty()){
            List<Integer> currentLeveNodes = new LinkedList<>();
            // Get the size of the current level
            int size = queue.size();

            for(int i = 0; i < size; i++){

                // Get the front node in the queue
                TreeNode node = queue.poll();

                // if left to right is true, insert node values from left to right, else start filling from right to left
                if(leftToRight)
                    ((LinkedList<Integer>) currentLeveNodes).addLast(node.val);
                else
                    ((LinkedList<Integer>) currentLeveNodes).addFirst(node.val);

                // Enqueue the child nodes if they exist
                if(node.left != null)
                    queue.add(node.left);

                if(node.right != null)
                    queue.add(node.right);
            }
            // after completing every level, toggle leftToRight value.
            leftToRight = !leftToRight;

            // return zigZag results.
            result.add(currentLeveNodes);
        }
        return result;
    }
}
