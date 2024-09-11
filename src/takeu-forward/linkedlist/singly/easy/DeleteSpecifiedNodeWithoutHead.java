package linkedlist.singly.easy;

/*
Problem:
    https://takeuforward.org/data-structure/delete-last-node-of-linked-list/
    https://leetcode.com/problems/delete-node-in-a-linked-list/description/
 */
public class DeleteSpecifiedNodeWithoutHead {

    /*
        Problem Statement:
            head is not given
            we have given the node which has to be deleted

       Approach:
            since we don't have head, we cannot traverse from the beginning of the linked list - stop at previous node
            link previous node with next node(node next to what we need to delete)
            remove link from node to be deleted with next node

            But this approach will not work
            what we can do is, we can copy contents of next node(node which needs be deleted - its next node)
            means copy its value and next node's next node reference
            from next node. next remove its reference to its next node
            so that it will be like removing one node.
            Thus, we successfully deleted one node maintaining linked list order.

            Time Complexity: O(1)
            Space Complexity: O(1)
     */
    public void deleteNode(Node node) {
        /* we cannot delete current node, since head is not given
         rather we can select next node, assign next node's value and nextNode.next to current node.next,
         remove next node from the list.
         note, this will not work, if we try to delete last element of the linked list,
         but it is given, there will not be a case of deleting last node.*/
        Node temp = node.next;
        node.val = temp.val;
        node.next = temp.next;

        temp.next = null;
    }
}
