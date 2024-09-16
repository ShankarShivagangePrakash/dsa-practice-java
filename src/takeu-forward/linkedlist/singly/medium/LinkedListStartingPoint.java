package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://takeuforward.org/data-structure/starting-point-of-loop-in-a-linked-list/
    https://leetcode.com/problems/linked-list-cycle-ii/description/
 */
public class LinkedListStartingPoint {

    /*
    Approach:
        Initialise two pointers, `slow` and `fast`, to the head of the linked list.
        `slow` will advance one step at a time, while `fast` will advance two steps at a time.
        These pointers will move simultaneously.

        Traverse the linked list with the `slow` and `fast` pointers.
        While traversing, repeatedly move `slow` one step and `fast` two steps at a time.

        Continue this traversal until one of the following conditions is met:
            `fast` or `fast.next` reaches the end of the linked list (i.e., becomes null).
            In this case, there is no loop in the linked list, and the algorithm terminates by returning null.

            `fast` and `slow` pointers meet at the same node. This indicates the presence of a loop in the linked list.

        Reset the `slow` pointer to the head of the linked list.
        Move `fast` and `slow` one step at a time until they meet again.
        The point where they meet again is the starting point.

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static Node startingPoint(Node head){
        Node slow = head, fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            // cycle present, find its starting point
            if(slow == fast){
                slow = head;

                // move both slow and fast by one step untill they meet.
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                // the position they have meet is the starting point of the linked list cycle.
                return slow;
            }
        }
        return null;
    }
}
