package linkedlist.doubly.medium;

import linkedlist.doubly.easy.Node;

/*
Problem:
    https://www.geeksforgeeks.org/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list
 */
public class DoubleLinkedListDeleteAllOccuranceOfKey {

    /*
    Approach:
        simple
            key can be at three places
            - head
            - middle of the linked list
            - tail
        Our code should handle all three scenario's
        Also since it is doubly linked list, links from both previous and after nodes should be correctly created upon deletion of any node

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static Node doubleLinkedListDeleteAllOccuranceOfKey(Node head, int key){
        Node temp = head;

        while(temp != null){
            // node value is equal to key, then delete the node.
            if(temp.val == key){
                // if current node is head, the make next node as head, since we will delete current node.
                if(temp == head)
                    head = head.next;

                Node after = temp.next;
                Node previous = temp.prev;

                // link previous and after nodes on deletion of current node.
                if(after != null)
                    after.prev = previous;
                if(previous != null)
                    previous.next = after;
            }
            // move to next node in the list
            temp = temp.next;
        }
        return head;
    }
}
