package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://takeuforward.org/binary-search-tree/floor-in-a-binary-search-tree/
    https://www.geeksforgeeks.org/problems/floor-in-bst/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=floor-in-bst
 */
public class FloorBST {

    /*
    Approach:
        Explained in notes, check

        Time Compleixty: O(Heigh of the BST)
            maximum we would visit height of binary search tree nodes to find ceil
        Space Complexity: O(Height of the BST)
            stack space
     */
    int findFloor(TreeNode root, int key){
        TreeNode cur = root;

        // Initialize the floor variable to store the floor value
        int floor = -1;
        while(cur != null){
            // If the key is found, assign it as the floor value and return
            if(cur.val == key)
                return key;

            // if key is greater than current node value
            if(key > cur.val){
                // then current node can be a possible floor value
                // so assign current node to floor
                floor = cur.val;
                // to find better floor move to right subtree, means anyone greater than current floor but less than key
                cur = cur.right;
            }
            // else means key < current node value,
            // find anyone lesser than key
            // so move towards left subtree
            else
                cur = cur.left;
        }
        return floor;
    }
}
