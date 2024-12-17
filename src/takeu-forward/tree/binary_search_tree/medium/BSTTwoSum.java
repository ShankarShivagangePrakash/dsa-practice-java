package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

import java.util.Stack;

/*
Problem:
    https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
 */
public class BSTTwoSum {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            we iterate the entire tree only once

        Space Complexity: O(Height of the tree) * 2
            we are using two stacks `before` and `next`
            each can have maximum of height number of elements
     */
    public boolean findTarget(TreeNode root, int k) {
        if(root == null)
            return false;

        BSTTwoSumIterator left = new BSTTwoSumIterator(root, false);
        BSTTwoSumIterator right = new BSTTwoSumIterator(root, true);

        // gives the smallest element
        int i = left.next();
        // gives the largest element
        int j = right.next();

        while(i < j){
            if(i + j == k)
                return true;

            else if(i + j < k)
                i = left.next();

            else
                j = right.next();
        }
        return false;
    }
}

/*
Clas which serves to create and maintain both `next` and `before` stack
 */
class BSTTwoSumIterator {

    Stack<TreeNode> stack;
    // reverse true means `before` stack
    boolean reverse = true;

    BSTTwoSumIterator(TreeNode root, boolean reverse) {
        this.stack = new Stack<>();
        this.reverse = reverse;
        pushAll(root);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        if (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // reverse true means,  `before` stack, we need to move to left child and add all its extrement right child
            // reverse false means,  `next` stack, we need to move to right child and add all its extrement left child
            if (reverse)
                pushAll(node.left);
            else
                pushAll(node.right);

            return node.val;
        }
        return -1;
    }

    public void pushAll(TreeNode node) {
        while (node != null) {
            stack.push(node);
            if (reverse)
                node = node.right;
            else
                node = node.left;
        }
    }
}
