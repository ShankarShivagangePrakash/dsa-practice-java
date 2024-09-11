package linkedlist.singly.easy;

/*
Problem:
    https://takeuforward.org/linked-list/search-an-element-in-a-linked-list
    https://www.geeksforgeeks.org/problems/search-in-linked-list-1664434326/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=search-in-linked-list-1664434326
 */
public class SearchInLinkedList {

    /*
        Approach:
            if head is null - it will not enter while loop, return false out of while loop will execute
            while traversing if element is found, we return true
            If we come out of while loop means, element is not present, return false

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    static boolean searchKey(int n, Node head, int key) {
        while(head != null){
            if(head.val == key)
                return true;

            head = head.next;
        }
        return false;
    }
}
