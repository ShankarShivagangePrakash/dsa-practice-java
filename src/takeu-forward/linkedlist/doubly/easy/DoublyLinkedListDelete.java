package linkedlist.doubly.easy;

/*
Problem:
    https://www.geeksforgeeks.org/problems/delete-node-in-doubly-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=delete-node-in-doubly-linked-list
    https://takeuforward.org/data-structure/delete-last-node-of-a-doubly-linked-list/

    Problem Statement:
        we will be given a non empty linked list and a postion `p` (starts from 1)
        we need to delete the element at index `p` and return head.

        ex:
            1 <--> 3 <--> 4, p = 3
            result will be 1 <--> 3
 */
public class DoublyLinkedListDelete {

    /*
    Approach:
        written in detail in comments

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static Node deleteNode(Node head, int pos){

        Node temp = head;
        int count = 1;

        // similar to what we have done in `DoublyLinkedListInsert.java`
        // but since they have mentioned that `pos` value starts from 1, so we have initialized `count` to 1.
        // when this while loop completes, it will at the node which we need to delete or at index `pos`
        while(count < pos && temp != null){
            count++;
            temp = temp.next;
        }

        // if the node we need to delete is the first element
        // it will be head, we need to update second element as the head
        if(temp == head){

            head = head.next;
            head.prev = null;

            temp.next = null;
            return head;
        }

        // since it is doubly linked list, get after and previous nodes.
        Node after = temp.next;
        Node previous = temp.prev;
        // link previous node with after node, since we will delete temp.
        previous.next = after;

        // temp delete.
        temp.next = null;
        temp.prev = null;

        // if after is not null, means we are not inserting at last position then after will not be null, link it with previous.
        if(after != null)
            after.prev = previous;

        return head;
    }
}
