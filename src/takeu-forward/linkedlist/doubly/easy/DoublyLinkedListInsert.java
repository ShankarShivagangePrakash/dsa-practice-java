package linkedlist.doubly.easy;

/*
Problem:
    https://www.geeksforgeeks.org/problems/insert-a-node-in-doubly-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=insert-a-node-in-doubly-linked-list
    https://takeuforward.org/data-structure/insert-at-end-of-doubly-linked-list/

    Problem statement:
        You will be given a doubly linked list and a position `p`
        p is array based index, can vary from 0 to linkedlist.size()-1

        you have to insert new node after index `p`

        for example:
                linked list is  1 ---> 2 ---> 3
                and newNode to be inserted is 5 and p is 0
                index 0 is first element `1` after it you have to insert = 1 ---> 5 ----> 2 ---> 3

                linked list is  1 ---> 2 ---> 3
                and newNode to be inserted is 5 and p is 2
                index 2 is last element `3` after it you have to insert = 1 ----> 2 ---> 3 ---> 5
 */
public class DoublyLinkedListInsert {

    /*
    Approach:
        Written in detail in comment.

        Time Complexity: O(n)
        Space Complexity: O(1)

     */
    public static void addNode(Node head, int data, int pos){

        // crreate new node to be inserted.
        Node newNode  = new Node(data);

        Node temp = head;
        int count = 0;

        // when we run this, temp will point to node at index `pos`
        while(count < pos && temp != null){
            count++;
            temp = temp.next;
        }

        // if temp != null means, pos is valid
        if(temp != null){
            // since we need to insert new node, we need to link that new node with the existing next node in the list
            Node after = temp.next;

            /* create link from newNode to
                - temp will become its previous
                - after will become its next node.
             */
            newNode.next = after;
            newNode.prev = temp;
            // after temp - newNode has to come, so link temp.next to newNode.
            temp.next = newNode;

            // if we are inserting at the last position, then after will be null, it will cause null pointer exception
            // so check if is not null, then only create link from after to newNode.
            if(after != null)
                after.prev = newNode;
        }
    }
}
