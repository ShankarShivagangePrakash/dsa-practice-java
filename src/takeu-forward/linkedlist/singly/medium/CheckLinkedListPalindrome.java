package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/check-if-given-linked-list-is-plaindrome/
    https://leetcode.com/problems/palindrome-linked-list/description/
 */
public class CheckLinkedListPalindrome {

    /*
    Approach:
        Brute force approach is to use an aditional data structure.
        since we need to compare the firste element of the linked list with the last element
        and second with last but one
        Stack the suitable data structure.

        First we travese the entrie linked list and store the elements in the stack

        Then we again traverse the linked list and simultaneously we pop element from the stack.
            If they both are same, move to next element in the linked list
            else, it it is not palindrome, return false
        If you have traversed the linked list, means it is a palindrome return true

        Time Complexity: O(2n)
        Space Complexity: O(n)
     */
    public static boolean checkLinkedListPalindromeBruteForce(Node head){

        Stack<Integer> stack = new Stack<>();

        Node temp = head;
        while(temp != null){
            stack.push(temp.val);
            temp = temp.next;
        }

        temp = head;
        while(temp != null){
            if(temp.val == stack.pop())
                temp = temp.next;
            else
                return false;
        }
        return true;
    }

    /*
    Approach:
        Idea behind this approach is we find the second half of the linked list
        we reverse second half
        we iterate first and second half and we compare corresponding elements
        if they doesn't match return false
        if we have traversed the second half completely meanms it is palindrome, return true
        before returning result, since you have modifified the original list, roll back to reverse it to original state and then return result.

        Steps:
            Find middle element
            reverse the linked list starting from middleNode to last node
            first half will start from head
            secondHalf will start from the middleNode (but since this half is reversed it's head will be `secondHalfHead`
            assign `secondHalfHead` to `second`

            keep a boolean `isPalindrome` = true
            keep iterating till `second` is not null
                keep comparing first.val and second.val
                    if they are not equal then make `isPalindrome` - false
                    break from loop
                keep moving first and second.

           when you come out of while loop reverse the second half.
           return `isPalindrome`, which will be false if it is not palindrome, else it will be true.

       Time Complexity: O (2* N)
           The algorithm traverses the linked list twice.
           n/2 for finding middle element.
           n/2 for reversing second half
           n/2 to check is it palindrome or not.

      Space Complexity: O(1)
     */
    public static boolean checkLinkedListPalindromeOptimal(Node head){

        if(head == null || head.next == null)
            return true;

        /*
        In case, if the linked list has even number of elements,
            then middleNode will point to n/2 + 1 the position.
            so you have to reverse starting from that point itself
            Example: 1 --> 2 ---> 2 ---> 1
                middleNode will point to position 3- array index[2]
                you start reversing from this position itself.
        whereas
            in case of odd number of elements
                1 ---> 2 ---> 3 ---> 2 ---> 1
                    middle node will point to element 3, which is exact mid point.
                    you can start reversing from this point.
                    If test cases break, them modify `reverseLinkedList(middleNode)` to `reverseLinkedList(middleNode.next)`
         */
        Node middleNode = findMiddleNodeLinkedList(head);
        Node secondHalfHead = reverseLinkedList(middleNode);

        Node first = head;
        Node second = secondHalfHead;
        boolean isPalindrome = true;

        while(second != null){
            if(first.val != second.val){
                isPalindrome = false;
                break;
            }
            first = first.next;
            second = second.next;
        }
        reverseLinkedList(secondHalfHead);
        return isPalindrome;
    }

    public static Node findMiddleNodeLinkedList(Node head){
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node reverseLinkedList(Node head){
        if(head == null || head.next == null)
            return head;

        Node newHead = reverseLinkedList(head.next);
        Node after = head.next;
        after.next = head;
        head.next = null;
        return newHead;
    }

}
