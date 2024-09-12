package linkedlist.doubly.easy;

/*
Problem:
    https://takeuforward.org/data-structure/reverse-a-doubly-linked-list/
    https://www.geeksforgeeks.org/problems/reverse-a-doubly-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=reverse-a-doubly-linked-list
 */
public class DoublyLinkedListReversal {

    /*
    Approach:
        explained in detail in comments

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static Node reverse(Node head){

        // only one node present, need not reverse
        if(head == null || head.next == null)
            return head;

        // before will point to previous element of temp, initially it will be null since we are at its previous is null.
        Node before = null;
        // with temp, we traverse the entire linked list
        Node temp = head;

        while(temp != null){
            // after will point to next element of temp.
            Node after = temp.next;

            // reverse link of temp.
            temp.next = before;
            temp.prev = after;

            // now before will become temp
            before = temp;
            // temp will become after, that's how we move to next element in the original linked list.
            temp = after;
        }
        return head;
    }
}
