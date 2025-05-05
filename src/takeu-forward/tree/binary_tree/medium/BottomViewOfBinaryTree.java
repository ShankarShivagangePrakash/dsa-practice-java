package tree.binary_tree.medium;

import java.util.AbstractMap.SimpleEntry;
import tree.binary_tree.traversal.TreeNode;

import java.util.*;

/*
Problem:
    https://takeuforward.org/data-structure/bottom-view-of-a-binary-tree/
    https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
 */
public class BottomViewOfBinaryTree {

    public ArrayList<Integer> bottomView(TreeNode root){
        // List to store the result
        ArrayList<Integer> result = new ArrayList<>();

        if(root == null)
            return result;

        // Map to store the bottom view nodes based on their vertical positions
        Map<Integer, Integer> map = new TreeMap<>();

        // Queue for BFS traversal, each element is a pair containing node and its vertical position
        Queue<SimpleEntry<TreeNode, Integer>> queue = new LinkedList<>();

        // Push the root node with its vertical position (0) into the queue
        queue.offer(new SimpleEntry<>(root, 0));

        while (!queue.isEmpty()) {
            // Retrieve the node and its vertical position from the front of the queue
            SimpleEntry<TreeNode, Integer> p = queue.poll();
            TreeNode node = p.getKey();
            int vertical = p.getValue();

            // Update the map with the node's data for the current vertical position
            map.put(vertical, node.val);

            // Push the left child with a decreased vertical position into the queue
            if(node.left != null)
                queue.offer(new SimpleEntry<>(node.left, vertical - 1));

            // Push the right child with an increased vertical position into the queue
            if(node.right != null)
                queue.offer(new SimpleEntry<>(node.right, vertical + 1));
        }

        // Transfer values from the map to the result list
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}
