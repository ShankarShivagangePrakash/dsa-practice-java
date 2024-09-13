package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;
/*
Problem:
    https://leetcode.com/problems/middle-of-the-linked-list/
    https://takeuforward.org/data-structure/find-middle-element-in-a-linked-list/
 */
public class LinkedListMiddleNode {

    /*
    Approach:
        solve using fast and slow pointers
        both slow and fast pointers will initally point to head.
        Whenever slow pointer moves by one step, fast pointer moves by two steps

        when fast has crossed the linked list or last element of the linked list,
            it means, slow will be at the middle of the linked list.
            return slow

        Dry Run:
            For odd number of elements
            Linked list: 1---> 2 ---> 3 ----> 4 ----> 5
                        slow = fast = 1 (initially)

                        slow = move by one step -- 2
                        fast = move by two step -- 3

                        slow = move by one step -- 3
                        fast = move by two step -- 5

                        now fast.next is null, means we are at the last element of the linked list.
                        return slow -- 3 which is the middle element of the linked list.

             For even number of elements
             Linked list: 1---> 2 ---> 3 ----> 4 ----> 5 ---> 6
                        slow = fast = 1 (initially)

                        slow = move by one step -- 2
                        fast = move by two step -- 3

                        slow = move by one step -- 3
                        fast = move by two step -- 5

                        slow = move by one step -- 4
                        fast = move by two step -- null (5.next - 6 - 6.next - null)

                        now fast is null, means we have crossed the linked list.
                        return slow -- 4 which is the middle element of the linked list.

      Time Complexity: O(n)
      Space Complexity: O(1)
     */
    public static Node middleElement(Node head){
        Node slow = head;
        Node fast = head;

        Node node = new Node(5);

        while(fast != null && fast.next != null && slow != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
