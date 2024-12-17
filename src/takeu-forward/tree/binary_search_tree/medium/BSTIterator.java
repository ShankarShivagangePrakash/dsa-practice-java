package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

import java.util.Stack;

/*
Problem:
    https://leetcode.com/problems/binary-search-tree-iterator/description/
 */
/*

Approach:
    Explained in notes, check

    Time Complexity: O(1) - average time complexity
        Even though, we push number of elements to stack few times
        totally, we will push n elements to the stack and if we call next() n times, all of them takes
        on an average takes O(1)
            few elements will take little more than O(1)
            majority takes O(1)
            average O(1)

    Space Complexity: O(Height of the binary tree)
        stack will have maximum of height of the tree elements
 */
public class BSTIterator {

    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        pushAll(root);
    }

    public int next() {
        if (!stack.isEmpty()) {
            TreeNode next = stack.pop();
            pushAll(next.right);
            return next.val;
        }
        return -1;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public void pushAll(TreeNode node){
        while(node != null){
            stack.push(node);
            node = node.left;
        }
    }
}
