package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://takeuforward.org/data-structure/reverse-a-linked-list/
    https://leetcode.com/problems/reverse-linked-list/description/
 */
public class LinkedListReverseIterative {

    /*
    Approach:
        Check notes

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static Node reverseIterative(Node head){
        Node before = null;
        Node temp = head;

        while(temp != null){
            Node after = temp.next;

            temp.next = before;

            before = temp;
            temp = after;
        }
        return before;
    }
}
