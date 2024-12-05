package tree.binary_tree.medium;

import javafx.util.Pair;
import tree.binary_tree.traversal.TreeNode;

import java.util.*;

/*
Problem:
    https://takeuforward.org/data-structure/vertical-order-traversal-of-binary-tree/
    https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 */
public class VerticalOrderTraversalOfBinaryTree {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * logn)
            O(n) for level order traversal
            since we are using tree set, for insertion and read operations it might take log n

        Space Complexity: O(3n)
            O(n) for returning the result
            O(n) the treemap at line 32 which stores all nodes in vertical order fashion
            O(n) for queue at worst case, majority of the time, it won't be O(n)
     */
    public List<List<Integer>> verticalTraversal(TreeNode root){
        // Map to store nodes based on
        // vertical and level information
        // outer map key is the vertical and inner map key is the level and the treeSet stores the nodes available at a particular vertical and particular level
        Map<Integer, Map<Integer, TreeSet<Integer>>> nodes = new TreeMap<>();

        // Queue for BFS traversal, each element is a pair containing node and its vertical and level information
        Queue<Pair<TreeNode, Pair<Integer, Integer>>> queue = new LinkedList<>();

        // Push the root node with initial vertical and level values (0, 0)
        queue.offer(new Pair<>(root, new Pair<>(0, 0)));

        // level order traversal
        while (!queue.isEmpty()) {
            // Retrieve the node and its vertical and level information from the front of the queue
            Pair<TreeNode, Pair<Integer, Integer>> p = queue.poll();
            TreeNode node = p.getKey();

            // x represents vertical(column) and y represents level
            int x = p.getValue().getKey();
            int y = p.getValue().getValue();

            // if there is no map for a particular vertical create it
            // if there is no set for a particular level at a particular vertical create it
            // then insert the data.
            nodes.computeIfAbsent(x, k -> new TreeMap<>())
                    .computeIfAbsent(y, k -> new TreeSet<>())
                    .add(node.val);

            // if left child present, insert it into the queue
            // we will move left, so vertical will be reduced by 1
            // and we are moving down, so level will increment by 1
            if(node.left != null)
                queue.offer(new Pair<>(node.left, new Pair<>(x - 1, y + 1)));

            // if right child present, insert it into the queue
            // we will move right, so vertical will be increased by 1
            // and we are moving down, so level will increment by 1
            if(node.right != null)
                queue.offer(new Pair<>(node.right, new Pair<>(x+1, y+1)));
        }

        List<List<Integer>> answer = new ArrayList<>();

        // now we have elements ordered at each vertical,
        // inside each vertical elements are sub categorised as levels.
        // now we need to collate elements at a particular level inside each vertical and insert into a list
        // do that for each vertical and return the answer.
        for (Map.Entry<Integer, Map<Integer, TreeSet<Integer>>> entry : nodes.entrySet()) {
            List<Integer> column = new ArrayList<>();
            // now we have all the nodes values belong to a particular vertical
            // but they are split across many levels, so iterate through each level and add it to the list
            for (TreeSet<Integer> set : entry.getValue().values()) {
                column.addAll(set);
            }
            answer.add(column);
        }
        return answer;
    }
}
