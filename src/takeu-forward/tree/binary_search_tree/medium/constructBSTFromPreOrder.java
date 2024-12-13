package tree.binary_search_tree.medium;

import tree.binary_tree.traversal.TreeNode;

/*
Problem:
    https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/description/
 */
public class constructBSTFromPreOrder {

    /*
    Approach:
        Explained in notes and in program, check

        Time Complexity:
            Each node can be visted at max three times
                once from up, once from left child and once from right child at maximum

            so O(3 * n)
            which is almost same as O(n)

        Space Complexity: O(1)
     */
    public TreeNode constructBSTFromPreorder(int[] preOrder){
        return constructBSTFromPreOrderRecusrive(preOrder, Integer.MAX_VALUE, new int[]{0});
    }

    public TreeNode constructBSTFromPreOrderRecusrive(int[] preOrder, int bound, int[] i){
        /* if we have reached end of the array,
         or the upper bound at current node is lesser than the array element we have to insert
         return null*/
        if(i[0] == preOrder.length || preOrder[i[0]] > bound)
            return null;

        // create new node with current array element and move iterator `i` value
        TreeNode node = new TreeNode(preOrder[i[0]++]);
        /* since we have incremented `i` in previous expression, now in below two steps,
         we will try to create new node for that next array element pointed by `i`
         and link as left or right child for current node
         check if you can insert node as left child, for left child upper bound will be new node value*/
        node.left = constructBSTFromPreOrderRecusrive(preOrder, node.val, i);
        // check if you need to insert as right child, for right child no updates to upper bound
        node.right = constructBSTFromPreOrderRecusrive(preOrder, bound, i);

        return node;
    }
}
