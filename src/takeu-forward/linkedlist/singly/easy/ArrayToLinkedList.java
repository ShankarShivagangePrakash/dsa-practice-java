package linkedlist.singly.easy;

/*
Problem:
    https://www.geeksforgeeks.org/problems/introduction-to-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=introduction-to-linked-list
 */
public class ArrayToLinkedList {

    /*
    Problem statement:
        We will be given an array, construct linked list for array elements

    Approach:
        construct head using array first element.
        we have to link previous element of the linked list to new node we are adding.
        So keep track of prev

        start from the array[1] -since head is already created from array[0]
        create new node for array element, link it with the previous node of the linked list
        set prev as current element, so it can be used to link it with next node we will add.

        finally return head

        Time Complexity: O(n)
        Space Complexity: O(n) - to return the linked list result
            excluding that it will become O(1)
     */
    static Node constructSingleLinkedList(int arr[]) {

        Node head = new Node(arr[0]);
        Node prev = head;

        for(int i = 1; i < arr.length; i++){
            Node temp = new Node(arr[i]);
            prev.next = temp;
            prev = temp;
        }
        return head;
    }
}
