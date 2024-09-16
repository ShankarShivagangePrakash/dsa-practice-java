package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://takeuforward.org/linked-list/delete-the-middle-node-of-the-linked-list
    https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/
 */
public class LinkedListDeleteMiddleNode {

    /*
    Approach:
        Using slow and fast pointers we find the middle element of the linked list
        slow will be pointint to middle element of the linked list.
        But we have to delete this node itself. Means we have to link its previous node with slow.next

        For that we traverse again from the begining of the linked list till temp.next == slow
            if so we delete slow

        Time Complexity: O(n)
            n/2 to get median - since fast pointer move by 2 steps each time.
            n/2 to reach to slow position

        Space Complexity: O(1)
     */
    public static Node linkedListDeleteMiddleNodeBruteForce(Node head){

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        Node temp = head;
        while(temp != null){
            if(temp.next == slow){
                temp.next = slow.next;
                slow.next = null;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    /*
   Approach:
       With small modification to brute force approach, we can solve this efficiently.
       before starting the loop
            we will move fast pointer once by two steps
            i.e fast = head;
            then fast = fast.next.next

       Thought process behind this is,
            when fast pointer reaches the end of the linked list
            slow will be pointing to previous of middle element
       so we can easily delete middle element.

       Time Complexity: O(n/2)
           n/2 to get median - since fast pointer move by 2 steps each time.

       Space Complexity: O(1)
    */
    public static Node linkedListDeleteMiddleNodeOptimal(Node head){

        Node slow = head;
        Node fast = head;
        fast = fast.next.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        Node deleteNode = slow.next;
        slow.next = deleteNode.next;
        deleteNode.next = null;

        return head;
    }


}
