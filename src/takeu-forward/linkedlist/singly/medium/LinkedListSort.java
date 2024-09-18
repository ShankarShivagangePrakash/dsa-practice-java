package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://takeuforward.org/linked-list/sort-a-linked-list
    https://leetcode.com/problems/sort-list/
 */
public class LinkedListSort {

    /*
    Approach:
        We use merge sort algorithm to sort linked list as well

        Time Complexity: O(n * log n)
        Space Complexity: O(n)
     */
    public Node mergeSortLinkedList(Node head){

        // Base case
        if(head == null || head.next == null)
            return head;

        Node middleNode = findMiddle(head);

        Node left = head;
        Node right = middleNode.next;
        middleNode.next = null;

        mergeSortLinkedList(left);
        mergeSortLinkedList(right);
        return merge(left, right);
    }

    /*
    Usually to find middle element
    we initalize both slow and fast to head
    but we cannot do that
    we have to initalize fast to head.next because
    when the linked list size becomes two ( by splitting)
        if we don't do fast = head.next
        both fast and slow will be pointing to head and the while loop executes once
            slow will point to second node, we return second node as the middle element
            but we have to split this into one node linked list, to execute our base case
            since it is not happening, it will again call the same method to split again
            this keeps happening infinitely and we get stack overflow error,
        To avoid this, we set fast as head.next
            in this case, it will not execute while loop and slow will be pointint to first node
            and we return first node as the middle element
            and we can split it into a single node linked list
     */
    public static Node findMiddle(Node head){
        Node slow = head;
        Node fast = head.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // merge sorted linked list, same as merge sort.
    public static Node merge(Node left, Node right){

        // we create one dummy node,
        // this will act as the tail of the merged linked list when we want to append a node, we append to this dummy
        Node dummy = new Node(-1);
        // dummy head will store the head reference of the merged linked list.
        Node dummyHead = dummy;

        while(left != null && right != null){
            if(left.val <= right.val){
                dummy.next = left;
                left = left.next;
            } else{
                dummy.next = right;
                right = right.next;
            }
            dummy = dummy.next;
        }
        while (left != null){
            dummy.next = left;
            left = left.next;
            dummy = dummy.next;
        }

        while (right != null){
            dummy.next = right;
            right = right.next;
            dummy = dummy.next;
        }

        // since dummyHead was pointing to dummy node we create and dummyHead.next will actually point to head of the linked list
        // we have to return dummyhead.next
        return dummyHead.next;
    }
}
