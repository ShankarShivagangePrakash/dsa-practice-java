package linkedlist.singly.hard;

import java.util.HashMap;
/*
Problem:
    https://takeuforward.org/data-structure/clone-linked-list-with-random-and-next-pointer/
    https://leetcode.com/problems/copy-list-with-random-pointer/description/
 */
public class LinkedListClone {

    /*
    Approach:
        Clearly explained in notes, check

        Time Complexity: O(2n)
            Reason: Two while loops traversing entire linked list
        Space Complexity: O(n) + O(n)
            One O(n) is to create cloned linked list, cannot be counted as space complexity
            since we are using it to return result

            Other part, keys in hash map even there are n keys
            so O(n) for it and we are using it to solve problem
            We should optimize this.
     */
    public static Node linkedListClone(Node head){

        HashMap<Node, Node> map = new HashMap<>();

        Node temp = head;
        // creating copy nodes and adding to hashmap
        // key is original node, value is copy node
        while(temp != null){
            Node newNode = new Node(temp.val);
            map.put(temp, newNode);
            temp = temp.next;
        }

        temp = head;
        // Connect the next and random
        // pointers of the copied nodes using the map
        while(temp != null){
            // get the copy node from map
            Node copyNode = map.get(temp);
            // copy node next will be copy node of original next - read notes
            copyNode.next = map.get(temp.next);
            // copy node random will be copy node of original random - read notes
            copyNode.random = map.get(temp.random);

            temp = temp.next;
        }

        // original head is head, copy head can be found in map
        return map.get(head);
    }

    /*
    Approach:
        It is a three step process
            	- Insert copy nodes in between the original linked list
				- Link random pointers for copy nodes
				- Link next pointers for copy nodes and return head of copied linked list
	    It is explained cleanly in notes, read

	    Time Complexity: O(3n)
	        Reason: Three method each takes O(n)
	    Space Complexity: O(n)
	        Reason: This O(n) space complexity is to return the result, not to solve the problem
     */
    public static Node linkedListOptimal(Node head){
        insertCopyNodesInBetween(head); // O(n)
        connectRandomPointersOfCopyNodes(head); // O(n)
        return connectNextPointerAndReturnCopiedLinkedList(head); // O(n)
    }

    public static void insertCopyNodesInBetween(Node head){
        Node temp = head;
        while(temp != null){
            // create new node/ copy node
            Node copyNode = new Node(temp.val);
            // point copy node to original list next node
            copyNode.next = temp.next;
            // point temp node to copy node we created
            temp.next = copyNode;

            // since you have insert a copyNode inbetween the current node and the actual next node in the original list
            // to move to next node in original list, you have to move two steps
            temp = temp.next.next;
        }
    }

    public static void connectRandomPointersOfCopyNodes(Node head){
        Node temp = head;
        while(temp != null){
            // copy node will be the next node of the original node, just get it
            Node copyNode = temp.next;
            // get to which node random pointer is pointing to
            Node originalRandom = temp.random;
            // if it is not null, then we have to link
            // copy node random pointer to that random nodes copy, that will be available next to random node
            // but random node can also be null, check that
            if(originalRandom != null)
                copyNode.random = temp.random.next;
            else
                copyNode.random = temp.random;

            // similarly, since we have added copy nodes in between we have to take two steps to go to next node of original list
            temp = temp.next.next;
        }
    }

    public static Node connectNextPointerAndReturnCopiedLinkedList(Node head){
        // create dummyNode which acts as tail of the copied linked list
        Node dummyNode = new Node(-1);
        // dummy head points to head of the copied linked list
        Node dummyHead = dummyNode;

        Node temp = head;
        while(temp != null){
            // point dummyNode .next to temp.next - which is the copy node.
            // this will link next nodes to copy nodes as well
            dummyNode.next = temp.next;
            // move the tail of the copied linked list
            dummyNode = dummyNode.next;

            // we have to reset original linked list
            // next original node is available at second step
            // link temp.next to that second node
            temp.next = temp.next.next;
            // move to next node in the original list
            temp = temp.next;
        }
        // return the head of the copied linked list
        return dummyHead.next;
    }

    static class Node{
        int val;
        Node next;
        Node random;

        Node(int val){
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
