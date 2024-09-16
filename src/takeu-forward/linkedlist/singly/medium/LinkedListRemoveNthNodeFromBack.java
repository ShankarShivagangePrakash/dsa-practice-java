package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://takeuforward.org/data-structure/remove-n-th-node-from-the-end-of-a-linked-list/
    https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class LinkedListRemoveNthNodeFromBack {

    /*
    Approach:
        First we traverse the entire linked list once to get the length of the linked list
        This value will be stored in the variable `count`

        If `count` == n,
            means we have to delete nth node from the back of the linked list
            since number of nodes in the linked list(length of the linked list) is equal to n means
            we have to remove first element of the linked list
            in this case remove head

        else
            int res = count - n
                which will point to previous node which we want to delete.
            then delete the node and return head

       Time Complexity: O(L) + O(L-n)
            where L is the length of the linked list, n is the nth node which we want to delete from the back
       Space Complexity: O(1)
     */
    public static Node removeNthNodeFromBackBruteForce(Node head, int n){

        if(head == null)
            return null;

        int count = 0;
        Node temp = head;

        // find the length of the linked list.
        while(temp != null){
            count++;
            temp = temp.next;
        }

        // if count == n means, we have to delete the first node - means head.
        if(count == n){
            Node newHead = head.next;
            head.next = null;
            return newHead;
        }

        // get the previous node which we want to delete.
        int res = count - n;
        temp = head;

        // reach till the previous node which we want to delete.
        while(temp != null){
            res--;
            if(res == 0)
                break;
            temp = temp.next;
        }

        // now temp.next is the node we want to delete. delete it.
        Node deleteNode = temp.next;
        temp.next = deleteNode.next;
        deleteNode.next = null;
        // return head
        return head;
    }

    /*
        In the brute force approach, we traverse linked list almost twice
        In optimal approach, we try to reduce it to one time
        Using slow and fast pointers
        slow and fast pointers will be pointing to head
        we will move fast pointer by n steps ahead
            Now if fast is pointing to null means, we have to delete the first element of the linked list
            remove head
        keep moving fast and slow pointers by one step, till fast pointer reaches the last element of the linked list
        when fast pointer reaches the last element of the linked list,
            slow will be pointint to previous node which we need to delete
            delete and return head

        Time Complexity: O(L)
            where L is the length of the linked list
            we traverse by steps, initally - fast pointer
            then fast pointer will traverse through remaning elements of the linked list
            so we traverse linked list completely one time.
        Space Complexity: O(1)
     */
    public static Node removeNthNodeFromBackOptimal(Node head, int n){

        // if linked list is empty remove null
        if(head == null)
            return null;

        Node slow = head;
        Node fast = head;

        // move fast pointer by n steps ahead.
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }

        // if fast is null means, we have to remove nth node from the back of the list
        // i.e first node - remove head
        if(fast == null) {
            Node newHead = head.next;
            head.next = null;
            return newHead;
        }

        // move fast pointer, till it reaches the last element
        // then slow will be pointing to the previous node of node we want to delete.
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        // delete the node
        Node deleteNode = slow.next;
        slow.next = deleteNode.next;
        deleteNode.next = null;
        return head;
    }
}
