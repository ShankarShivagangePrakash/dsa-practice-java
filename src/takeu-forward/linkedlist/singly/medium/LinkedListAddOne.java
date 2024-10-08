package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://www.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=add-1-to-a-number-represented-as-linked-list
 */
public class LinkedListAddOne {

    /*
    Approach:
        Steps are as follows:
            reverse linked list

            declare and initialize variable `carry` as 1

            traverse through the reversed linked list
                for each node add carry value.
                If the sum is less than 10 means,
                    we need not add value to further nodes in the linked list
                    break out of the loop
                else
                    temp.data will be the modulo of sum by 10
                    carry will be 1, because
                    else case means temp.data initally it is 9 and + carry 1
                    sum will be 10 always, so
                    output of modulo operation is 0
                    and carry will be 1 always
                move to next node in the reversed linked list.

            reverse back list to original list

            if the carry value is still 1
                for ex: given input is 999
                    even after completing the loop, linked list currently looks like
                    0 0 0 and carry 1
                    we have to add a new node with value as 1 to begining of the linked list
                    and we have to make this new node as the new head of the linked list.

           If varry value is 0, no action

           return head

         Time Complexity: O(3n)
         Space Complexity: O(1)

     */
    public static Node linkedListAddOneBruteForce(Node head){
        Node reverseHead = reverse(head);

        int carry = 1;
        Node temp = reverseHead; // O(n)
        while(temp != null){ // O(n)
            temp.val = temp.val + carry;

            if(temp.val < 10){
                carry = 0;
                break;
            } else{
                temp.val = temp.val % 10;
                carry = 1;
            }
            temp = temp.next;
        }

        temp = reverse(reverseHead); //O(n)

        if(carry == 1){
            Node newNode = new Node(1);
            newNode.next = head;
            head = newNode;
        }

        return head;
    }

    public static Node reverse(Node head) {
        if (head == null || head.next == null)
            return head;

        Node newHead = reverse(head.next);
        Node after = head.next;
        after.next = head;
        head.next = null;
        return newHead;
    }

    /*
    Approach:
        If we want to add 1 to a number, we know that we have to start from unit place
        add 1 to number at particular unit's ten's current place, if carry available, add that also
        if this sum, generates carry, keep track of it, use it for element at next position.

        finally if carry is still 1 after finishing addition, add 1 to begining of the number
        ex: 99 + 1 = 100

        same instead of number, we have linked list, each node represents the number at that particular place
        and we need to add 1 to it

        with the above background, we get to know that last element of the linked list is the unit place
        so we have to start addition from last node
        keep track of carry generated while addition at each position
        use it in next addition.

        so, we need to travese to end of the linked list
        we have to add 1 to it, keep track of carry
        now, comeback to its previous node, add carry generated by addition in previous step
        generate carry; carry = sum /10;

        when you have completed the addition for entire list,
        if the carry is still 1 means, we have to add 1 to the begining of the linked list
        add new Node with value 1, make it as head
        // we use recursion to implement from line number 106 to 113

        return head
         recursion suits for this problem

        Time Complexity: O(n)
            Reason: We are traversing the entire linked list
        Space Complexity: O(n)
            Reason: When we travese the linked list, we are keep entire method in recursive stack space untill base condition is met
            later we are popping from the stack space and we are solving.
            so method call for all elements in the linked list will be stored in recursive stack space
            so we can say space complexity is O(n)
     */
    public static Node linkedListAddOneOptimal(Node head){
        // final carry is the carry generated after completing sum for entire linked list.
        int finalCarry = addOneRecursively(head); // O(n)

        if(finalCarry == 1){
            Node newNode = new Node(1);
            newNode.next = head;
            head = newNode;
        }
        return head;
    }

    public static int addOneRecursively(Node head){
        if(head == null){
            // when we have traversed the entire linked list,
            // return 1 as the carry, so we can start adding 1 to the linked list
            // this carry will be returned to last node of the linked list
            return 1;
        }

        int carry = addOneRecursively(head.next);
        head.val = head.val + carry;
        if(head.val < 10){
            // if temp.data is less than 10, then there will be no carry
            return 0;
        }
        else{
            head.val = head.val % 10;
            return 1;
        }
    }
}
