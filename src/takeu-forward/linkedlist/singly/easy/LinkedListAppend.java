package linkedlist.singly.easy;

import java.util.Arrays;
import java.util.List;
/*
Problem:
    https://www.geeksforgeeks.org/problems/linked-list-insertion-1587115620/0?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=linked-list-insertion
    https://takeuforward.org/linked-list/insert-at-the-head-of-a-linked-list
 */
public class LinkedListAppend {

    /*
        Problem Statement: Insert at the end

        Approach:
            Create new node which needs we need to add to linked list.
            If head is null means linked list is empty. set new node as head and return it

            Else, traverse till the end of the linked list
            link new node to last element

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static Node append(Node head, int x) {
        Node newNode = new Node(x);
        if(head == null){
            head = newNode;
            return head;
        }

        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }

        temp.next = newNode;
        return head;
    }

    public static void main(String[] args) {
        // Sample array and value for insertion
        List<Integer> arr = Arrays.asList(12, 8, 5, 7);
        int val = 100;

        // Creating a linked list with initial elements from the array
        Node head = new Node(arr.get(0));
        head.next = new Node(arr.get(1));
        head.next.next = new Node(arr.get(2));
        head.next.next.next = new Node(arr.get(3));

        // Inserting a new node at the head of the linked list
        head = append(head, val);

    }
}
