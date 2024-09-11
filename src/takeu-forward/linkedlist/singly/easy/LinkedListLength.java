package linkedlist.singly.easy;

/*
Problem:
    https://takeuforward.org/linked-list/find-the-length-of-a-linked-list
    https://www.geeksforgeeks.org/problems/count-nodes-of-linked-list/0?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=count-nodes-of-linked-list
 */
public class LinkedListLength {

    /*
        Approach:
            traverse till the end of the linked list
            while traversing increment counter

            return count

         Time Complexity: O(n)
         Space Complexity: O(1)
     */
    public int getCount(Node temp) {
        int count = 0;
        while(temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }
}
