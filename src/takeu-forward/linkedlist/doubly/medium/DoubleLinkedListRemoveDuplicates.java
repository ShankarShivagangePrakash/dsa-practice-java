package linkedlist.doubly.medium;

import linkedlist.doubly.easy.Node;

/*
Problem:
    https://www.geeksforgeeks.org/problems/remove-duplicates-from-a-sorted-doubly-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=remove-duplicates-from-a-sorted-doubly-linked-list

    Problem statement:
        Given a sorted linked list, remove duplicates from it
 */
public class DoubleLinkedListRemoveDuplicates {

    /*
    Approach:
        we keep a pointer temp initally it will point to head
        we traverse linked list using temp
        at each node, we point `nextNode` to temp.next
        and we keep moving nextNode forward untill it is not equal to temp.val
        once it is found, we link nextNode to temp in both the directions.

        Time Complexity: O(n)
            Reason: Even though there are two while loops time complexity is still O(n)
                    from inside while loop we traverse duplicates
                    from outer loop we traverse only unique elements
                    combining both we travese entire linked list
                    so it is O(n) only
        Space Complexity: O(1)
     */
    public static Node doubleLinkedListRemoveDuplicatesFromSortedList(Node head){
        Node temp = head;

        // stopping condition either when we have reached end of the list or last element of the linked list
        // because if we have recieved last element means, if there were duplicates of it, those whould have been removed
        // this is the single occurance of a number, so no need to remove it.
        while(temp != null && temp.next != null){
            Node nextNode = temp.next;
            // keep moving nextNode untill you find node value which is different from temp.val
            while(nextNode != null && nextNode.val == temp.val)
                nextNode = nextNode.next;

            // link temp and nextNode
            temp.next = nextNode;
            if(nextNode != null)
                nextNode.prev = temp;

            // move to next element in the list - traversal
            temp = temp.next;
        }
        return head;
    }
}
