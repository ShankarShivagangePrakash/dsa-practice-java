package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://takeuforward.org/data-structure/add-two-numbers-represented-as-linked-lists/
    https://leetcode.com/problems/add-two-numbers/description/
 */
public class LinkedListAddTwoNumbers {

    /*
    Problem Statement:
        l1 and l2 are two numbers in reversed order
        add them and return the head of the linked list

    Approach:
        since numbers are in reverse order, process is simple

        Create a dummyNode with value -1 and create pointer dummyHead pointing to this dummyNode
        initialize carry as 0

        start from first node of both the linked list
        keep a temporary variable sum initalized to 0 inside the while loop

        each time when you are inside the loop perform below steps
            if temp1 != null add temp1.val to sum
            if temp2 != null add temp2.val to sum
            add carry to sum

        Now sum value can be more than 10
        but we have to keep only single digit
        so perform modulo of sum by 10
        create newNode with this modulo operation result
        now, link this new node to dummyNode.next
        and move dummyNode to dummyNode.next - so that it is pointing to tail of the addition linked list
        reset carry to sum/10

        When you complete the loop
        if carray is grater than 0
        you need to create a new node with value carry and link that to dummyNode.next

        return dummyHead.next - which represents the head of the addition linked list

        Time Complexity: O( max(n1, n2))
        Space Complexity: O( max(n1, n2)
            but space is used to return result, not to solve problem
     */
    public static Node linkedListAddTwoNumbersOptimal(Node l1, Node l2){
        Node temp1 = l1, temp2 = l2;

        Node dummyNode = new Node(-1);
        Node dummyHead = dummyNode;
        int carry = 0;

        while(temp1 != null || temp2 != null){
            int sum = 0;

            //  if linked list 1 node is not null(number 1 not yet finished) add its value to sum
            if(temp1 != null){
                sum += temp1.val;
                temp1 = temp1.next;
            }
            //  if linked list 2 node is not null(number 2 not yet finished) add its value to sum
            if(temp2 != null){
                sum += temp2.val;
                temp2 = temp2.next;
            }
            // add carry to sum
            sum += carry;

            carry = sum/10;
            // we have to store ony one digit in the one node of linked list
            // so perform modulous operation to get the value at unit's place of the `sum`
            sum = sum % 10;
            Node newNode = new Node(sum);

            // link new node to dummyNode.next - which stores two numbers addition
            dummyNode.next = newNode;
            // since dummyNode points to tail of the result list, point it to last node of the result
            // so next number addition can be linked to this tail
            dummyNode = dummyNode.next;
        }

        // if final carry is greater than 0, means we have to add new node  eg: 99 + 1 = 100
        if(carry > 0){
            Node newNode = new Node(carry);
            dummyNode.next = newNode;
        }

        // dummyHead.next points to head of the result list
        return dummyHead.next;
    }
}
