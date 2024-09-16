package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://takeuforward.org/linked-list/length-of-loop-in-linked-list
    https://www.geeksforgeeks.org/problems/find-length-of-loop/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=find-length-of-loop
 */
public class LinkedListCylceLength {

    /*
    Approach:
        almost similar to `LinkedListDetectCycle`.java
        when we detect there is a cyle in the linked list

            initalize count to 1, move slow pointer by one step ahead
            keep moving slow pointer by one step and keep incrementing count
            keep doing it, till it reaches back fast pointer
            when it reaches, we have found the length of the cycle in the linked list, return count.

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static int linkedListCylecLength(Node head){
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){

                /*
                    here count is initalized with 1 and slow is move one step forwared before while loop
                    this is because since both slow and fast pointer are at same node, while loop doesn't execute
                    so we move slow one step forward, since that has moved one step forward, we are initalizing count to 1
                    then we traverse till it reaches back fast.
                    That is the length of the loop
                 */
                int count = 1;
                slow = slow.next;

                while(slow != fast){
                    slow = slow.next;
                    count++;
                }
                return count;
            }
        }
        return 0;
    }
}
