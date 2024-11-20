package tree.binary_tree.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/iterative-postorder-traversal-of-binary-tree-using-2-stack
    https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 */
public class PostOrderTraversalIterativeOneStack {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2n)
            consider skewed tree towards right
            in that case, we keep adding nodes to stack till we reach leaf node
            Then we comeback
            we iterate the entire tree twice
        Space Complexity: O(n)
            we are using a stack.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();

        if(root == null)
            return result;

        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        // while loop runs till both stack is empty  cur is null.
        while (cur != null || !stack.isEmpty()){

            // current node is not null, so insert into stack and move left
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            else{
                // current node is null, so assign temp with stack top's -> right
                TreeNode temp = stack.peek().right;

                // if temp is also null, stack, top's -> right child is not present
                if(temp == null){
                    // if we are inside this condition means, left child has been evaluated or doesn't exists
                    // and right child doesn't exists or evaluated
                    // you can add temp - stack top to the `result`
                    temp = stack.pop();
                    result.add(temp.val);

                    // you check is stack top right equal to temp, whatever we have popped
                    // it means that there is nothing to process for those nodes
                    // so pop them also and add to the result
                    while(!stack.isEmpty() && stack.peek().right == temp){
                        temp = stack.pop();
                        result.add(temp.val);
                    }
                }
                // stack top has right child, assign it to current and continue moving in the tree.
                else
                    cur = temp;
            }
        }
        return result;
    }
}
