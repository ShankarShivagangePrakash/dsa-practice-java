package tree.binary_tree.hard;

import tree.binary_tree.traversal.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/serialize-and-deserialize-a-binary-tree/
    https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
 */
public class SerializeDeserializeBinaryTree {

    // Encodes a tree to a single string.
    /*
    Approach:
        Exaplained in notes, check

        Time Complexity: O(n)
            level order traversal
        Space Complexity: O(n)
            space for queue.
     */
    public String serialize(TreeNode root) {
        if(root == null)
            return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();

        // add root to queue.
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();

            // if current node is null, to string build append #
            // character , is a delimiter.
            if(node == null)
                sb.append("#,");
            else{
                // else, we have valid data, add it and then delimiter
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    /*
    Approach:
        Exaplained in notes, check

        Time Complexity: O(n)
            we traverse the serialized text

        Space Complexity: O(n) + O(n/2)
            O(n) for binary tree
            O(n/2) for queue.
     */
    public TreeNode deserialize(String data) {
        if(data.isEmpty())
            return null;

        Queue<TreeNode> queue = new LinkedList<>();

        // split the serialized string based on delimiter.
        String[] values = data.split(",");

        // create root from the first node in the string array.
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));

        // add root to queue.
        queue.offer(root);

        // we have to use remaining elements in the string array and creater tree.
        // since root has been already created using array[0]
        // we will start processing from array[1]
        for(int i = 1; i < values.length; i++){
            TreeNode parent = queue.poll();
            // # represents null, if the serialized string is not null, then link it to its parent
            if(!values[i].equals("#")){
                TreeNode leftChild = new TreeNode(Integer.parseInt(values[i]));
                parent.left = leftChild;
                // this left child has to be linked with its children, so add it to the queue.
                queue.offer(leftChild);
            }
            // above if condition will add left child
            // now we need to add right child
            // now check for next element in the serialized list
            i++;

            // # represents null, if the serialized string is not null, then link it to its parent
            if(!values[i].equals("#")){
                TreeNode rightChild = new TreeNode(Integer.parseInt(values[i]));
                parent.right = rightChild;
                // this right child has to be linked with its children, so add it to the queue.
                queue.offer(rightChild);
            }
        }
        return root;
    }
}
