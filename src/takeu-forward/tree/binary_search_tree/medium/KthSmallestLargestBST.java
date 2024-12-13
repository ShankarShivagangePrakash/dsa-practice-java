package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/data-structure/kth-largest-smallest-element-in-binary-search-tree/

 */
public class KthSmallestLargestBST {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            DFS traversal for tree (in order)

        Space Complexity: O(Height of the tree)
            stack space
     */
    public int kthSmallest(TreeNode root, int k){
        if(root == null)
            return -1;

        // if we pass primitive values across, the recursive function
        // value will be passed and we cannot access those inside this method
        // so we create array, so it becomes pass by reference and changes will be made to reference we passed
        int[] counter = new int[1];
        int[] kthSmallest = new int[1];

        inOrder(root, k, counter, kthSmallest);
        return kthSmallest[0];
    }

    public void inOrder(TreeNode node, int k, int[] counter, int[] kthSmallest){
        if(node == null || counter[0] >= k)
            return;

        // Traverse left subtree
        inOrder(node.left, k, counter, kthSmallest);

        // Increment counter value, since below logic represents adding a node to in order list
        counter[0]++;
        // if the node you have added to in order list is kth element, then this is the value, we are looking for
        // add it to kthSmallest[0] and return
        if(counter[0] == k){
            kthSmallest[0] = node.val;
            return;
        }

        // traverse right subtree
        inOrder(node.right, k, counter, kthSmallest);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            DFS traversal for tree (in order)

        Space Complexity: O(Height of the tree)
            stack space
     */
    public int kthLargest(TreeNode root, int k){
        if(root == null)
            return -1;

        // if we pass primitive values across, the recursive function
        // value will be passed and we cannot access those inside this method
        // so we create array, so it becomes pass by reference and changes will be made to reference we passed
        int[] counter = new int[1];
        int[] kthLargest = new int[1];

        reverseInOrder(root, k, counter, kthLargest);
        return kthLargest[0];
    }

    public void reverseInOrder(TreeNode node, int k, int[] counter, int[] kthLargest){
        if(node == null || counter[0] >= k)
            return;

        // Traverse right subtree
        reverseInOrder(node.right, k, counter, kthLargest);

        // Increment counter value, since below logic represents adding a node to in order list
        counter[0]++;
        // if the node you have added to in order list is kth element, then this is the value, we are looking for
        // add it to kthLargest[0] and return
        if(counter[0] == k){
            kthLargest[0] = node.val;
            return;
        }

        // traverse left subtree
        reverseInOrder(node.left, k, counter, kthLargest);
    }


}
