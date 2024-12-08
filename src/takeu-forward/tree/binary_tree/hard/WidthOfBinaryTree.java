package tree.binary_tree.hard;

import javafx.util.Pair;
import tree.binary_tree.traversal.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/maximum-width-of-a-binary-tree/
    https://leetcode.com/problems/maximum-width-of-binary-tree/description/
 */
public class WidthOfBinaryTree {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            Level order traversal
        Space Complexity: O(n)
            level order traversal queue. But majority of times it is O(n/2)
     */
    public int widthOfBinaryTree(TreeNode root){
        // If the root is null, the width is zero
        if(root == null)
            return 0;

        // Initialize a variable 'ans' to store the maximum width
        int answer = 0;

        // Create a queue to perform level-order traversal, where each element is a pair of TreeNode and its position in the level
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();

        // Push the root node and its position (0) into the queue
        queue.offer(new Pair<>(root, 0));

        // Perform level-order traversal
        while(!queue.isEmpty()){
            // Get the number of nodes at the current level
            int currentLevelSize = queue.size();

            // Get the position of the front node in the current level
            int currentLeveMinimum = queue.peek().getValue();
            // Store the first and last positions
            // of nodes in the current level
            int first = 0, last = 0;
            for(int i = 0; i < currentLevelSize; i++){
                // Get the current node
                TreeNode node = queue.peek().getKey();
                // Calculate current position relative to the minimum position in the level
                int currentNodeId = queue.peek().getValue() - currentLeveMinimum;

                // remove the current node from the queue.
                queue.poll();

                // if i == 0, it is the first node in the current level, so set `currentNodeId` to `first
                if(i == 0)
                    first = currentNodeId;
                // if i == currentlevelSize - 1, it is the last node in the current level. so set `currentNodeId` to `last`
                if(i == currentLevelSize  -1)
                    last = currentNodeId;

                // if left and right child exists add them to queue, with their positions
                if(node.left != null)
                    queue.offer(new Pair<>(node.left, 2 * currentNodeId + 1));

                if(node.right != null)
                    queue.offer(new Pair<>(node.right, 2 * currentNodeId + 2));
            }
            // compute maximum length in current level, compare with `answer` and update
            answer = Math.max(answer, last - first + 1);
        }
        return answer;
    }
}
