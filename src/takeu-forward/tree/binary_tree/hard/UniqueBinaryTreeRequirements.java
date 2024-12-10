package tree.binary_tree.hard;

/*
Problem:
    https://www.geeksforgeeks.org/problems/unique-binary-tree-requirements/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=unique-binary-tree-requirements
 */
public class UniqueBinaryTreeRequirements {

    /*
    Approach:
        Each traversal is represented with an integer: preorder - 1, inorder - 2, postorder - 3.

        Time Complexity: O(1)
     */
    public static boolean isPossible(int a, int b){
        if(a == 2 && b == 2)
            return false;
        if(a == 2 || b == 2)
            return true;
        else
            return false;
    }
}
