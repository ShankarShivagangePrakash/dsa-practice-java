package tree.binary_tree.hard;

import tree.binary_tree.traversal.TreeNode;

import java.util.*;

/*
Problem:
    https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/
 */
public class NodesAtDistanceKBinaryTree {

    /*
    Approach:
        Explained here and in notes, check

        Time Complexity: O(2n)
            O(n) for creating parentsMap
            O(n) for queue traversal in distanceK()

        Space Complexity: O(3n)
            O(n) for parentsMap
            O(n) for visitedMap
            O(n) for queue at worst case.
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // to keep track of parents of each node
        // first parameter is the node and second parameter represents its parent
        Map<TreeNode, TreeNode> parentsMap = new HashMap<>();
        markParents(root, parentsMap);

        // to keep track of what nodes are visited.
        // if node is visited we add it to this map and set the value as true.
        Map<TreeNode, Boolean> visitedMap = new HashMap<>();

        // Queue for traversing k distance from the target node.
        Queue<TreeNode> queue = new LinkedList<>();

        // add target node to queue. to start traversal.
        queue.offer(target);
        // add the target node to visited map, so we don't comeback to this node from others.
        visitedMap.put(target, true);

        // create and initialize `distanceTravelled` to 0
        int distanceTravelled = 0;
        while(!queue.isEmpty()){

            // if `distanceTravelled` = 0, we don't need to travel further stop
            if(distanceTravelled == k)
                break;

            // same as level order travesal.
            // queue size gives the number of nodes, through which we have to travel in this phase.
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                // if the left node exists from current node and it has not been visited
                // we can travel in that direction, so add it to the queue and mark it as visited
                if(node.left != null && visitedMap.get(node.left) == null) {
                    queue.offer(node.left);
                    visitedMap.put(node.left, true);
                }

                // if the right node exists from current node and it has not been visited
                // we can travel in that direction, so add it to the queue and mark it as visited
                if(node.right != null && visitedMap.get(node.right) == null) {
                    queue.offer(node.right);
                    visitedMap.put(node.right, true);
                }

                // if the parent node exists from current node and it has not been visited
                // we can travel in that direction, so add it to the queue and mark it as visited
                if(parentsMap.get(node) != null && visitedMap.get(parentsMap.get(node)) == null) {
                    queue.offer(parentsMap.get(node));
                    visitedMap.put(parentsMap.get(node), true);
                }
            }
            // we have travelled one step from possible nodes in this phase, so increment `distanceTravelled`
            distanceTravelled++;
        }

        // after while loop, whatever the contents in the queue are the destination nodes reached after travelling
        // k steps from target node. Add all of them to list and return it
        ArrayList<Integer> result = new ArrayList<>();
        while(!queue.isEmpty())
            result.add(queue.poll().val);

        return result;
    }

    /*
        This method fills the map, with key as node and value as parent
        for each node in the tree

        We use level order approach for this

        we create a queue and add root to it initially

        then till queue becomes empty
            we poll queue element
            if the left and right child exists
            for left and right child, we will mark current node as parent and add it to the `parentsMap`
            also add left and right child to queue
            so that for those nodes, parents child relationship will be formed.
     */
    public void markParents(TreeNode root, Map<TreeNode, TreeNode> parentsMap){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();

            if(node.left != null) {
                parentsMap.put(node.left, node);
                queue.offer(node.left);
            }

            if(node.right != null) {
                parentsMap.put(node.right, node);
                queue.offer(node.right);
            }
        }
    }
}
