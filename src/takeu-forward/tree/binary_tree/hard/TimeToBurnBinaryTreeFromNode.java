package tree.binary_tree.hard;

import tree.binary_tree.traversal.TreeNode;

import java.util.*;

public class TimeToBurnBinaryTreeFromNode {

    /*
    Approach:
        Same as `NodesAtDistanceKBinaryTree.java`
        There we were asked to find list of nodes which are at a distance `k` from a given node

        In this problem we will be given a node value
        and we need to find the time required to burn all nodes in the binary tree,
        starting from the node they have given (they have given node value)

        We know from each node, we can move in three direction (left, right, upwards)
            to burn nodes in all these three directions it will take 1 unit of time

        We have to start from the node they have specified and say how much time required to burn entire tree

        Since we need to move in upward direction, we need to find the parent of each node
        we will achieve it using markParents()

        also, while traversing, we should not visit the same node
            so we make use of visited map

        also one more thing, our logic will work based on node
            but in the problem they have given target node value
            so we search the node with value `target` in the binary tree
            and use it

        only few changes in this program compared to others
            - stopping condition while loop is only when queue becomes empty, because we need to burn entire tree
            - we should increase `time` only if we burn any node in current phase
                so we keep a variable `burnt` initialized to 0 in each phase
                if it is set to 1
                    then only we update `time`

        Time Complexity: O(3n)
            O(n) for creating parentsMap
            O(n) for searching node
            O(n) for queue traversal in distanceK()

        Space Complexity: O(3n)
            O(n) for parentsMap
            O(n) for visitedMap
            O(n) for queue at worst case.
     */
    public int distanceK(TreeNode root, int target, int k) {
        // to keep track of parents of each node
        // first parameter is the node and second parameter represents its parent
        Map<TreeNode, TreeNode> parentsMap = new HashMap<>();
        markParents(root, parentsMap);

        // to keep track of what nodes are visited.
        // if node is visited we add it to this map and set the value as true.
        Map<TreeNode, Boolean> visitedMap = new HashMap<>();

        // Queue for traversing nodes from the target node.
        Queue<TreeNode> queue = new LinkedList<>();

        // since the input is the node value, we need to search for that node,
        // then it will become `NodesAtDistanceKBinaryTree.java`
        TreeNode targetNode = searchNodeWithValue(root, target);

        // add target node to queue. to start traversal.
        queue.offer(targetNode);
        // add the target node to visited map, so we don't comeback to this node from others.
        visitedMap.put(targetNode, true);

        // create and initialize `timeRequired` to 0
        int timeRequired = 0;
        while(!queue.isEmpty()){

            // burnt represents any node burnt in current phase.
            int burnt = 0;

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
                    burnt = 1;
                }

                // if the right node exists from current node and it has not been visited
                // we can travel in that direction, so add it to the queue and mark it as visited
                if(node.right != null && visitedMap.get(node.right) == null) {
                    queue.offer(node.right);
                    visitedMap.put(node.right, true);
                    burnt = 1;
                }

                // if the parent node exists from current node and it has not been visited
                // we can travel in that direction, so add it to the queue and mark it as visited
                if(parentsMap.get(node) != null && visitedMap.get(parentsMap.get(node)) == null) {
                    queue.offer(parentsMap.get(node));
                    visitedMap.put(parentsMap.get(node), true);
                    burnt = 1;
                }
            }
            // we have travelled one step from possible nodes in this phase, so increment `distanceTravelled`
            if(burnt == 1)
                timeRequired++;
        }

        // timeRequires is the total amount of time required to burn entire tree from target node, return it
        return timeRequired;
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

    public TreeNode searchNodeWithValue(TreeNode node, int target){
        // Base case: if the node is null, return null
        if (node == null)
            return null;

        // Found the target node
        if (node.val == target)
            return node;

        // Search in the left subtree
        TreeNode foundNode = searchNodeWithValue(node.left, target);
        // If found in left subtree, return it
        if (foundNode != null)
            return foundNode;

        // Otherwise, search in the right subtree
        return searchNodeWithValue(node.right, target);
    }
}
