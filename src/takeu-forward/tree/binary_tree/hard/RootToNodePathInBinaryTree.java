package tree.binary_tree.hard;

import tree.binary_tree.traversal.TreeNode;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/print-root-to-node-path-in-a-binary-tree/
    https://www.geeksforgeeks.org/problems/root-to-leaf-paths/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=root-to-leaf-paths
 */
public class RootToNodePathInBinaryTree {

    /*
    Approach:
        Explained in notes, check
        We are using DFS to solve this problem

        Time Complexity: O(n)
            Same as DFS

        Space Complexity: O(height of the tree) to store the result.
     */
    public static ArrayList<Integer> rootToNodePath(TreeNode root, int val){
        ArrayList<Integer> path = new ArrayList<>();

        if(root == null)
            return path;

        getPathDFS(root, val, path);
        return path;
    }

    public static boolean getPathDFS(TreeNode node, int val, ArrayList<Integer> result){
        // Base case: If the current node is null, return false,
        // means we have reached further after leaf but not found the search val so this is not the path
        if(node == null)
            return false;

        // if the current node is not null, then it can be a path to our search val, so add it to the result list
        result.add(node.val);

        // try to find the search val in both left and right directions of current node.
        // if you find the search val in either of the both directions, you have found the path to the search val
        // so return true and avoid further new recursive calls
        if(getPathDFS(node.left, val, result) || getPathDFS(node.right, val, result))
            return true;

        // If the target value 'x' is not found in the current path, backtrack
        result.remove(result.size() - 1);
        return false;
    }
}
