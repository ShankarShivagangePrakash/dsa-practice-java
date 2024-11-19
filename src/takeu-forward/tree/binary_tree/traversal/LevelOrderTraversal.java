package tree.binary_tree.traversal;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/level-order-traversal-of-a-binary-tree/
    https://leetcode.com/problems/binary-tree-level-order-traversal/description/
 */
public class LevelOrderTraversal {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            We visit every node once

        Space Complexity: O(n)
            consider the tree with only 1 nodes, then queue can store 1 node in it, which is O(n)
            which is the worst case scenario

            in majority of the scenario's, queue size can be maximum of (largerst number of nodes levelwise in a tree)

            But we are not considering the space utilized by result list
            because we are using it to return the answer, nothing else
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // Create a list of lists to store levels
        List<List<Integer>> result = new ArrayList<>();

        // If the tree is empty, return an empty list
        if(root == null)
            return result;

        // Create a queue to store nodes for level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();

        // Push the root node to the queue
        queue.add(root);

        while(!queue.isEmpty()){
            // Get the size of the current level
            int size = queue.size();

            List<Integer> currentLevelNodes = new ArrayList<>();
            for(int i = 0; i < size; i++){
                // Get the front node in the queue
                TreeNode node = queue.poll();

                currentLevelNodes.add(node.val);

                // Enqueue the child nodes if they exist
                if(node.left != null)
                    queue.add(node.left);

                if(node.right != null)
                    queue.add(node.right);
            }
            // Store the current level in the answer list
            result.add(currentLevelNodes);
        }
        return result;
    }
}
