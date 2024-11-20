package tree.binary_tree.traversal;

import java.util.ArrayList;
import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/preorder-inorder-postorder-traversals-in-one-traversal/
    https://www.geeksforgeeks.org/problems/postorder-traversal-iterative/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=postorder-traversal-iterative
 */
public class PreorderInorderPostOrderTraversal {

    /*
    Approach:
        Explained in detail, in notes

        Time Complexity: O(3n)
            to compute inorder, preorder and postorder we visit every node thrice

        Space Complexity: O(2n)
            we are using stack of pairs
     */
    public void preorderInorderPostorder(TreeNode root){
        if(root == null)
            return;

        ArrayList<Integer> preOrder = new ArrayList<>();
        ArrayList<Integer> inOrder = new ArrayList<>();
        ArrayList<Integer> postOrder = new ArrayList<>();

        Stack<Pair> stack = new Stack<>();

        // push root node with frequency as 1 to stack, before while loop
        stack.push(new Pair(root, 1));
        while(!stack.isEmpty()){
            Pair temp = stack.pop();

            // 1 means preorder
            if(temp.num == 1){
                preOrder.add(temp.node.val);
                temp.num++;
                stack.push(temp);

                if(temp.node.left != null)
                    stack.push(new Pair(temp.node.left, 1));
            }
            // 2 means inorder
            else if(temp.num == 2){
                inOrder.add(temp.node.val);
                temp.num++;
                stack.push(temp);

                if(temp.node.right != null)
                    stack.push(new Pair(temp.node.right, 1));
            }
            // 3 means postorder
            else{
                postOrder.add(temp.node.val);
            }
        }
    }
}

class Pair{
    TreeNode node;
    int num;

    Pair(TreeNode node, int num){
        this.node = node;
        this.num = num;
    }
}
