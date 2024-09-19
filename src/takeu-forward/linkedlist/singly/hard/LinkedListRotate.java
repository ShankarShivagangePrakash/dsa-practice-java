package linkedlist.singly.hard;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://takeuforward.org/data-structure/rotate-a-linked-list/
    https://leetcode.com/problems/rotate-list/description/

    Problem Statement:
        Given a linked list and a variable k
            rotate the linked list by k times(towards right)
            and return the head of the rotated linked list
 */
public class LinkedListRotate {

    /*
    Approach:
        In linked list, we can link last element of the list to first node
        we explicitly need not swap elements unlike arrays
        It is a two step process in linked list rotation
            - we just have to link tail with the head
            - find which node from original list will become last node in the rotated list,
                its next node will become head of the rotated list
                make its next to null, so it will become tail of rotated list
            return head

        Time Complexity: O(2n)
            Reason: one traversal to find tail of the linked list
            and other traversal to find nth node (new tail of the rotated list)
        Space Complexity: O(1)
     */
    public static Node linkedListRotateKTimes(Node head, int k){
        // if head is null or k is zero, we need not do anything, return head
        if(head == null || k == 0)
            return head;

        // find the length of the linked list and the tail
        int len = 1;
        Node tail = head;
        while(tail.next != null){
            len++;
            tail = tail.next;
        }

        /*  now we know the length of the linked list
         if the modulo division of k and len yields 0 as reminder means
         upon rotating we get the same original linked list, so no need to explicitly perform rotation simply return head*/
        if(k % len == 0)
            return head;

        // rotating k times is equivalent to, rotating k % len times
        // This helps if k is greater than the size of the linked list
        k = k % len;

        // now link tail to first element of the linked list
        tail.next = head;

        /* now we need to find the last element of the rotated linked list
         it's next element will become head of the rotated linked list
         if we have a linked list with 5 elements and we want to rotate 2 times
         last two elements will move to front and 3rd elment from the back(in the original list) will become tail
         so we have to find that element
         in this case 3rd
         general formula, (len - k)th element
         (5-2) = 3rd element*/
        Node newLastNode = findNthNode(head, len-k);
        // last node next will be the head of the rotated linked list
        head = newLastNode.next;
        newLastNode.next = null;

        return head;
    }

    // get's new tail of the rotated linked list
    public static Node findNthNode(Node head, int k){
        int count = 1;
        Node temp = head;
        while(temp != null && k > 0){
            if(count == k)
                return temp;
            count++;
            temp = temp.next;
        }
        return null;
    }
}
