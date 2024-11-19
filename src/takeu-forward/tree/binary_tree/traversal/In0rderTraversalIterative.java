package tree.binary_tree.traversal;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
/*
Problem:
    https://takeuforward.org/data-structure/inorder-traversal-of-binary-tree/
    https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 */
public class In0rderTraversalIterative {

    /*
    Approach:
        We implement the recursive solution by ourselves,
        In recursive solution, elements and there orders are mainitained by auxiliary stack space created by recursion.
        Here, we try to achieve it on our own

        We create an empty stack

        we keep a while loop
            we add current node to the stack
            and if the current node is not empty, we keep moving towards its left child

            when the current node becomes null, it there is no need to move further down
            so pop the top element from the stack assign it as current node
            add the value of current node to result
                since we have already visited the left child of the curren node,
                in previous step we have encountered null for left child, that is the reason we popped element from stack

                so now, we vist right child by moving towards right

            The moment, stack becomes empty, it means there are no elements to process
            break from the loop

        return result

        Time Complexity: O(n)
            we visit every node

        Space Complexity: O(n)
            if the tree is skewed, we end up storing all nodes in stack
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();

        if(root == null)
            return inOrder;

        // create stack
        Stack<TreeNode> stack = new Stack<>();

        // assing root to node, node acts as a current node
        TreeNode node = root;
        while(true){
            // if the current node is not null, move towards left.
            if(node != null){
                stack.push(node);
                node = node.left;
            }
            else{
                // else means current node is null
                // check, is stack empty - if so there is nothing to process, break out
                if(stack.isEmpty())
                    break;

                // since current node is null, pop element from the stack add it to the result
                // since popped element left child has been already explored, now explore its right
                node = stack.pop();
                inOrder.add(node.val);
                node = node.right;
            }
        }
        return inOrder;
    }
}
