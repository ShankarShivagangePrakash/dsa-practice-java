package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://www.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=given-a-linked-list-of-0s-1s-and-2s-sort-it
 */
public class LinkedListSort012 {

    /*
    Approach:
        We traverse the entire linked list and keep a count of number of zeroes, one's, two's in the linked list
        Then we traverse the linked list again, we keep modifying node values to zero, one, two
        till those counts becomes zero in the order.

        Time Complexity: O(2n)
            Reason: we are traversing the linked list twice
        Space Complexity: O(1)
     */
    public static Node SortLinkedList012BruteForce(Node head){
        int zero = 0, one = 0, two = 0;

        Node temp = head;
        while(temp != null){
            if(temp.val == 0)
                zero++;
            else if(temp.val == 1)
                one++;
            else
                two++;
            temp = temp.next;
        }

        temp = head;
        while(temp != null){
            if(zero > 0){
                temp.val = 0;
                zero--;
            } else if(one > 0){
                temp.val = 1;
                one--;
            } else {
                temp.val = 2;
                two--;
            }
            temp = temp.next;
        }

        return head;
    }

    /*
    Approach:
        Optimal approach is using Dutch National flag algorithm.
        You can check `SortArrayOf012.java`

        here we create three dummy nodes
            zero, one, two - which will point to the tails of zero,one, two linked list respectively

        we also create three more pointers
            zeroHead, oneHead, twoHead which will point to head of these zero, one, two linked lists respectively

        Finally after traversing, we link the linked lists.
        return the head of the sorted linked list

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static Node sortLinkedList012Optimal(Node head){
        // edge case if linked list is empty or has only one node, no need to sort, just return as it is
        if(head == null || head.next == null)
            return head;

        // dummy nodes points to zero, one, two linked lists tails.
        Node zero = new Node(-1);
        Node one = new Node(-1);
        Node two = new Node(-1);

        // points to zero, one, two linked lists head.
        Node zeroHead = zero;
        Node oneHead = one;
        Node twoHead = two;

        Node temp = head;
        while(temp != null){
            if(temp.val == 0){
                zero.next = temp;
                zero = zero.next;
            } else if(temp.val == 1){
                one.next = temp;
                one = one.next;
            } else{
                two.next = temp;
                two = two.next;
            }
            temp = temp.next;
        }

        /*
            we have to link zero linked list last element to head of
                either one's linked list
                if one's linked list is empty - then link it to two's linked list

            If oneHead.next == null, means there is no 1 in the linked list - so we have to link two's linked list.
         */
        zero.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        // we have to link last elment of the one's linked list to two's linked list head.
        one.next = twoHead.next;
        // last element of the sorted linked list will be always two and it's next has to be null always.
        two.next = null;

        // zeroHead.next will point to actual head of the sorted linked list.
        return zeroHead.next;
    }
}
