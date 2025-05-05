package tree.binary_tree.medium;

import java.util.AbstractMap.SimpleEntry;
import tree.binary_tree.traversal.TreeNode;

import java.util.*;

/*
Problem:
    https://takeuforward.org/data-structure/top-view-of-a-binary-tree/
    https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
 */
public class TopViewOfBinaryTree {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            since it is a variation of level order traversal, we visit every node in the tree

        Space Complexity: O(n) + O(n/2)
            O(n) for storing results
            O(n/2) for queue, since it will store nodes only a particular level, maximum it will be O(n/2)
     */
    public ArrayList<Integer> topView(TreeNode root){
        // List to store the result
        ArrayList<Integer> result = new ArrayList<>();

        if(root == null)
            return  result;

        // Map to store the top view nodes, based on their vertical positions
        Map<Integer, Integer> map = new TreeMap<>();

        // Queue for BFS traversal, each element is a pair containing node and its vertical position
        Queue<SimpleEntry<TreeNode, Integer>> queue = new LinkedList<>();

        // Push the root node with its vertical position (0) into the queue
        queue.offer(new SimpleEntry<>(root, 0));

        while (!queue.isEmpty()) {
            // Retrieve the node and its vertical position from the front of the queue
            SimpleEntry<TreeNode, Integer> temp = queue.poll();
            TreeNode node = temp.getKey();
            int vertical = temp.getValue();

            // If the vertical position is not already in the map, add the node's data to the map
            // this ensures, the element viewable from top only be present in the map
            if(!map.containsKey(vertical))
                map.put(vertical, node.val);

            // Push the left child with a decreased vertical position into the queue
            if(node.left != null)
                queue.offer(new SimpleEntry<>(node.left, vertical - 1));

            // Push the right child with an increased vertical position into the queue
            if(node.right != null)
                queue.offer(new SimpleEntry<>(node.right, vertical + 1));
        }

        // Transfer values from the map to the result list
        for( Map.Entry<Integer, Integer> entry : map.entrySet()){
            result.add(entry.getValue());
        }

        return result;
    }
}
