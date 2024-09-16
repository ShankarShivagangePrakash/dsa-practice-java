package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://takeuforward.org/data-structure/segregate-even-and-odd-nodes-in-linkedlist
        In this variant, we have to Segregate based on values in the node
    https://leetcode.com/problems/odd-even-linked-list/
        In this variant, we have to segregate based on the index
 */
public class LinkedListSegregateOddEven {

    /*
    Problem:
        You will be given a linked list
        you have to put nodes at odd index together, then all nodes at even index.
        Order should be maintained.

    Approach:
        Have pointers for even and odd.
            odd will point to head - first node (odd)
            even will point to head.next - second node (even)
            evenHead - head pointer for even linked list

        for both even and add
            we link next to next node with them, because that will be either next even or odd nodes
            once you link that next even or odd nodes to odd or even
            move odd or even that node you linked
            perform the same operation, till you reach the end of the list

        since even pointer will start from second node, it will reach the end of the linked list early that odd pointer.
        so we check has even pointer reached end of the list.

        link last odd node with even list head

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static Node segregateBasedOnIndex(Node head){

        Node odd = head;
        Node even = head.next;
        Node evenHead = even;

        while(even != null && even.next != null){

            // for odd node, next odd node index will be found at odd.next.next
            // link that next odd index node with current odd node
            odd.next = odd.next.next;
            // since you have linked a new odd node to current odd node
            // move to that new odd node, from that position you have to find next odd node
            odd = odd.next;

            // same for even nodes
            even.next = even.next.next;
            even = even.next;
        }
        // link last odd node with even linked list head
        odd.next = evenHead;

        // since head is at odd index(first index), it will be the first node of odd list,
        // we can return head as the result.
        return head;
    }

    /*
    Approach:
        1. Traverse the linked list once, segregating nodes into two separate lists:
           - One list for nodes with odd values.
           - One list for nodes with even values.

        2. Maintain `oddHead` and `oddTail` pointers for the odd list, and `evenHead` and `evenTail` pointers for the even list.
           - If it's the first odd or even node encountered, set both the head and tail.
           - For subsequent nodes, append them to the respective list by updating the tail's `next` pointer.

        3. After traversing the list:
           - Link the odd list to the even list by connecting `oddTail.next` to `evenHead`.
           - Ensure the last node in the even list (`evenTail`) points to `null` to terminate the list properly.

        4. Return the head of the combined list:
           - If there are odd nodes, return `oddHead`.
           - If there are no odd nodes, return `evenHead` (if any even nodes exist).

    Time Complexity: O(n) - where n is the number of nodes in the linked list (single traversal).
    Space Complexity: O(1) - constant extra space used for the pointers.
    */
    public static Node segregateBasedOnValue(Node head){

        if (head == null) {
            return null;
        }

        Node oddHead = null, oddTail = null;
        Node evenHead = null, evenTail = null;
        Node current = head;

        while (current != null) {
            if (current.val % 2 != 0) { // Odd value
                if (oddHead == null) {
                    oddHead = current; // First odd node
                    oddTail = oddHead;
                } else {
                    oddTail.next = current;
                    oddTail = oddTail.next;
                }
            } else { // Even value
                if (evenHead == null) {
                    evenHead = current; // First even node
                    evenTail = evenHead;
                } else {
                    evenTail.next = current;
                    evenTail = evenTail.next;
                }
            }
            current = current.next;
        }

        // If there are no odd or no even values, return the appropriate list
        if (oddTail != null) {
            oddTail.next = evenHead; // Append even list to odd list
        }

        if (evenTail != null) {
            evenTail.next = null; // End of even list
        }

        // Return oddHead if there are odd values, else evenHead
        return oddHead != null ? oddHead : evenHead;
    }
}
