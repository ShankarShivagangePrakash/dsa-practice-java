package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://leetcode.com/problems/delete-node-in-a-bst/description/
 */
public class DeleteNodeBST {

    /*
    Approach:
        Explained in notes,

        We break this problem into three cases:

            - The node we want to delete is leaf node
            - The node we want to delete has only one child
               first  we will check, is left child null, if so we assign right child as the new child of the parent of the node we want to delete
               else we will check, is right child null, if so we assign left child as the new child of the parent of the node we want to delete

               the above conditions will cover for first case (node we want to delete is leaf node)
            - The node we want to delete has both left and right child

                we will link left child of the node we want to delete as the child of the delete node's parent
                we will move to right subtree in the left child and move to right most child
                    to that right most child, we assign the right child of the delete node

            This way we can still maintain the property of BST even after deletion

         Time Complexity: O(Height of the BST)

         Space Complexity: O(1)
     */
    public TreeNode deleteNodeBST(TreeNode root, int key){
        if(root == null)
            return null;

        // if the node, we want to delete is root itself, call the helper method,
        // which will delete the node and return root of the subtree, return that as the root.
        if(root.val == key)
            return helper(root);

        TreeNode temp = root;

        while(temp != null){
            // the key we want to delete is lesser than the current node
            // so we move in left subtree
            if(key < temp.val) {
                // if the left child of current node is not null and it's value is equal to `key`
                // then we have to delete that node
                // so call helper method and break while loop after it
                if (temp.left != null && temp.left.val == key) {
                    temp.left = helper(temp.left);
                    break;
                }
                // the left child value is not equal to key, so move left
                temp = temp.left;
            }
            // else block means, `key` is greater than current node value
            else{
                // check current node right is not null and its value is same as key
                // it means you have to remove temp.right, call helper method for it and break while loop
                if(temp.right != null && temp.right.val == key){
                    temp.right = helper(temp.right);
                    break;
                }
                // temp.right is not the node we want to delete, so move further right
                temp = temp.right;
            }
        }
        return root;
    }

    // delete the node we have passed
    public TreeNode helper(TreeNode node){
        // if the left child is null return right child, this will be linked to delete node's parent
        if(node.left == null)
            return node.right;
        // else if the right child is null return left child, this will be linked to delete node's parent
        else if(node.right == null)
            return node.left;
        else{
            // both children are not null
            // we will link delete node's right child to
            // delete node's left child right subtree's last right node
            TreeNode rightChild = node.right;
            TreeNode lastRigghtInLeftSubTree = findLastRight(node.left);
            lastRigghtInLeftSubTree.right = rightChild;
            return node.left;
        }
    }

    public TreeNode findLastRight(TreeNode node){
        while(node.right != null)
            node = node.right;

        return node;
    }
}
