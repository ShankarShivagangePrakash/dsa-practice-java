package tree.binary_tree.medium;

import tree.binary_tree.traversal.TreeNode;

import java.util.List;
import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/right-left-view-of-binary-tree/
    https://leetcode.com/problems/binary-tree-right-side-view/description/
 */
public class RightLeftViewOfBinaryTree {

    /*
    Problem statement:
        We have to show the nodes which will be visible from the right side of the tree.

        Time Complexity: O(n)
            Same as DFS
        Space Complexity: O(Height of the tree)
            Same as DFS
     */
    public List<Integer> rightSideView(TreeNode root){
        ArrayList<Integer> result = new ArrayList<>();

        if(root == null)
            return result;

        // we will pass root, current level and the result.
        rightSideViewRecursive(root, 0, result);
        return result;
    }

    public void rightSideViewRecursive(TreeNode node, int level, List<Integer> result){
        // node == null means we have reached the end of the tree, don't do anything return
        if (node == null)
            return;

        // level == result.size means, we have found a new node in the new level,
        // so insert it into the result.
        if(level == result.size())
            result.add(node.val);

        // we are using DFS recursive solution,
        // since we want right side view,
        // we have slightly tweeked the preOrder (Root, Left, Right) to
        // Root - Right - Left
        // this way we will encounter, right side node first and it will be added to result.
        rightSideViewRecursive(node.right, level + 1, result);
        rightSideViewRecursive(node.left, level + 1, result);
    }

    /*
   Problem statement:
       We have to show the nodes which will be visible from the left side of the tree.

       Time Complexity: O(n)
           Same as DFS
       Space Complexity: O(Height of the tree)
           Same as DFS
    */
    public List<Integer> LeftSideView(TreeNode root){
        ArrayList<Integer> result = new ArrayList<>();

        if(root == null)
            return result;

        // we will pass root, current level and the result.
        LeftViewRecursive(root, 0, result);
        return result;
    }

    public void LeftViewRecursive(TreeNode node, int level, List<Integer> result){
        // node == null means we have reached the end of the tree, don't do anything return
        if (node == null)
            return;

        // level == result.size means, we have found a new node in the new level,
        // so insert it into the result.
        if(level == result.size())
            result.add(node.val);

        // we are using DFS recursive solution,
        // since we want left side view,
        // we will use preOrder (Root, Left, Right)
        // this way we will encounter, left side node first and it will be added to result.
        rightSideViewRecursive(node.left, level + 1, result);
        rightSideViewRecursive(node.right, level + 1, result);
    }
}
