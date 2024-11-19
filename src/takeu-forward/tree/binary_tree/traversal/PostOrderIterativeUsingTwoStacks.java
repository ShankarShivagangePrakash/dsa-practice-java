package tree.binary_tree.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/iterative-postorder-traversal-of-binary-tree-using-2-stack
    https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 */
public class PostOrderIterativeUsingTwoStacks {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2n)
            we iterate the entire tree twice
        Space Complexity: O(2n)
            we are using two stacks.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();

        if(root == null)
            return result;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);

        TreeNode node = root;
        while(!stack1.isEmpty()){
            node = stack1.pop();
            stack2.add(node);

            if(node.left != null)
                stack1.push(node.left);
            if(node.right != null)
                stack1.push(node.right);
        }

        while(!stack2.isEmpty()){
            result.add(stack2.pop().val);
        }
        return result;
    }

}
