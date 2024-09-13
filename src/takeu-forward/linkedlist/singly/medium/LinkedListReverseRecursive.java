package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://takeuforward.org/data-structure/reverse-a-linked-list/
    https://leetcode.com/problems/reverse-linked-list/description/
 */
public class LinkedListReverseRecursive {

    /*
    Approach:

     */
    public static Node reverseRecursive(Node head){

        // Base case: When linked list is empty or has only one node, no need to reverse. Return head.
        if(head == null || head.next == null)
            return head;

        /*
        When linked list has more than one element, we break the problem into sub problem and solve recursively
        we exclude current node and try to reverse remaining nodes in the linked list.
        so the problem keeps becoming smaller and smaller untill it becomes a list with only one node - for which base case executes and control comes back

        Now the actual reversal logic will execute.
        When subproblem is solved for only one node, newHead will be pointing to last Node of the linked list.
        now, remaining below logic will execute.
            it will attach the current node to last node of the reversed list.
            we return newHead of the small reversed linked list.
            recursive call keep reversing one more node and problem will be solved for all nodes
            and the reversed list head will be returned.

        Dry run:
            Say linked List: 1 ---> 2 --> 3 ---> 4 ---> 5
                when head is at 1
                    neither head not head.next is null,
                    it will invoke  reverseRecursive(head.next) - reverseRecursive(1.next) - reverseRecursive(2)
                    and reverseRecursive(1) will be added to recursive stack.
                when head is at 2
                    neither head not head.next is null,
                    it will invoke  reverseRecursive(head.next) - reverseRecursive(2.next) - reverseRecursive(3)
                    and reverseRecursive(2) will be added to recursive stack.
                when head is at 3
                    neither head not head.next is null,
                    it will invoke  reverseRecursive(head.next) - reverseRecursive(3.next) - reverseRecursive(4)
                    and reverseRecursive(3) will be added to recursive stack.
                when head is at 4
                    neither head not head.next is null,
                    it will invoke  reverseRecursive(head.next) - reverseRecursive(4.next) - reverseRecursive(5)
                    and reverseRecursive(4) will be added to recursive stack.
                when head is at 5
                    head.next is null, means it is the last node
                    it is the base case, when there is only one node - we need not do anything just return that node.
                    so we return 5

                Pop reverseRecursive(4) from recursive stack
                    reverseRecursive(4):
                        newHead is 5 (result of reverseRecursive(5).
                        now execute remaining logic,

                        Node after = head.next;
                            --- head will be pointing to 4
                            --- 4.next will be the node next to 4 in the actual list.
                            --- in the original list 4.next - 5
                        after.next = head;
                            --- after is pointing to 5
                            --- 5.next = head means.
                            --- we are attaching the head(current node) after its next node in the original list
                            --- means, 5 is the node after 4 in the original list, add 4 after 5.
                        head.next = null;
                            --- make head.next - null, to avoid cyclic links.
                        return newHead;
                            --- newHead is the head of the so far reversed linked list.

                Pop reverseRecursive(3) from recursive stack
                    reverseRecursive(3):
                        newHead is 5 (result of reverseRecursive(4).
                        now execute remaining logic,

                        Node after = head.next;
                            --- head will be pointing to 3
                            --- 3.next will be the node next to 3 in the actual list.
                            --- in the original list 3.next - 4
                        after.next = head;
                            --- after is pointing to 4
                            --- 4.next = head means.
                            --- we are attaching the head(current node) after its next node in the original list
                            --- means, 4 is the node after 3 in the original list, add 3 after 4.
                        head.next = null;
                            --- make head.next - null, to avoid cyclic links.
                        return newHead;
                            --- newHead is the head of the so far reversed linked list.

                Pop reverseRecursive(2) from recursive stack
                    reverseRecursive(2):
                        newHead is 5 (result of reverseRecursive(3) result.
                        now execute remaining logic,

                        Node after = head.next;
                            --- head will be pointing to 2
                            --- 2.next will be the node next to 3 in the actual list.
                            --- in the original list 2.next - 3
                        after.next = head;
                            --- after is pointing to 3
                            --- 3.next = head means.
                            --- we are attaching the head(current node) after its next node in the original list
                            --- means, 3 is the node after 2 in the original list, add 2 after 3.
                        head.next = null;
                            --- make head.next - null, to avoid cyclic links.
                        return newHead;
                            --- newHead is the head of the so far reversed linked list.

                Pop reverseRecursive(1) from recursive stack
                    reverseRecursive(1):
                        newHead is 5 (result of reverseRecursive(3) result.
                        now execute remaining logic,

                        Node after = head.next;
                            --- head will be pointing to 1
                            --- 1.next will be the node next to 2 in the actual list.
                            --- in the original list 1.next - 2
                        after.next = head;
                            --- after is pointing to 2
                            --- 2.next = head means.
                            --- we are attaching the head(current node) after its next node in the original list
                            --- means, 2 is the node after 1 in the original list, add 1 after 2.
                        head.next = null;
                            --- make head.next - null, to avoid cyclic links.
                        return newHead;
                            --- newHead is the head of the so far reversed linked list.

                Finally all things are popped from recursive stack
                    return newHead which is 5.

         Time Complexity: O(N)
             This is because we traverse the linked list twice:
             once to push the values onto the stack, and once to pop the values and update the linked list. Both traversals take O(N) time.

        Space Complexity : O(1)
            No additional space is used explicitly for data structures or allocations during the linked list reversal process.
            However, it's important to note that there is an implicit use of stack space due to recursion.
            This recursive stack space stores function calls and associated variables during the recursive traversal and reversal of the linked list.
            Despite this, no extra memory beyond the program's existing execution space is allocated, hence maintaining a space complexity of O(1).

            But if you consider recursive stack space then it might become O(n)
         */
        Node newHead = reverseRecursive(head.next);

        Node after = head.next;
        after.next = head;
        head.next = null;
        return newHead;
    }
}
